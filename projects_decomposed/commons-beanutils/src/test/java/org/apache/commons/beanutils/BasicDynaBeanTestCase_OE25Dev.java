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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * <p>Test Case for the <code>BasicDynaBean</code> implementation class.
 * These tests were based on the ones in <code>PropertyUtilsTestCase</code>
 * because the two classes provide similar levels of functionality.</p>
 *
 * @version $Id$
 */

public class BasicDynaBeanTestCase_OE25Dev extends TestCase {


    // ---------------------------------------------------- Instance Variables


    /**
     * The basic test bean for each test.
     */
    protected DynaBean bean = null;


    /**
     * The set of property names we expect to have returned when calling
     * <code>getDynaProperties()</code>.  You should update this list
     * when new properties are added to TestBean.
     */
    protected final static String[] properties = {
        "booleanProperty",
        "booleanSecond",
        "doubleProperty",
        "floatProperty",
        "intArray",
        "intIndexed",
        "intProperty",
        "listIndexed",
        "longProperty",
        "mappedProperty",
        "mappedIntProperty",
        "nullProperty",
        "shortProperty",
        "stringArray",
        "stringIndexed",
        "stringProperty",
    };


    // ---------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public BasicDynaBeanTestCase_OE25Dev(final String name) {

        super(name);

    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() throws Exception {

        // Instantiate a new DynaBean instance
        final DynaClass dynaClass = createDynaClass();
        bean = dynaClass.newInstance();

        // Initialize the DynaBean's property values (like TestBean)
        bean.set("booleanProperty", new Boolean(true));
        bean.set("booleanSecond", new Boolean(true));
        bean.set("doubleProperty", new Double(321.0));
        bean.set("floatProperty", new Float((float) 123.0));
        final int intArray[] = { 0, 10, 20, 30, 40 };
        bean.set("intArray", intArray);
        final int intIndexed[] = { 0, 10, 20, 30, 40 };
        bean.set("intIndexed", intIndexed);
        bean.set("intProperty", new Integer(123));
        final List<String> listIndexed = new ArrayList<String>();
        listIndexed.add("String 0");
        listIndexed.add("String 1");
        listIndexed.add("String 2");
        listIndexed.add("String 3");
        listIndexed.add("String 4");
        bean.set("listIndexed", listIndexed);
        bean.set("longProperty", new Long(321));
        final HashMap<String, String> mappedProperty = new HashMap<String, String>();
        mappedProperty.put("First Key", "First Value");
        mappedProperty.put("Second Key", "Second Value");
        bean.set("mappedProperty", mappedProperty);
        final HashMap<String, Integer> mappedIntProperty = new HashMap<String, Integer>();
        mappedIntProperty.put("One", new Integer(1));
        mappedIntProperty.put("Two", new Integer(2));
        bean.set("mappedIntProperty", mappedIntProperty);
        // Property "nullProperty" is not initialized, so it should return null
        bean.set("shortProperty", new Short((short) 987));
        final String stringArray[] =
                { "String 0", "String 1", "String 2", "String 3", "String 4" };
        bean.set("stringArray", stringArray);
        final String stringIndexed[] =
                { "String 0", "String 1", "String 2", "String 3", "String 4" };
        bean.set("stringIndexed", stringIndexed);
        bean.set("stringProperty", "This is a string");

    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {

        return (new TestSuite(BasicDynaBeanTestCase_OE25Dev.class));

    }


    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {

        bean = null;

    }



    // ------------------------------------------------ Individual Test Methods


    /**
     * Corner cases on getDynaProperty invalid arguments.
     */


    /**
     * Positive getDynaProperty on property <code>booleanProperty</code>.
     */
    public void testGetDescriptorBoolean() {

        testGetDescriptorBase("booleanProperty", Boolean.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>doubleProperty</code>.
     */
    public void testGetDescriptorDouble() {

        testGetDescriptorBase("doubleProperty", Double.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>floatProperty</code>.
     */
    public void testGetDescriptorFloat() {

        testGetDescriptorBase("floatProperty", Float.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>intProperty</code>.
     */
    public void testGetDescriptorInt() {

        testGetDescriptorBase("intProperty", Integer.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>longProperty</code>.
     */
    public void testGetDescriptorLong() {

        testGetDescriptorBase("longProperty", Long.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>booleanSecond</code>
     * that uses an "is" method as the getter.
     */
    public void testGetDescriptorSecond() {

        testGetDescriptorBase("booleanSecond", Boolean.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>shortProperty</code>.
     */
    public void testGetDescriptorShort() {

        testGetDescriptorBase("shortProperty", Short.TYPE);

    }


    /**
     * Positive getDynaProperty on property <code>stringProperty</code>.
     */
    public void testGetDescriptorString() {

        testGetDescriptorBase("stringProperty", String.class);

    }


    /**
     * Positive test for getDynaPropertys().  Each property name
     * listed in <code>properties</code> should be returned exactly once.
     */


    /**
     * Corner cases on getIndexedProperty invalid arguments.
     */


    /**
     * Positive and negative tests on getIndexedProperty valid arguments.
     */


    /**
     * Corner cases on getMappedProperty invalid arguments.
     */


    /**
     * Positive and negative tests on getMappedProperty valid arguments.
     */


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
     * Test getSimpleProperty on a int property.
     */


    /**
     * Test getSimpleProperty on a long property.
     */


    /**
     * Test getSimpleProperty on a short property.
     */


    /**
     * Test getSimpleProperty on a String property.
     */


    /**
     * Test <code>contains()</code> method for mapped properties.
     */


    /**
     * Test <code>remove()</code> method for mapped properties.
     */


    /**
     * Test serialization and deserialization.
     */


    /**
     * Corner cases on setIndexedProperty invalid arguments.
     */


    /**
     * Positive and negative tests on setIndexedProperty valid arguments.
     */


    /**
     * Positive and negative tests on setMappedProperty valid arguments.
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
     * Test setSimpleProperty on a int property.
     */


    /**
     * Test setSimpleProperty on a long property.
     */


    /**
     * Test setSimpleProperty on a short property.
     */


    /**
     * Test setSimpleProperty on a String property.
     */


    // ------------------------------------------------------ Protected Methods


    /**
     * Create and return a <code>DynaClass</code> instance for our test
     * <code>DynaBean</code>.
     */
    protected DynaClass createDynaClass() {

        final int intArray[] = new int[0];
        final String stringArray[] = new String[0];

        final DynaClass dynaClass = new BasicDynaClass
                ("TestDynaClass", null,
                        new DynaProperty[]{
                            new DynaProperty("booleanProperty", Boolean.TYPE),
                            new DynaProperty("booleanSecond", Boolean.TYPE),
                            new DynaProperty("doubleProperty", Double.TYPE),
                            new DynaProperty("floatProperty", Float.TYPE),
                            new DynaProperty("intArray", intArray.getClass()),
                            new DynaProperty("intIndexed", intArray.getClass()),
                            new DynaProperty("intProperty", Integer.TYPE),
                            new DynaProperty("listIndexed", List.class),
                            new DynaProperty("longProperty", Long.TYPE),
                            new DynaProperty("mappedProperty", Map.class),
                            new DynaProperty("mappedIntProperty", Map.class),
                            new DynaProperty("nullProperty", String.class),
                            new DynaProperty("shortProperty", Short.TYPE),
                            new DynaProperty("stringArray", stringArray.getClass()),
                            new DynaProperty("stringIndexed", stringArray.getClass()),
                            new DynaProperty("stringProperty", String.class),
                        });
        return (dynaClass);

    }


    /**
     * Base for testGetDescriptorXxxxx() series of tests.
     *
     * @param name Name of the property to be retrieved
     * @param type Expected class type of this property
     */
    protected void testGetDescriptorBase(final String name, final Class<?> type) {

        try {
            final DynaProperty descriptor =
                    bean.getDynaClass().getDynaProperty(name);
            assertNotNull("Got descriptor", descriptor);
            assertEquals("Got correct type", type, descriptor.getType());
        } catch (final Throwable t) {
            fail("Threw an exception: " + t);
        }

    }


    public void testGetDescriptorArguments_2_oe() {

        try {
            final DynaProperty descriptor =
                    bean.getDynaClass().getDynaProperty("unknown");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of returning null");
    }
    }

    public void testGetDescriptorArguments_4_oe() {

        try {
            final DynaProperty descriptor =
                    bean.getDynaClass().getDynaProperty("unknown");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.getDynaClass().getDynaProperty(null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException");
    }
    }

    public void testGetDescriptors_1_oe() {

        final DynaProperty pd[] = bean.getDynaClass().getDynaProperties();
        assertNotNull("Got descriptors", pd);
    }

    public void testGetDescriptors_2_oe() {

        final DynaProperty pd[] = bean.getDynaClass().getDynaProperties();
        // removed other assertion
        final int count[] = new int[properties.length];
        for (DynaProperty element : pd) {
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

        final DynaProperty pd[] = bean.getDynaClass().getDynaProperties();
        // removed other assertion
        final int count[] = new int[properties.length];
        for (DynaProperty element : pd) {
            final String name = element.getName();
            for (int j = 0; j < properties.length; j++) {
                if (name.equals(properties[j])) {
                    count[j]++;
                }
            }
        }
        for (int j = 0; j < properties.length; j++) {
            if (count[j] < 0) {
                // removed other assertion
            } else if (count[j] > 1) {
                fail("Duplicate property " + properties[j]);
    }
    }
    }

    public void testGetIndexedArguments_2_oe() {

        try {
            bean.get("intArray", -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_4_oe() {

        Object value = null;

        for (int i = 0; i < 5; i++) {

            try {
                value = bean.get("intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("intArray " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_8_oe() {

        Object value = null;

        for (int i = 0; i < 5; i++) {

            try {
                value = bean.get("intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("intIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_12_oe() {

        Object value = null;

        for (int i = 0; i < 5; i++) {

            try {
                value = bean.get("intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("listIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_16_oe() {

        Object value = null;

        for (int i = 0; i < 5; i++) {

            try {
                value = bean.get("intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("stringArray " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_20_oe() {

        Object value = null;

        for (int i = 0; i < 5; i++) {

            try {
                value = bean.get("intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value = bean.get("stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("stringIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetMappedArguments_2_oe() {


        try {
            final Object value = bean.get("mappedProperty", "unknown");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of returning null");
    }
    }

    public void testGetMappedValues_2_oe() {

        Object value = null;

        try {
            value = bean.get("mappedProperty", "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding first value threw " + t);
    }
    }

    public void testGetMappedValues_4_oe() {

        Object value = null;

        try {
            value = bean.get("mappedProperty", "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = bean.get("mappedProperty", "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding second value threw " + t);
    }
    }

    public void testGetMappedValues_6_oe() {

        Object value = null;

        try {
            value = bean.get("mappedProperty", "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = bean.get("mappedProperty", "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = bean.get("mappedProperty", "Third Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding third value threw " + t);
    }
    }

    public void testGetSimpleArguments_2_oe() {

        try {
            bean.get(null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException");
    }
    }

    public void testGetSimpleBoolean_4_oe() {

        try {
            final Object value = bean.get("booleanProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable e) {
            fail("Exception: " + e);
    }
    }

    public void testGetSimpleDouble_4_oe() {

        try {
            final Object value = bean.get("doubleProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testGetSimpleFloat_4_oe() {

        try {
            final Object value = bean.get("floatProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testGetSimpleInt_4_oe() {

        try {
            final Object value = bean.get("intProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testGetSimpleLong_4_oe() {

        try {
            final Object value = bean.get("longProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testGetSimpleShort_4_oe() {

        try {
            final Object value = bean.get("shortProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testGetSimpleString_4_oe() {

        try {
            final Object value = bean.get("stringProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testMappedContains_2_oe() {

        try {
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testMappedContains_4_oe() {

        try {
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }


        try {
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testMappedRemove_3_oe() {

        try {
            // removed other assertion
            bean.remove("mappedProperty", "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testMappedRemove_6_oe() {

        try {
            // removed other assertion
            bean.remove("mappedProperty", "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            // removed other assertion
            bean.remove("mappedProperty", "Unknown Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Exception: " + t);
    }
    }

    public void testSerialization_1_oe() {

        // Serialize the test bean
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(bean);
            oos.flush();
            oos.close();
        } catch (final Exception e) {
            fail("Exception during serialization: " + e);
    }
    }

    public void testSerialization_2_oe() {

        // Serialize the test bean
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(bean);
            oos.flush();
            oos.close();
        } catch (final Exception e) {
            // removed other assertion
        }

        // Deserialize the test bean
        try {
            bean = null;
            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final ObjectInputStream ois = new ObjectInputStream(bais);
            bean = (DynaBean) ois.readObject();
            bais.close();
        } catch (final Exception e) {
            fail("Exception during deserialization: " + e);
    }
    }

    public void testSerialization_3_oe() {

        // Serialize the test bean
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(bean);
            oos.flush();
            oos.close();
        } catch (final Exception e) {
            // removed other assertion
        }

        // Deserialize the test bean
        try {
            bean = null;
            final ByteArrayInputStream bais =
                new ByteArrayInputStream(baos.toByteArray());
            final ObjectInputStream ois = new ObjectInputStream(bais);
            bean = (DynaBean) ois.readObject();
            bais.close();
        } catch (final Exception e) {
            // removed other assertion
        }

        // Confirm property values
        testGetDescriptorArguments();
        testGetDescriptorBoolean();
        testGetDescriptorDouble();
        testGetDescriptorFloat();
        testGetDescriptorInt();
        testGetDescriptorLong();
        testGetDescriptorSecond();
        testGetDescriptorShort();
        testGetDescriptorString();
        testGetDescriptors();
        testGetIndexedArguments();
        testGetIndexedValues();
        testGetMappedArguments();
        testGetMappedValues();
        testGetSimpleArguments();
        testGetSimpleBoolean();
        testGetSimpleDouble();
        testGetSimpleFloat();
        testGetSimpleInt();
        testGetSimpleLong();
        testGetSimpleShort();
        testGetSimpleString();
        testMappedContains();
        testMappedRemove();

        // Ensure that we can create a new instance of the same DynaClass
        try {
            bean = bean.getDynaClass().newInstance();
        } catch (final Exception e) {
            fail("Exception creating new instance: " + e);
    }
    }

    public void testSetIndexedArguments_2_oe() {

        try {
            bean.set("intArray", -1, new Integer(0));
            // removed other assertion
        } catch (final IndexOutOfBoundsException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_4_oe() {

        Object value = null;

        try {
            bean.set("intArray", 0, new Integer(1));
            value = bean.get("intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_8_oe() {

        Object value = null;

        try {
            bean.set("intArray", 0, new Integer(1));
            value = bean.get("intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("intIndexed", 1, new Integer(11));
            value = bean.get("intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_12_oe() {

        Object value = null;

        try {
            bean.set("intArray", 0, new Integer(1));
            value = bean.get("intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("intIndexed", 1, new Integer(11));
            value = bean.get("intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("listIndexed", 2, "New Value 2");
            value = bean.get("listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_16_oe() {

        Object value = null;

        try {
            bean.set("intArray", 0, new Integer(1));
            value = bean.get("intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("intIndexed", 1, new Integer(11));
            value = bean.get("intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("listIndexed", 2, "New Value 2");
            value = bean.get("listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("stringArray", 3, "New Value 3");
            value = bean.get("stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_20_oe() {

        Object value = null;

        try {
            bean.set("intArray", 0, new Integer(1));
            value = bean.get("intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("intIndexed", 1, new Integer(11));
            value = bean.get("intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("listIndexed", 2, "New Value 2");
            value = bean.get("listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("stringArray", 3, "New Value 3");
            value = bean.get("stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("stringIndexed", 4, "New Value 4");
            value = bean.get("stringIndexed", 4);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetMappedValues_2_oe() {

        try {
            bean.set("mappedProperty", "First Key", "New First Value");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding fourth value threw " + t);
    }
    }

    public void testSetMappedValues_4_oe() {

        try {
            bean.set("mappedProperty", "First Key", "New First Value");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            bean.set("mappedProperty", "Fourth Key", "Fourth Value");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding fourth value threw " + t);
    }
    }

    public void testSetSimpleBoolean_2_oe() {

        try {
            final boolean oldValue =
                    ((Boolean) bean.get("booleanProperty")).booleanValue();
            final boolean newValue = !oldValue;
            bean.set("booleanProperty", new Boolean(newValue));
            // removed other assertion
        } catch (final Throwable e) {
            fail("Exception: " + e);
    }
    }

    public void testSetSimpleDouble_2_oe() {

        try {
            final double oldValue =
                    ((Double) bean.get("doubleProperty")).doubleValue();
            final double newValue = oldValue + 1.0;
            bean.set("doubleProperty", new Double(newValue));
            // removed other assertion
        } catch (final Throwable e) {
            fail("Exception: " + e);
    }
    }

    public void testSetSimpleFloat_2_oe() {

        try {
            final float oldValue =
                    ((Float) bean.get("floatProperty")).floatValue();
            final float newValue = oldValue + (float) 1.0;
            bean.set("floatProperty", new Float(newValue));
            // removed other assertion
        } catch (final Throwable e) {
            fail("Exception: " + e);
    }
    }

    public void testSetSimpleInt_2_oe() {

        try {
            final int oldValue =
                    ((Integer) bean.get("intProperty")).intValue();
            final int newValue = oldValue + 1;
            bean.set("intProperty", new Integer(newValue));
            // removed other assertion
        } catch (final Throwable e) {
            fail("Exception: " + e);
    }
    }

    public void testSetSimpleLong_2_oe() {

        try {
            final long oldValue =
                    ((Long) bean.get("longProperty")).longValue();
            final long newValue = oldValue + 1;
            bean.set("longProperty", new Long(newValue));
            // removed other assertion
        } catch (final Throwable e) {
            fail("Exception: " + e);
    }
    }

    public void testSetSimpleShort_2_oe() {

        try {
            final short oldValue =
                    ((Short) bean.get("shortProperty")).shortValue();
            final short newValue = (short) (oldValue + 1);
            bean.set("shortProperty", new Short(newValue));
            // removed other assertion
        } catch (final Throwable e) {
            fail("Exception: " + e);
    }
    }

    public void testSetSimpleString_2_oe() {

        try {
            final String oldValue = (String) bean.get("stringProperty");
            final String newValue = oldValue + " Extra Value";
            bean.set("stringProperty", newValue);
            // removed other assertion
        } catch (final Throwable e) {
            fail("Exception: " + e);
    }
    }

}
