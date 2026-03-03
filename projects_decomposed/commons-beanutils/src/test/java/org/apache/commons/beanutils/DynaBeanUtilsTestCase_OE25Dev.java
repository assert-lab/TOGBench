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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Test case for BeanUtils when the underlying bean is actually a DynaBean.
 *
 * @version $Id$
 */

public class DynaBeanUtilsTestCase_OE25Dev extends TestCase {


    // ----------------------------------------------------- Instance Variables


    /**
     * The basic test bean for each test.
     */
    protected DynaBean bean = null;


    /**
     * The nested bean pointed at by the "nested" property.
     */
    protected TestBean nested = null;


    /**
     * The set of properties that should be described.
     */
    protected String describes[] =
    { "booleanProperty",
      "booleanSecond",
      "byteProperty",
      "doubleProperty",
      "dupProperty",
      "floatProperty",
      "intArray",
      "intIndexed",
      "intProperty",
      "listIndexed",
      "longProperty",
      "mapProperty",
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


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public DynaBeanUtilsTestCase_OE25Dev(final String name) {

        super(name);

    }


    // --------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() throws Exception {

        ConvertUtils.deregister();

        // Instantiate a new DynaBean instance
        final DynaClass dynaClass = createDynaClass();
        bean = dynaClass.newInstance();

        // Initialize the DynaBean's property values (like TestBean)
        bean.set("booleanProperty", new Boolean(true));
        bean.set("booleanSecond", new Boolean(true));
        bean.set("byteProperty", new Byte((byte) 121));
        bean.set("doubleProperty", new Double(321.0));
        bean.set("floatProperty", new Float((float) 123.0));
        final String dupProperty[] = { "Dup 0", "Dup 1", "Dup 2", "Dup 3", "Dup 4"};
        bean.set("dupProperty", dupProperty);
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

        return (new TestSuite(DynaBeanUtilsTestCase_OE25Dev.class));

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
     * Test the cloneBean() method from a DynaBean.
     */

    /**
     * Test the copyProperties() method from a DynaBean.
     */


    /**
     * Test copyProperties() when the origin is a a <code>Map</code>.
     */


    /**
     * Test the copyProperties() method from a standard JavaBean.
     */


    /**
     * Test the describe() method.
     */


    /**
     * Test populate() method on array properties as a whole.
     */


    /**
     *  tests the string and int arrays of TestBean
     */


    /**
     *  tests getting an indexed property
     */


    /**
     *  tests getting an indexed property
     */


    /**
     *  tests getting a nested property
     */


    /**
     *  tests getting a 'whatever' property
     */


    /**
     *  tests getting a 'whatever' property
     */


    /**
     * Test populate() method on individual array elements.
     */


    /**
     * Test populate() on mapped properties.
     */


    /**
     * Test populate() method on nested properties.
     */


    /**
     * Test populate() method on scalar properties.
     */


    /**
     * Test calling setProperty() with null property values.
     */


    /**
     * Test converting to and from primitive wrapper types.
     */


    /**
     * Test setting a null property value.
     */


    /**
     * Test narrowing and widening conversions on byte.
     */


    /**
     * Test narrowing and widening conversions on double.
     */


    /**
     * Test narrowing and widening conversions on float.
     */


    /**
     * Test narrowing and widening conversions on int.
     */


    /**
     * Test narrowing and widening conversions on long.
     */


    /**
     * Test copying a null property value.
     */


    /**
     * Test narrowing and widening conversions on short.
     */


    /**
     * Test copying a property using a nested indexed array expression,
     * with and without conversions.
     */
    public void testCopyPropertyNestedIndexedArray() throws Exception {

        final int origArray[] = { 0, 10, 20, 30, 40};
        final int intArray[] = { 0, 0, 0 };
        ((TestBean) bean.get("nested")).setIntArray(intArray);
        final int intChanged[] = { 0, 0, 0 };

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intArray[1]", new Integer(1));
        checkIntArray((int[]) bean.get("intArray"), origArray);
        intChanged[1] = 1;
        checkIntArray(((TestBean) bean.get("nested")).getIntArray(),
                      intChanged);

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intArray[1]", new Byte((byte) 2));
        checkIntArray((int[]) bean.get("intArray"), origArray);
        intChanged[1] = 2;
        checkIntArray(((TestBean) bean.get("nested")).getIntArray(),
                      intChanged);

        // Narrowing conversion required
        BeanUtils.copyProperty(bean, "nested.intArray[1]", new Long(3));
        checkIntArray((int[]) bean.get("intArray"), origArray);
        intChanged[1] = 3;
        checkIntArray(((TestBean) bean.get("nested")).getIntArray(),
                      intChanged);

        // String conversion required
        BeanUtils.copyProperty(bean, "nested.intArray[1]", "4");
        checkIntArray((int[]) bean.get("intArray"), origArray);
        intChanged[1] = 4;
        checkIntArray(((TestBean) bean.get("nested")).getIntArray(),
                      intChanged);

    }


    /**
     * Test copying a property using a nested mapped map property.
     */
    public void testCopyPropertyNestedMappedMap() throws Exception {

        final Map<String, Object> origMap = new HashMap<String, Object>();
        origMap.put("First Key", "First Value");
        origMap.put("Second Key", "Second Value");
        final Map<String, Object> changedMap = new HashMap<String, Object>();
        changedMap.put("First Key", "First Value");
        changedMap.put("Second Key", "Second Value");

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.mapProperty(Second Key)",
                               "New Second Value");
        checkMap((Map<?, ?>) bean.get("mapProperty"), origMap);
        changedMap.put("Second Key", "New Second Value");
        checkMap(((TestBean) bean.get("nested")).getMapProperty(), changedMap);

    }


    /**
     * Test copying a property using a nested simple expression, with and
     * without conversions.
     */


    // ------------------------------------------------------ Protected Methods


    // Ensure that the nested intArray matches the specified values
    protected void checkIntArray(final int actual[], final int expected[]) {
        assertNotNull("actual array not null", actual);
        assertEquals("actual array length", expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            assertEquals("actual array value[" + i + "]",expected[i],actual[i]);
        }
    }


    // Ensure that the actual Map matches the expected Map
    protected void checkMap(final Map<?, ?> actual, final Map<?, ?> expected) {
        assertNotNull("actual map not null", actual);
        assertEquals("actual map size", expected.size(), actual.size());
        final Iterator<?> keys = expected.keySet().iterator();
        while (keys.hasNext()) {
            final Object key = keys.next();
            assertEquals("actual map value(" + key + ")",expected.get(key),actual.get(key));
        }
    }


    /**
     * Create and return a <code>DynaClass</code> instance for our test
     * <code>DynaBean</code>.
     */
    protected static DynaClass createDynaClass() {

        final int intArray[] = new int[0];
        final String stringArray[] = new String[0];

        final DynaClass dynaClass = new BasicDynaClass
                ("TestDynaClass", null,
                        new DynaProperty[]{
                            new DynaProperty("booleanProperty", Boolean.TYPE),
                            new DynaProperty("booleanSecond", Boolean.TYPE),
                            new DynaProperty("byteProperty", Byte.TYPE),
                            new DynaProperty("doubleProperty", Double.TYPE),
                            new DynaProperty("dupProperty", stringArray.getClass()),
                            new DynaProperty("floatProperty", Float.TYPE),
                            new DynaProperty("intArray", intArray.getClass()),
                            new DynaProperty("intIndexed", intArray.getClass()),
                            new DynaProperty("intProperty", Integer.TYPE),
                            new DynaProperty("listIndexed", List.class),
                            new DynaProperty("longProperty", Long.TYPE),
                            new DynaProperty("mapProperty", Map.class),
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


    public void testCloneDynaBean_1_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            fail("newInstance(): " + e);
    }
    }

    public void testCloneDynaBean_2_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
            fail("Threw exception: " + e);
    }
    }

    public void testCloneDynaBean_3_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }

        assertEquals("Cloned boolean property",false,((Boolean)clonedBean.get("booleanProperty")).booleanValue());
    }

    public void testCloneDynaBean_4_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }

        assertEquals("Cloned byte property",(byte)111,((Byte)clonedBean.get("byteProperty")).byteValue());
    }

    public void testCloneDynaBean_5_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }

        assertEquals("Cloned double property",333.33,((Double)clonedBean.get("doubleProperty")).doubleValue(),0.005);
    }

    public void testCloneDynaBean_6_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }

        assertEquals("Cloned int property",333,((Integer)clonedBean.get("intProperty")).intValue());
    }

    public void testCloneDynaBean_7_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }

        assertEquals("Cloned long property",3333,((Long)clonedBean.get("longProperty")).longValue());
    }

    public void testCloneDynaBean_8_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }

        assertEquals("Cloned short property",(short)33,((Short)clonedBean.get("shortProperty")).shortValue());
    }

    public void testCloneDynaBean_9_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }

        assertEquals("Cloned string property","Custom string",(String)clonedBean.get("stringProperty"));
    }

    public void testCloneDynaBean_10_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        assertNotNull("dupProperty present", dupProperty);
    }

    public void testCloneDynaBean_11_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        assertEquals("dupProperty length", 3, dupProperty.length);
    }

    public void testCloneDynaBean_12_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        assertEquals("dupProperty[0]", "New 0", dupProperty[0]);
    }

    public void testCloneDynaBean_13_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        assertEquals("dupProperty[1]", "New 1", dupProperty[1]);
    }

    public void testCloneDynaBean_14_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        assertEquals("dupProperty[2]", "New 2", dupProperty[2]);
    }

    public void testCloneDynaBean_15_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        final int intArray[] = (int[]) clonedBean.get("intArray");
        assertNotNull("intArray present", intArray);
    }

    public void testCloneDynaBean_16_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        final int intArray[] = (int[]) clonedBean.get("intArray");
        assertEquals("intArray length", 3, intArray.length);
    }

    public void testCloneDynaBean_17_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        final int intArray[] = (int[]) clonedBean.get("intArray");
        assertEquals("intArray[0]", 100, intArray[0]);
    }

    public void testCloneDynaBean_18_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        final int intArray[] = (int[]) clonedBean.get("intArray");
        assertEquals("intArray[1]", 200, intArray[1]);
    }

    public void testCloneDynaBean_19_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        final int intArray[] = (int[]) clonedBean.get("intArray");
        assertEquals("intArray[2]", 300, intArray[2]);
    }

    public void testCloneDynaBean_20_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        final int intArray[] = (int[]) clonedBean.get("intArray");
        final String stringArray[] = (String[]) clonedBean.get("stringArray");
        assertNotNull("stringArray present", stringArray);
    }

    public void testCloneDynaBean_21_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        final int intArray[] = (int[]) clonedBean.get("intArray");
        final String stringArray[] = (String[]) clonedBean.get("stringArray");
        assertEquals("stringArray length", 2, stringArray.length);
    }

    public void testCloneDynaBean_22_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        final int intArray[] = (int[]) clonedBean.get("intArray");
        final String stringArray[] = (String[]) clonedBean.get("stringArray");
        assertEquals("stringArray[0]", "New 0", stringArray[0]);
    }

    public void testCloneDynaBean_23_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        DynaBean clonedBean = null;
        try {
            clonedBean = (DynaBean) BeanUtils.cloneBean(orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) clonedBean.get("dupProperty");
        final int intArray[] = (int[]) clonedBean.get("intArray");
        final String stringArray[] = (String[]) clonedBean.get("stringArray");
        assertEquals("stringArray[1]", "New 1", stringArray[1]);
    }

    public void testCopyPropertiesDynaBean_1_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            fail("newInstance(): " + e);
    }
    }

    public void testCopyPropertiesDynaBean_2_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            fail("Threw exception: " + e);
    }
    }

    public void testCopyPropertiesDynaBean_3_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied boolean property",false,((Boolean)bean.get("booleanProperty")).booleanValue());
    }

    public void testCopyPropertiesDynaBean_4_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied byte property",(byte)111,((Byte)bean.get("byteProperty")).byteValue());
    }

    public void testCopyPropertiesDynaBean_5_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied double property",333.33,((Double)bean.get("doubleProperty")).doubleValue(),0.005);
    }

    public void testCopyPropertiesDynaBean_6_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied int property",333,((Integer)bean.get("intProperty")).intValue());
    }

    public void testCopyPropertiesDynaBean_7_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied long property",3333,((Long)bean.get("longProperty")).longValue());
    }

    public void testCopyPropertiesDynaBean_8_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied short property",(short)33,((Short)bean.get("shortProperty")).shortValue());
    }

    public void testCopyPropertiesDynaBean_9_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied string property","Custom string",(String)bean.get("stringProperty"));
    }

    public void testCopyPropertiesDynaBean_10_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertNotNull("dupProperty present", dupProperty);
    }

    public void testCopyPropertiesDynaBean_11_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty length", 3, dupProperty.length);
    }

    public void testCopyPropertiesDynaBean_12_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty[0]", "New 0", dupProperty[0]);
    }

    public void testCopyPropertiesDynaBean_13_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty[1]", "New 1", dupProperty[1]);
    }

    public void testCopyPropertiesDynaBean_14_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty[2]", "New 2", dupProperty[2]);
    }

    public void testCopyPropertiesDynaBean_15_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertNotNull("intArray present", intArray);
    }

    public void testCopyPropertiesDynaBean_16_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray length", 3, intArray.length);
    }

    public void testCopyPropertiesDynaBean_17_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray[0]", 100, intArray[0]);
    }

    public void testCopyPropertiesDynaBean_18_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray[1]", 200, intArray[1]);
    }

    public void testCopyPropertiesDynaBean_19_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray[2]", 300, intArray[2]);
    }

    public void testCopyPropertiesDynaBean_20_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        final String stringArray[] = (String[]) bean.get("stringArray");
        assertNotNull("stringArray present", stringArray);
    }

    public void testCopyPropertiesDynaBean_21_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        final String stringArray[] = (String[]) bean.get("stringArray");
        assertEquals("stringArray length", 2, stringArray.length);
    }

    public void testCopyPropertiesDynaBean_22_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        final String stringArray[] = (String[]) bean.get("stringArray");
        assertEquals("stringArray[0]", "New 0", stringArray[0]);
    }

    public void testCopyPropertiesDynaBean_23_oe() {

        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte)111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        final String stringArray[] = (String[]) bean.get("stringArray");
        assertEquals("stringArray[1]", "New 1", stringArray[1]);
    }

    public void testCopyPropertiesMap_1_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
            fail("Threw " + t.toString());
    }
    }

    public void testCopyPropertiesMap_2_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("booleanProperty",false,((Boolean)bean.get("booleanProperty")).booleanValue());
    }

    public void testCopyPropertiesMap_3_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("byteProperty",(byte)111,((Byte)bean.get("byteProperty")).byteValue());
    }

    public void testCopyPropertiesMap_4_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("doubleProperty",333.0,((Double)bean.get("doubleProperty")).doubleValue(),0.005);
    }

    public void testCopyPropertiesMap_5_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("floatProperty",(float)222.0,((Float)bean.get("floatProperty")).floatValue(),(float)0.005);
    }

    public void testCopyPropertiesMap_6_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("intProperty",111,((Integer)bean.get("intProperty")).intValue());
    }

    public void testCopyPropertiesMap_7_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("longProperty",444,((Long)bean.get("longProperty")).longValue());
    }

    public void testCopyPropertiesMap_8_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("shortProperty",(short)555,((Short)bean.get("shortProperty")).shortValue());
    }

    public void testCopyPropertiesMap_9_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }

        assertEquals("stringProperty","New String Property",(String)bean.get("stringProperty"));
    }

    public void testCopyPropertiesMap_10_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertNotNull("dupProperty present", dupProperty);
    }

    public void testCopyPropertiesMap_11_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty length", 3, dupProperty.length);
    }

    public void testCopyPropertiesMap_12_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty[0]", "New 0", dupProperty[0]);
    }

    public void testCopyPropertiesMap_13_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty[1]", "New 1", dupProperty[1]);
    }

    public void testCopyPropertiesMap_14_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty[2]", "New 2", dupProperty[2]);
    }

    public void testCopyPropertiesMap_15_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertNotNull("intArray present", intArray);
    }

    public void testCopyPropertiesMap_16_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray length", 3, intArray.length);
    }

    public void testCopyPropertiesMap_17_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray[0]", 0, intArray[0]);
    }

    public void testCopyPropertiesMap_18_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray[1]", 100, intArray[1]);
    }

    public void testCopyPropertiesMap_19_oe() {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("booleanProperty", "false");
        map.put("byteProperty", "111");
        map.put("doubleProperty", "333.0");
        map.put("dupProperty", new String[] { "New 0", "New 1", "New 2" });
        map.put("floatProperty", "222.0");
        map.put("intArray", new String[] { "0", "100", "200" });
        map.put("intProperty", "111");
        map.put("longProperty", "444");
        map.put("shortProperty", "555");
        map.put("stringProperty", "New String Property");

        try {
            BeanUtils.copyProperties(bean, map);
        } catch (final Throwable t) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray[2]", 200, intArray[2]);
    }

    public void testCopyPropertiesStandard_1_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            fail("Threw exception: " + e);
    }
    }

    public void testCopyPropertiesStandard_2_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied boolean property",false,((Boolean)bean.get("booleanProperty")).booleanValue());
    }

    public void testCopyPropertiesStandard_3_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied byte property",(byte)111,((Byte)bean.get("byteProperty")).byteValue());
    }

    public void testCopyPropertiesStandard_4_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied double property",333.33,((Double)bean.get("doubleProperty")).doubleValue(),0.005);
    }

    public void testCopyPropertiesStandard_5_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied int property",333,((Integer)bean.get("intProperty")).intValue());
    }

    public void testCopyPropertiesStandard_6_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied long property",3333,((Long)bean.get("longProperty")).longValue());
    }

    public void testCopyPropertiesStandard_7_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied short property",(short)33,((Short)bean.get("shortProperty")).shortValue());
    }

    public void testCopyPropertiesStandard_8_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }

        assertEquals("Copied string property","Custom string",(String)bean.get("stringProperty"));
    }

    public void testCopyPropertiesStandard_9_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertNotNull("dupProperty present", dupProperty);
    }

    public void testCopyPropertiesStandard_10_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty length", 3, dupProperty.length);
    }

    public void testCopyPropertiesStandard_11_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty[0]", "New 0", dupProperty[0]);
    }

    public void testCopyPropertiesStandard_12_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty[1]", "New 1", dupProperty[1]);
    }

    public void testCopyPropertiesStandard_13_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        assertEquals("dupProperty[2]", "New 2", dupProperty[2]);
    }

    public void testCopyPropertiesStandard_14_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertNotNull("intArray present", intArray);
    }

    public void testCopyPropertiesStandard_15_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray length", 3, intArray.length);
    }

    public void testCopyPropertiesStandard_16_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray[0]", 100, intArray[0]);
    }

    public void testCopyPropertiesStandard_17_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray[1]", 200, intArray[1]);
    }

    public void testCopyPropertiesStandard_18_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        assertEquals("intArray[2]", 300, intArray[2]);
    }

    public void testCopyPropertiesStandard_19_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        final String stringArray[] = (String[]) bean.get("stringArray");
        assertNotNull("stringArray present", stringArray);
    }

    public void testCopyPropertiesStandard_20_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        final String stringArray[] = (String[]) bean.get("stringArray");
        assertEquals("stringArray length", 2, stringArray.length);
    }

    public void testCopyPropertiesStandard_21_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        final String stringArray[] = (String[]) bean.get("stringArray");
        assertEquals("stringArray[0]", "New 0", stringArray[0]);
    }

    public void testCopyPropertiesStandard_22_oe() {

        final TestBean orig = new TestBean();
        orig.setBooleanProperty(false);
        orig.setByteProperty((byte) 111);
        orig.setDoubleProperty(333.33);
        orig.setDupProperty(new String[] { "New 0", "New 1", "New 2" });
        orig.setIntArray(new int[] { 100, 200, 300 });
        orig.setIntProperty(333);
        orig.setLongProperty(3333);
        orig.setShortProperty((short) 33);
        orig.setStringArray(new String[] { "New 0", "New 1" });
        orig.setStringProperty("Custom string");

        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
        }


        final String dupProperty[] = (String[]) bean.get("dupProperty");
        final int intArray[] = (int[]) bean.get("intArray");
        final String stringArray[] = (String[]) bean.get("stringArray");
        assertEquals("stringArray[1]", "New 1", stringArray[1]);
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

        assertEquals("Value of 'byteProperty'",new Byte((byte)121),map.get("byteProperty"));
    }

    public void testDescribe_6_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'doubleProperty'",new Double(321.0),map.get("doubleProperty"));
    }

    public void testDescribe_7_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'floatProperty'",new Float((float)123.0),map.get("floatProperty"));
    }

    public void testDescribe_8_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'intProperty'",new Integer(123),map.get("intProperty"));
    }

    public void testDescribe_9_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'longProperty'",new Long(321),map.get("longProperty"));
    }

    public void testDescribe_10_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'shortProperty'",new Short((short)987),map.get("shortProperty"));
    }

    public void testDescribe_11_oe() {

        Map<String, Object> map = null;
        try {
            map = PropertyUtils.describe(bean);
        } catch (final Exception e) {
        }

        for (String describe : describes) {
        }

        assertEquals("Value of 'stringProperty'","This is a string",(String)map.get("stringProperty"));
    }

    public void testPopulateArrayProperties_11_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            final String intArrayIn[] = new String[] { "123", "456", "789" };
            map.put("intArray", intArrayIn);
            String stringArray[] = new String[]
                { "New String 0", "New String 1" };
            map.put("stringArray", stringArray);

            BeanUtils.populate(bean, map);

            final int intArray[] = (int[]) bean.get("intArray");
            stringArray = (String[]) bean.get("stringArray");

        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetArrayProperty_5_oe() {
        try {
            String arr[] = BeanUtils.getArrayProperty(bean, "stringArray");
            final String comp[] = (String[]) bean.get("stringArray");


            arr = BeanUtils.getArrayProperty(bean, "intArray");
            final int iarr[] = (int[]) bean.get("intArray");

        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetIndexedProperty1_5_oe() {
        try {
            String val = BeanUtils.getIndexedProperty(bean, "intIndexed[3]");
            String comp = String.valueOf(bean.get("intIndexed", 3));

            val = BeanUtils.getIndexedProperty(bean, "stringIndexed[3]");
            comp = (String) bean.get("stringIndexed", 3);
        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetIndexedProperty2_5_oe() {
        try {
            String val = BeanUtils.getIndexedProperty(bean, "intIndexed", 3);
            String comp = String.valueOf(bean.get("intIndexed", 3));


            val = BeanUtils.getIndexedProperty(bean, "stringIndexed", 3);
            comp = (String) bean.get("stringIndexed", 3);


        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedProperty_4_oe() {
        try {
            final String val = BeanUtils.getNestedProperty(bean, "nested.stringProperty");
            final String comp = nested.getStringProperty();
        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetGeneralProperty_4_oe() {
        try {
            final String val = BeanUtils.getProperty(bean, "nested.intIndexed[2]");
            final String comp = String.valueOf(bean.get("intIndexed", 2));

        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleProperty_4_oe() {
        try {
            final String val = BeanUtils.getSimpleProperty(bean, "shortProperty");
            final String comp = String.valueOf(bean.get("shortProperty"));

        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testPopulateArrayElements_12_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("intIndexed[0]", "100");
            map.put("intIndexed[2]", "120");
            map.put("intIndexed[4]", "140");

            BeanUtils.populate(bean, map);
            final Integer intIndexed0 = (Integer) bean.get("intIndexed", 0);
            final Integer intIndexed1 = (Integer) bean.get("intIndexed", 1);
            final Integer intIndexed2 = (Integer) bean.get("intIndexed", 2);
            final Integer intIndexed3 = (Integer) bean.get("intIndexed", 3);
            final Integer intIndexed4 = (Integer) bean.get("intIndexed", 4);

            map.clear();
            map.put("stringIndexed[1]", "New String 1");
            map.put("stringIndexed[3]", "New String 3");

            BeanUtils.populate(bean, map);


        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testPopulateMapped_6_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("mappedProperty(First Key)", "New First Value");
            map.put("mappedProperty(Third Key)", "New Third Value");

            BeanUtils.populate(bean, map);


        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testPopulateNested_10_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("nested.booleanProperty", "false");
            map.put("nested.doubleProperty", "432.0");
            map.put("nested.intProperty", "543");
            map.put("nested.shortProperty", "654");

            BeanUtils.populate(bean, map);

            final TestBean nested = (TestBean) bean.get("nested");

        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testPopulateScalar_11_oe() {

        try {

            bean.set("nullProperty", "non-null value");

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("booleanProperty", "false");
            map.put("doubleProperty", "432.0");
            map.put("intProperty", "543");
            map.put("nullProperty", null);
            map.put("shortProperty", "654");

            BeanUtils.populate(bean, map);

            final Boolean booleanProperty = (Boolean) bean.get("booleanProperty");
            final Boolean booleanSecond = (Boolean) bean.get("booleanSecond");
            final Double doubleProperty = (Double) bean.get("doubleProperty");
            final Float floatProperty = (Float) bean.get("floatProperty");
            final Integer intProperty = (Integer) bean.get("intProperty");
            final Long longProperty = (Long) bean.get("longProperty");
            final Short shortProperty = (Short) bean.get("shortProperty");

        } catch (final IllegalAccessException e) {
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetPropertyNullValues_1_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        assertNotNull("stringArray is not null", newValue);
    }

    public void testSetPropertyNullValues_2_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        assertTrue("stringArray of correct type",newValue instanceof String[]);
    }

    public void testSetPropertyNullValues_3_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        assertEquals("stringArray length",1,((String[])newValue).length);
    }

    public void testSetPropertyNullValues_4_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        assertNotNull("stringArray is not null", newValue);
    }

    public void testSetPropertyNullValues_5_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        assertTrue("stringArray of correct type",newValue instanceof String[]);
    }

    public void testSetPropertyNullValues_6_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        assertEquals("stringArray length",5,((String[])newValue).length);
    }

    public void testSetPropertyNullValues_7_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        assertTrue("stringArray[2] is null",((String[])newValue)[2] == null);
    }

    public void testSetPropertyNullValues_8_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        BeanUtils.setProperty(bean, "stringProperty", null);
        assertTrue("stringProperty is now null",BeanUtils.getProperty(bean,"stringProperty")== null);
    }

    public void testSetPropertyOnPrimitiveWrappers_1_oe() throws Exception {

        BeanUtils.setProperty(bean,"intProperty", new Integer(1));
        assertEquals(1,((Integer) bean.get("intProperty")).intValue());
    }

    public void testSetPropertyOnPrimitiveWrappers_2_oe() throws Exception {

        BeanUtils.setProperty(bean,"intProperty", new Integer(1));
        BeanUtils.setProperty(bean,"stringProperty", new Integer(1));
        assertEquals(1, Integer.parseInt((String) bean.get("stringProperty")));
    }

    public void testSetPropertyNull_1_oe() throws Exception {

        bean.set("nullProperty", "non-null value");
        BeanUtils.setProperty(bean, "nullProperty", null);
        assertNull("nullProperty is null", bean.get("nullProperty"));
    }

    public void testCopyPropertyByte_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "byteProperty", new Byte((byte) 123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
    }

    public void testCopyPropertyByte_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "byteProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "byteProperty", new Double((double) 123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
        BeanUtils.setProperty(bean, "byteProperty", new Float((float) 123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
*/
        BeanUtils.setProperty(bean, "byteProperty", new Integer(123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
    }

    public void testCopyPropertyByte_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "byteProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "byteProperty", new Double((double) 123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
        BeanUtils.setProperty(bean, "byteProperty", new Float((float) 123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
*/
        BeanUtils.setProperty(bean, "byteProperty", new Integer(123));
        BeanUtils.setProperty(bean, "byteProperty", new Long(123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
    }

    public void testCopyPropertyByte_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "byteProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "byteProperty", new Double((double) 123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
        BeanUtils.setProperty(bean, "byteProperty", new Float((float) 123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
*/
        BeanUtils.setProperty(bean, "byteProperty", new Integer(123));
        BeanUtils.setProperty(bean, "byteProperty", new Long(123));
        BeanUtils.setProperty(bean, "byteProperty", new Short((short) 123));
        assertEquals((byte) 123, ((Byte) bean.get("byteProperty")).byteValue());
    }

    public void testCopyPropertyDouble_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        assertEquals(123, ((Double) bean.get("doubleProperty")).doubleValue(), 0.005);
    }

    public void testCopyPropertyDouble_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        assertEquals(123, ((Double) bean.get("doubleProperty")).doubleValue(), 0.005);
    }

    public void testCopyPropertyDouble_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Float(123));
        assertEquals(123, ((Double) bean.get("doubleProperty")).doubleValue(), 0.005);
    }

    public void testCopyPropertyDouble_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Float(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Integer(123));
        assertEquals(123, ((Double) bean.get("doubleProperty")).doubleValue(), 0.005);
    }

    public void testCopyPropertyDouble_5_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Float(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Integer(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Long(123));
        assertEquals(123, ((Double) bean.get("doubleProperty")).doubleValue(), 0.005);
    }

    public void testCopyPropertyDouble_6_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Float(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Integer(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Long(123));
        BeanUtils.setProperty(bean, "doubleProperty", new Short((short) 123));
        assertEquals(123, ((Double) bean.get("doubleProperty")).doubleValue(), 0.005);
    }

    public void testCopyPropertyFloat_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        assertEquals(123, ((Float) bean.get("floatProperty")).floatValue(), 0.005);
    }

    public void testCopyPropertyFloat_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        assertEquals(123, ((Float) bean.get("floatProperty")).floatValue(), 0.005);
    }

    public void testCopyPropertyFloat_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        BeanUtils.setProperty(bean, "floatProperty", new Float(123));
        assertEquals(123, ((Float) bean.get("floatProperty")).floatValue(), 0.005);
    }

    public void testCopyPropertyFloat_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        BeanUtils.setProperty(bean, "floatProperty", new Float(123));
        BeanUtils.setProperty(bean, "floatProperty", new Integer(123));
        assertEquals(123, ((Float) bean.get("floatProperty")).floatValue(), 0.005);
    }

    public void testCopyPropertyFloat_5_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        BeanUtils.setProperty(bean, "floatProperty", new Float(123));
        BeanUtils.setProperty(bean, "floatProperty", new Integer(123));
        BeanUtils.setProperty(bean, "floatProperty", new Long(123));
        assertEquals(123, ((Float) bean.get("floatProperty")).floatValue(), 0.005);
    }

    public void testCopyPropertyFloat_6_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        BeanUtils.setProperty(bean, "floatProperty", new Float(123));
        BeanUtils.setProperty(bean, "floatProperty", new Integer(123));
        BeanUtils.setProperty(bean, "floatProperty", new Long(123));
        BeanUtils.setProperty(bean, "floatProperty", new Short((short) 123));
        assertEquals(123, ((Float) bean.get("floatProperty")).floatValue(), 0.005);
    }

    public void testCopyPropertyInteger_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        assertEquals(123, ((Integer) bean.get("intProperty")).intValue());
    }

    public void testCopyPropertyInteger_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((int) 123, ((Integer) bean.get("intProperty")).intValue());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((int) 123, ((Integer) bean.get("intProperty")).intValue());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        assertEquals(123, ((Integer) bean.get("intProperty")).intValue());
    }

    public void testCopyPropertyInteger_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((int) 123, ((Integer) bean.get("intProperty")).intValue());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((int) 123, ((Integer) bean.get("intProperty")).intValue());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        BeanUtils.setProperty(bean, "longProperty", new Long(123));
        assertEquals(123, ((Integer) bean.get("intProperty")).intValue());
    }

    public void testCopyPropertyInteger_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((int) 123, ((Integer) bean.get("intProperty")).intValue());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((int) 123, ((Integer) bean.get("intProperty")).intValue());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        BeanUtils.setProperty(bean, "longProperty", new Long(123));
        BeanUtils.setProperty(bean, "longProperty", new Short((short) 123));
        assertEquals(123, ((Integer) bean.get("intProperty")).intValue());
    }

    public void testCopyPropertyLong_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        assertEquals(123, ((Long) bean.get("longProperty")).longValue());
    }

    public void testCopyPropertyLong_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((long) 123, ((Long) bean.get("longProperty")).longValue());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((long) 123, ((Long) bean.get("longProperty")).longValue());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        assertEquals(123, ((Long) bean.get("longProperty")).longValue());
    }

    public void testCopyPropertyLong_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((long) 123, ((Long) bean.get("longProperty")).longValue());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((long) 123, ((Long) bean.get("longProperty")).longValue());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        BeanUtils.setProperty(bean, "longProperty", new Long(123));
        assertEquals(123, ((Long) bean.get("longProperty")).longValue());
    }

    public void testCopyPropertyLong_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((long) 123, ((Long) bean.get("longProperty")).longValue());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((long) 123, ((Long) bean.get("longProperty")).longValue());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        BeanUtils.setProperty(bean, "longProperty", new Long(123));
        BeanUtils.setProperty(bean, "longProperty", new Short((short) 123));
        assertEquals(123, ((Long) bean.get("longProperty")).longValue());
    }

    public void testCopyPropertyNull_1_oe() throws Exception {

        bean.set("nullProperty", "non-null value");
        BeanUtils.copyProperty(bean, "nullProperty", null);
        assertNull("nullProperty is null", bean.get("nullProperty"));
    }

    public void testCopyPropertyShort_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "shortProperty", new Byte((byte) 123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
    }

    public void testCopyPropertyShort_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "shortProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "shortProperty", new Double((double) 123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
        BeanUtils.setProperty(bean, "shortProperty", new Float((float) 123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
*/
        BeanUtils.setProperty(bean, "shortProperty", new Integer(123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
    }

    public void testCopyPropertyShort_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "shortProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "shortProperty", new Double((double) 123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
        BeanUtils.setProperty(bean, "shortProperty", new Float((float) 123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
*/
        BeanUtils.setProperty(bean, "shortProperty", new Integer(123));
        BeanUtils.setProperty(bean, "shortProperty", new Long(123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
    }

    public void testCopyPropertyShort_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "shortProperty", new Byte((byte) 123));
/*
        BeanUtils.setProperty(bean, "shortProperty", new Double((double) 123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
        BeanUtils.setProperty(bean, "shortProperty", new Float((float) 123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
*/
        BeanUtils.setProperty(bean, "shortProperty", new Integer(123));
        BeanUtils.setProperty(bean, "shortProperty", new Long(123));
        BeanUtils.setProperty(bean, "shortProperty", new Short((short) 123));
        assertEquals((short) 123, ((Short) bean.get("shortProperty")).shortValue());
    }

    public void testCopyPropertyNestedSimple_1_oe() throws Exception {

        bean.set("intProperty", new Integer(0));
        nested.setIntProperty(0);

        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        assertEquals(0, ((Integer) bean.get("intProperty")).intValue());
    }

    public void testCopyPropertyNestedSimple_2_oe() throws Exception {

        bean.set("intProperty", new Integer(0));
        nested.setIntProperty(0);

        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        assertEquals(1, nested.getIntProperty());
    }

    public void testCopyPropertyNestedSimple_3_oe() throws Exception {

        bean.set("intProperty", new Integer(0));
        nested.setIntProperty(0);

        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        assertEquals(0, ((Integer) bean.get("intProperty")).intValue());
    }

    public void testCopyPropertyNestedSimple_4_oe() throws Exception {

        bean.set("intProperty", new Integer(0));
        nested.setIntProperty(0);

        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        assertEquals(2, nested.getIntProperty());
    }

    public void testCopyPropertyNestedSimple_5_oe() throws Exception {

        bean.set("intProperty", new Integer(0));
        nested.setIntProperty(0);

        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));
        assertEquals(0, ((Integer) bean.get("intProperty")).intValue());
    }

    public void testCopyPropertyNestedSimple_6_oe() throws Exception {

        bean.set("intProperty", new Integer(0));
        nested.setIntProperty(0);

        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));
        assertEquals(3, nested.getIntProperty());
    }

    public void testCopyPropertyNestedSimple_7_oe() throws Exception {

        bean.set("intProperty", new Integer(0));
        nested.setIntProperty(0);

        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));

        BeanUtils.copyProperty(bean, "nested.intProperty", "4");
        assertEquals(0, ((Integer) bean.get("intProperty")).intValue());
    }

    public void testCopyPropertyNestedSimple_8_oe() throws Exception {

        bean.set("intProperty", new Integer(0));
        nested.setIntProperty(0);

        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));

        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));

        BeanUtils.copyProperty(bean, "nested.intProperty", "4");
        assertEquals(4, nested.getIntProperty());
    }

}
