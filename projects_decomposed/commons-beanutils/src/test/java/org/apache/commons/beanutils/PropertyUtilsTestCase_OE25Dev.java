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


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.priv.PrivateBeanFactory;
import org.apache.commons.beanutils.priv.PrivateDirect;
import org.apache.commons.beanutils.priv.PublicSubBean;


/**
 * <p>Test Case for the PropertyUtils class.  The majority of these tests use
 * instances of the TestBean class, so be sure to update the tests if you
 * change the characteristics of that class.</p>
 *
 * <p>So far, this test case has tests for the following methods of the
 * <code>PropertyUtils</code> class:</p>
 * <ul>
 * <li>getIndexedProperty(Object,String)</li>
 * <li>getIndexedProperty(Object,String,int)</li>
 * <li>getMappedProperty(Object,String)</li>
 * <li>getMappedProperty(Object,String,String</li>
 * <li>getNestedProperty(Object,String)</li>
 * <li>getPropertyDescriptor(Object,String)</li>
 * <li>getPropertyDescriptors(Object)</li>
 * <li>getPropertyType(Object,String)</li>
 * <li>getSimpleProperty(Object,String)</li>
 * <li>setIndexedProperty(Object,String,Object)</li>
 * <li>setIndexedProperty(Object,String,String,Object)</li>
 * <li>setMappedProperty(Object,String,Object)</li>
 * <li>setMappedProperty(Object,String,String,Object)</li>
 * <li>setNestedProperty(Object,String,Object)</li>
 * <li>setSimpleProperty(Object,String,Object)</li>
 * </ul>
 *
 * @version $Id$
 */

public class PropertyUtilsTestCase_OE25Dev extends TestCase {


    // ---------------------------------------------------- Instance Variables


    /**
     * The fully qualified class name of our private directly
     * implemented interface.
     */
    private static final String PRIVATE_DIRECT_CLASS =
            "org.apache.commons.beanutils.priv.PrivateDirect";


    /**
     * The fully qualified class name of our private indirectly
     * implemented interface.
     */
    private static final String PRIVATE_INDIRECT_CLASS =
            "org.apache.commons.beanutils.priv.PrivateIndirect";


    /**
     * The fully qualified class name of our test bean class.
     */
    private static final String TEST_BEAN_CLASS =
            "org.apache.commons.beanutils.TestBean";


    /**
     * The basic test bean for each test.
     */
    protected TestBean bean = null;


    /**
     * The "package private subclass" test bean for each test.
     */
    protected TestBeanPackageSubclass beanPackageSubclass = null;


    /**
     * The test bean for private access tests.
     */
    protected PrivateDirect beanPrivate = null;


    /**
     * The test bean for private access tests of subclasses.
     */
    protected PrivateDirect beanPrivateSubclass = null;


    /**
     * The "public subclass" test bean for each test.
     */
    protected TestBeanPublicSubclass beanPublicSubclass = null;


    /**
     * The set of properties that should be described.
     */
    protected String describes[] =
    { "booleanProperty",
      "booleanSecond",
      "doubleProperty",
      "floatProperty",
      "intArray",
      //      "intIndexed",
      "intProperty",
      "listIndexed",
      "longProperty",
      //      "mappedObjects",
      //      "mappedProperty",
      //      "mappedIntProperty",
      "nested",
      "nullProperty",
      //      "readOnlyProperty",
      "shortProperty",
      "stringArray",
      //      "stringIndexed",
      "stringProperty"
    };


    /**
     * The set of property names we expect to have returned when calling
     * <code>getPropertyDescriptors()</code>.  You should update this list
     * when new properties are added to TestBean.
     */
    protected final static String[] properties = {
        "booleanProperty",
        "booleanSecond",
        "doubleProperty",
        "dupProperty",
        "floatProperty",
        "intArray",
        "intIndexed",
        "intProperty",
        "listIndexed",
        "longProperty",
        "nested",
        "nullProperty",
        "readOnlyProperty",
        "shortProperty",
        "stringArray",
        "stringIndexed",
        "stringProperty",
        "writeOnlyProperty",
    };


    // ---------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public PropertyUtilsTestCase_OE25Dev(final String name) {

        super(name);

    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() {

        bean = new TestBean();
        beanPackageSubclass = new TestBeanPackageSubclass();
        beanPrivate = PrivateBeanFactory.create();
        beanPrivateSubclass = PrivateBeanFactory.createSubclass();
        beanPublicSubclass = new TestBeanPublicSubclass();

        final DynaProperty[] properties = new DynaProperty[] {
                new DynaProperty("stringProperty", String.class),
                new DynaProperty("nestedBean", TestBean.class),
                new DynaProperty("nullDynaBean", DynaBean.class)
                };
        final BasicDynaClass dynaClass = new BasicDynaClass("nestedDynaBean", BasicDynaBean.class, properties);
        final BasicDynaBean nestedDynaBean = new BasicDynaBean(dynaClass);
        nestedDynaBean.set("nestedBean", bean);
        bean.setNestedDynaBean(nestedDynaBean);
        PropertyUtils.clearDescriptors();
    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {

        return (new TestSuite(PropertyUtilsTestCase_OE25Dev.class));

    }


    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {

        bean = null;
        beanPackageSubclass = null;
        beanPrivate = null;
        beanPrivateSubclass = null;
        beanPublicSubclass = null;

        PropertyUtils.resetBeanIntrospectors();
    }



    // ------------------------------------------------ Individual Test Methods


    /**
     * Test copyProperties() when the origin is a a <code>Map</code>.
     */


    /**
     * Test the describe() method.
     */


    /**
     * Corner cases on getPropertyDescriptor invalid arguments.
     */


    /**
     * Positive getPropertyDescriptor on property <code>booleanProperty</code>.
     */
    public void testGetDescriptorBoolean() {

        testGetDescriptorBase("booleanProperty", "getBooleanProperty",
                "setBooleanProperty");

    }


    /**
     * Positive getPropertyDescriptor on property <code>doubleProperty</code>.
     */
    public void testGetDescriptorDouble() {

        testGetDescriptorBase("doubleProperty", "getDoubleProperty",
                "setDoubleProperty");

    }


    /**
     * Positive getPropertyDescriptor on property <code>floatProperty</code>.
     */
    public void testGetDescriptorFloat() {

        testGetDescriptorBase("floatProperty", "getFloatProperty",
                "setFloatProperty");

    }


    /**
     * Positive getPropertyDescriptor on property <code>intProperty</code>.
     */
    public void testGetDescriptorInt() {

        testGetDescriptorBase("intProperty", "getIntProperty",
                "setIntProperty");

    }


    /**
     * <p>Negative tests on an invalid property with two different boolean
     * getters (which is fine, according to the JavaBeans spec) but a
     * String setter instead of a boolean setter.</p>
     *
     * <p>Although one could logically argue that this combination of method
     * signatures should not identify a property at all, there is a sentence
     * in Section 8.3.1 making it clear that the behavior tested for here
     * is correct:  "If we find only one of these methods, then we regard
     * it as defining either a read-only or write-only property called
     * <em>&lt;property-name&gt;</em>.</p>
     */


    /**
     * Positive getPropertyDescriptor on property <code>longProperty</code>.
     */
    public void testGetDescriptorLong() {

        testGetDescriptorBase("longProperty", "getLongProperty",
                "setLongProperty");

    }

    /**
     * Test getting mapped descriptor with periods in the key.
     */


    /**
     * Positive getPropertyDescriptor on property
     * <code>readOnlyProperty</code>.
     */
    public void testGetDescriptorReadOnly() {

        testGetDescriptorBase("readOnlyProperty", "getReadOnlyProperty",
                null);

    }


    /**
     * Positive getPropertyDescriptor on property <code>booleanSecond</code>
     * that uses an "is" method as the getter.
     */
    public void testGetDescriptorSecond() {

        testGetDescriptorBase("booleanSecond", "isBooleanSecond",
                "setBooleanSecond");

    }


    /**
     * Positive getPropertyDescriptor on property <code>shortProperty</code>.
     */
    public void testGetDescriptorShort() {

        testGetDescriptorBase("shortProperty", "getShortProperty",
                "setShortProperty");

    }


    /**
     * Positive getPropertyDescriptor on property <code>stringProperty</code>.
     */
    public void testGetDescriptorString() {

        testGetDescriptorBase("stringProperty", "getStringProperty",
                "setStringProperty");

    }


    /**
     * Negative getPropertyDescriptor on property <code>unknown</code>.
     */
    public void testGetDescriptorUnknown() {

        testGetDescriptorBase("unknown", null, null);

    }


    /**
     * Positive getPropertyDescriptor on property
     * <code>writeOnlyProperty</code>.
     */
    public void testGetDescriptorWriteOnly() {

        testGetDescriptorBase("writeOnlyProperty", null,
                "setWriteOnlyProperty");

    }


    /**
     * Positive test for getPropertyDescriptors().  Each property name
     * listed in <code>properties</code> should be returned exactly once.
     */


    /**
     * Corner cases on getPropertyDescriptors invalid arguments.
     */


    /**
     * Corner cases on getIndexedProperty invalid arguments.
     */


    /**
     * Positive and negative tests on getIndexedProperty valid arguments.
     */


    /**
     * Test getting an indexed value out of a multi-dimensional array
     */

    /**
     * Test getting an indexed value out of List of Lists
     */

    /**
     * Test getting a value out of a mapped Map
     */

    /**
     * Corner cases on getMappedProperty invalid arguments.
     */

    /**
     * Test getting an indexed value out of a mapped array
     */

    /**
     * Test getting an indexed value out of a mapped List
     */

    /**
     * Test getting a value out of a mapped Map
     */

    /**
     * Test getting mapped values with periods in the key.
     */


    /**
     * Test getting mapped values with slashes in the key.  This is different
     * from periods because slashes are not syntactically significant.
     */


    /**
     * Positive and negative tests on getMappedProperty valid arguments.
     */


    /**
     * Corner cases on getNestedProperty invalid arguments.
     */


    /**
     * Test getNestedProperty on a boolean property.
     */


    /**
     * Test getNestedProperty on a double property.
     */


    /**
     * Test getNestedProperty on a float property.
     */


    /**
     * Test getNestedProperty on an int property.
     */


    /**
     * Test getNestedProperty on a long property.
     */


    /**
     * Test getNestedProperty on a read-only String property.
     */


    /**
     * Test getNestedProperty on a short property.
     */


    /**
     * Test getNestedProperty on a String property.
     */


    /**
     * Negative test getNestedProperty on an unknown property.
     */
    public void testGetNestedUnknown() {

        try {
            PropertyUtils.getNestedProperty(bean, "nested.unknown");
            fail("Should have thrown NoSuchMethodException");
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
        } catch (final NoSuchMethodException e) {
            // Correct result for this test
        }

    }

    /**
     * When a bean has a null property which is reference by the standard access language,
     * this should throw a NestedNullException.
     */
    public void testThrowNestedNull() throws Exception {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        // don't init!

        try {
            PropertyUtils.getProperty(
                                nestedBean,
                                "simpleBeanProperty.indexedProperty[0]");
            fail("NestedNullException not thrown");
        } catch (final NestedNullException e) {
            // that's what we wanted!
        }
    }

    /**
     * Test getNestedProperty on a write-only String property.
     */
    public void testGetNestedWriteOnly() {

        try {
            PropertyUtils.getNestedProperty(bean, "writeOnlyProperty");
            fail("Should have thrown NoSuchMethodException");
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
        } catch (final NoSuchMethodException e) {
            // Correct result for this test
        }

    }


    /**
     * Test getPropertyType() on all kinds of properties.
     */


    /**
     * Test accessing a public sub-bean of a package scope bean
     */


    /**
     * Test getting accessible property reader methods for a specified
     * list of properties of our standard test bean.
     */
    public void testGetReadMethodBasic() {

        testGetReadMethod(bean, properties, TEST_BEAN_CLASS);

    }


    /**
     * Test getting accessible property reader methods for a specified
     * list of properties of a package private subclass of our standard
     * test bean.
     */
    public void testGetReadMethodPackageSubclass() {

        testGetReadMethod(beanPackageSubclass, properties, TEST_BEAN_CLASS);

    }


    /**
     * Test getting accessible property reader methods for a specified
     * list of properties that are declared either directly or via
     * implemented interfaces.
     */


    /**
     * Test getting accessible property reader methods for a specified
     * list of properties of a public subclass of our standard test bean.
     */
    public void testGetReadMethodPublicSubclass() {

        testGetReadMethod(beanPublicSubclass, properties, TEST_BEAN_CLASS);

    }


    /**
     * Corner cases on getSimpleProperty invalid arguments.
     */


    /**
     * Test getSimpleProperty on a boolean property.
     */


    /**
     * Test getSimpleProperty on a double property.
     */


    /**
     * Test getSimpleProperty on a float property.
     */


    /**
     * Negative test getSimpleProperty on an indexed property.
     */


    /**
     * Test getSimpleProperty on an int property.
     */


    /**
     * Test getSimpleProperty on a long property.
     */


    /**
     * Negative test getSimpleProperty on a nested property.
     */


    /**
     * Test getSimpleProperty on a read-only String property.
     */


    /**
     * Test getSimpleProperty on a short property.
     */


    /**
     * Test getSimpleProperty on a String property.
     */


    /**
     * Negative test getSimpleProperty on an unknown property.
     */


    /**
     * Test getSimpleProperty on a write-only String property.
     */


    /**
     * Test getting accessible property writer methods for a specified
     * list of properties of our standard test bean.
     */
    public void testGetWriteMethodBasic() {

        testGetWriteMethod(bean, properties, TEST_BEAN_CLASS);

    }


    /**
     * Test getting accessible property writer methods for a specified
     * list of properties of a package private subclass of our standard
     * test bean.
     */
    public void testGetWriteMethodPackageSubclass() {

        testGetWriteMethod(beanPackageSubclass, properties, TEST_BEAN_CLASS);

    }


    /**
     * Test getting accessible property writer methods for a specified
     * list of properties of a public subclass of our standard test bean.
     */
    public void testGetWriteMethodPublicSubclass() {

        testGetWriteMethod(beanPublicSubclass, properties, TEST_BEAN_CLASS);

    }

    /**
     * Test isReadable() method.
     */

    /**
     * Test isWriteable() method.
     */


    /**
     * Test the mappedPropertyType of MappedPropertyDescriptor.
     */


    /**
     * Corner cases on setIndexedProperty invalid arguments.
     */

    /**
     * Test setting an indexed value out of a multi-dimensional array
     */

    /**
     * Test setting an indexed value out of List of Lists
     */

    /**
     * Test setting a value out of a mapped Map
     */


    /**
     * Positive and negative tests on setIndexedProperty valid arguments.
     */


    /**
     * Corner cases on getMappedProperty invalid arguments.
     */


    /**
     * Test setting an indexed value out of a mapped array
     */

    /**
     * Test setting an indexed value out of a mapped List
     */

    /**
     * Test setting a value out of a mapped Map
     */

    /**
     * Positive and negative tests on setMappedProperty valid arguments.
     */

    /**
     * Test setting mapped values with periods in the key.
     */


    /**
     * Corner cases on setNestedProperty invalid arguments.
     */


    /**
     * Test setNextedProperty on a boolean property.
     */


    /**
     * Test setNestedProperty on a double property.
     */


    /**
     * Test setNestedProperty on a float property.
     */


    /**
     * Test setNestedProperty on a int property.
     */


    /**
     * Test setNestedProperty on a long property.
     */


    /**
     * Test setNestedProperty on a read-only String property.
     */
    public void testSetNestedReadOnly() {

        try {
            final String oldValue = bean.getNested().getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.readOnlyProperty",
                    newValue);
            fail("Should have thrown NoSuchMethodException");
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
        } catch (final NoSuchMethodException e) {
            // Correct result for this test
        }

    }


    /**
     * Test setNestedProperty on a short property.
     */


    /**
     * Test setNestedProperty on a String property.
     */


    /**
     * Test setNestedProperty on an unknown property name.
     */
    public void testSetNestedUnknown() {

        try {
            final String newValue = "New String Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.unknown",
                    newValue);
            fail("Should have thrown NoSuchMethodException");
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
        } catch (final NoSuchMethodException e) {
            // Correct result for this test
        }

    }


    /**
     * Test setNestedProperty on a write-only String property.
     */


    /**
     * Corner cases on setSimpleProperty invalid arguments.
     */


    /**
     * Test setSimpleProperty on a boolean property.
     */


    /**
     * Test setSimpleProperty on a double property.
     */


    /**
     * Test setSimpleProperty on a float property.
     */


    /**
     * Negative test setSimpleProperty on an indexed property.
     */


    /**
     * Test setSimpleProperty on a int property.
     */


    /**
     * Test setSimpleProperty on a long property.
     */


    /**
     * Negative test setSimpleProperty on a nested property.
     */


    /**
     * Test setSimpleProperty on a read-only String property.
     */


    /**
     * Test setSimpleProperty on a short property.
     */


    /**
     * Test setSimpleProperty on a String property.
     */


    /**
     * Test setSimpleProperty on an unknown property name.
     */


    /**
     * Test setSimpleProperty on a write-only String property.
     */


    // ------------------------------------------------------ Protected Methods


    /**
     * Base for testGetDescriptorXxxxx() series of tests.
     *
     * @param name Name of the property to be retrieved
     * @param read Expected name of the read method (or null)
     * @param write Expected name of the write method (or null)
     */
    protected void testGetDescriptorBase(final String name, final String read,
                                         final String write) {

        try {
            final PropertyDescriptor pd =
                    PropertyUtils.getPropertyDescriptor(bean, name);
            if ((read != null) || (write != null)) {
                assertNotNull("Got descriptor", pd);
            } else {
                assertNull("Got descriptor", pd);
                return;
            }
            final Method rm = pd.getReadMethod();
            if (read != null) {
                assertNotNull("Got read method", rm);
                assertEquals("Got correct read method",rm.getName(),read);
            } else {
                assertNull("Got read method", rm);
            }
            final Method wm = pd.getWriteMethod();
            if (write != null) {
                assertNotNull("Got write method", wm);
                assertEquals("Got correct write method",wm.getName(),write);
            } else {
                assertNull("Got write method", wm);
            }
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
        }

    }


    /**
     * Base for testGetReadMethod() series of tests.
     *
     * @param bean Bean for which to retrieve read methods.
     * @param properties Property names to search for
     * @param className Class name where this method should be defined
     */
    protected void testGetReadMethod(final Object bean, final String properties[],
                                     final String className) {

        final PropertyDescriptor pd[] =
                PropertyUtils.getPropertyDescriptors(bean);
        for (String propertie : properties) {

            // Identify the property descriptor for this property
            if (propertie.equals("intIndexed")) {
                continue;
            }
            if (propertie.equals("stringIndexed")) {
                continue;
            }
            if (propertie.equals("writeOnlyProperty")) {
                continue;
            }
            int n = -1;
            for (int j = 0; j < pd.length; j++) {
                if (propertie.equals(pd[j].getName())) {
                    n = j;
                    break;
                }
            }
            assertTrue("PropertyDescriptor for " + propertie,n >= 0);

            // Locate an accessible property reader method for it
            final Method reader = PropertyUtils.getReadMethod(pd[n]);
            assertNotNull("Reader for " + propertie,reader);
            final Class<?> clazz = reader.getDeclaringClass();
            assertNotNull("Declaring class for " + propertie,clazz);
            assertEquals("Correct declaring class for " + propertie,clazz.getName(),className);

            // Actually call the reader method we received
            try {
                reader.invoke(bean, (Object[]) new Class<?>[0]);
            } catch (final Throwable t) {
                fail("Call for " + propertie + ": " + t);
            }

        }

    }


    /**
     * Base for testGetWriteMethod() series of tests.
     *
     * @param bean Bean for which to retrieve write methods.
     * @param properties Property names to search for
     * @param className Class name where this method should be defined
     */
    protected void testGetWriteMethod(final Object bean, final String properties[],
                                      final String className) {


        final PropertyDescriptor pd[] =
                PropertyUtils.getPropertyDescriptors(bean);
        for (String propertie : properties) {

            // Identify the property descriptor for this property
            if (propertie.equals("intIndexed")) {
                continue;
            }
            if (propertie.equals("listIndexed")) {
                continue;
            }
            if (propertie.equals("nested"))
             {
                continue; // This property is read only
            }
            if (propertie.equals("readOnlyProperty")) {
                continue;
            }
            if (propertie.equals("stringIndexed")) {
                continue;
            }
            int n = -1;
            for (int j = 0; j < pd.length; j++) {
                if (propertie.equals(pd[j].getName())) {
                    n = j;
                    break;
                }
            }
            assertTrue("PropertyDescriptor for " + propertie,n >= 0);

            // Locate an accessible property reader method for it
            final Method writer = PropertyUtils.getWriteMethod(pd[n]);
            assertNotNull("Writer for " + propertie,writer);
            final Class<?> clazz = writer.getDeclaringClass();
            assertNotNull("Declaring class for " + propertie,clazz);
            assertEquals("Correct declaring class for " + propertie,clazz.getName(),className);

        }

    }

    /** Text case for setting properties on inner classes */

    /** Text case for setting properties on parent */

    /**
     * Test accessing a public sub-bean of a package scope bean
     */

    /**
     * There is an issue in setNestedProperty/getNestedProperty when the
     * target bean is a map and the name string requests mapped or indexed
     * operations on a field. These are not supported for fields of a Map,
     * but it's an easy mistake to make and this test case ensures that an
     * appropriate exception is thrown when a user does this.
     * <p>
     * The problem is with passing strings of form "a(b)" or "a[3]" to
     * setNestedProperty or getNestedProperty when the target bean they
     * are applied to implements Map. These strings are actually requesting
     * "the result of calling mapped method a on the target object with
     * a parameter of b" or "the result of calling indexed method a on the
     * target object with a parameter of 3". And these requests are not valid
     * when the target is a Map as a Map only supports calling get(fieldName)
     * or put(fieldName), neither of which can be further indexed with a
     * string or an integer.
     * <p>
     * However it is likely that some users will assume that "a[3]" when applied
     * to a map will be equivalent to (map.get("a"))[3] with the appropriate
     * typecasting, or for "a(b)" to be equivalent to map.get("a").get("b").
     * <p>
     * Here we verify that an exception is thrown if the user makes this
     * mistake.
     */

    /**
     * Returns a single string containing all the keys in the map,
     * sorted in alphabetical order and separated by ", ".
     * <p>
     * If there are no keys, an empty string is returned.
     */
    private String keysToString(final Map<?, ?> map) {
        final Object[] mapKeys = map.keySet().toArray();
        java.util.Arrays.sort(mapKeys);
        final StringBuilder buf = new StringBuilder();
        for(int i=0; i<mapKeys.length; ++i) {
            if (i != 0) {
                buf.append(", ");
            }
            buf.append(mapKeys[i]);
        }
        return buf.toString();
    }

    /**
     * This tests to see that classes that implement Map always have their
     * custom properties ignored.
     * <p>
     * Note that this behaviour has changed several times over past releases
     * of beanutils, breaking backwards compatibility each time. Here's hoping
     * that the current 1.7.1 release is the last time this behaviour changes!
     */

    /**
     * This tests to see that it is possible to subclass PropertyUtilsBean
     * and change the behaviour of setNestedProperty/getNestedProperty when
     * dealing with objects that implement Map.
     */

    /**
     * Test {@link PropertyUtilsBean}'s invoke method throwing an IllegalArgumentException
     * and check that the "cause" has been properly initialized for JDK 1.4+
     * See BEANUTILS-266 for changes and reason for test
     */

    /**
     * Tests whether the default introspection mechanism can be replaced by a
     * custom BeanIntrospector.
     */

    /**
     * Finds the descriptor of the name property.
     *
     * @param desc the array with descriptors
     * @return the found descriptor or null
     */
    private static PropertyDescriptor findNameDescriptor(
            final PropertyDescriptor[] desc) {
        for (PropertyDescriptor element : desc) {
            if (element.getName().equals("name")) {
                return element;
            }
        }
        return null;
    }

    /**
     * Tests whether exceptions during custom introspection are handled.
     */

    /**
     * Tests whether a BeanIntrospector can be removed.
     */

    /**
     * Tries to add a null BeanIntrospector.
     */
    public void testAddBeanIntrospectorNull() {
        try {
            PropertyUtils.addBeanIntrospector(null);
            fail("Could add null BeanIntrospector!");
        } catch (final IllegalArgumentException iex) {
            // ok
        }
    }

    /**
     * Tests whether a reset of the registered BeanIntrospectors can be performed.
     */

    public void testCopyPropertiesMap_1_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
            fail("Threw " + t.toString());
    }
    }

    public void testCopyPropertiesMap_2_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("booleanProperty",false,bean.getBooleanProperty());
    }

    public void testCopyPropertiesMap_3_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("doubleProperty",333.0,bean.getDoubleProperty(),0.005);
    }

    public void testCopyPropertiesMap_4_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("floatProperty",(float)222.0,bean.getFloatProperty(),(float)0.005);
    }

    public void testCopyPropertiesMap_5_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("intProperty",111,bean.getIntProperty());
    }

    public void testCopyPropertiesMap_6_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("longProperty",444,bean.getLongProperty());
    }

    public void testCopyPropertiesMap_7_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("shortProperty",(short)555,bean.getShortProperty());
    }

    public void testCopyPropertiesMap_8_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("stringProperty","New String Property",bean.getStringProperty());
    }

    public void testCopyPropertiesMap_9_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        assertNotNull("dupProperty present", dupProperty);
    }

    public void testCopyPropertiesMap_10_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        assertEquals("dupProperty length", 3, dupProperty.length);
    }

    public void testCopyPropertiesMap_11_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        assertEquals("dupProperty[0]", "New 0", dupProperty[0]);
    }

    public void testCopyPropertiesMap_12_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        assertEquals("dupProperty[1]", "New 1", dupProperty[1]);
    }

    public void testCopyPropertiesMap_13_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        assertEquals("dupProperty[2]", "New 2", dupProperty[2]);
    }

    public void testCopyPropertiesMap_14_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        final int intArray[] = bean.getIntArray();
        assertNotNull("intArray present", intArray);
    }

    public void testCopyPropertiesMap_15_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        final int intArray[] = bean.getIntArray();
        assertEquals("intArray length", 3, intArray.length);
    }

    public void testCopyPropertiesMap_16_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        final int intArray[] = bean.getIntArray();
        assertEquals("intArray[0]", 0, intArray[0]);
    }

    public void testCopyPropertiesMap_17_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        final int intArray[] = bean.getIntArray();
        assertEquals("intArray[1]", 100, intArray[1]);
    }

    public void testCopyPropertiesMap_18_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", Boolean.FALSE);
        map.put("doubleProperty", new Double(333.0));
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", new Float((float) 222.0));
        map.put("intArray", new int[] { 0, 100, 200 });
        map.put("intProperty", new Integer(111));
        map.put("longProperty", new Long(444));
        map.put("shortProperty", new Short((short) 555));
        map.put("stringProperty", "New String Property");

        try {
            PropertyUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = bean.getDupProperty();
        final int intArray[] = bean.getIntArray();
        assertEquals("intArray[2]", 200, intArray[2]);
    }

    public void testDescribe_1_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
            fail("Threw exception " + e);
    }
    }

    public void testDescribe_2_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
            assertTrue("Property '" + describe + "' is present",map.containsKey(describe));
    }
    }

    public void testDescribe_3_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }
        assertTrue("Property 'writeOnlyProperty' is not present",!map.containsKey("writeOnlyProperty"));
    }

    public void testDescribe_4_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'booleanProperty'",Boolean.TRUE,map.get("booleanProperty"));
    }

    public void testDescribe_5_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'doubleProperty'",new Double(321.0),map.get("doubleProperty"));
    }

    public void testDescribe_6_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'floatProperty'",new Float((float)123.0),map.get("floatProperty"));
    }

    public void testDescribe_7_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'intProperty'",new Integer(123),map.get("intProperty"));
    }

    public void testDescribe_8_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'longProperty'",new Long(321),map.get("longProperty"));
    }

    public void testDescribe_9_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'shortProperty'",new Short((short)987),map.get("shortProperty"));
    }

    public void testDescribe_10_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'stringProperty'","This is a string",(String)map.get("stringProperty"));
    }

    public void testGetDescriptorArguments_2_oe() {

        try {
            PropertyUtils.getPropertyDescriptor(null, "stringProperty");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetDescriptorArguments_4_oe() {

        try {
            PropertyUtils.getPropertyDescriptor(null, "stringProperty");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getPropertyDescriptor(bean, null);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetDescriptorInvalidBoolean_1_oe() throws Exception {

    final PropertyDescriptor pd =
        PropertyUtils.getPropertyDescriptor(bean, "invalidBoolean");
    assertNotNull("invalidBoolean is a property", pd);
    }

    public void testGetDescriptorInvalidBoolean_2_oe() throws Exception {

    final PropertyDescriptor pd =
        PropertyUtils.getPropertyDescriptor(bean, "invalidBoolean");
    assertNotNull("invalidBoolean has a getter method",pd.getReadMethod());
    }

    public void testGetDescriptorInvalidBoolean_3_oe() throws Exception {

    final PropertyDescriptor pd =
        PropertyUtils.getPropertyDescriptor(bean, "invalidBoolean");
    assertNull("invalidBoolean has no write method",pd.getWriteMethod());
    }

    public void testGetDescriptorInvalidBoolean_4_oe() throws Exception {

    final PropertyDescriptor pd =
        PropertyUtils.getPropertyDescriptor(bean, "invalidBoolean");
    assertTrue("invalidBoolean getter method is isInvalidBoolean or getInvalidBoolean",Arrays.asList("isInvalidBoolean","getInvalidBoolean").contains(pd.getReadMethod().getName()));
    }

    public void testGetDescriptorMappedPeriods_1_oe() {

        bean.getMappedIntProperty("xyz"); // initializes mappedIntProperty

        PropertyDescriptor desc;
        final Integer testIntegerValue = new Integer(1234);

        bean.setMappedIntProperty("key.with.a.dot", testIntegerValue.intValue());
        assertEquals("Can retrieve directly",testIntegerValue,new Integer(bean.getMappedIntProperty("key.with.a.dot")));
    }

    public void testGetDescriptorMappedPeriods_3_oe() {

        bean.getMappedIntProperty("xyz"); // initializes mappedIntProperty

        PropertyDescriptor desc;
        final Integer testIntegerValue = new Integer(1234);

        bean.setMappedIntProperty("key.with.a.dot", testIntegerValue.intValue());
        try {
            desc = PropertyUtils.getPropertyDescriptor
                         (bean, "mappedIntProperty(key.with.a.dot)");
        } catch (final Exception e) {
            fail("Threw exception (A): " + e);
    }
    }

    public void testGetDescriptorMappedPeriods_4_oe() {

        bean.getMappedIntProperty("xyz"); // initializes mappedIntProperty

        PropertyDescriptor desc;
        final Integer testIntegerValue = new Integer(1234);

        bean.setMappedIntProperty("key.with.a.dot", testIntegerValue.intValue());
        try {
            desc = PropertyUtils.getPropertyDescriptor
                         (bean, "mappedIntProperty(key.with.a.dot)");
        } catch (final Exception e) {
        }

        bean.setMappedObjects("nested.property", new TestBean(testIntegerValue.intValue()));
        assertEquals("Can retrieve directly",testIntegerValue,new Integer(((TestBean)bean.getMappedObjects("nested.property")).getIntProperty()));
    }

    public void testGetDescriptorMappedPeriods_6_oe() {

        bean.getMappedIntProperty("xyz"); // initializes mappedIntProperty

        PropertyDescriptor desc;
        final Integer testIntegerValue = new Integer(1234);

        bean.setMappedIntProperty("key.with.a.dot", testIntegerValue.intValue());
        try {
            desc = PropertyUtils.getPropertyDescriptor
                         (bean, "mappedIntProperty(key.with.a.dot)");
        } catch (final Exception e) {
        }

        bean.setMappedObjects("nested.property", new TestBean(testIntegerValue.intValue()));
        try {
            desc = PropertyUtils.getPropertyDescriptor
                         (bean, "mappedObjects(nested.property).intProperty");
        } catch (final Exception e) {
            fail("Threw exception (B): " + e);
    }
    }

    public void testGetDescriptors_1_oe() {

        final PropertyDescriptor pd[] =
                PropertyUtils.getPropertyDescriptors(bean);
        assertNotNull("Got descriptors", pd);
    }

    public void testGetDescriptors_2_oe() {

        final PropertyDescriptor pd[] =
                PropertyUtils.getPropertyDescriptors(bean);
        final int count[] = new int[properties.length];
        for (PropertyDescriptor element : pd) {
            final String name = element.getName();
            for (int j = 0; j < properties.length; j++) {
                if (name.equals(properties[j])) {
                    count[j]++;
                }
            }
        }
        for (int j = 0; j < properties.length; j++) {
            if (count[j] < 0) {
                fail("Missing property " + properties[j]);
    }
    }
    }

    public void testGetDescriptors_3_oe() {

        final PropertyDescriptor pd[] =
                PropertyUtils.getPropertyDescriptors(bean);
        final int count[] = new int[properties.length];
        for (PropertyDescriptor element : pd) {
            final String name = element.getName();
            for (int j = 0; j < properties.length; j++) {
                if (name.equals(properties[j])) {
                    count[j]++;
                }
            }
        }
        for (int j = 0; j < properties.length; j++) {
            if (count[j] < 0) {
            } else if (count[j] > 1) {
                fail("Duplicate property " + properties[j]);
    }
    }
    }

    public void testGetDescriptorsArguments_2_oe() {

        try {
            PropertyUtils.getPropertyDescriptors(null);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException");
    }
    }

    public void testGetIndexedArguments_2_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetIndexedArguments_4_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetIndexedArguments_6_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testGetIndexedArguments_8_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 4");
    }
    }

    public void testGetIndexedArguments_10_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 5");
    }
    }

    public void testGetIndexedArguments_12_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetIndexedArguments_14_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetIndexedArguments_16_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intIndexed[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testGetIndexedArguments_18_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intIndexed[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 4");
    }
    }

    public void testGetIndexedArguments_20_oe() {


        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getIndexedProperty(null,
                    "intIndexed[0]");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intIndexed");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 5");
    }
    }

    public void testGetIndexedValues_4_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
                fail("dupProperty " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_8_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
                fail("intArray " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_12_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
                fail("intIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_16_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
                fail("listIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_20_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
                fail("stringArray " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_24_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
                fail("stringIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_28_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
                fail("dupProperty " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_32_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
                fail("intArray " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_36_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
                fail("intIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_40_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
                fail("listIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_44_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
                fail("stringArray " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_48_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
                fail("stringIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_50_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_52_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_54_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_56_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_58_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_60_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_62_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_64_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_66_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_68_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_70_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_72_oe() {

        Object value = null;


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
            } catch (final Throwable t) {
            }

        }


        for (int i = 0; i < 5; i++) {

            try {
                value = PropertyUtils.getIndexedProperty
                    (bean, "dupProperty[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
            } catch (final Throwable t) {
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
            } catch (final Throwable t) {
            }

        }


        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringIndexed", -1);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringIndexed", 5);
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedArray_8_oe() {
        final String[] firstArray = new String[] {"FIRST-1", "FIRST-2", "FIRST-3"};
        final String[] secondArray = new String[] {"SECOND-1", "SECOND-2", "SECOND-3",  "SECOND-4"};
        final String[][] mainArray = {firstArray, secondArray};
        final TestBean bean = new TestBean(mainArray);
        try {
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testGetIndexedList_8_oe() {
        final String[] firstArray = new String[] {"FIRST-1", "FIRST-2", "FIRST-3"};
        final String[] secondArray = new String[] {"SECOND-1", "SECOND-2", "SECOND-3",  "SECOND-4"};
        final List<Object> mainList = new ArrayList<Object>();
        mainList.add(Arrays.asList(firstArray));
        mainList.add(Arrays.asList(secondArray));
        final TestBean bean = new TestBean(mainList);
        try {
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testGetIndexedMap_5_oe() {
        final Map<String, Object> firstMap  = new HashMap<String, Object>();
        firstMap.put("FIRST-KEY-1", "FIRST-VALUE-1");
        firstMap.put("FIRST-KEY-2", "FIRST-VALUE-2");
        final Map<String, Object> secondMap  = new HashMap<String, Object>();
        secondMap.put("SECOND-KEY-1", "SECOND-VALUE-1");
        secondMap.put("SECOND-KEY-2", "SECOND-VALUE-2");

        final List<Object> mainList   = new ArrayList<Object>();
        mainList.add(firstMap);
        mainList.add(secondMap);
        final TestBean bean = new TestBean(mainList);
        try {
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testGetMappedArguments_2_oe() {


        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetMappedArguments_4_oe() {


        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetMappedArguments_6_oe() {


        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty", null);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testGetMappedArguments_8_oe() {


        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty", null);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getMappedProperty(null,
                    "mappedProperty(First Key)");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 4");
    }
    }

    public void testGetMappedArguments_10_oe() {


        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty", null);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getMappedProperty(null,
                    "mappedProperty(First Key)");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, "(Second Key)");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 5");
    }
    }

    public void testGetMappedArguments_12_oe() {


        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty", null);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.getMappedProperty(null,
                    "mappedProperty(First Key)");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, "(Second Key)");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 6");
    }
    }

    public void testGetMappedArray_4_oe() {
        final TestBean bean = new TestBean();
        final String[] array = new String[] {"abc", "def", "ghi"};
        bean.getMapProperty().put("mappedArray", array);
        try {
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testGetMappedList_4_oe() {
        final TestBean bean = new TestBean();
        final List<Object> list = new ArrayList<Object>();
        list.add("klm");
        list.add("nop");
        list.add("qrs");
        bean.getMapProperty().put("mappedList", list);
        try {
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testGetMappedMap_4_oe() {
        final TestBean bean = new TestBean();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("sub-key-1", "sub-value-1");
        map.put("sub-key-2", "sub-value-2");
        map.put("sub-key-3", "sub-value-3");
        bean.getMapProperty().put("mappedMap", map);
        try {
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testGetMappedPeriods_1_oe() {

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        assertEquals("Can retrieve directly","Special Value",bean.getMappedProperty("key.with.a.dot"));
    }

    public void testGetMappedPeriods_3_oe() {

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        try {
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedPeriods_5_oe() {

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        try {
        } catch (final Exception e) {
        }
        try {
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedPeriods_6_oe() {

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        try {
        } catch (final Exception e) {
        }
        try {
        } catch (final Exception e) {
        }

        bean.setMappedObjects("nested.property", new TestBean());
        assertNotNull("Can retrieve directly",bean.getMappedObjects("nested.property"));
    }

    public void testGetMappedPeriods_8_oe() {

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        try {
        } catch (final Exception e) {
        }
        try {
        } catch (final Exception e) {
        }

        bean.setMappedObjects("nested.property", new TestBean());
        try {
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedPeriods_10_oe() {

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        try {
        } catch (final Exception e) {
        }
        try {
        } catch (final Exception e) {
        }

        bean.setMappedObjects("nested.property", new TestBean());
        try {
        } catch (final Exception e) {
        }

        try
        {
        } catch (final Exception e)
        {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedSlashes_1_oe() {

        bean.setMappedProperty("key/with/a/slash", "Special Value");
        assertEquals("Can retrieve directly","Special Value",bean.getMappedProperty("key/with/a/slash"));
    }

    public void testGetMappedSlashes_3_oe() {

        bean.setMappedProperty("key/with/a/slash", "Special Value");
        try {
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedSlashes_5_oe() {

        bean.setMappedProperty("key/with/a/slash", "Special Value");
        try {
        } catch (final Exception e) {
        }
        try {
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedSlashes_6_oe() {

        bean.setMappedProperty("key/with/a/slash", "Special Value");
        try {
        } catch (final Exception e) {
        }
        try {
        } catch (final Exception e) {
        }

        bean.setMappedObjects("nested/property", new TestBean());
        assertNotNull("Can retrieve directly",bean.getMappedObjects("nested/property"));
    }

    public void testGetMappedSlashes_8_oe() {

        bean.setMappedProperty("key/with/a/slash", "Special Value");
        try {
        } catch (final Exception e) {
        }
        try {
        } catch (final Exception e) {
        }

        bean.setMappedObjects("nested/property", new TestBean());
        try {
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedValues_2_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
        } catch (final Throwable t) {
            fail("Finding first value threw " + t);
    }
    }

    public void testGetMappedValues_4_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
        } catch (final Throwable t) {
            fail("Finding second value threw " + t);
    }
    }

    public void testGetMappedValues_6_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
        } catch (final Throwable t) {
            fail("Finding third value threw " + t);
    }
    }

    public void testGetMappedValues_8_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
        } catch (final Throwable t) {
            fail("Finding first value threw " + t);
    }
    }

    public void testGetMappedValues_10_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
        } catch (final Throwable t) {
            fail("Finding second value threw " + t);
    }
    }

    public void testGetMappedValues_12_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Third Key)");
        } catch (final Throwable t) {
            fail("Finding third value threw " + t);
    }
    }

    public void testGetMappedValues_14_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Third Key)");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.First Key");
        } catch (final Throwable t) {
            fail("Finding first value threw " + t);
    }
    }

    public void testGetMappedValues_16_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Third Key)");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.First Key");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Second Key");
        } catch (final Throwable t) {
            fail("Finding second value threw " + t);
    }
    }

    public void testGetMappedValues_18_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Third Key)");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.First Key");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Second Key");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Third Key");
        } catch (final Throwable t) {
            fail("Finding third value threw " + t);
    }
    }

    public void testGetNestedArguments_2_oe() {

        try {
            PropertyUtils.getNestedProperty(null, "stringProperty");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetNestedArguments_4_oe() {

        try {
            PropertyUtils.getNestedProperty(null, "stringProperty");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getNestedProperty(bean, null);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetNestedBoolean_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.booleanProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedDouble_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.doubleProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedFloat_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.floatProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedInt_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.intProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedLong_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.longProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedReadOnly_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.readOnlyProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedShort_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.shortProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedString_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.stringProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetPropertyType_44_oe() {

        Class<?> clazz = null;
        final int intArray[] = new int[0];
        final String stringArray[] = new String[0];

        try {

            clazz = PropertyUtils.getPropertyType(bean, "booleanProperty");
            clazz = PropertyUtils.getPropertyType(bean, "booleanSecond");
            clazz = PropertyUtils.getPropertyType(bean, "doubleProperty");
            clazz = PropertyUtils.getPropertyType(bean, "dupProperty");
            clazz = PropertyUtils.getPropertyType(bean, "floatProperty");
            clazz = PropertyUtils.getPropertyType(bean, "intArray");
            clazz = PropertyUtils.getPropertyType(bean, "intIndexed");
            clazz = PropertyUtils.getPropertyType(bean, "intProperty");
            clazz = PropertyUtils.getPropertyType(bean, "listIndexed");
            clazz = PropertyUtils.getPropertyType(bean, "longProperty");
            clazz = PropertyUtils.getPropertyType(bean, "mappedProperty");
            clazz = PropertyUtils.getPropertyType(bean, "mappedIntProperty");
            clazz = PropertyUtils.getPropertyType(bean, "readOnlyProperty");
            clazz = PropertyUtils.getPropertyType(bean, "shortProperty");
            clazz = PropertyUtils.getPropertyType(bean, "stringArray");
            clazz = PropertyUtils.getPropertyType(bean, "stringIndexed");
            clazz = PropertyUtils.getPropertyType(bean, "stringProperty");
            clazz = PropertyUtils.getPropertyType(bean, "writeOnlyProperty");

            clazz = PropertyUtils.getPropertyType(bean, "nested.booleanProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.booleanSecond");
            clazz = PropertyUtils.getPropertyType(bean, "nested.doubleProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.dupProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.floatProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.intArray");
            clazz = PropertyUtils.getPropertyType(bean, "nested.intIndexed");
            clazz = PropertyUtils.getPropertyType(bean, "nested.intProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.listIndexed");
            clazz = PropertyUtils.getPropertyType(bean, "nested.longProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.mappedProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.mappedIntProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.readOnlyProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.shortProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.stringArray");
            clazz = PropertyUtils.getPropertyType(bean, "nested.stringIndexed");
            clazz = PropertyUtils.getPropertyType(bean, "nested.stringProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nested.writeOnlyProperty");

            clazz = PropertyUtils.getPropertyType(bean, "nestedDynaBean");
            clazz = PropertyUtils.getPropertyType(bean, "nestedDynaBean.stringProperty");
            clazz = PropertyUtils.getPropertyType(bean, "nestedDynaBean.nestedBean");
            clazz = PropertyUtils.getPropertyType(bean, "nestedDynaBean.nestedBean.nestedDynaBean");
            clazz = PropertyUtils.getPropertyType(bean, "nestedDynaBean.nestedBean.nestedDynaBean.stringProperty");

            clazz = PropertyUtils.getPropertyType(bean, "nestedDynaBean.nullDynaBean");
            try {
                clazz = PropertyUtils.getPropertyType(bean, "nestedDynaBean.nullDynaBean.foo");
            } catch (final NestedNullException e) {
            }

        } catch (final Exception e) {
            fail("Exception: " + e.getMessage());
    }
    }

    public void testGetPublicSubBean_of_PackageBean_1_oe() {

        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("foo-start");
        bean.setBar("bar-start");
        Object result = null;

        try {
            result = PropertyUtils.getProperty(bean, "foo");
        } catch (final Throwable t) {
            fail("getProperty(foo) threw " + t);
    }
    }

    public void testGetPublicSubBean_of_PackageBean_2_oe() {

        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("foo-start");
        bean.setBar("bar-start");
        Object result = null;

        try {
            result = PropertyUtils.getProperty(bean, "foo");
        } catch (final Throwable t) {
        }
        assertEquals("foo property", "foo-start", result);
    }

    public void testGetPublicSubBean_of_PackageBean_3_oe() {

        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("foo-start");
        bean.setBar("bar-start");
        Object result = null;

        try {
            result = PropertyUtils.getProperty(bean, "foo");
        } catch (final Throwable t) {
        }

        try {
            result = PropertyUtils.getProperty(bean, "bar");
        } catch (final Throwable t) {
            fail("getProperty(bar) threw " + t);
    }
    }

    public void testGetPublicSubBean_of_PackageBean_4_oe() {

        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("foo-start");
        bean.setBar("bar-start");
        Object result = null;

        try {
            result = PropertyUtils.getProperty(bean, "foo");
        } catch (final Throwable t) {
        }

        try {
            result = PropertyUtils.getProperty(bean, "bar");
        } catch (final Throwable t) {
        }
        assertEquals("bar property", "bar-start", result);
    }

    public void testGetReadMethodPublicInterface_1_oe() {

        testGetReadMethod(beanPrivate,
                new String[]{ "bar" },
                PRIVATE_DIRECT_CLASS);
        testGetReadMethod(beanPrivate,
                new String[]{ "baz" },
                PRIVATE_INDIRECT_CLASS);

        testGetReadMethod(beanPrivateSubclass,
                new String[]{ "bar" },
                PRIVATE_DIRECT_CLASS);
        testGetReadMethod(beanPrivateSubclass,
                new String[]{ "baz" },
                PRIVATE_INDIRECT_CLASS);

        final PropertyDescriptor pd[] =
                PropertyUtils.getPropertyDescriptors(beanPrivate);
        int n = -1;
        for (int i = 0; i < pd.length; i++) {
            if ("foo".equals(pd[i].getName())) {
                n = i;
                break;
            }
        }
        assertTrue("Found foo descriptor", n >= 0);
    }

    public void testGetReadMethodPublicInterface_2_oe() {

        testGetReadMethod(beanPrivate,
                new String[]{ "bar" },
                PRIVATE_DIRECT_CLASS);
        testGetReadMethod(beanPrivate,
                new String[]{ "baz" },
                PRIVATE_INDIRECT_CLASS);

        testGetReadMethod(beanPrivateSubclass,
                new String[]{ "bar" },
                PRIVATE_DIRECT_CLASS);
        testGetReadMethod(beanPrivateSubclass,
                new String[]{ "baz" },
                PRIVATE_INDIRECT_CLASS);

        final PropertyDescriptor pd[] =
                PropertyUtils.getPropertyDescriptors(beanPrivate);
        int n = -1;
        for (int i = 0; i < pd.length; i++) {
            if ("foo".equals(pd[i].getName())) {
                n = i;
                break;
            }
        }
        final Method reader = pd[n].getReadMethod();
        assertNotNull("Found foo read method", reader);
    }

    public void testGetReadMethodPublicInterface_4_oe() {

        testGetReadMethod(beanPrivate,
                new String[]{ "bar" },
                PRIVATE_DIRECT_CLASS);
        testGetReadMethod(beanPrivate,
                new String[]{ "baz" },
                PRIVATE_INDIRECT_CLASS);

        testGetReadMethod(beanPrivateSubclass,
                new String[]{ "bar" },
                PRIVATE_DIRECT_CLASS);
        testGetReadMethod(beanPrivateSubclass,
                new String[]{ "baz" },
                PRIVATE_INDIRECT_CLASS);

        final PropertyDescriptor pd[] =
                PropertyUtils.getPropertyDescriptors(beanPrivate);
        int n = -1;
        for (int i = 0; i < pd.length; i++) {
            if ("foo".equals(pd[i].getName())) {
                n = i;
                break;
            }
        }
        final Method reader = pd[n].getReadMethod();
        try {
            reader.invoke(beanPrivate, (Object[]) new Class<?>[0]);
        } catch (final IllegalAccessException e) {
        } catch (final Throwable t) {
            fail("Invoke foo reader: " + t);
    }
    }

    public void testGetSimpleArguments_2_oe() {

        try {
            PropertyUtils.getSimpleProperty(null, "stringProperty");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetSimpleArguments_4_oe() {

        try {
            PropertyUtils.getSimpleProperty(null, "stringProperty");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.getSimpleProperty(bean, null);
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetSimpleBoolean_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "booleanProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleDouble_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "doubleProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleFloat_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "floatProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleIndexed_4_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean,
                    "intIndexed[0]");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleInt_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "intProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleLong_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "longProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleNested_4_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean,
                    "nested.stringProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleReadOnly_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "readOnlyProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleShort_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "shortProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleString_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "stringProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleUnknown_5_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean, "unknown");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            assertEquals("Unknown property 'unknown' on class '" + bean.getClass()+ "'",e.getMessage());
    }
    }

    public void testGetSimpleWriteOnly_5_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean, "writeOnlyProperty");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            assertEquals("Property 'writeOnlyProperty' has no getter method in class '" + bean.getClass()+ "'",e.getMessage());
    }
    }

    public void testIsReadable_2_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsReadable_4_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsReadable_6_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsReadable_8_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsReadable_10_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsReadable_12_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsReadable_14_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean";
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsReadable_16_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsReadable_18_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nullDynaBean";
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsReadable_21_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nullDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nullDynaBean.foo";
        } catch (final NestedNullException e) {
        } catch (final Throwable t) {
            fail("Property " + property +" isReadable Threw exception: " + t);
    }
    }

    public void testIsWriteable_2_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testIsWriteable_4_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testIsWriteable_6_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testIsWriteable_8_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testIsWriteable_10_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
            t.printStackTrace();
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testIsWriteable_12_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
            t.printStackTrace();
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testIsWriteable_14_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
            t.printStackTrace();
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean";
        } catch (final Throwable t) {
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testIsWriteable_16_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
            t.printStackTrace();
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testIsWriteable_18_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
            t.printStackTrace();
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nullDynaBean";
        } catch (final Throwable t) {
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testIsWriteable_21_oe() {
        String property = null;
        try {
            property = "stringProperty";
        } catch (final Throwable t) {
        }
        try {
            property = "stringIndexed";
        } catch (final Throwable t) {
        }
        try {
            property = "mappedProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
            t.printStackTrace();
        }

        try {
            property = "nestedDynaBean.nestedBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nestedBean.nestedDynaBean.stringProperty";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nullDynaBean";
        } catch (final Throwable t) {
        }

        try {
            property = "nestedDynaBean.nullDynaBean.foo";
        } catch (final NestedNullException e) {
        } catch (final Throwable t) {
            fail("Property " + property +" isWriteable Threw exception: " + t);
    }
    }

    public void testMappedPropertyType_1_oe() throws Exception {

        MappedPropertyDescriptor desc;

        desc = (MappedPropertyDescriptor)
                PropertyUtils.getPropertyDescriptor(bean,
                        "mappedProperty");
        assertEquals(String.class, desc.getMappedPropertyType());
    }

    public void testMappedPropertyType_2_oe() throws Exception {

        MappedPropertyDescriptor desc;

        desc = (MappedPropertyDescriptor)
                PropertyUtils.getPropertyDescriptor(bean,
                        "mappedProperty");

        desc = (MappedPropertyDescriptor)
                PropertyUtils.getPropertyDescriptor(bean,
                        "mappedIntProperty");
        assertEquals(Integer.TYPE, desc.getMappedPropertyType());
    }

    public void testSetIndexedArguments_2_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetIndexedArguments_4_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetIndexedArguments_6_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testSetIndexedArguments_8_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 4");
    }
    }

    public void testSetIndexedArguments_10_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 5");
    }
    }

    public void testSetIndexedArguments_12_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetIndexedArguments_14_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetIndexedArguments_16_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intIndexed[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testSetIndexedArguments_18_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intIndexed[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 4");
    }
    }

    public void testSetIndexedArguments_20_oe() {


        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(null,
                    "intIndexed[0]",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intIndexed",
                    new Integer(1));
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 5");
    }
    }

    public void testSetIndexedArray_1_oe() {
        final String[] firstArray = new String[] {"FIRST-1", "FIRST-2", "FIRST-3"};
        final String[] secondArray = new String[] {"SECOND-1", "SECOND-2", "SECOND-3",  "SECOND-4"};
        final String[][] mainArray = {firstArray, secondArray};
        final TestBean bean = new TestBean(mainArray);
        assertEquals("BEFORE", "SECOND-3", bean.getString2dArray(1)[2]);
    }

    public void testSetIndexedArray_2_oe() {
        final String[] firstArray = new String[] {"FIRST-1", "FIRST-2", "FIRST-3"};
        final String[] secondArray = new String[] {"SECOND-1", "SECOND-2", "SECOND-3",  "SECOND-4"};
        final String[][] mainArray = {firstArray, secondArray};
        final TestBean bean = new TestBean(mainArray);
        try {
            PropertyUtils.setProperty(bean, "string2dArray[1][2]", "SECOND-3-UPDATED");
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testSetIndexedArray_3_oe() {
        final String[] firstArray = new String[] {"FIRST-1", "FIRST-2", "FIRST-3"};
        final String[] secondArray = new String[] {"SECOND-1", "SECOND-2", "SECOND-3",  "SECOND-4"};
        final String[][] mainArray = {firstArray, secondArray};
        final TestBean bean = new TestBean(mainArray);
        try {
            PropertyUtils.setProperty(bean, "string2dArray[1][2]", "SECOND-3-UPDATED");
        } catch (final Throwable t) {
        }
        assertEquals("AFTER", "SECOND-3-UPDATED", bean.getString2dArray(1)[2]);
    }

    public void testSetIndexedList_1_oe() {
        final String[] firstArray = new String[] {"FIRST-1", "FIRST-2", "FIRST-3"};
        final String[] secondArray = new String[] {"SECOND-1", "SECOND-2", "SECOND-3",  "SECOND-4"};
        final List<Object> mainList   = new ArrayList<Object>();
        mainList.add(Arrays.asList(firstArray));
        mainList.add(Arrays.asList(secondArray));
        final TestBean bean = new TestBean(mainList);
        assertEquals("BEFORE", "SECOND-4", ((List<?>)bean.getListIndexed().get(1)).get(3));
    }

    public void testSetIndexedList_2_oe() {
        final String[] firstArray = new String[] {"FIRST-1", "FIRST-2", "FIRST-3"};
        final String[] secondArray = new String[] {"SECOND-1", "SECOND-2", "SECOND-3",  "SECOND-4"};
        final List<Object> mainList   = new ArrayList<Object>();
        mainList.add(Arrays.asList(firstArray));
        mainList.add(Arrays.asList(secondArray));
        final TestBean bean = new TestBean(mainList);
        try {
            PropertyUtils.setProperty(bean, "listIndexed[1][3]", "SECOND-4-UPDATED");
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testSetIndexedList_3_oe() {
        final String[] firstArray = new String[] {"FIRST-1", "FIRST-2", "FIRST-3"};
        final String[] secondArray = new String[] {"SECOND-1", "SECOND-2", "SECOND-3",  "SECOND-4"};
        final List<Object> mainList   = new ArrayList<Object>();
        mainList.add(Arrays.asList(firstArray));
        mainList.add(Arrays.asList(secondArray));
        final TestBean bean = new TestBean(mainList);
        try {
            PropertyUtils.setProperty(bean, "listIndexed[1][3]", "SECOND-4-UPDATED");
        } catch (final Throwable t) {
        }
        assertEquals("AFTER", "SECOND-4-UPDATED", ((List<?>)bean.getListIndexed().get(1)).get(3));
    }

    public void testSetIndexedMap_1_oe() {
        final Map<String, Object> firstMap  = new HashMap<String, Object>();
        firstMap.put("FIRST-KEY-1", "FIRST-VALUE-1");
        firstMap.put("FIRST-KEY-2", "FIRST-VALUE-2");
        final Map<String, Object> secondMap  = new HashMap<String, Object>();
        secondMap.put("SECOND-KEY-1", "SECOND-VALUE-1");
        secondMap.put("SECOND-KEY-2", "SECOND-VALUE-2");

        final List<Object> mainList = new ArrayList<Object>();
        mainList.add(firstMap);
        mainList.add(secondMap);
        final TestBean bean = new TestBean(mainList);

        assertEquals("BEFORE",  null,              ((Map<?, ?>)bean.getListIndexed().get(0)).get("FIRST-NEW-KEY"));
    }

    public void testSetIndexedMap_2_oe() {
        final Map<String, Object> firstMap  = new HashMap<String, Object>();
        firstMap.put("FIRST-KEY-1", "FIRST-VALUE-1");
        firstMap.put("FIRST-KEY-2", "FIRST-VALUE-2");
        final Map<String, Object> secondMap  = new HashMap<String, Object>();
        secondMap.put("SECOND-KEY-1", "SECOND-VALUE-1");
        secondMap.put("SECOND-KEY-2", "SECOND-VALUE-2");

        final List<Object> mainList = new ArrayList<Object>();
        mainList.add(firstMap);
        mainList.add(secondMap);
        final TestBean bean = new TestBean(mainList);

        assertEquals("BEFORE",  "SECOND-VALUE-1",  ((Map<?, ?>)bean.getListIndexed().get(1)).get("SECOND-KEY-1"));
    }

    public void testSetIndexedMap_3_oe() {
        final Map<String, Object> firstMap  = new HashMap<String, Object>();
        firstMap.put("FIRST-KEY-1", "FIRST-VALUE-1");
        firstMap.put("FIRST-KEY-2", "FIRST-VALUE-2");
        final Map<String, Object> secondMap  = new HashMap<String, Object>();
        secondMap.put("SECOND-KEY-1", "SECOND-VALUE-1");
        secondMap.put("SECOND-KEY-2", "SECOND-VALUE-2");

        final List<Object> mainList = new ArrayList<Object>();
        mainList.add(firstMap);
        mainList.add(secondMap);
        final TestBean bean = new TestBean(mainList);

        try {
            PropertyUtils.setProperty(bean, "listIndexed[0](FIRST-NEW-KEY)", "FIRST-NEW-VALUE");
            PropertyUtils.setProperty(bean, "listIndexed[1](SECOND-KEY-1)",  "SECOND-VALUE-1-UPDATED");
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testSetIndexedMap_4_oe() {
        final Map<String, Object> firstMap  = new HashMap<String, Object>();
        firstMap.put("FIRST-KEY-1", "FIRST-VALUE-1");
        firstMap.put("FIRST-KEY-2", "FIRST-VALUE-2");
        final Map<String, Object> secondMap  = new HashMap<String, Object>();
        secondMap.put("SECOND-KEY-1", "SECOND-VALUE-1");
        secondMap.put("SECOND-KEY-2", "SECOND-VALUE-2");

        final List<Object> mainList = new ArrayList<Object>();
        mainList.add(firstMap);
        mainList.add(secondMap);
        final TestBean bean = new TestBean(mainList);

        try {
            PropertyUtils.setProperty(bean, "listIndexed[0](FIRST-NEW-KEY)", "FIRST-NEW-VALUE");
            PropertyUtils.setProperty(bean, "listIndexed[1](SECOND-KEY-1)",  "SECOND-VALUE-1-UPDATED");
        } catch (final Throwable t) {
        }
        assertEquals("BEFORE", "FIRST-NEW-VALUE",         ((Map<?, ?>)bean.getListIndexed().get(0)).get("FIRST-NEW-KEY"));
    }

    public void testSetIndexedMap_5_oe() {
        final Map<String, Object> firstMap  = new HashMap<String, Object>();
        firstMap.put("FIRST-KEY-1", "FIRST-VALUE-1");
        firstMap.put("FIRST-KEY-2", "FIRST-VALUE-2");
        final Map<String, Object> secondMap  = new HashMap<String, Object>();
        secondMap.put("SECOND-KEY-1", "SECOND-VALUE-1");
        secondMap.put("SECOND-KEY-2", "SECOND-VALUE-2");

        final List<Object> mainList = new ArrayList<Object>();
        mainList.add(firstMap);
        mainList.add(secondMap);
        final TestBean bean = new TestBean(mainList);

        try {
            PropertyUtils.setProperty(bean, "listIndexed[0](FIRST-NEW-KEY)", "FIRST-NEW-VALUE");
            PropertyUtils.setProperty(bean, "listIndexed[1](SECOND-KEY-1)",  "SECOND-VALUE-1-UPDATED");
        } catch (final Throwable t) {
        }
        assertEquals("AFTER",  "SECOND-VALUE-1-UPDATED",  ((Map<?, ?>)bean.getListIndexed().get(1)).get("SECOND-KEY-1"));
    }

    public void testSetIndexedValues_4_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_8_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_12_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_16_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_20_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_24_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_28_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_32_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_36_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_40_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_44_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_48_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_50_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_52_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_54_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_56_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_58_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_60_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_62_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_64_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_66_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", -1,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_68_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", -1,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 5,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_70_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", -1,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 5,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringIndexed", -1,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_72_oe() {

        Object value = null;


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 0,
                    "New 0");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty[4]",
                    "New 4");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "dupProperty[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", -1,
                    "New -1");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "dupProperty", 5,
                    "New 5");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
        } catch (final IndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", -1,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 5,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringIndexed", -1,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringIndexed", 5,
                    "New String");
        } catch (final ArrayIndexOutOfBoundsException t) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetMappedArguments_2_oe() {


        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetMappedArguments_4_oe() {


        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetMappedArguments_6_oe() {


        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", null,
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testSetMappedArguments_8_oe() {


        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", null,
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setMappedProperty(null,
                    "mappedProperty(First Key)",
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 4");
    }
    }

    public void testSetMappedArguments_10_oe() {


        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", null,
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setMappedProperty(null,
                    "mappedProperty(First Key)",
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "(Second Key)",
                    "Second Value");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 5");
    }
    }

    public void testSetMappedArguments_12_oe() {


        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", null,
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }


        try {
            PropertyUtils.setMappedProperty(null,
                    "mappedProperty(First Key)",
                    "First Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "(Second Key)",
                    "Second Value");
        } catch (final NoSuchMethodException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Third Value");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 6");
    }
    }

    public void testSetMappedArray_1_oe() {
        final TestBean bean = new TestBean();
        final String[] array = new String[] {"abc", "def", "ghi"};
        bean.getMapProperty().put("mappedArray", array);

        assertEquals("BEFORE", "def", ((String[])bean.getMapProperty().get("mappedArray"))[1]);
    }

    public void testSetMappedArray_2_oe() {
        final TestBean bean = new TestBean();
        final String[] array = new String[] {"abc", "def", "ghi"};
        bean.getMapProperty().put("mappedArray", array);

        try {
            PropertyUtils.setProperty(bean, "mapProperty(mappedArray)[1]", "DEF-UPDATED");
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testSetMappedArray_3_oe() {
        final TestBean bean = new TestBean();
        final String[] array = new String[] {"abc", "def", "ghi"};
        bean.getMapProperty().put("mappedArray", array);

        try {
            PropertyUtils.setProperty(bean, "mapProperty(mappedArray)[1]", "DEF-UPDATED");
        } catch (final Throwable t) {
        }
        assertEquals("AFTER", "DEF-UPDATED", ((String[])bean.getMapProperty().get("mappedArray"))[1]);
    }

    public void testSetMappedList_1_oe() {
        final TestBean bean = new TestBean();
        final List<Object> list = new ArrayList<Object>();
        list.add("klm");
        list.add("nop");
        list.add("qrs");
        bean.getMapProperty().put("mappedList", list);

        assertEquals("BEFORE", "klm", ((List<?>)bean.getMapProperty().get("mappedList")).get(0));
    }

    public void testSetMappedList_2_oe() {
        final TestBean bean = new TestBean();
        final List<Object> list = new ArrayList<Object>();
        list.add("klm");
        list.add("nop");
        list.add("qrs");
        bean.getMapProperty().put("mappedList", list);

        try {
            PropertyUtils.setProperty(bean, "mapProperty(mappedList)[0]", "KLM-UPDATED");
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testSetMappedList_3_oe() {
        final TestBean bean = new TestBean();
        final List<Object> list = new ArrayList<Object>();
        list.add("klm");
        list.add("nop");
        list.add("qrs");
        bean.getMapProperty().put("mappedList", list);

        try {
            PropertyUtils.setProperty(bean, "mapProperty(mappedList)[0]", "KLM-UPDATED");
        } catch (final Throwable t) {
        }
        assertEquals("AFTER", "KLM-UPDATED", ((List<?>)bean.getMapProperty().get("mappedList")).get(0));
    }

    public void testSetMappedMap_1_oe() {
        final TestBean bean = new TestBean();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("sub-key-1", "sub-value-1");
        map.put("sub-key-2", "sub-value-2");
        map.put("sub-key-3", "sub-value-3");
        bean.getMapProperty().put("mappedMap", map);

        assertEquals("BEFORE", "sub-value-3", ((Map<?, ?>)bean.getMapProperty().get("mappedMap")).get("sub-key-3"));
    }

    public void testSetMappedMap_2_oe() {
        final TestBean bean = new TestBean();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("sub-key-1", "sub-value-1");
        map.put("sub-key-2", "sub-value-2");
        map.put("sub-key-3", "sub-value-3");
        bean.getMapProperty().put("mappedMap", map);

        try {
            PropertyUtils.setProperty(bean, "mapProperty(mappedMap)(sub-key-3)", "SUB-KEY-3-UPDATED");
        } catch (final Throwable t) {
            fail("Threw " + t + "");
    }
    }

    public void testSetMappedMap_3_oe() {
        final TestBean bean = new TestBean();
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("sub-key-1", "sub-value-1");
        map.put("sub-key-2", "sub-value-2");
        map.put("sub-key-3", "sub-value-3");
        bean.getMapProperty().put("mappedMap", map);

        try {
            PropertyUtils.setProperty(bean, "mapProperty(mappedMap)(sub-key-3)", "SUB-KEY-3-UPDATED");
        } catch (final Throwable t) {
        }
        assertEquals("AFTER", "SUB-KEY-3-UPDATED", ((Map<?, ?>)bean.getMapProperty().get("mappedMap")).get("sub-key-3"));
    }

    public void testSetMappedValues_2_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
            fail("Finding fourth value threw " + t);
    }
    }

    public void testSetMappedValues_3_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
            fail("Setting fourth value threw " + t);
    }
    }

    public void testSetMappedValues_5_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
            fail("Finding fourth value threw " + t);
    }
    }

    public void testSetMappedValues_7_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
            fail("Finding fifth value threw " + t);
    }
    }

    public void testSetMappedValues_8_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean,
                    "mappedProperty(Fifth Key)",
                    "Fifth Value");
        } catch (final Throwable t) {
            fail("Setting fifth value threw " + t);
    }
    }

    public void testSetMappedValues_10_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean,
                    "mappedProperty(Fifth Key)",
                    "Fifth Value");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
            fail("Finding fifth value threw " + t);
    }
    }

    public void testSetMappedValues_12_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean,
                    "mappedProperty(Fifth Key)",
                    "Fifth Value");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Sixth Key");
        } catch (final Throwable t) {
            fail("Finding fifth value threw " + t);
    }
    }

    public void testSetMappedValues_13_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean,
                    "mappedProperty(Fifth Key)",
                    "Fifth Value");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Sixth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setNestedProperty(bean,
                    "mapProperty.Sixth Key",
                    "Sixth Value");
        } catch (final Throwable t) {
            fail("Setting sixth value threw " + t);
    }
    }

    public void testSetMappedValues_15_oe() {

        Object value = null;


        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setMappedProperty(bean,
                    "mappedProperty(Fifth Key)",
                    "Fifth Value");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
        } catch (final Throwable t) {
        }


        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Sixth Key");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setNestedProperty(bean,
                    "mapProperty.Sixth Key",
                    "Sixth Value");
        } catch (final Throwable t) {
        }

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Sixth Key");
        } catch (final Throwable t) {
            fail("Finding sixth value threw " + t);
    }
    }

    public void testSetMappedPeriods_1_oe() {


        bean.setMappedProperty("key.with.a.dot", "Special Value");
        assertEquals("Can retrieve directly(A)","Special Value",bean.getMappedProperty("key.with.a.dot"));
    }

    public void testSetMappedPeriods_3_oe() {


        bean.setMappedProperty("key.with.a.dot", "Special Value");

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", "key.with.a.dot", "Updated Special Value");
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testSetMappedPeriods_4_oe() {


        bean.setMappedProperty("key.with.a.dot", "Special Value");

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", "key.with.a.dot", "Updated Special Value");
        } catch (final Exception e) {
        }

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        assertEquals("Can retrieve directly(B)","Special Value",bean.getMappedProperty("key.with.a.dot"));
    }

    public void testSetMappedPeriods_6_oe() {


        bean.setMappedProperty("key.with.a.dot", "Special Value");

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", "key.with.a.dot", "Updated Special Value");
        } catch (final Exception e) {
        }

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        try {
            PropertyUtils.setNestedProperty(bean, "mappedProperty(key.with.a.dot)", "Updated Special Value");
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testSetMappedPeriods_7_oe() {


        bean.setMappedProperty("key.with.a.dot", "Special Value");

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", "key.with.a.dot", "Updated Special Value");
        } catch (final Exception e) {
        }

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        try {
            PropertyUtils.setNestedProperty(bean, "mappedProperty(key.with.a.dot)", "Updated Special Value");
        } catch (final Exception e) {
        }


        final TestBean testBean = new TestBean();
        bean.setMappedObjects("nested.property", testBean);
        assertEquals("Can retrieve directly(C)","This is a string",testBean.getStringProperty());
    }

    public void testSetMappedPeriods_9_oe() {


        bean.setMappedProperty("key.with.a.dot", "Special Value");

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", "key.with.a.dot", "Updated Special Value");
        } catch (final Exception e) {
        }

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        try {
            PropertyUtils.setNestedProperty(bean, "mappedProperty(key.with.a.dot)", "Updated Special Value");
        } catch (final Exception e) {
        }


        final TestBean testBean = new TestBean();
        bean.setMappedObjects("nested.property", testBean);
        try {
            PropertyUtils.setNestedProperty(bean, "mappedObjects(nested.property).stringProperty",
                                                  "Updated String Value");
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testSetMappedPeriods_12_oe() {


        bean.setMappedProperty("key.with.a.dot", "Special Value");

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", "key.with.a.dot", "Updated Special Value");
        } catch (final Exception e) {
        }

        bean.setMappedProperty("key.with.a.dot", "Special Value");
        try {
            PropertyUtils.setNestedProperty(bean, "mappedProperty(key.with.a.dot)", "Updated Special Value");
        } catch (final Exception e) {
        }


        final TestBean testBean = new TestBean();
        bean.setMappedObjects("nested.property", testBean);
        try {
            PropertyUtils.setNestedProperty(bean, "mappedObjects(nested.property).stringProperty",
                                                  "Updated String Value");
        } catch (final Exception e) {
        }

        bean.getNested().setMappedProperty("Mapped Key", "Nested Mapped Value");
        try {
            PropertyUtils.setNestedProperty(bean, "nested.mappedProperty(Mapped Key)",
                                                  "Updated Nested Mapped Value");
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testSetNestedArguments_2_oe() {

        try {
            PropertyUtils.setNestedProperty(null, "stringProperty", "");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetNestedArguments_4_oe() {

        try {
            PropertyUtils.setNestedProperty(null, "stringProperty", "");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setNestedProperty(bean, null, "");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetNestedBoolean_5_oe() {

        try {
            final boolean oldValue = bean.getNested().getBooleanProperty();
            final boolean newValue = !oldValue;
            PropertyUtils.setNestedProperty(bean,
                    "nested.booleanProperty",
                    new Boolean(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedDouble_5_oe() {

        try {
            final double oldValue = bean.getNested().getDoubleProperty();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.doubleProperty",
                    new Double(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedFloat_5_oe() {

        try {
            final float oldValue = bean.getNested().getFloatProperty();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.floatProperty",
                    new Float(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedInt_5_oe() {

        try {
            final int oldValue = bean.getNested().getIntProperty();
            final int newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.intProperty",
                    new Integer(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedLong_5_oe() {

        try {
            final long oldValue = bean.getNested().getLongProperty();
            final long newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.longProperty",
                    new Long(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedShort_5_oe() {

        try {
            final short oldValue = bean.getNested().getShortProperty();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setNestedProperty(bean,
                    "nested.shortProperty",
                    new Short(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedString_5_oe() {

        try {
            final String oldValue = bean.getNested().getStringProperty();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.stringProperty",
                    newValue);
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedWriteOnly_5_oe() {

        try {
            final String oldValue = bean.getNested().getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.writeOnlyProperty",
                    newValue);
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleArguments_2_oe() {

        try {
            PropertyUtils.setSimpleProperty(null, "stringProperty", "");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetSimpleArguments_4_oe() {

        try {
            PropertyUtils.setSimpleProperty(null, "stringProperty", "");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setSimpleProperty(bean, null, "");
        } catch (final IllegalArgumentException e) {
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetSimpleBoolean_5_oe() {

        try {
            final boolean oldValue = bean.getBooleanProperty();
            final boolean newValue = !oldValue;
            PropertyUtils.setSimpleProperty(bean,
                    "booleanProperty",
                    new Boolean(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleDouble_5_oe() {

        try {
            final double oldValue = bean.getDoubleProperty();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "doubleProperty",
                    new Double(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleFloat_5_oe() {

        try {
            final float oldValue = bean.getFloatProperty();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "floatProperty",
                    new Float(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleIndexed_4_oe() {

        try {
            PropertyUtils.setSimpleProperty(bean,
                    "stringIndexed[0]",
                    "New String Value");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleInt_5_oe() {

        try {
            final int oldValue = bean.getIntProperty();
            final int newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "intProperty",
                    new Integer(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleLong_5_oe() {

        try {
            final long oldValue = bean.getLongProperty();
            final long newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "longProperty",
                    new Long(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleNested_4_oe() {

        try {
            PropertyUtils.setSimpleProperty(bean,
                    "nested.stringProperty",
                    "New String Value");
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleReadOnly_5_oe() {

        try {
            final String oldValue = bean.getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setSimpleProperty(bean,
                    "readOnlyProperty",
                    newValue);
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            assertEquals("Property 'readOnlyProperty' has no setter method in class '" + bean.getClass()+ "'",e.getMessage());
    }
    }

    public void testSetSimpleShort_5_oe() {

        try {
            final short oldValue = bean.getShortProperty();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setSimpleProperty(bean,
                    "shortProperty",
                    new Short(newValue));
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleString_5_oe() {

        try {
            final String oldValue = bean.getStringProperty();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setSimpleProperty(bean,
                    "stringProperty",
                    newValue);
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleUnknown_5_oe() {

        try {
            final String newValue = "New String Value";
            PropertyUtils.setSimpleProperty(bean,
                    "unknown",
                    newValue);
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            assertEquals("Unknown property 'unknown' on class '" + bean.getClass()+ "'",e.getMessage());
    }
    }

    public void testSetSimpleWriteOnly_5_oe() {

        try {
            final String oldValue = bean.getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setSimpleProperty(bean,
                    "writeOnlyProperty",
                    newValue);
        } catch (final IllegalAccessException e) {
        } catch (final IllegalArgumentException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testNestedWithIndex_1_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");
        assertEquals("Cannot get simple index(1)", "Bean@0", value.getName());
    }

    public void testNestedWithIndex_2_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");
        assertEquals("Bug in NestedTestBean", "NOT SET", value.getTestString());
    }

    public void testNestedWithIndex_3_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");
        assertEquals("Cannot get simple index(1)", "Bean@1", value.getName());
    }

    public void testNestedWithIndex_4_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");
        assertEquals("Bug in NestedTestBean", "NOT SET", value.getTestString());
    }

    public void testNestedWithIndex_5_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");

        String
        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0].testString");
        assertEquals("Get property on indexes failed (1)", "NOT SET", prop);
    }

    public void testNestedWithIndex_6_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");

        String
        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0].testString");

        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1].testString");
        assertEquals("Get property on indexes failed (2)", "NOT SET", prop);
    }

    public void testNestedWithIndex_7_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");

        String
        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0].testString");

        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1].testString");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[0].testString",
                                "Test#1");
        assertEquals("Cannot set property on indexed bean(1)","Test#1",nestedBean.getIndexedProperty(0).getTestString());
    }

    public void testNestedWithIndex_8_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");

        String
        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0].testString");

        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1].testString");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[0].testString",
                                "Test#1");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[1].testString",
                                "Test#2");
        assertEquals("Cannot set property on indexed bean(2)","Test#2",nestedBean.getIndexedProperty(1).getTestString());
    }

    public void testNestedWithIndex_9_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");

        String
        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0].testString");

        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1].testString");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[0].testString",
                                "Test#1");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[1].testString",
                                "Test#2");



        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "simpleBeanProperty");
        assertEquals("Cannot get simple bean", "Simple Property Bean", value.getName());
    }

    public void testNestedWithIndex_10_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");

        String
        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0].testString");

        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1].testString");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[0].testString",
                                "Test#1");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[1].testString",
                                "Test#2");



        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "simpleBeanProperty");
        assertEquals("Bug in NestedTestBean", "NOT SET", value.getTestString());
    }

    public void testNestedWithIndex_11_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");

        String
        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0].testString");

        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1].testString");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[0].testString",
                                "Test#1");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[1].testString",
                                "Test#2");



        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "simpleBeanProperty");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "simpleBeanProperty.indexedProperty[3]");
        assertEquals("Cannot get index property on property", "Bean@3", value.getName());
    }

    public void testNestedWithIndex_12_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");

        String
        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0].testString");

        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1].testString");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[0].testString",
                                "Test#1");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[1].testString",
                                "Test#2");



        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "simpleBeanProperty");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "simpleBeanProperty.indexedProperty[3]");
        assertEquals("Bug in NestedTestBean", "NOT SET", value.getTestString());
    }

    public void testNestedWithIndex_13_oe() throws Exception
    {
        final NestedTestBean nestedBean = new NestedTestBean("base");
        nestedBean.init();
        nestedBean.getSimpleBeanProperty().init();

        NestedTestBean


        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0]");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1]");

        String
        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[0].testString");

        prop = (String) PropertyUtils.getProperty(
                                nestedBean,
                                "indexedProperty[1].testString");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[0].testString",
                                "Test#1");

        PropertyUtils.setProperty(
                                nestedBean,
                                "indexedProperty[1].testString",
                                "Test#2");



        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "simpleBeanProperty");

        value = (NestedTestBean) PropertyUtils.getProperty(
                                nestedBean,
                                "simpleBeanProperty.indexedProperty[3]");

        PropertyUtils.setProperty(
                                nestedBean,
                                "simpleBeanProperty.indexedProperty[3].testString",
                                "Test#3");
        assertEquals("Cannot set property on indexed property on property","Test#3",nestedBean.getSimpleBeanProperty().getIndexedProperty(3).getTestString());
    }

    public void testGetSetInnerBean_1_oe() throws Exception {
        final BeanWithInnerBean bean = new BeanWithInnerBean();

        PropertyUtils.setProperty(bean, "innerBean.fish(loiterTimer)", "5");
        String out = (String) PropertyUtils.getProperty(bean.getInnerBean(), "fish(loiterTimer)");
        assertEquals("(1)Inner class property set/get property failed.","5",out);
    }

    public void testGetSetInnerBean_2_oe() throws Exception {
        final BeanWithInnerBean bean = new BeanWithInnerBean();

        PropertyUtils.setProperty(bean, "innerBean.fish(loiterTimer)", "5");
        String out = (String) PropertyUtils.getProperty(bean.getInnerBean(), "fish(loiterTimer)");

        out = (String) PropertyUtils.getProperty(bean, "innerBean.fish(loiterTimer)");

        assertEquals("(2)Inner class property set/get property failed.","5",out);
    }

    public void testGetSetParentBean_1_oe() throws Exception {

        final SonOfAlphaBean bean = new SonOfAlphaBean("Roger");

        final String out = (String) PropertyUtils.getProperty(bean, "name");
        assertEquals("(1)Get/Set On Parent.","Roger",out);
    }

    public void testGetSetParentBean_2_oe() throws Exception {

        final SonOfAlphaBean bean = new SonOfAlphaBean("Roger");

        final String out = (String) PropertyUtils.getProperty(bean, "name");

        PropertyUtils.setProperty(bean, "name", "abcd");
        assertEquals("(2)Get/Set On Parent.","abcd",bean.getName());
    }

    public void testSetNoGetter_1_oe() throws Exception
    {
        final BetaBean bean = new BetaBean("Cedric");

        bean.setNoGetterProperty("Sigma");
        assertEquals("BetaBean test failed", "Sigma", bean.getSecret());
    }

    public void testSetNoGetter_2_oe() throws Exception
    {
        final BetaBean bean = new BetaBean("Cedric");

        bean.setNoGetterProperty("Sigma");

        assertNotNull("Descriptor is null", PropertyUtils.getPropertyDescriptor(bean, "noGetterProperty"));
    }

    public void testSetNoGetter_3_oe() throws Exception
    {
        final BetaBean bean = new BetaBean("Cedric");

        bean.setNoGetterProperty("Sigma");


        BeanUtils.setProperty(bean, "noGetterProperty",  "Omega");
        assertEquals("Cannot set no-getter property", "Omega", bean.getSecret());
    }

    public void testSetNoGetter_4_oe() throws Exception
    {
        final BetaBean bean = new BetaBean("Cedric");

        bean.setNoGetterProperty("Sigma");


        BeanUtils.setProperty(bean, "noGetterProperty",  "Omega");

        assertNotNull("Map Descriptor is null", PropertyUtils.getPropertyDescriptor(bean, "noGetterMappedProperty"));
    }

    public void testSetNoGetter_5_oe() throws Exception
    {
        final BetaBean bean = new BetaBean("Cedric");

        bean.setNoGetterProperty("Sigma");


        BeanUtils.setProperty(bean, "noGetterProperty",  "Omega");


        PropertyUtils.setMappedProperty(bean, "noGetterMappedProperty",  "Epsilon", "Epsilon");
        assertEquals("Cannot set mapped no-getter property", "MAP:Epsilon", bean.getSecret());
    }

    public void testSetPublicSubBean_of_PackageBean_1_oe() {

        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("foo-start");
        bean.setBar("bar-start");

        try {
            PropertyUtils.setProperty(bean, "foo", "foo-updated");
        } catch (final Throwable t) {
            fail("setProperty(foo) threw " + t);
    }
    }

    public void testSetPublicSubBean_of_PackageBean_2_oe() {

        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("foo-start");
        bean.setBar("bar-start");

        try {
            PropertyUtils.setProperty(bean, "foo", "foo-updated");
        } catch (final Throwable t) {
        }
        assertEquals("foo property", "foo-updated", bean.getFoo());
    }

    public void testSetPublicSubBean_of_PackageBean_3_oe() {

        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("foo-start");
        bean.setBar("bar-start");

        try {
            PropertyUtils.setProperty(bean, "foo", "foo-updated");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setProperty(bean, "bar", "bar-updated");
        } catch (final Throwable t) {
            fail("setProperty(bar) threw " + t);
    }
    }

    public void testSetPublicSubBean_of_PackageBean_4_oe() {

        final PublicSubBean bean = new PublicSubBean();
        bean.setFoo("foo-start");
        bean.setBar("bar-start");

        try {
            PropertyUtils.setProperty(bean, "foo", "foo-updated");
        } catch (final Throwable t) {
        }

        try {
            PropertyUtils.setProperty(bean, "bar", "bar-updated");
        } catch (final Throwable t) {
        }
        assertEquals("bar property", "bar-updated", bean.getBar());
    }

    public void testNestedPropertyKeyOrIndexOnBeanImplementingMap_1_oe() throws Exception {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        final HashMap<String, Object> submap = new HashMap<String, Object>();
        final BetaBean betaBean1 = new BetaBean("test1");
        final BetaBean betaBean2 = new BetaBean("test2");

        PropertyUtils.setNestedProperty(map, "submap", submap);

        PropertyUtils.setNestedProperty(map, "submap.beta1", betaBean1);
        assertEquals("Unexpected keys in map", "submap", keysToString(map));
    }

    public void testNestedPropertyKeyOrIndexOnBeanImplementingMap_2_oe() throws Exception {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        final HashMap<String, Object> submap = new HashMap<String, Object>();
        final BetaBean betaBean1 = new BetaBean("test1");
        final BetaBean betaBean2 = new BetaBean("test2");

        PropertyUtils.setNestedProperty(map, "submap", submap);

        PropertyUtils.setNestedProperty(map, "submap.beta1", betaBean1);
        assertEquals("Unexpected keys in submap", "beta1", keysToString(submap));
    }

    public void testNestedPropertyKeyOrIndexOnBeanImplementingMap_4_oe() throws Exception {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        final HashMap<String, Object> submap = new HashMap<String, Object>();
        final BetaBean betaBean1 = new BetaBean("test1");
        final BetaBean betaBean2 = new BetaBean("test2");

        PropertyUtils.setNestedProperty(map, "submap", submap);

        PropertyUtils.setNestedProperty(map, "submap.beta1", betaBean1);

        try {
            PropertyUtils.setNestedProperty(map, "submap(beta2)", betaBean2);

        } catch(final IllegalArgumentException ex) {
            final int index = ex.getMessage().indexOf(
                    "Indexed or mapped properties are not supported");
            assertTrue("Unexpected exception message", index>=0);
    }
    }

    public void testNestedPropertyKeyOrIndexOnBeanImplementingMap_6_oe() throws Exception {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        final HashMap<String, Object> submap = new HashMap<String, Object>();
        final BetaBean betaBean1 = new BetaBean("test1");
        final BetaBean betaBean2 = new BetaBean("test2");

        PropertyUtils.setNestedProperty(map, "submap", submap);

        PropertyUtils.setNestedProperty(map, "submap.beta1", betaBean1);

        try {
            PropertyUtils.setNestedProperty(map, "submap(beta2)", betaBean2);

        } catch(final IllegalArgumentException ex) {
            final int index = ex.getMessage().indexOf(
                    "Indexed or mapped properties are not supported");
        }

        try {
            PropertyUtils.getNestedProperty(map, "submap[3]");

        } catch(final IllegalArgumentException ex) {
            final int index = ex.getMessage().indexOf(
                    "Indexed or mapped properties are not supported");
            assertTrue("Unexpected exception message", index>=0);
    }
    }

    public void testMapExtensionDefault_1_oe() throws Exception {
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");
        assertEquals("Set property direct failed", "bean value", bean.getUnusuallyNamedProperty());
    }

    public void testMapExtensionDefault_2_oe() throws Exception {
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");
        assertNull("Get on unset map property failed",PropertyUtils.getNestedProperty(bean,"unusuallyNamedProperty"));
    }

    public void testMapExtensionDefault_3_oe() throws Exception {
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");

        PropertyUtils.setSimpleProperty(bean, "unusuallyNamedProperty", "new value");
        assertEquals("Set property on map failed (1)", "new value", bean.getUnusuallyNamedProperty());
    }

    public void testMapExtensionDefault_4_oe() throws Exception {
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");

        PropertyUtils.setSimpleProperty(bean, "unusuallyNamedProperty", "new value");
        assertNull("Get on unset map property failed",PropertyUtils.getNestedProperty(bean,"unusuallyNamedProperty"));
    }

    public void testMapExtensionDefault_5_oe() throws Exception {
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");

        PropertyUtils.setSimpleProperty(bean, "unusuallyNamedProperty", "new value");

        PropertyUtils.setProperty(bean, "unusuallyNamedProperty", "next value");
        assertEquals("setNestedProperty on map not visible to getNestedProperty","next value",PropertyUtils.getNestedProperty(bean,"unusuallyNamedProperty"));
    }

    public void testMapExtensionDefault_6_oe() throws Exception {
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");

        PropertyUtils.setSimpleProperty(bean, "unusuallyNamedProperty", "new value");

        PropertyUtils.setProperty(bean, "unusuallyNamedProperty", "next value");
        assertEquals("Set nested property on map unexpected affected simple property","new value",bean.getUnusuallyNamedProperty());
    }

    public void testMapExtensionCustom_1_oe() throws Exception {
        final PropsFirstPropertyUtilsBean utilsBean = new PropsFirstPropertyUtilsBean();
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");
        assertEquals("Set property direct failed", "bean value", bean.getUnusuallyNamedProperty());
    }

    public void testMapExtensionCustom_2_oe() throws Exception {
        final PropsFirstPropertyUtilsBean utilsBean = new PropsFirstPropertyUtilsBean();
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");

        utilsBean.setSimpleProperty(bean, "unusuallyNamedProperty", "new value");
        assertEquals("Set property on map failed (1)", "new value", bean.getUnusuallyNamedProperty());
    }

    public void testMapExtensionCustom_3_oe() throws Exception {
        final PropsFirstPropertyUtilsBean utilsBean = new PropsFirstPropertyUtilsBean();
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");

        utilsBean.setSimpleProperty(bean, "unusuallyNamedProperty", "new value");

        utilsBean.setProperty(bean, "unusuallyNamedProperty", "next value");
        assertEquals("Set property on map failed (2)", "next value", bean.getUnusuallyNamedProperty());
    }

    public void testMapExtensionCustom_4_oe() throws Exception {
        final PropsFirstPropertyUtilsBean utilsBean = new PropsFirstPropertyUtilsBean();
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");

        utilsBean.setSimpleProperty(bean, "unusuallyNamedProperty", "new value");

        utilsBean.setProperty(bean, "unusuallyNamedProperty", "next value");
        assertEquals("setNestedProperty on non-simple property failed","next value",utilsBean.getNestedProperty(bean,"unusuallyNamedProperty"));
    }

    public void testMapExtensionCustom_5_oe() throws Exception {
        final PropsFirstPropertyUtilsBean utilsBean = new PropsFirstPropertyUtilsBean();
        final ExtendMapBean bean = new ExtendMapBean();

        bean.setUnusuallyNamedProperty("bean value");

        utilsBean.setSimpleProperty(bean, "unusuallyNamedProperty", "new value");

        utilsBean.setProperty(bean, "unusuallyNamedProperty", "next value");

        utilsBean.setProperty(bean, "mapProperty", "value1");
        assertEquals("setNestedProperty on non-simple property failed","value1",utilsBean.getNestedProperty(bean,"mapProperty"));
    }

    public void testExceptionFromInvoke_1_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            return;
        }
        try {
            PropertyUtils.setSimpleProperty(bean, "intProperty","XXX");
        } catch(final IllegalArgumentException t) {
            final Throwable cause = (Throwable)PropertyUtils.getProperty(t, "cause");
            assertNotNull("Cause not found", cause);
    }
    }

    public void testExceptionFromInvoke_2_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            return;
        }
        try {
            PropertyUtils.setSimpleProperty(bean, "intProperty","XXX");
        } catch(final IllegalArgumentException t) {
            final Throwable cause = (Throwable)PropertyUtils.getProperty(t, "cause");
            assertTrue("Expected cause to be IllegalArgumentException,but was: " + cause.getClass(),cause instanceof IllegalArgumentException);
    }
    }

    public void testExceptionFromInvoke_3_oe() throws Exception {
        if (BeanUtilsTestCase.isPre14JVM()) {
            return;
        }
        try {
            PropertyUtils.setSimpleProperty(bean, "intProperty","XXX");
        } catch(final IllegalArgumentException t) {
            final Throwable cause = (Throwable)PropertyUtils.getProperty(t, "cause");
        } catch(final Throwable t) {
            fail("Expected IllegalArgumentException, but threw " + t);
    }
    }

    public void testCustomIntrospection_1_oe() {
        final PropertyDescriptor[] desc1 = PropertyUtils
                .getPropertyDescriptors(AlphaBean.class);
        PropertyDescriptor nameDescriptor = findNameDescriptor(desc1);
        assertNotNull("No write method", nameDescriptor.getWriteMethod());
    }

    public void testCustomIntrospection_2_oe() {
        final PropertyDescriptor[] desc1 = PropertyUtils
                .getPropertyDescriptors(AlphaBean.class);
        PropertyDescriptor nameDescriptor = findNameDescriptor(desc1);

        final BeanIntrospector bi = new BeanIntrospector() {
            public void introspect(final IntrospectionContext icontext)
                    throws IntrospectionException {
                final Set<String> names = icontext.propertyNames();
                final PropertyDescriptor[] newDescs = new PropertyDescriptor[names
                        .size()];
                int idx = 0;
                for (final Iterator<String> it = names.iterator(); it.hasNext(); idx++) {
                    final String propName = it.next();
                    final PropertyDescriptor pd = icontext
                            .getPropertyDescriptor(propName);
                    newDescs[idx] = new PropertyDescriptor(pd.getName(),
                            pd.getReadMethod(), null);
                }
                icontext.addPropertyDescriptors(newDescs);
            }
        };
        PropertyUtils.clearDescriptors();
        PropertyUtils.addBeanIntrospector(bi);
        final PropertyDescriptor[] desc2 = PropertyUtils
                .getPropertyDescriptors(AlphaBean.class);
        assertEquals("Different number of properties",desc1.length,desc2.length);
    }

    public void testCustomIntrospection_3_oe() {
        final PropertyDescriptor[] desc1 = PropertyUtils
                .getPropertyDescriptors(AlphaBean.class);
        PropertyDescriptor nameDescriptor = findNameDescriptor(desc1);

        final BeanIntrospector bi = new BeanIntrospector() {
            public void introspect(final IntrospectionContext icontext)
                    throws IntrospectionException {
                final Set<String> names = icontext.propertyNames();
                final PropertyDescriptor[] newDescs = new PropertyDescriptor[names
                        .size()];
                int idx = 0;
                for (final Iterator<String> it = names.iterator(); it.hasNext(); idx++) {
                    final String propName = it.next();
                    final PropertyDescriptor pd = icontext
                            .getPropertyDescriptor(propName);
                    newDescs[idx] = new PropertyDescriptor(pd.getName(),
                            pd.getReadMethod(), null);
                }
                icontext.addPropertyDescriptors(newDescs);
            }
        };
        PropertyUtils.clearDescriptors();
        PropertyUtils.addBeanIntrospector(bi);
        final PropertyDescriptor[] desc2 = PropertyUtils
                .getPropertyDescriptors(AlphaBean.class);
        nameDescriptor = findNameDescriptor(desc2);
        assertNull("Got a write method", nameDescriptor.getWriteMethod());
    }

    public void testCustomIntrospectionEx_1_oe() {
        final BeanIntrospector bi = new BeanIntrospector() {
            public void introspect(final IntrospectionContext icontext)
                    throws IntrospectionException {
                throw new IntrospectionException("TestException");
            }
        };
        PropertyUtils.clearDescriptors();
        PropertyUtils.addBeanIntrospector(bi);
        final PropertyDescriptor[] desc = PropertyUtils
                .getPropertyDescriptors(AlphaBean.class);
        assertNotNull("Introspection did not work", findNameDescriptor(desc));
    }

    public void testRemoveBeanIntrospector_1_oe() {
        assertTrue("Wrong result",PropertyUtils .removeBeanIntrospector(DefaultBeanIntrospector.INSTANCE));
    }

    public void testResetBeanIntrospectors_1_oe() {
        assertTrue("Wrong result",PropertyUtils.removeBeanIntrospector(DefaultBeanIntrospector.INSTANCE));
    }

    public void testResetBeanIntrospectors_2_oe() {
        PropertyUtils.resetBeanIntrospectors();
        final PropertyDescriptor[] desc = PropertyUtils.getPropertyDescriptors(AlphaBean.class);
        assertTrue("Got no descriptors", desc.length > 0);
    }

}
