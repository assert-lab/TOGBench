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


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Test accessing DynaBeans transparently via PropertyUtils.
 *
 * @version $Id$
 */

public class DynaPropertyUtilsTestCase_OE25Dev extends TestCase {


    // ----------------------------------------------------- Instance Variables


    /**
     * The basic test bean for each test.
     */
    protected DynaBean bean = null;


    /**
     * The set of properties that should be described.
     */
    protected String describes[] =
    { "booleanProperty",
      "booleanSecond",
      "doubleProperty",
      "floatProperty",
      "intArray",
      "intIndexed",
      "intProperty",
      "listIndexed",
      "longProperty",
      "mappedObjects",
      "mappedProperty",
      "mappedIntProperty",
      "nested",
      "nullProperty",
      //      "readOnlyProperty",
      "shortProperty",
      "stringArray",
      "stringIndexed",
      "stringProperty"
    };


    /**
     * The nested bean pointed at by the "nested" property.
     */
    protected TestBean nested = null;


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public DynaPropertyUtilsTestCase_OE25Dev(final String name) {

        super(name);

    }


    // --------------------------------------------------- Overall Test Methods


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
        final HashMap<String, Object> mapProperty = new HashMap<String, Object>();
        mapProperty.put("First Key", "First Value");
        mapProperty.put("Second Key", "Second Value");
        bean.set("mapProperty", mapProperty);
        final HashMap<String, Object> mappedObjects = new HashMap<String, Object>();
        mappedObjects.put("First Key", "First Value");
        mappedObjects.put("Second Key", "Second Value");
        bean.set("mappedObjects", mappedObjects);
        final HashMap<String, Object> mappedProperty = new HashMap<String, Object>();
        mappedProperty.put("First Key", "First Value");
        mappedProperty.put("Second Key", "Second Value");
        bean.set("mappedProperty", mappedProperty);
        final HashMap<String, Integer> mappedIntProperty = new HashMap<String, Integer>();
        mappedIntProperty.put("One", new Integer(1));
        mappedIntProperty.put("Two", new Integer(2));
        bean.set("mappedIntProperty", mappedIntProperty);
        nested = new TestBean();
        bean.set("nested", nested);
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

        return (new TestSuite(DynaPropertyUtilsTestCase.class));

    }


    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {

        bean = null;
        nested = null;

    }



    // ------------------------------------------------ Individual Test Methods


    /**
     * Test copyProperties() when the origin is a a <code>Map</code>.
     */


    /**
     * Test the describe() method.
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
     * Test getSimpleProperty on a short property.
     */


    /**
     * Test getSimpleProperty on a String property.
     */


    /**
     * Negative test getSimpleProperty on an unknown property.
     */


    /**
     * Corner cases on setIndexedProperty invalid arguments.
     */


    /**
     * Positive and negative tests on setIndexedProperty valid arguments.
     */


    /**
     * Corner cases on getMappedProperty invalid arguments.
     */


    /**
     * Positive and negative tests on setMappedProperty valid arguments.
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


    /**
     * Test setNestedProperty on a short property.
     */


    /**
     * Test setNestedProperty on a String property.
     */


    /**
     * Test setNestedProperty on an unknown property name.
     */


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
     * Test setSimpleProperty on a short property.
     */


    /**
     * Test setSimpleProperty on a String property.
     */


    /**
     * Test setSimpleProperty on an unknown property name.
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
                            new DynaProperty("dupProperty", stringArray.getClass()),
                            new DynaProperty("floatProperty", Float.TYPE),
                            new DynaProperty("intArray", intArray.getClass()),
                            new DynaProperty("intIndexed", intArray.getClass()),
                            new DynaProperty("intProperty", Integer.TYPE),
                            new DynaProperty("listIndexed", List.class),
                            new DynaProperty("longProperty", Long.TYPE),
                            new DynaProperty("mapProperty", Map.class),
                            new DynaProperty("mappedObjects", Map.class),
                            new DynaProperty("mappedProperty", Map.class),
                            new DynaProperty("mappedIntProperty", Map.class),
                            new DynaProperty("nested", TestBean.class),
                            new DynaProperty("nullProperty", String.class),
                            new DynaProperty("shortProperty", Short.TYPE),
                            new DynaProperty("stringArray", stringArray.getClass()),
                            new DynaProperty("stringIndexed", stringArray.getClass()),
                            new DynaProperty("stringProperty", String.class),
                        });
        return (dynaClass);

    }


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
            // removed other assertion
        }

        // Scalar properties
        assertEquals("booleanProperty", false, ((Boolean) bean.get("booleanProperty")).booleanValue());
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        assertEquals("doubleProperty", 333.0, ((Double) bean.get("doubleProperty")).doubleValue(), 0.005);
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        assertEquals("floatProperty", (float) 222.0, ((Float) bean.get("floatProperty")).floatValue(), (float) 0.005);
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("intProperty", 111, ((Integer) bean.get("intProperty")).intValue());
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("longProperty", 444, ((Long) bean.get("longProperty")).longValue());
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("shortProperty", (short) 555, ((Short) bean.get("shortProperty")).shortValue());
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("stringProperty", "New String Property", (String) bean.get("stringProperty"));
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
        // removed other assertion
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
        // removed other assertion
        // removed other assertion
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = (int[]) bean.get("intArray");
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = (int[]) bean.get("intArray");
        // removed other assertion
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = (int[]) bean.get("intArray");
        // removed other assertion
        // removed other assertion
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = (int[]) bean.get("intArray");
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = (String[]) bean.get("dupProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = (int[]) bean.get("intArray");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            assertTrue("Property '" + describe + "' is present", map.containsKey(describe));
    }
    }

    public void testDescribe_3_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            // removed other assertion
        }
        assertTrue("Property 'writeOnlyProperty' is not present", !map.containsKey("writeOnlyProperty"));
    }

    public void testDescribe_4_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            // removed other assertion
        }
        // removed other assertion

        // Verify the values of scalar properties
        assertEquals("Value of 'booleanProperty'", Boolean.TRUE, map.get("booleanProperty"));
    }

    public void testDescribe_5_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            // removed other assertion
        }
        // removed other assertion

        // Verify the values of scalar properties
        // removed other assertion
        assertEquals("Value of 'doubleProperty'", new Double(321.0), map.get("doubleProperty"));
    }

    public void testDescribe_6_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            // removed other assertion
        }
        // removed other assertion

        // Verify the values of scalar properties
        // removed other assertion
        // removed other assertion
        assertEquals("Value of 'floatProperty'", new Float((float) 123.0), map.get("floatProperty"));
    }

    public void testDescribe_7_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            // removed other assertion
        }
        // removed other assertion

        // Verify the values of scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Value of 'intProperty'", new Integer(123), map.get("intProperty"));
    }

    public void testDescribe_8_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            // removed other assertion
        }
        // removed other assertion

        // Verify the values of scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Value of 'longProperty'", new Long(321), map.get("longProperty"));
    }

    public void testDescribe_9_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            // removed other assertion
        }
        // removed other assertion

        // Verify the values of scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Value of 'shortProperty'", new Short((short) 987), map.get("shortProperty"));
    }

    public void testDescribe_10_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            // removed other assertion
        }
        // removed other assertion

        // Verify the values of scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Value of 'stringProperty'", "This is a string", (String) map.get("stringProperty"));
    }

    public void testGetIndexedArguments_2_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetIndexedArguments_4_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetIndexedArguments_6_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testGetIndexedArguments_8_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 4");
    }
    }

    public void testGetIndexedArguments_10_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 5");
    }
    }

    public void testGetIndexedArguments_12_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetIndexedArguments_14_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetIndexedArguments_16_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intIndexed[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testGetIndexedArguments_18_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intIndexed[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 4");
    }
    }

    public void testGetIndexedArguments_20_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intArray", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intArray[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intArray");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.getIndexedProperty(null, "intIndexed", 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, null, 0);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.getIndexedProperty(null,
                    "intIndexed[0]");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "[0]");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getIndexedProperty(bean, "intIndexed");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 5");
    }
    }

    public void testGetIndexedValues_4_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
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

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
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

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
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

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
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

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("stringIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_24_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("intArray " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_28_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("intIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_32_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("listIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_36_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("stringArray " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_40_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                fail("stringIndexed " + i + " threw " + t);
    }
    }
    }

    public void testGetIndexedValues_42_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_44_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_46_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_48_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_50_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_52_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_54_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_56_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_58_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetIndexedValues_60_oe() {

        Object value = null;

        // Use explicit key argument

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "intIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "listIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringArray", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean, "stringIndexed", i);
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Use key expression

        for (int i = 0; i < 5; i++) {

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "intIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "listIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringArray[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

            try {
                value =
                        PropertyUtils.getIndexedProperty(bean,
                                "stringIndexed[" + i + "]");
                // removed other assertion
                // removed other assertion
                // removed other assertion
            } catch (final Throwable t) {
                // removed other assertion
            }

        }

        // Index out of bounds tests

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", -1);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 5);
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringIndexed", -1);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringIndexed", 5);
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testGetMappedArguments_2_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetMappedArguments_4_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetMappedArguments_6_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty", null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testGetMappedArguments_8_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty", null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression

        try {
            PropertyUtils.getMappedProperty(null,
                    "mappedProperty(First Key)");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 4");
    }
    }

    public void testGetMappedArguments_10_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty", null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression

        try {
            PropertyUtils.getMappedProperty(null,
                    "mappedProperty(First Key)");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, "(Second Key)");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 5");
    }
    }

    public void testGetMappedArguments_12_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.getMappedProperty(null, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, null, "First Key");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty", null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression

        try {
            PropertyUtils.getMappedProperty(null,
                    "mappedProperty(First Key)");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, "(Second Key)");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getMappedProperty(bean, "mappedProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 6");
    }
    }

    public void testGetMappedPeriods_1_oe() {

        bean.set("mappedProperty", "key.with.a.dot", "Special Value");
        assertEquals("Can retrieve directly", "Special Value", (String) bean.get("mappedProperty", "key.with.a.dot"));
    }

    public void testGetMappedPeriods_3_oe() {

        bean.set("mappedProperty", "key.with.a.dot", "Special Value");
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedPeriods_5_oe() {

        bean.set("mappedProperty", "key.with.a.dot", "Special Value");
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }
        try {
            // removed other assertion
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedPeriods_6_oe() {

        bean.set("mappedProperty", "key.with.a.dot", "Special Value");
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }

        bean.set("mappedObjects", "nested.property", new TestBean());
        assertNotNull("Can retrieve directly", bean.get("mappedObjects", "nested.property"));
    }

    public void testGetMappedPeriods_8_oe() {

        bean.set("mappedProperty", "key.with.a.dot", "Special Value");
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }

        bean.set("mappedObjects", "nested.property", new TestBean());
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedSlashes_1_oe() {

        bean.set("mappedProperty", "key/with/a/slash", "Special Value");
        assertEquals("Can retrieve directly", "Special Value", bean.get("mappedProperty", "key/with/a/slash"));
    }

    public void testGetMappedSlashes_3_oe() {

        bean.set("mappedProperty", "key/with/a/slash", "Special Value");
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedSlashes_5_oe() {

        bean.set("mappedProperty", "key/with/a/slash", "Special Value");
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }
        try {
            // removed other assertion
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedSlashes_6_oe() {

        bean.set("mappedProperty", "key/with/a/slash", "Special Value");
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }

        bean.set("mappedObjects", "nested/property", new TestBean());
        assertNotNull("Can retrieve directly", bean.get("mappedObjects", "nested/property"));
    }

    public void testGetMappedSlashes_8_oe() {

        bean.set("mappedProperty", "key/with/a/slash", "Special Value");
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }
        try {
            // removed other assertion
        } catch (final Exception e) {
            // removed other assertion
        }

        bean.set("mappedObjects", "nested/property", new TestBean());
        // removed other assertion
        try {
            // removed other assertion
        } catch (final Exception e) {
            fail("Thew exception: " + e);
    }
    }

    public void testGetMappedValues_2_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding first value threw " + t);
    }
    }

    public void testGetMappedValues_4_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding second value threw " + t);
    }
    }

    public void testGetMappedValues_6_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding third value threw " + t);
    }
    }

    public void testGetMappedValues_8_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding first value threw " + t);
    }
    }

    public void testGetMappedValues_10_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding second value threw " + t);
    }
    }

    public void testGetMappedValues_12_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Third Key)");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding third value threw " + t);
    }
    }

    public void testGetMappedValues_14_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Third Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with dotted syntax

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.First Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding first value threw " + t);
    }
    }

    public void testGetMappedValues_16_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Third Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with dotted syntax

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding second value threw " + t);
    }
    }

    public void testGetMappedValues_18_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Third Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(First Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Second Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Third Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with dotted syntax

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.First Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Second Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Third Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding third value threw " + t);
    }
    }

    public void testGetNestedArguments_2_oe() {

        try {
            PropertyUtils.getNestedProperty(null, "stringProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetNestedArguments_4_oe() {

        try {
            PropertyUtils.getNestedProperty(null, "stringProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getNestedProperty(bean, null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetNestedBoolean_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.booleanProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedBoolean_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.booleanProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetNestedBoolean_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.booleanProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetNestedBoolean_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.booleanProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedDouble_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.doubleProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedDouble_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.doubleProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetNestedDouble_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.doubleProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetNestedDouble_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.doubleProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedFloat_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.floatProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedFloat_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.floatProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetNestedFloat_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.floatProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetNestedFloat_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.floatProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedInt_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.intProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedInt_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.intProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetNestedInt_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.intProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetNestedInt_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.intProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedLong_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.longProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedLong_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.longProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetNestedLong_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.longProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetNestedLong_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.longProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedReadOnly_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.readOnlyProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedReadOnly_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.readOnlyProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetNestedReadOnly_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.readOnlyProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetNestedReadOnly_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.readOnlyProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedShort_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.shortProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedShort_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.shortProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetNestedShort_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.shortProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetNestedShort_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.shortProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedString_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.stringProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedString_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.stringProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetNestedString_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.stringProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetNestedString_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getNestedProperty
                    (bean, "nested.stringProperty");
            // removed other assertion
            // removed other assertion
            final TestBean nested = (TestBean) bean.get("nested");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedUnknown_2_oe() {

        try {
            PropertyUtils.getNestedProperty(bean, "nested.unknown");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedUnknown_3_oe() {

        try {
            PropertyUtils.getNestedProperty(bean, "nested.unknown");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetNestedUnknown_4_oe() {

        try {
            PropertyUtils.getNestedProperty(bean, "nested.unknown");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleArguments_2_oe() {

        try {
            PropertyUtils.getSimpleProperty(null, "stringProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testGetSimpleArguments_4_oe() {

        try {
            PropertyUtils.getSimpleProperty(null, "stringProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.getSimpleProperty(bean, null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testGetSimpleBoolean_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "booleanProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleBoolean_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "booleanProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetSimpleBoolean_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "booleanProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleBoolean_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "booleanProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleDouble_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "doubleProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleDouble_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "doubleProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetSimpleDouble_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "doubleProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleDouble_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "doubleProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleFloat_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "floatProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleFloat_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "floatProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetSimpleFloat_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "floatProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleFloat_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "floatProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleIndexed_2_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean,
                    "intIndexed[0]");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleIndexed_3_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean,
                    "intIndexed[0]");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Correct result for this test
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleIndexed_4_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean,
                    "intIndexed[0]");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Correct result for this test
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleInt_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "intProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleInt_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "intProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetSimpleInt_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "intProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleInt_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "intProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleLong_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "longProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleLong_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "longProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetSimpleLong_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "longProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleLong_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "longProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleNested_2_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean,
                    "nested.stringProperty");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleNested_3_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean,
                    "nested.stringProperty");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Correct result for this test
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleNested_4_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean,
                    "nested.stringProperty");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Correct result for this test
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleShort_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "shortProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleShort_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "shortProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetSimpleShort_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "shortProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleShort_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "shortProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleString_4_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "stringProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleString_5_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "stringProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetSimpleString_6_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "stringProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleString_7_oe() {

        try {
            final Object value =
                    PropertyUtils.getSimpleProperty(bean,
                            "stringProperty");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleUnknown_2_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean, "unknown");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleUnknown_3_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean, "unknown");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testGetSimpleUnknown_4_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean, "unknown");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleUnknown_5_oe() {

        try {
            PropertyUtils.getSimpleProperty(bean, "unknown");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Correct result for this test
            assertEquals("Unknown property 'unknown' on dynaclass '" + bean.getDynaClass() + "'", e.getMessage() );
    }
    }

    public void testSetIndexedArguments_2_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetIndexedArguments_4_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetIndexedArguments_6_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testSetIndexedArguments_8_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 4");
    }
    }

    public void testSetIndexedArguments_10_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 5");
    }
    }

    public void testSetIndexedArguments_12_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetIndexedArguments_14_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetIndexedArguments_16_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intIndexed[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testSetIndexedArguments_18_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intIndexed[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 4");
    }
    }

    public void testSetIndexedArguments_20_oe() {

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intArray", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intArray[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intArray",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(null, "intIndexed", 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, null, 0,
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(null,
                    "intIndexed[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "[0]",
                    new Integer(1));
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean, "intIndexed",
                    new Integer(1));
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 5");
    }
    }

    public void testSetIndexedValues_4_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_8_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_12_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_16_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_20_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_24_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_28_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_32_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_36_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_40_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetIndexedValues_42_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_44_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_46_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_48_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_50_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_52_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_54_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", -1,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_56_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", -1,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 5,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_58_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", -1,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 5,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringIndexed", -1,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetIndexedValues_60_oe() {

        Object value = null;

        // Use explicit index argument

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 0,
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray", 0);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 1,
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed", 1);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 2,
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 2);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 3,
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray", 3);
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use index expression

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray[4]",
                    new Integer(1));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intArray[4]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed[3]",
                    new Integer(11));
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "intIndexed[3]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "listIndexed[1]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[1]",
                    "New Value 2");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[2]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray[0]",
                    "New Value 3");
            value =
                    PropertyUtils.getIndexedProperty(bean,
                            "stringArray[0]");
            // removed other assertion
            // removed other assertion
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Index out of bounds tests

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intArray", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", -1,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "intIndexed", 5,
                    new Integer(0));
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", 5,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "listIndexed", -1,
                    "New String");
            // removed other assertion
        } catch (final IndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", -1,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringArray", 5,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringIndexed", -1,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setIndexedProperty(bean,
                    "stringIndexed", 5,
                    "New String");
            // removed other assertion
        } catch (final ArrayIndexOutOfBoundsException t) {
            // Expected results
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of ArrayIndexOutOfBoundsException");
    }
    }

    public void testSetMappedArguments_2_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetMappedArguments_4_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetMappedArguments_6_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", null,
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 3");
    }
    }

    public void testSetMappedArguments_8_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", null,
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression

        try {
            PropertyUtils.setMappedProperty(null,
                    "mappedProperty(First Key)",
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 4");
    }
    }

    public void testSetMappedArguments_10_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", null,
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression

        try {
            PropertyUtils.setMappedProperty(null,
                    "mappedProperty(First Key)",
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "(Second Key)",
                    "Second Value");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of NoSuchMethodException 5");
    }
    }

    public void testSetMappedArguments_12_oe() {

        // Use explicit key argument

        try {
            PropertyUtils.setMappedProperty(null, "mappedProperty",
                    "First Key", "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, null, "First Key",
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty", null,
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression

        try {
            PropertyUtils.setMappedProperty(null,
                    "mappedProperty(First Key)",
                    "First Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "(Second Key)",
                    "Second Value");
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Third Value");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 6");
    }
    }

    public void testSetMappedValues_2_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding fourth value threw " + t);
    }
    }

    public void testSetMappedValues_3_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
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

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding fourth value threw " + t);
    }
    }

    public void testSetMappedValues_7_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding fifth value threw " + t);
    }
    }

    public void testSetMappedValues_8_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
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

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean,
                    "mappedProperty(Fifth Key)",
                    "Fifth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding fifth value threw " + t);
    }
    }

    public void testSetMappedValues_12_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean,
                    "mappedProperty(Fifth Key)",
                    "Fifth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with dotted expression

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Sixth Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding fifth value threw " + t);
    }
    }

    public void testSetMappedValues_13_oe() {

        Object value = null;

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean,
                    "mappedProperty(Fifth Key)",
                    "Fifth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with dotted expression

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Sixth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
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

        // Use explicit key argument

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean, "mappedProperty",
                    "Fourth Key", "Fourth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value = PropertyUtils.getMappedProperty(bean, "mappedProperty",
                    "Fourth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with parentheses

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setMappedProperty(bean,
                    "mappedProperty(Fifth Key)",
                    "Fifth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getMappedProperty(bean,
                            "mappedProperty(Fifth Key)");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        // Use key expression with dotted expression

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Sixth Key");
            // removed other assertion
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setNestedProperty(bean,
                    "mapProperty.Sixth Key",
                    "Sixth Value");
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            value =
                    PropertyUtils.getNestedProperty(bean,
                            "mapProperty.Sixth Key");
            // removed other assertion
        } catch (final Throwable t) {
            fail("Finding sixth value threw " + t);
    }
    }

    public void testSetNestedArguments_2_oe() {

        try {
            PropertyUtils.setNestedProperty(null, "stringProperty", "");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetNestedArguments_4_oe() {

        try {
            PropertyUtils.setNestedProperty(null, "stringProperty", "");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setNestedProperty(bean, null, "");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetNestedBoolean_2_oe() {

        try {
            final boolean oldValue = nested.getBooleanProperty();
            final boolean newValue = !oldValue;
            PropertyUtils.setNestedProperty(bean,
                    "nested.booleanProperty",
                    new Boolean(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedBoolean_3_oe() {

        try {
            final boolean oldValue = nested.getBooleanProperty();
            final boolean newValue = !oldValue;
            PropertyUtils.setNestedProperty(bean,
                    "nested.booleanProperty",
                    new Boolean(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedBoolean_4_oe() {

        try {
            final boolean oldValue = nested.getBooleanProperty();
            final boolean newValue = !oldValue;
            PropertyUtils.setNestedProperty(bean,
                    "nested.booleanProperty",
                    new Boolean(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedBoolean_5_oe() {

        try {
            final boolean oldValue = nested.getBooleanProperty();
            final boolean newValue = !oldValue;
            PropertyUtils.setNestedProperty(bean,
                    "nested.booleanProperty",
                    new Boolean(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedDouble_2_oe() {

        try {
            final double oldValue = nested.getDoubleProperty();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.doubleProperty",
                    new Double(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedDouble_3_oe() {

        try {
            final double oldValue = nested.getDoubleProperty();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.doubleProperty",
                    new Double(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedDouble_4_oe() {

        try {
            final double oldValue = nested.getDoubleProperty();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.doubleProperty",
                    new Double(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedDouble_5_oe() {

        try {
            final double oldValue = nested.getDoubleProperty();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.doubleProperty",
                    new Double(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedFloat_2_oe() {

        try {
            final float oldValue = nested.getFloatProperty();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.floatProperty",
                    new Float(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedFloat_3_oe() {

        try {
            final float oldValue = nested.getFloatProperty();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.floatProperty",
                    new Float(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedFloat_4_oe() {

        try {
            final float oldValue = nested.getFloatProperty();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.floatProperty",
                    new Float(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedFloat_5_oe() {

        try {
            final float oldValue = nested.getFloatProperty();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setNestedProperty(bean,
                    "nested.floatProperty",
                    new Float(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedInt_2_oe() {

        try {
            final int oldValue = nested.getIntProperty();
            final int newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.intProperty",
                    new Integer(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedInt_3_oe() {

        try {
            final int oldValue = nested.getIntProperty();
            final int newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.intProperty",
                    new Integer(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedInt_4_oe() {

        try {
            final int oldValue = nested.getIntProperty();
            final int newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.intProperty",
                    new Integer(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedInt_5_oe() {

        try {
            final int oldValue = nested.getIntProperty();
            final int newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.intProperty",
                    new Integer(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedLong_2_oe() {

        try {
            final long oldValue = nested.getLongProperty();
            final long newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.longProperty",
                    new Long(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedLong_3_oe() {

        try {
            final long oldValue = nested.getLongProperty();
            final long newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.longProperty",
                    new Long(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedLong_4_oe() {

        try {
            final long oldValue = nested.getLongProperty();
            final long newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.longProperty",
                    new Long(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedLong_5_oe() {

        try {
            final long oldValue = nested.getLongProperty();
            final long newValue = oldValue + 1;
            PropertyUtils.setNestedProperty(bean,
                    "nested.longProperty",
                    new Long(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedReadOnly_2_oe() {

        try {
            final String oldValue = nested.getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.readOnlyProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedReadOnly_3_oe() {

        try {
            final String oldValue = nested.getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.readOnlyProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedReadOnly_4_oe() {

        try {
            final String oldValue = nested.getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.readOnlyProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedShort_2_oe() {

        try {
            final short oldValue = nested.getShortProperty();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setNestedProperty(bean,
                    "nested.shortProperty",
                    new Short(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedShort_3_oe() {

        try {
            final short oldValue = nested.getShortProperty();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setNestedProperty(bean,
                    "nested.shortProperty",
                    new Short(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedShort_4_oe() {

        try {
            final short oldValue = nested.getShortProperty();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setNestedProperty(bean,
                    "nested.shortProperty",
                    new Short(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedShort_5_oe() {

        try {
            final short oldValue = nested.getShortProperty();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setNestedProperty(bean,
                    "nested.shortProperty",
                    new Short(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedString_2_oe() {

        try {
            final String oldValue = nested.getStringProperty();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.stringProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedString_3_oe() {

        try {
            final String oldValue = nested.getStringProperty();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.stringProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedString_4_oe() {

        try {
            final String oldValue = nested.getStringProperty();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.stringProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedString_5_oe() {

        try {
            final String oldValue = nested.getStringProperty();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.stringProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetNestedUnknown_2_oe() {

        try {
            final String newValue = "New String Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.unknown",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedUnknown_3_oe() {

        try {
            final String newValue = "New String Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.unknown",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedUnknown_4_oe() {

        try {
            final String newValue = "New String Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.unknown",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedWriteOnly_2_oe() {

        try {
            final String oldValue = nested.getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.writeOnlyProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetNestedWriteOnly_3_oe() {

        try {
            final String oldValue = nested.getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.writeOnlyProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetNestedWriteOnly_4_oe() {

        try {
            final String oldValue = nested.getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.writeOnlyProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetNestedWriteOnly_5_oe() {

        try {
            final String oldValue = nested.getWriteOnlyPropertyValue();
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setNestedProperty(bean,
                    "nested.writeOnlyProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleArguments_2_oe() {

        try {
            PropertyUtils.setSimpleProperty(null, "stringProperty", "");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 1");
    }
    }

    public void testSetSimpleArguments_4_oe() {

        try {
            PropertyUtils.setSimpleProperty(null, "stringProperty", "");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            // removed other assertion
        }

        try {
            PropertyUtils.setSimpleProperty(bean, null, "");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected response
        } catch (final Throwable t) {
            fail("Threw " + t + " instead of IllegalArgumentException 2");
    }
    }

    public void testSetSimpleBoolean_2_oe() {

        try {
            final boolean oldValue = ((Boolean) bean.get("booleanProperty")).booleanValue();
            final boolean newValue = !oldValue;
            PropertyUtils.setSimpleProperty(bean,
                    "booleanProperty",
                    new Boolean(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleBoolean_3_oe() {

        try {
            final boolean oldValue = ((Boolean) bean.get("booleanProperty")).booleanValue();
            final boolean newValue = !oldValue;
            PropertyUtils.setSimpleProperty(bean,
                    "booleanProperty",
                    new Boolean(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetSimpleBoolean_4_oe() {

        try {
            final boolean oldValue = ((Boolean) bean.get("booleanProperty")).booleanValue();
            final boolean newValue = !oldValue;
            PropertyUtils.setSimpleProperty(bean,
                    "booleanProperty",
                    new Boolean(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleBoolean_5_oe() {

        try {
            final boolean oldValue = ((Boolean) bean.get("booleanProperty")).booleanValue();
            final boolean newValue = !oldValue;
            PropertyUtils.setSimpleProperty(bean,
                    "booleanProperty",
                    new Boolean(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleDouble_2_oe() {

        try {
            final double oldValue = ((Double) bean.get("doubleProperty")).doubleValue();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "doubleProperty",
                    new Double(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleDouble_3_oe() {

        try {
            final double oldValue = ((Double) bean.get("doubleProperty")).doubleValue();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "doubleProperty",
                    new Double(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetSimpleDouble_4_oe() {

        try {
            final double oldValue = ((Double) bean.get("doubleProperty")).doubleValue();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "doubleProperty",
                    new Double(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleDouble_5_oe() {

        try {
            final double oldValue = ((Double) bean.get("doubleProperty")).doubleValue();
            final double newValue = oldValue + 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "doubleProperty",
                    new Double(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleFloat_2_oe() {

        try {
            final float oldValue = ((Float) bean.get("floatProperty")).floatValue();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "floatProperty",
                    new Float(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleFloat_3_oe() {

        try {
            final float oldValue = ((Float) bean.get("floatProperty")).floatValue();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "floatProperty",
                    new Float(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetSimpleFloat_4_oe() {

        try {
            final float oldValue = ((Float) bean.get("floatProperty")).floatValue();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "floatProperty",
                    new Float(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleFloat_5_oe() {

        try {
            final float oldValue = ((Float) bean.get("floatProperty")).floatValue();
            final float newValue = oldValue + (float) 1.0;
            PropertyUtils.setSimpleProperty(bean,
                    "floatProperty",
                    new Float(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleIndexed_2_oe() {

        try {
            PropertyUtils.setSimpleProperty(bean,
                    "stringIndexed[0]",
                    "New String Value");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleIndexed_3_oe() {

        try {
            PropertyUtils.setSimpleProperty(bean,
                    "stringIndexed[0]",
                    "New String Value");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Correct result for this test
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleIndexed_4_oe() {

        try {
            PropertyUtils.setSimpleProperty(bean,
                    "stringIndexed[0]",
                    "New String Value");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Correct result for this test
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleInt_2_oe() {

        try {
            final int oldValue = ((Integer) bean.get("intProperty")).intValue();
            final int newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "intProperty",
                    new Integer(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleInt_3_oe() {

        try {
            final int oldValue = ((Integer) bean.get("intProperty")).intValue();
            final int newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "intProperty",
                    new Integer(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetSimpleInt_4_oe() {

        try {
            final int oldValue = ((Integer) bean.get("intProperty")).intValue();
            final int newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "intProperty",
                    new Integer(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleInt_5_oe() {

        try {
            final int oldValue = ((Integer) bean.get("intProperty")).intValue();
            final int newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "intProperty",
                    new Integer(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleLong_2_oe() {

        try {
            final long oldValue = ((Long) bean.get("longProperty")).longValue();
            final long newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "longProperty",
                    new Long(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleLong_3_oe() {

        try {
            final long oldValue = ((Long) bean.get("longProperty")).longValue();
            final long newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "longProperty",
                    new Long(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetSimpleLong_4_oe() {

        try {
            final long oldValue = ((Long) bean.get("longProperty")).longValue();
            final long newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "longProperty",
                    new Long(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleLong_5_oe() {

        try {
            final long oldValue = ((Long) bean.get("longProperty")).longValue();
            final long newValue = oldValue + 1;
            PropertyUtils.setSimpleProperty(bean,
                    "longProperty",
                    new Long(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleNested_2_oe() {

        try {
            PropertyUtils.setSimpleProperty(bean,
                    "nested.stringProperty",
                    "New String Value");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleNested_3_oe() {

        try {
            PropertyUtils.setSimpleProperty(bean,
                    "nested.stringProperty",
                    "New String Value");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Correct result for this test
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleNested_4_oe() {

        try {
            PropertyUtils.setSimpleProperty(bean,
                    "nested.stringProperty",
                    "New String Value");
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Correct result for this test
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleShort_2_oe() {

        try {
            final short oldValue = ((Short) bean.get("shortProperty")).shortValue();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setSimpleProperty(bean,
                    "shortProperty",
                    new Short(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleShort_3_oe() {

        try {
            final short oldValue = ((Short) bean.get("shortProperty")).shortValue();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setSimpleProperty(bean,
                    "shortProperty",
                    new Short(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetSimpleShort_4_oe() {

        try {
            final short oldValue = ((Short) bean.get("shortProperty")).shortValue();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setSimpleProperty(bean,
                    "shortProperty",
                    new Short(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleShort_5_oe() {

        try {
            final short oldValue = ((Short) bean.get("shortProperty")).shortValue();
            short newValue = oldValue;
            newValue++;
            PropertyUtils.setSimpleProperty(bean,
                    "shortProperty",
                    new Short(newValue));
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleString_2_oe() {

        try {
            final String oldValue = (String) bean.get("stringProperty");
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setSimpleProperty(bean,
                    "stringProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleString_3_oe() {

        try {
            final String oldValue = (String) bean.get("stringProperty");
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setSimpleProperty(bean,
                    "stringProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetSimpleString_4_oe() {

        try {
            final String oldValue = (String) bean.get("stringProperty");
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setSimpleProperty(bean,
                    "stringProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleString_5_oe() {

        try {
            final String oldValue = (String) bean.get("stringProperty");
            final String newValue = oldValue + " Extra Value";
            PropertyUtils.setSimpleProperty(bean,
                    "stringProperty",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testSetSimpleUnknown_2_oe() {

        try {
            final String newValue = "New String Value";
            PropertyUtils.setSimpleProperty(bean,
                    "unknown",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testSetSimpleUnknown_3_oe() {

        try {
            final String newValue = "New String Value";
            PropertyUtils.setSimpleProperty(bean,
                    "unknown",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            fail("IllegalArgumentException");
    }
    }

    public void testSetSimpleUnknown_4_oe() {

        try {
            final String newValue = "New String Value";
            PropertyUtils.setSimpleProperty(bean,
                    "unknown",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetSimpleUnknown_5_oe() {

        try {
            final String newValue = "New String Value";
            PropertyUtils.setSimpleProperty(bean,
                    "unknown",
                    newValue);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            // Correct result for this test
            assertEquals("Unknown property 'unknown' on dynaclass '" + bean.getDynaClass() + "'", e.getMessage() );
    }
    }

}
