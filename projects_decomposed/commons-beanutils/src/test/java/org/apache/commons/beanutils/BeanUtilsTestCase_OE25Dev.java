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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;


/**
 * <p>
 *  Test Case for the BeanUtils class.  The majority of these tests use
 *  instances of the TestBean class, so be sure to update the tests if you
 *  change the characteristics of that class.
 * </p>
 *
 * <p>
 *  Template for this stolen from Craigs PropertyUtilsTestCase
 * </p>
 *
 * <p>
 *   Note that the tests are dependant upon the static aspects
 *   (such as array sizes...) of the TestBean.java class, so ensure
 *   than all changes to TestBean are reflected here.
 * </p>
 *
 * <p>
 *  So far, this test case has tests for the following methods of the
 *  <code>BeanUtils</code> class:
 * </p>
 * <ul>
 *   <li>getArrayProperty(Object bean, String name)</li>
 * </ul>
 *
 * @version $Id$
 */

public class BeanUtilsTestCase_OE25Dev extends TestCase {

    // ---------------------------------------------------- Instance Variables

    /**
     * The test bean for each test.
     */
    protected TestBean bean = null;


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
      //      "intIndexed",
      "longProperty",
      "listIndexed",
      "longProperty",
      //      "mappedProperty",
      //      "mappedIntProperty",
      "nested",
      "nullProperty",
      "readOnlyProperty",
      "shortProperty",
      "stringArray",
      //      "stringIndexed",
      "stringProperty"
    };

    /** Test Calendar value */
    protected java.util.Calendar testCalendar;

    /** Test java.util.Date value */
    protected java.util.Date testUtilDate;

    /** Test String Date value */
    protected String testStringDate;

    // ---------------------------------------------------------- Constructors

    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public BeanUtilsTestCase_OE25Dev(final String name) {
        super(name);
    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() {
        ConvertUtils.deregister();
        BeanUtilsBean.setInstance(new BeanUtilsBean());
        setUpShared();
    }

    /**
     * Shared Set up.
     */
    protected void setUpShared() {
        bean = new TestBean();

        final DateConverter dateConverter = new DateConverter(null);
        dateConverter.setLocale(Locale.US);
        dateConverter.setPattern("dd.MM.yyyy");
        ConvertUtils.register(dateConverter, java.util.Date.class);

        final ArrayConverter dateArrayConverter =
            new ArrayConverter(java.util.Date[].class, dateConverter, 0);
        ConvertUtils.register(dateArrayConverter, java.util.Date[].class);

        testCalendar = Calendar.getInstance();
        testCalendar.set(1992, 11, 28, 0, 0, 0);
        testCalendar.set(Calendar.MILLISECOND, 0);
        testUtilDate = testCalendar.getTime();
        testStringDate = "28.12.1992";
    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(BeanUtilsTestCase_OE25Dev.class));
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
     *  tests the string and int arrays of TestBean
     */

    /**
     * Test <code>getArrayProperty()</code> converting to a String.
     */

    /**
     *  tests getting an indexed property
     */

    /**
     * Test <code>getArrayProperty()</code> converting to a String.
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
     * Test <code>getSimpleProperty()</code> converting to a String.
     */

    /**
     * Test populate() method on individual array elements.
     */


    /**
     * Test populate() method on array properties as a whole.
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
     * Test narrowing and widening conversions on byte.
     */

    /**
     * Test <code>setProperty()</code> conversion.
     */

    /**
     * Test <code>setProperty()</code> converting from a String.
     */

    /**
     * Test <code>setProperty()</code> converting to a String.
     */

    /**
     * Test <code>setProperty()</code> converting to a String array.
     */

    /**
     * Test <code>setProperty()</code> converting to a String on indexed property
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
     * Test setting a null property value.
     */


    /**
     * Test narrowing and widening conversions on short.
     */

    /**
     * Test setting a String value to a String array property
     */


    /**
     * Test narrowing and widening conversions on byte.
     */

    /**
     * Test <code>copyProperty()</code> conversion.
     */

    /**
     * Test <code>copyProperty()</code> converting from a String.
     */

    /**
     * Test <code>copyProperty()</code> converting to a String.
     */

    /**
     * Test <code>copyProperty()</code> converting to a String.
     */

    /**
     * Test <code>copyProperty()</code> converting to a String on indexed property
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
     * Test narrowing and widening conversions on short.
     */


    /**
     * Test copying a property using a nested indexed array expression,
     * with and without conversions.
     */
    public void testCopyPropertyNestedIndexedArray() throws Exception {

        final int origArray[] = { 0, 10, 20, 30, 40 };
        final int intArray[] = { 0, 0, 0 };
        bean.getNested().setIntArray(intArray);
        final int intChanged[] = { 0, 0, 0 };

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intArray[1]", new Integer(1));
        checkIntArray(bean.getIntArray(), origArray);
        intChanged[1] = 1;
        checkIntArray(bean.getNested().getIntArray(), intChanged);

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intArray[1]", new Byte((byte) 2));
        checkIntArray(bean.getIntArray(), origArray);
        intChanged[1] = 2;
        checkIntArray(bean.getNested().getIntArray(), intChanged);

        // Narrowing conversion required
        BeanUtils.copyProperty(bean, "nested.intArray[1]", new Long(3));
        checkIntArray(bean.getIntArray(), origArray);
        intChanged[1] = 3;
        checkIntArray(bean.getNested().getIntArray(), intChanged);

        // String conversion required
        BeanUtils.copyProperty(bean, "nested.intArray[1]", "4");
        checkIntArray(bean.getIntArray(), origArray);
        intChanged[1] = 4;
        checkIntArray(bean.getNested().getIntArray(), intChanged);

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
        checkMap(bean.getMapProperty(), origMap);
        changedMap.put("Second Key", "New Second Value");
        checkMap(bean.getNested().getMapProperty(), changedMap);

    }


    /**
     * Test copying a property using a nested simple expression, with and
     * without conversions.
     */


    /**
     * Test copying a null property value.
     */


    /**
     * Test copying a new value to a write-only property, with and without
     * conversions.
     */


    /**
     * Test setting a new value to a write-only property, with and without
     * conversions.
     */

    /**
     * Test setting a value out of a mapped Map
     */

    /** Tests that separate instances can register separate instances */

    // Ensure that the actual int[] matches the expected int[]
    protected void checkIntArray(final int actual[], final int expected[]) {
        assertNotNull("actual array not null", actual);
        assertEquals("actual array length", expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            assertEquals("actual array value[" + i + "]",
                         expected[i], actual[i]);
        }
    }


    // Ensure that the actual Map matches the expected Map
    protected void checkMap(final Map<?, ?> actual, final Map<?, ?> expected) {
        assertNotNull("actual map not null", actual);
        assertEquals("actual map size", expected.size(), actual.size());
        final Iterator<?> keys = expected.keySet().iterator();
        while (keys.hasNext()) {
            final Object key = keys.next();
            assertEquals("actual map value(" + key + ")",
                         expected.get(key), actual.get(key));
        }
    }

    /**
     * Test for {@link BeanUtilsBean#initCause(Throwable, Throwable)} method.
     */

    /**
     * Use reflection to get the cause
     */
    private Throwable getCause(final Throwable t) throws Throwable {
        return (Throwable)PropertyUtils.getProperty(t, "cause");
    }

    /**
     * Catch a cause, initialize using BeanUtils.initCause() and throw new exception
     */
    private void initCauseAndThrowException(final String parent, final String cause) throws Throwable {
        try {
            throwException(cause);
        } catch (final Throwable e) {
            final Throwable t = new Exception(parent);
            BeanUtils.initCause(t, e);
            throw t;
        }
    }

    /**
     * Throw an exception with the specified message.
     */
    private void throwException(final String msg) throws Throwable {
        throw new Exception(msg);
    }

    /**
     * Test for JDK 1.4
     */
    public static boolean isPre14JVM() {
        final String version = System.getProperty("java.specification.version");
        final StringTokenizer tokenizer = new StringTokenizer(version,".");
        if (tokenizer.nextToken().equals("1")) {
            final String minorVersion = tokenizer.nextToken();
            if (minorVersion.equals("0")) {
                return true;
            }
            if (minorVersion.equals("1")) {
                return true;
            }
            if (minorVersion.equals("2")) {
                return true;
            }
            if (minorVersion.equals("3")) {
                return true;
            }
        }
        return false;
    }

    public void testCopyPropertiesDynaBean_1_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            fail("newInstance(): " + e);
    }
    }

    public void testCopyPropertiesDynaBean_2_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            fail("Threw exception: " + e);
    }
    }

    public void testCopyPropertiesDynaBean_3_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        assertEquals("Copied boolean property", false, bean.getBooleanProperty());
    }

    public void testCopyPropertiesDynaBean_4_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        assertEquals("Copied byte property", (byte) 111, bean.getByteProperty());
    }

    public void testCopyPropertiesDynaBean_5_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        assertEquals("Copied double property", 333.33, bean.getDoubleProperty(), 0.005);
    }

    public void testCopyPropertiesDynaBean_6_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Copied int property", 333, bean.getIntProperty());
    }

    public void testCopyPropertiesDynaBean_7_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Copied long property", 3333, bean.getLongProperty());
    }

    public void testCopyPropertiesDynaBean_8_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Copied short property", (short) 33, bean.getShortProperty());
    }

    public void testCopyPropertiesDynaBean_9_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Copied string property", "Custom string", bean.getStringProperty());
    }

    public void testCopyPropertiesDynaBean_10_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        assertNotNull("dupProperty present", dupProperty);
    }

    public void testCopyPropertiesDynaBean_11_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        assertEquals("dupProperty length", 3, dupProperty.length);
    }

    public void testCopyPropertiesDynaBean_12_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        assertEquals("dupProperty[0]", "New 0", dupProperty[0]);
    }

    public void testCopyPropertiesDynaBean_13_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("dupProperty[1]", "New 1", dupProperty[1]);
    }

    public void testCopyPropertiesDynaBean_14_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("dupProperty[2]", "New 2", dupProperty[2]);
    }

    public void testCopyPropertiesDynaBean_15_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        assertNotNull("intArray present", intArray);
    }

    public void testCopyPropertiesDynaBean_16_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        assertEquals("intArray length", 3, intArray.length);
    }

    public void testCopyPropertiesDynaBean_17_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        assertEquals("intArray[0]", 100, intArray[0]);
    }

    public void testCopyPropertiesDynaBean_18_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("intArray[1]", 200, intArray[1]);
    }

    public void testCopyPropertiesDynaBean_19_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("intArray[2]", 300, intArray[2]);
    }

    public void testCopyPropertiesDynaBean_20_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String stringArray[] = bean.getStringArray();
        assertNotNull("stringArray present", stringArray);
    }

    public void testCopyPropertiesDynaBean_21_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String stringArray[] = bean.getStringArray();
        // removed other assertion
        assertEquals("stringArray length", 2, stringArray.length);
    }

    public void testCopyPropertiesDynaBean_22_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String stringArray[] = bean.getStringArray();
        // removed other assertion
        // removed other assertion
        assertEquals("stringArray[0]", "New 0", stringArray[0]);
    }

    public void testCopyPropertiesDynaBean_23_oe() {

        // Set up an origin bean with customized properties
        final DynaClass dynaClass = DynaBeanUtilsTestCase.createDynaClass();
        DynaBean orig = null;
        try {
            orig = dynaClass.newInstance();
        } catch (final Exception e) {
            // removed other assertion
        }
        orig.set("booleanProperty", Boolean.FALSE);
        orig.set("byteProperty", new Byte((byte) 111));
        orig.set("doubleProperty", new Double(333.33));
        orig.set("dupProperty",
                 new String[] { "New 0", "New 1", "New 2" });
        orig.set("intArray", new int[] { 100, 200, 300 });
        orig.set("intProperty", new Integer(333));
        orig.set("longProperty", new Long(3333));
        orig.set("shortProperty", new Short((short) 33));
        orig.set("stringArray", new String[] { "New 0", "New 1" });
        orig.set("stringProperty", "Custom string");

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String stringArray[] = bean.getStringArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
            // removed other assertion
        }

        // Scalar properties
        assertEquals("booleanProperty", false, bean.getBooleanProperty());
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        assertEquals("byteProperty", (byte) 111, bean.getByteProperty());
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        assertEquals("doubleProperty", 333.0, bean.getDoubleProperty(), 0.005);
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("floatProperty", (float) 222.0, bean.getFloatProperty(), (float) 0.005);
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("longProperty", 111, bean.getIntProperty());
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("longProperty", 444, bean.getLongProperty());
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
            // removed other assertion
        }

        // Scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("shortProperty", (short) 555, bean.getShortProperty());
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
        assertEquals("stringProperty", "New String Property", bean.getStringProperty());
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
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
        // removed other assertion

        // Indexed Properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("intArray[2]", 200, intArray[2]);
    }

    public void testCopyPropertiesStandard_1_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            fail("Threw exception: " + e);
    }
    }

    public void testCopyPropertiesStandard_2_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        assertEquals("Copied boolean property", false, bean.getBooleanProperty());
    }

    public void testCopyPropertiesStandard_3_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        assertEquals("Copied byte property", (byte) 111, bean.getByteProperty());
    }

    public void testCopyPropertiesStandard_4_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        assertEquals("Copied double property", 333.33, bean.getDoubleProperty(), 0.005);
    }

    public void testCopyPropertiesStandard_5_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Copied int property", 333, bean.getIntProperty());
    }

    public void testCopyPropertiesStandard_6_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Copied long property", 3333, bean.getLongProperty());
    }

    public void testCopyPropertiesStandard_7_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Copied short property", (short) 33, bean.getShortProperty());
    }

    public void testCopyPropertiesStandard_8_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Copied string property", "Custom string", bean.getStringProperty());
    }

    public void testCopyPropertiesStandard_9_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        assertNotNull("dupProperty present", dupProperty);
    }

    public void testCopyPropertiesStandard_10_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        assertEquals("dupProperty length", 3, dupProperty.length);
    }

    public void testCopyPropertiesStandard_11_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        assertEquals("dupProperty[0]", "New 0", dupProperty[0]);
    }

    public void testCopyPropertiesStandard_12_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("dupProperty[1]", "New 1", dupProperty[1]);
    }

    public void testCopyPropertiesStandard_13_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("dupProperty[2]", "New 2", dupProperty[2]);
    }

    public void testCopyPropertiesStandard_14_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        assertNotNull("intArray present", intArray);
    }

    public void testCopyPropertiesStandard_15_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        assertEquals("intArray length", 3, intArray.length);
    }

    public void testCopyPropertiesStandard_16_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        assertEquals("intArray[0]", 100, intArray[0]);
    }

    public void testCopyPropertiesStandard_17_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("intArray[1]", 200, intArray[1]);
    }

    public void testCopyPropertiesStandard_18_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("intArray[2]", 300, intArray[2]);
    }

    public void testCopyPropertiesStandard_19_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String stringArray[] = bean.getStringArray();
        assertNotNull("stringArray present", stringArray);
    }

    public void testCopyPropertiesStandard_20_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String stringArray[] = bean.getStringArray();
        // removed other assertion
        assertEquals("stringArray length", 2, stringArray.length);
    }

    public void testCopyPropertiesStandard_21_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String stringArray[] = bean.getStringArray();
        // removed other assertion
        // removed other assertion
        assertEquals("stringArray[0]", "New 0", stringArray[0]);
    }

    public void testCopyPropertiesStandard_22_oe() {

        // Set up an origin bean with customized properties
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

        // Copy the origin bean to our destination test bean
        try {
            BeanUtils.copyProperties(bean, orig);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Validate the results for scalar properties
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Validate the results for array properties
        final String dupProperty[] = bean.getDupProperty();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final int intArray[] = bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        final String stringArray[] = bean.getStringArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("stringArray[1]", "New 1", stringArray[1]);
    }

    public void testDescribe_1_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
        } catch (final Exception e) {
            fail("Threw exception " + e);
    }
    }

    public void testDescribe_2_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            assertTrue("Property '" + describe + "' is present", map.containsKey(describe));
    }
    }

    public void testDescribe_3_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
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

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Verify existence of all the properties that should be present
        for (String describe : describes) {
            // removed other assertion
        }
        // removed other assertion

        // Verify the values of scalar properties
        assertEquals("Value of 'booleanProperty'", "true", map.get("booleanProperty"));
    }

    public void testDescribe_5_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
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
        assertEquals("Value of 'byteProperty'", "121", map.get("byteProperty"));
    }

    public void testDescribe_6_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
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
        assertEquals("Value of 'doubleProperty'", "321.0", map.get("doubleProperty"));
    }

    public void testDescribe_7_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
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
        assertEquals("Value of 'floatProperty'", "123.0", map.get("floatProperty"));
    }

    public void testDescribe_8_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
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
        assertEquals("Value of 'intProperty'", "123", map.get("intProperty"));
    }

    public void testDescribe_9_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
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
        assertEquals("Value of 'longProperty'", "321", map.get("longProperty"));
    }

    public void testDescribe_10_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
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
        assertEquals("Value of 'shortProperty'", "987", map.get("shortProperty"));
    }

    public void testDescribe_11_oe() {

        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(bean);
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
        // removed other assertion
        assertEquals("Value of 'stringProperty'", "This is a string", map.get("stringProperty"));
    }

    public void testGetArrayProperty_7_oe() {
        try {
            String arr[] = BeanUtils.getArrayProperty(bean, "stringArray");
            final String comp[] = bean.getStringArray();

            // removed other assertion

            arr = BeanUtils.getArrayProperty(bean, "intArray");
            final int iarr[] = bean.getIntArray();

            // removed other assertion


            // Test property which isn't array or collection
            arr = BeanUtils.getArrayProperty(bean, "shortProperty");
            final String shortAsString = "" + bean.getShortProperty();
            // removed other assertion
            // removed other assertion


            // Test comma delimited list
            bean.setStringProperty("ABC");
            arr = BeanUtils.getArrayProperty(bean, "stringProperty");
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetArrayProperty_8_oe() {
        try {
            String arr[] = BeanUtils.getArrayProperty(bean, "stringArray");
            final String comp[] = bean.getStringArray();

            // removed other assertion

            arr = BeanUtils.getArrayProperty(bean, "intArray");
            final int iarr[] = bean.getIntArray();

            // removed other assertion


            // Test property which isn't array or collection
            arr = BeanUtils.getArrayProperty(bean, "shortProperty");
            final String shortAsString = "" + bean.getShortProperty();
            // removed other assertion
            // removed other assertion


            // Test comma delimited list
            bean.setStringProperty("ABC");
            arr = BeanUtils.getArrayProperty(bean, "stringProperty");
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetArrayProperty_9_oe() {
        try {
            String arr[] = BeanUtils.getArrayProperty(bean, "stringArray");
            final String comp[] = bean.getStringArray();

            // removed other assertion

            arr = BeanUtils.getArrayProperty(bean, "intArray");
            final int iarr[] = bean.getIntArray();

            // removed other assertion


            // Test property which isn't array or collection
            arr = BeanUtils.getArrayProperty(bean, "shortProperty");
            final String shortAsString = "" + bean.getShortProperty();
            // removed other assertion
            // removed other assertion


            // Test comma delimited list
            bean.setStringProperty("ABC");
            arr = BeanUtils.getArrayProperty(bean, "stringProperty");
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetArrayPropertyDate_1_oe() {
        String[] value = null;
        try {
            bean.setDateArrayProperty(new java.util.Date[] {testUtilDate});
            value = BeanUtils.getArrayProperty(bean, "dateArrayProperty");
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testGetArrayPropertyDate_2_oe() {
        String[] value = null;
        try {
            bean.setDateArrayProperty(new java.util.Date[] {testUtilDate});
            value = BeanUtils.getArrayProperty(bean, "dateArrayProperty");
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("java.util.Date[] --> String[] length", 1, value.length);
    }

    public void testGetArrayPropertyDate_3_oe() {
        String[] value = null;
        try {
            bean.setDateArrayProperty(new java.util.Date[] {testUtilDate});
            value = BeanUtils.getArrayProperty(bean, "dateArrayProperty");
        } catch (final Throwable t) {
            // removed other assertion
        }
        // removed other assertion
        assertEquals("java.util.Date[] --> String[] value ", testUtilDate.toString(), value[0]);
    }

    public void testGetIndexedProperty1_3_oe() {
        try {
            String val = BeanUtils.getIndexedProperty(bean, "intIndexed[3]");
            String comp = String.valueOf(bean.getIntIndexed(3));
            // removed other assertion

            val = BeanUtils.getIndexedProperty(bean, "stringIndexed[3]");
            comp = bean.getStringIndexed(3);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetIndexedProperty1_4_oe() {
        try {
            String val = BeanUtils.getIndexedProperty(bean, "intIndexed[3]");
            String comp = String.valueOf(bean.getIntIndexed(3));
            // removed other assertion

            val = BeanUtils.getIndexedProperty(bean, "stringIndexed[3]");
            comp = bean.getStringIndexed(3);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetIndexedProperty1_5_oe() {
        try {
            String val = BeanUtils.getIndexedProperty(bean, "intIndexed[3]");
            String comp = String.valueOf(bean.getIntIndexed(3));
            // removed other assertion

            val = BeanUtils.getIndexedProperty(bean, "stringIndexed[3]");
            comp = bean.getStringIndexed(3);
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetIndexedPropertyDate_1_oe() {
        String value = null;
        try {
            bean.setDateArrayProperty(new java.util.Date[] {testUtilDate});
            value = BeanUtils.getIndexedProperty(bean, "dateArrayProperty[0]");
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testGetIndexedPropertyDate_2_oe() {
        String value = null;
        try {
            bean.setDateArrayProperty(new java.util.Date[] {testUtilDate});
            value = BeanUtils.getIndexedProperty(bean, "dateArrayProperty[0]");
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("java.util.Date[0] --> String", testUtilDate.toString(), value);
    }

    public void testGetIndexedProperty2_3_oe() {
        try {
            String val = BeanUtils.getIndexedProperty(bean, "intIndexed", 3);
            String comp = String.valueOf(bean.getIntIndexed(3));

            // removed other assertion

            val = BeanUtils.getIndexedProperty(bean, "stringIndexed", 3);
            comp = bean.getStringIndexed(3);

            // removed other assertion

        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetIndexedProperty2_4_oe() {
        try {
            String val = BeanUtils.getIndexedProperty(bean, "intIndexed", 3);
            String comp = String.valueOf(bean.getIntIndexed(3));

            // removed other assertion

            val = BeanUtils.getIndexedProperty(bean, "stringIndexed", 3);
            comp = bean.getStringIndexed(3);

            // removed other assertion

        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetIndexedProperty2_5_oe() {
        try {
            String val = BeanUtils.getIndexedProperty(bean, "intIndexed", 3);
            String comp = String.valueOf(bean.getIntIndexed(3));

            // removed other assertion

            val = BeanUtils.getIndexedProperty(bean, "stringIndexed", 3);
            comp = bean.getStringIndexed(3);

            // removed other assertion

        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetNestedProperty_2_oe() {
        try {
            final String val = BeanUtils.getNestedProperty(bean, "nested.stringProperty");
            final String comp = bean.getNested().getStringProperty();
            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetNestedProperty_3_oe() {
        try {
            final String val = BeanUtils.getNestedProperty(bean, "nested.stringProperty");
            final String comp = bean.getNested().getStringProperty();
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetNestedProperty_4_oe() {
        try {
            final String val = BeanUtils.getNestedProperty(bean, "nested.stringProperty");
            final String comp = bean.getNested().getStringProperty();
            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetGeneralProperty_2_oe() {
        try {
            final String val = BeanUtils.getProperty(bean, "nested.intIndexed[2]");
            final String comp = String.valueOf(bean.getIntIndexed(2));

            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetGeneralProperty_3_oe() {
        try {
            final String val = BeanUtils.getProperty(bean, "nested.intIndexed[2]");
            final String comp = String.valueOf(bean.getIntIndexed(2));

            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetGeneralProperty_4_oe() {
        try {
            final String val = BeanUtils.getProperty(bean, "nested.intIndexed[2]");
            final String comp = String.valueOf(bean.getIntIndexed(2));

            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimpleProperty_2_oe() {
        try {
            final String val = BeanUtils.getSimpleProperty(bean, "shortProperty");
            final String comp = String.valueOf(bean.getShortProperty());

            // removed other assertion
        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testGetSimpleProperty_3_oe() {
        try {
            final String val = BeanUtils.getSimpleProperty(bean, "shortProperty");
            final String comp = String.valueOf(bean.getShortProperty());

            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testGetSimpleProperty_4_oe() {
        try {
            final String val = BeanUtils.getSimpleProperty(bean, "shortProperty");
            final String comp = String.valueOf(bean.getShortProperty());

            // removed other assertion
        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            // removed other assertion
        } catch (final NoSuchMethodException e) {
            fail("NoSuchMethodException");
    }
    }

    public void testGetSimplePropertyDate_1_oe() {
        String value = null;
        try {
            bean.setDateProperty(testUtilDate);
            value = BeanUtils.getSimpleProperty(bean, "dateProperty");
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testGetSimplePropertyDate_2_oe() {
        String value = null;
        try {
            bean.setDateProperty(testUtilDate);
            value = BeanUtils.getSimpleProperty(bean, "dateProperty");
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("java.util.Date --> String", testUtilDate.toString(), value);
    }

    public void testPopulateArrayElements_11_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("intIndexed[0]", "100");
            map.put("intIndexed[2]", "120");
            map.put("intIndexed[4]", "140");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

            map.clear();
            map.put("stringIndexed[1]", "New String 1");
            map.put("stringIndexed[3]", "New String 3");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testPopulateArrayElements_12_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("intIndexed[0]", "100");
            map.put("intIndexed[2]", "120");
            map.put("intIndexed[4]", "140");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

            map.clear();
            map.put("stringIndexed[1]", "New String 1");
            map.put("stringIndexed[3]", "New String 3");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testPopulateArrayProperties_10_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            int intArray[] = new int[] { 123, 456, 789 };
            map.put("intArray", intArray);
            String stringArray[] = new String[]
                { "New String 0", "New String 1" };
            map.put("stringArray", stringArray);

            BeanUtils.populate(bean, map);

            intArray = bean.getIntArray();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            stringArray = bean.getStringArray();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testPopulateArrayProperties_11_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            int intArray[] = new int[] { 123, 456, 789 };
            map.put("intArray", intArray);
            String stringArray[] = new String[]
                { "New String 0", "New String 1" };
            map.put("stringArray", stringArray);

            BeanUtils.populate(bean, map);

            intArray = bean.getIntArray();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            stringArray = bean.getStringArray();
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testPopulateMapped_5_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("mappedProperty(First Key)", "New First Value");
            map.put("mappedProperty(Third Key)", "New Third Value");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testPopulateMapped_6_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("mappedProperty(First Key)", "New First Value");
            map.put("mappedProperty(Third Key)", "New Third Value");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testPopulateNested_10_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("nested.booleanProperty", "false");
            // booleanSecond is left at true
            map.put("nested.doubleProperty", "432.0");
            // floatProperty is left at 123.0
            map.put("nested.intProperty", "543");
            // longProperty is left at 321
            map.put("nested.shortProperty", "654");
            // stringProperty is left at "This is a string"
            map.put("nested.writeOnlyProperty", "New writeOnlyProperty value");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testPopulateNested_11_oe() {

        try {

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("nested.booleanProperty", "false");
            // booleanSecond is left at true
            map.put("nested.doubleProperty", "432.0");
            // floatProperty is left at 123.0
            map.put("nested.intProperty", "543");
            // longProperty is left at 321
            map.put("nested.shortProperty", "654");
            // stringProperty is left at "This is a string"
            map.put("nested.writeOnlyProperty", "New writeOnlyProperty value");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testPopulateScalar_13_oe() {

        try {

            bean.setNullProperty("Non-null value");

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("booleanProperty", "false");
            // booleanSecond is left at true
            map.put("byteProperty", "111");
            map.put("doubleProperty", "432.0");
            // floatProperty is left at 123.0
            map.put("intProperty", "543");
            map.put("longProperty", "");
            map.put("nullProperty", null);
            map.put("shortProperty", "654");
            // stringProperty is left at "This is a string"
            map.put("writeOnlyProperty", "New writeOnlyProperty value");
            map.put("readOnlyProperty", "New readOnlyProperty value");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            fail("IllegalAccessException");
    }
    }

    public void testPopulateScalar_14_oe() {

        try {

            bean.setNullProperty("Non-null value");

            final HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("booleanProperty", "false");
            // booleanSecond is left at true
            map.put("byteProperty", "111");
            map.put("doubleProperty", "432.0");
            // floatProperty is left at 123.0
            map.put("intProperty", "543");
            map.put("longProperty", "");
            map.put("nullProperty", null);
            map.put("shortProperty", "654");
            // stringProperty is left at "This is a string"
            map.put("writeOnlyProperty", "New writeOnlyProperty value");
            map.put("readOnlyProperty", "New readOnlyProperty value");

            BeanUtils.populate(bean, map);

            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion

        } catch (final IllegalAccessException e) {
            // removed other assertion
        } catch (final InvocationTargetException e) {
            fail("InvocationTargetException");
    }
    }

    public void testSetPropertyNullValues_1_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        // Scalar value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        assertNotNull("stringArray is not null", newValue);
    }

    public void testSetPropertyNullValues_2_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        // Scalar value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        assertTrue("stringArray of correct type", newValue instanceof String[]);
    }

    public void testSetPropertyNullValues_3_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        // Scalar value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        // removed other assertion
        assertEquals("stringArray length", 1, ((String[]) newValue).length);
    }

    public void testSetPropertyNullValues_4_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        // Scalar value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        // Indexed value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        assertNotNull("stringArray is not null", newValue);
    }

    public void testSetPropertyNullValues_5_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        // Scalar value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        // Indexed value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        assertTrue("stringArray of correct type", newValue instanceof String[]);
    }

    public void testSetPropertyNullValues_6_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        // Scalar value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        // Indexed value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        // removed other assertion
        assertEquals("stringArray length", 5, ((String[]) newValue).length);
    }

    public void testSetPropertyNullValues_7_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        // Scalar value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        // Indexed value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("stringArray[2] is null", ((String[]) newValue)[2] == null);
    }

    public void testSetPropertyNullValues_8_oe() throws Exception {

        Object oldValue = null;
        Object newValue = null;

        // Scalar value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        // Indexed value into array
        oldValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        BeanUtils.setProperty(bean, "stringArray[2]", (String) null);
        newValue = PropertyUtils.getSimpleProperty(bean, "stringArray");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        PropertyUtils.setProperty(bean, "stringArray", oldValue);

        // Value into scalar
        BeanUtils.setProperty(bean, "stringProperty", null);
        assertTrue("stringProperty is now null", BeanUtils.getProperty(bean, "stringProperty") == null);
    }

    public void testSetPropertyOnPrimitiveWrappers_1_oe() throws Exception {

        BeanUtils.setProperty(bean,"intProperty", new Integer(1));
        assertEquals(1,bean.getIntProperty());
    }

    public void testSetPropertyOnPrimitiveWrappers_2_oe() throws Exception {

        BeanUtils.setProperty(bean,"intProperty", new Integer(1));
        // removed other assertion
        BeanUtils.setProperty(bean,"stringProperty", new Integer(1));
        assertEquals(1, Integer.parseInt(bean.getStringProperty()));
    }

    public void testSetPropertyByte_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "byteProperty", new Byte((byte) 123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testSetPropertyByte_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "byteProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "byteProperty", new Double((double) 123));
        assertEquals((byte) 123, bean.getByteProperty());
        BeanUtils.setProperty(bean, "byteProperty", new Float((float) 123));
        assertEquals((byte) 123, bean.getByteProperty());
*/
        BeanUtils.setProperty(bean, "byteProperty", new Integer(123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testSetPropertyByte_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "byteProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "byteProperty", new Double((double) 123));
        assertEquals((byte) 123, bean.getByteProperty());
        BeanUtils.setProperty(bean, "byteProperty", new Float((float) 123));
        assertEquals((byte) 123, bean.getByteProperty());
*/
        BeanUtils.setProperty(bean, "byteProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "byteProperty", new Long(123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testSetPropertyByte_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "byteProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "byteProperty", new Double((double) 123));
        assertEquals((byte) 123, bean.getByteProperty());
        BeanUtils.setProperty(bean, "byteProperty", new Float((float) 123));
        assertEquals((byte) 123, bean.getByteProperty());
*/
        BeanUtils.setProperty(bean, "byteProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "byteProperty", new Long(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "byteProperty", new Short((short) 123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testSetPropertyConvert_1_oe() {
        try {
            BeanUtils.setProperty(bean, "dateProperty", testCalendar);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetPropertyConvert_2_oe() {
        try {
            BeanUtils.setProperty(bean, "dateProperty", testCalendar);
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("Calendar --> java.util.Date", testUtilDate, bean.getDateProperty());
    }

    public void testSetPropertyConvertFromString_1_oe() {
        try {
            BeanUtils.setProperty(bean, "dateProperty", testStringDate);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetPropertyConvertFromString_2_oe() {
        try {
            BeanUtils.setProperty(bean, "dateProperty", testStringDate);
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("String --> java.util.Date", testUtilDate, bean.getDateProperty());
    }

    public void testSetPropertyConvertToString_1_oe() {
        try {
            BeanUtils.setProperty(bean, "stringProperty", testUtilDate);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetPropertyConvertToString_2_oe() {
        try {
            BeanUtils.setProperty(bean, "stringProperty", testUtilDate);
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("java.util.Date --> String", testUtilDate.toString(), bean.getStringProperty());
    }

    public void testSetPropertyConvertToStringArray_1_oe() {
        try {
            bean.setStringArray(null);
            BeanUtils.setProperty(bean, "stringArray", new java.util.Date[] {testUtilDate});
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetPropertyConvertToStringArray_2_oe() {
        try {
            bean.setStringArray(null);
            BeanUtils.setProperty(bean, "stringArray", new java.util.Date[] {testUtilDate});
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("java.util.Date[] --> String[] length", 1, bean.getStringArray().length);
    }

    public void testSetPropertyConvertToStringArray_3_oe() {
        try {
            bean.setStringArray(null);
            BeanUtils.setProperty(bean, "stringArray", new java.util.Date[] {testUtilDate});
        } catch (final Throwable t) {
            // removed other assertion
        }
        // removed other assertion
        assertEquals("java.util.Date[] --> String[] value ", testUtilDate.toString(), bean.getStringArray()[0]);
    }

    public void testSetPropertyConvertToStringIndexed_1_oe() {
        try {
            bean.setStringArray(new String[1]);
            BeanUtils.setProperty(bean, "stringArray[0]", testUtilDate);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testSetPropertyConvertToStringIndexed_2_oe() {
        try {
            bean.setStringArray(new String[1]);
            BeanUtils.setProperty(bean, "stringArray[0]", testUtilDate);
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("java.util.Date --> String[]", testUtilDate.toString(), bean.getStringArray()[0]);
    }

    public void testSetPropertyDouble_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testSetPropertyDouble_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testSetPropertyDouble_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Float(123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testSetPropertyDouble_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Float(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Integer(123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testSetPropertyDouble_5_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Float(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Long(123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testSetPropertyDouble_6_oe() throws Exception {

        BeanUtils.setProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Double(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Float(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Long(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "doubleProperty", new Short((short) 123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testSetPropertyFloat_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testSetPropertyFloat_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testSetPropertyFloat_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Float(123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testSetPropertyFloat_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Float(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Integer(123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testSetPropertyFloat_5_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Float(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Long(123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testSetPropertyFloat_6_oe() throws Exception {

        BeanUtils.setProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Double(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Float(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Long(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "floatProperty", new Short((short) 123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testSetPropertyInteger_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testSetPropertyInteger_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((int) 123, bean.getIntProperty());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((int) 123, bean.getIntProperty());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testSetPropertyInteger_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((int) 123, bean.getIntProperty());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((int) 123, bean.getIntProperty());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "longProperty", new Long(123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testSetPropertyInteger_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((int) 123, bean.getIntProperty());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((int) 123, bean.getIntProperty());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "longProperty", new Long(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "longProperty", new Short((short) 123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testSetPropertyLong_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testSetPropertyLong_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((long) 123, bean.getLongProperty());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((long) 123, bean.getLongProperty());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testSetPropertyLong_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((long) 123, bean.getLongProperty());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((long) 123, bean.getLongProperty());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "longProperty", new Long(123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testSetPropertyLong_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "longProperty", new Double((double) 123));
        assertEquals((long) 123, bean.getLongProperty());
        BeanUtils.setProperty(bean, "longProperty", new Float((float) 123));
        assertEquals((long) 123, bean.getLongProperty());
*/
        BeanUtils.setProperty(bean, "longProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "longProperty", new Long(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "longProperty", new Short((short) 123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testSetPropertyNull_1_oe() throws Exception {

        bean.setNullProperty("non-null value");
        BeanUtils.setProperty(bean, "nullProperty", null);
        assertNull("nullProperty is null", bean.getNullProperty());
    }

    public void testSetPropertyShort_1_oe() throws Exception {

        BeanUtils.setProperty(bean, "shortProperty", new Byte((byte) 123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testSetPropertyShort_2_oe() throws Exception {

        BeanUtils.setProperty(bean, "shortProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "shortProperty", new Double((double) 123));
        assertEquals((short) 123, bean.getShortProperty());
        BeanUtils.setProperty(bean, "shortProperty", new Float((float) 123));
        assertEquals((short) 123, bean.getShortProperty());
*/
        BeanUtils.setProperty(bean, "shortProperty", new Integer(123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testSetPropertyShort_3_oe() throws Exception {

        BeanUtils.setProperty(bean, "shortProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "shortProperty", new Double((double) 123));
        assertEquals((short) 123, bean.getShortProperty());
        BeanUtils.setProperty(bean, "shortProperty", new Float((float) 123));
        assertEquals((short) 123, bean.getShortProperty());
*/
        BeanUtils.setProperty(bean, "shortProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "shortProperty", new Long(123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testSetPropertyShort_4_oe() throws Exception {

        BeanUtils.setProperty(bean, "shortProperty", new Byte((byte) 123));
        // removed other assertion
/*
        BeanUtils.setProperty(bean, "shortProperty", new Double((double) 123));
        assertEquals((short) 123, bean.getShortProperty());
        BeanUtils.setProperty(bean, "shortProperty", new Float((float) 123));
        assertEquals((short) 123, bean.getShortProperty());
*/
        BeanUtils.setProperty(bean, "shortProperty", new Integer(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "shortProperty", new Long(123));
        // removed other assertion
        BeanUtils.setProperty(bean, "shortProperty", new Short((short) 123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testSetPropertyStringToArray_1_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        assertEquals("length", 3, strArray.length);
    }

    public void testSetPropertyStringToArray_2_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        // removed other assertion
        assertEquals("value[0]", "ABC", strArray[0]);
    }

    public void testSetPropertyStringToArray_3_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        // removed other assertion
        // removed other assertion
        assertEquals("value[1]", "DEF", strArray[1]);
    }

    public void testSetPropertyStringToArray_4_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("value[2]", "GHI", strArray[2]);
    }

    public void testSetPropertyStringToArray_5_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        BeanUtils.setProperty(bean, "intArray", "0, 10, 20, 30, 40");
        final int[] intArray =  bean.getIntArray();
        assertEquals("length", 5, intArray.length);
    }

    public void testSetPropertyStringToArray_6_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        BeanUtils.setProperty(bean, "intArray", "0, 10, 20, 30, 40");
        final int[] intArray =  bean.getIntArray();
        // removed other assertion
        assertEquals("value[0]", 0, intArray[0]);
    }

    public void testSetPropertyStringToArray_7_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        BeanUtils.setProperty(bean, "intArray", "0, 10, 20, 30, 40");
        final int[] intArray =  bean.getIntArray();
        // removed other assertion
        // removed other assertion
        assertEquals("value[1]", 10, intArray[1]);
    }

    public void testSetPropertyStringToArray_8_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        BeanUtils.setProperty(bean, "intArray", "0, 10, 20, 30, 40");
        final int[] intArray =  bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("value[2]", 20, intArray[2]);
    }

    public void testSetPropertyStringToArray_9_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        BeanUtils.setProperty(bean, "intArray", "0, 10, 20, 30, 40");
        final int[] intArray =  bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("value[3]", 30, intArray[3]);
    }

    public void testSetPropertyStringToArray_10_oe() throws Exception {
        BeanUtils.setProperty(bean, "stringArray", "ABC,DEF,GHI");
        final String[] strArray =  bean.getStringArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        BeanUtils.setProperty(bean, "intArray", "0, 10, 20, 30, 40");
        final int[] intArray =  bean.getIntArray();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("value[4]", 40, intArray[4]);
    }

    public void testCopyPropertyByte_1_oe() throws Exception {

        BeanUtils.copyProperty(bean, "byteProperty", new Byte((byte) 123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testCopyPropertyByte_2_oe() throws Exception {

        BeanUtils.copyProperty(bean, "byteProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Double(123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testCopyPropertyByte_3_oe() throws Exception {

        BeanUtils.copyProperty(bean, "byteProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Float(123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testCopyPropertyByte_4_oe() throws Exception {

        BeanUtils.copyProperty(bean, "byteProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Integer(123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testCopyPropertyByte_5_oe() throws Exception {

        BeanUtils.copyProperty(bean, "byteProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Long(123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testCopyPropertyByte_6_oe() throws Exception {

        BeanUtils.copyProperty(bean, "byteProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Long(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "byteProperty", new Short((short) 123));
        assertEquals((byte) 123, bean.getByteProperty());
    }

    public void testCopyPropertyConvert_1_oe() {
        try {
            BeanUtils.copyProperty(bean, "dateProperty", testCalendar);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testCopyPropertyConvert_2_oe() {
        try {
            BeanUtils.copyProperty(bean, "dateProperty", testCalendar);
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("Calendar --> java.util.Date", testUtilDate, bean.getDateProperty());
    }

    public void testCopyPropertyConvertFromString_1_oe() {
        try {
            BeanUtils.copyProperty(bean, "dateProperty", testStringDate);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testCopyPropertyConvertFromString_2_oe() {
        try {
            BeanUtils.copyProperty(bean, "dateProperty", testStringDate);
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("String --> java.util.Date", testUtilDate, bean.getDateProperty());
    }

    public void testCopyPropertyConvertToString_1_oe() {
        try {
            BeanUtils.copyProperty(bean, "stringProperty", testUtilDate);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testCopyPropertyConvertToString_2_oe() {
        try {
            BeanUtils.copyProperty(bean, "stringProperty", testUtilDate);
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("java.util.Date --> String", testUtilDate.toString(), bean.getStringProperty());
    }

    public void testCopyPropertyConvertToStringArray_1_oe() {
        try {
            bean.setStringArray(null);
            BeanUtils.copyProperty(bean, "stringArray", new java.util.Date[] {testUtilDate});
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testCopyPropertyConvertToStringArray_2_oe() {
        try {
            bean.setStringArray(null);
            BeanUtils.copyProperty(bean, "stringArray", new java.util.Date[] {testUtilDate});
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("java.util.Date[] --> String[] length", 1, bean.getStringArray().length);
    }

    public void testCopyPropertyConvertToStringArray_3_oe() {
        try {
            bean.setStringArray(null);
            BeanUtils.copyProperty(bean, "stringArray", new java.util.Date[] {testUtilDate});
        } catch (final Throwable t) {
            // removed other assertion
        }
        // removed other assertion
        assertEquals("java.util.Date[] --> String[] value ", testUtilDate.toString(), bean.getStringArray()[0]);
    }

    public void testCopyPropertyConvertToStringIndexed_1_oe() {
        try {
            bean.setStringArray(new String[1]);
            BeanUtils.copyProperty(bean, "stringArray[0]", testUtilDate);
        } catch (final Throwable t) {
            fail("Threw " + t);
    }
    }

    public void testCopyPropertyConvertToStringIndexed_2_oe() {
        try {
            bean.setStringArray(new String[1]);
            BeanUtils.copyProperty(bean, "stringArray[0]", testUtilDate);
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("java.util.Date --> String[]", testUtilDate.toString(), bean.getStringArray()[0]);
    }

    public void testCopyPropertyDouble_1_oe() throws Exception {

        BeanUtils.copyProperty(bean, "doubleProperty", new Byte((byte) 123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testCopyPropertyDouble_2_oe() throws Exception {

        BeanUtils.copyProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Double(123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testCopyPropertyDouble_3_oe() throws Exception {

        BeanUtils.copyProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Float(123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testCopyPropertyDouble_4_oe() throws Exception {

        BeanUtils.copyProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Integer(123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testCopyPropertyDouble_5_oe() throws Exception {

        BeanUtils.copyProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Long(123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testCopyPropertyDouble_6_oe() throws Exception {

        BeanUtils.copyProperty(bean, "doubleProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Long(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "doubleProperty", new Short((short) 123));
        assertEquals(123, bean.getDoubleProperty(), 0.005);
    }

    public void testCopyPropertyFloat_1_oe() throws Exception {

        BeanUtils.copyProperty(bean, "floatProperty", new Byte((byte) 123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testCopyPropertyFloat_2_oe() throws Exception {

        BeanUtils.copyProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Double(123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testCopyPropertyFloat_3_oe() throws Exception {

        BeanUtils.copyProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Float(123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testCopyPropertyFloat_4_oe() throws Exception {

        BeanUtils.copyProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Integer(123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testCopyPropertyFloat_5_oe() throws Exception {

        BeanUtils.copyProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Long(123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testCopyPropertyFloat_6_oe() throws Exception {

        BeanUtils.copyProperty(bean, "floatProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Long(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "floatProperty", new Short((short) 123));
        assertEquals(123, bean.getFloatProperty(), 0.005);
    }

    public void testCopyPropertyInteger_1_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testCopyPropertyInteger_2_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testCopyPropertyInteger_3_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Float(123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testCopyPropertyInteger_4_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Integer(123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testCopyPropertyInteger_5_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Long(123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testCopyPropertyInteger_6_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Long(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Short((short) 123));
        assertEquals(123, bean.getIntProperty());
    }

    public void testCopyPropertyLong_1_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testCopyPropertyLong_2_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testCopyPropertyLong_3_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Float(123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testCopyPropertyLong_4_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Integer(123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testCopyPropertyLong_5_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Long(123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testCopyPropertyLong_6_oe() throws Exception {

        BeanUtils.copyProperty(bean, "longProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Long(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "longProperty", new Short((short) 123));
        assertEquals(123, bean.getLongProperty());
    }

    public void testCopyPropertyShort_1_oe() throws Exception {

        BeanUtils.copyProperty(bean, "shortProperty", new Byte((byte) 123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testCopyPropertyShort_2_oe() throws Exception {

        BeanUtils.copyProperty(bean, "shortProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Double(123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testCopyPropertyShort_3_oe() throws Exception {

        BeanUtils.copyProperty(bean, "shortProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Float(123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testCopyPropertyShort_4_oe() throws Exception {

        BeanUtils.copyProperty(bean, "shortProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Integer(123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testCopyPropertyShort_5_oe() throws Exception {

        BeanUtils.copyProperty(bean, "shortProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Long(123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testCopyPropertyShort_6_oe() throws Exception {

        BeanUtils.copyProperty(bean, "shortProperty", new Byte((byte) 123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Double(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Float(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Integer(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Long(123));
        // removed other assertion
        BeanUtils.copyProperty(bean, "shortProperty", new Short((short) 123));
        assertEquals((short) 123, bean.getShortProperty());
    }

    public void testCopyPropertyNestedSimple_1_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        assertNotNull(bean.getNested());
    }

    public void testCopyPropertyNestedSimple_2_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        assertEquals(0, bean.getIntProperty());
    }

    public void testCopyPropertyNestedSimple_3_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        assertEquals(1, bean.getNested().getIntProperty());
    }

    public void testCopyPropertyNestedSimple_4_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        assertNotNull(bean.getNested());
    }

    public void testCopyPropertyNestedSimple_5_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        // removed other assertion
        assertEquals(0, bean.getIntProperty());
    }

    public void testCopyPropertyNestedSimple_6_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        // removed other assertion
        // removed other assertion
        assertEquals(2, bean.getNested().getIntProperty());
    }

    public void testCopyPropertyNestedSimple_7_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Narrowing conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));
        assertNotNull(bean.getNested());
    }

    public void testCopyPropertyNestedSimple_8_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Narrowing conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));
        // removed other assertion
        assertEquals(0, bean.getIntProperty());
    }

    public void testCopyPropertyNestedSimple_9_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Narrowing conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));
        // removed other assertion
        // removed other assertion
        assertEquals(3, bean.getNested().getIntProperty());
    }

    public void testCopyPropertyNestedSimple_10_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Narrowing conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // String conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", "4");
        assertNotNull(bean.getNested());
    }

    public void testCopyPropertyNestedSimple_11_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Narrowing conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // String conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", "4");
        // removed other assertion
        assertEquals(0, bean.getIntProperty());
    }

    public void testCopyPropertyNestedSimple_12_oe() throws Exception {

        bean.setIntProperty(0);
        bean.getNested().setIntProperty(0);

        // No conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Integer(1));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Widening conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Byte((byte) 2));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Narrowing conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", new Long(3));
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // String conversion required
        BeanUtils.copyProperty(bean, "nested.intProperty", "4");
        // removed other assertion
        // removed other assertion
        assertEquals(4, bean.getNested().getIntProperty());
    }

    public void testCopyPropertyNull_1_oe() throws Exception {

        bean.setNullProperty("non-null value");
        BeanUtils.copyProperty(bean, "nullProperty", null);
        assertNull("nullProperty is null", bean.getNullProperty());
    }

    public void testCopyPropertyWriteOnly_1_oe() throws Exception {

        bean.setWriteOnlyProperty("Original value");

        // No conversion required
        BeanUtils.copyProperty(bean, "writeOnlyProperty", "New value");
        assertEquals("New value", bean.getWriteOnlyPropertyValue());
    }

    public void testCopyPropertyWriteOnly_2_oe() throws Exception {

        bean.setWriteOnlyProperty("Original value");

        // No conversion required
        BeanUtils.copyProperty(bean, "writeOnlyProperty", "New value");
        // removed other assertion

        // Integer->String conversion required
        BeanUtils.copyProperty(bean, "writeOnlyProperty", new Integer(123));
        assertEquals("123", bean.getWriteOnlyPropertyValue());
    }

    public void testSetPropertyWriteOnly_1_oe() throws Exception {

        bean.setWriteOnlyProperty("Original value");

        // No conversion required
        BeanUtils.setProperty(bean, "writeOnlyProperty", "New value");
        assertEquals("New value", bean.getWriteOnlyPropertyValue());
    }

    public void testSetPropertyWriteOnly_2_oe() throws Exception {

        bean.setWriteOnlyProperty("Original value");

        // No conversion required
        BeanUtils.setProperty(bean, "writeOnlyProperty", "New value");
        // removed other assertion

        // Integer->String conversion required
        BeanUtils.setProperty(bean, "writeOnlyProperty", new Integer(123));
        assertEquals("123", bean.getWriteOnlyPropertyValue());
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

        // removed other assertion
        try {
            BeanUtils.setProperty(bean, "mapProperty(mappedMap)(sub-key-3)", "SUB-KEY-3-UPDATED");
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

        // removed other assertion
        try {
            BeanUtils.setProperty(bean, "mapProperty(mappedMap)(sub-key-3)", "SUB-KEY-3-UPDATED");
        } catch (final Throwable t) {
            // removed other assertion
        }
        assertEquals("AFTER", "SUB-KEY-3-UPDATED", ((Map<?, ?>)bean.getMapProperty().get("mappedMap")).get("sub-key-3"));
    }

    public void testSeparateInstances_1_oe() throws Exception {
        final BeanUtilsBean utilsOne = new BeanUtilsBean(
                                                new ConvertUtilsBean(),
                                                new PropertyUtilsBean());
        final BeanUtilsBean utilsTwo = new BeanUtilsBean(
                                                new ConvertUtilsBean(),
                                                new PropertyUtilsBean());


        final TestBean bean = new TestBean();

        // Make sure what we're testing works
        bean.setBooleanProperty(false);
        utilsOne.setProperty(bean, "booleanProperty", "true");
        assertEquals("Set property failed (1)", bean.getBooleanProperty(), true);
    }

    public void testSeparateInstances_2_oe() throws Exception {
        final BeanUtilsBean utilsOne = new BeanUtilsBean(
                                                new ConvertUtilsBean(),
                                                new PropertyUtilsBean());
        final BeanUtilsBean utilsTwo = new BeanUtilsBean(
                                                new ConvertUtilsBean(),
                                                new PropertyUtilsBean());


        final TestBean bean = new TestBean();

        // Make sure what we're testing works
        bean.setBooleanProperty(false);
        utilsOne.setProperty(bean, "booleanProperty", "true");
        // removed other assertion

        bean.setBooleanProperty(false);
        utilsTwo.setProperty(bean, "booleanProperty", "true");
        assertEquals("Set property failed (2)", bean.getBooleanProperty(), true);
    }

    public void testSeparateInstances_5_oe() throws Exception {
        final BeanUtilsBean utilsOne = new BeanUtilsBean(
                                                new ConvertUtilsBean(),
                                                new PropertyUtilsBean());
        final BeanUtilsBean utilsTwo = new BeanUtilsBean(
                                                new ConvertUtilsBean(),
                                                new PropertyUtilsBean());


        final TestBean bean = new TestBean();

        // Make sure what we're testing works
        bean.setBooleanProperty(false);
        utilsOne.setProperty(bean, "booleanProperty", "true");
        // removed other assertion

        bean.setBooleanProperty(false);
        utilsTwo.setProperty(bean, "booleanProperty", "true");
        // removed other assertion

        // now change the registered conversion

        utilsOne.getConvertUtils().register(new ThrowExceptionConverter(), Boolean.TYPE);
        try {

            bean.setBooleanProperty(false);
            utilsOne.setProperty(bean, "booleanProperty", "true");
            // removed other assertion

        } catch (final PassTestException e) { /* Do nothing */ }

        // make sure that this conversion has no been registered in the other instance
        try {

            bean.setBooleanProperty(false);
            utilsTwo.setProperty(bean, "booleanProperty", "true");
            // removed other assertion

        } catch (final PassTestException e) {
            fail("Registed converter is used by other instances");
    }
    }

    public void testArrayPropertyConversion_1_oe() throws Exception {
        final BeanUtilsBean beanUtils = new BeanUtilsBean(
                                                    new ConvertUtilsBean(),
                                                    new PropertyUtilsBean());

        final TestBean bean = new TestBean();
        final String [] results = beanUtils.getArrayProperty(bean, "intArray");

        final int[] values = bean.getIntArray();
        assertEquals( "Converted array size not equal to property array size.", results.length, values.length);
    }

    public void testArrayPropertyConversion_2_oe() throws Exception {
        final BeanUtilsBean beanUtils = new BeanUtilsBean(
                                                    new ConvertUtilsBean(),
                                                    new PropertyUtilsBean());

        final TestBean bean = new TestBean();
        final String [] results = beanUtils.getArrayProperty(bean, "intArray");

        final int[] values = bean.getIntArray();
        // removed other assertion
        for (int i=0, size=values.length ;  i<size; i++) {
            assertEquals( "Value " + i + " incorrectly converted ", values[i] + "", results[i]);
    }
    }

    public void testMappedProperty_1_oe() throws Exception {
        final MappedPropertyTestBean bean = new MappedPropertyTestBean();

        BeanUtils.setProperty(bean, "mapproperty(this.that.the-other)", "some.dotty.value");

        assertEquals( "Mapped property set correctly", "some.dotty.value", bean.getMapproperty("this.that.the-other"));
    }

    public void testInitCause_1_oe() {
        if (isPre14JVM()) {
            return;
        }
        final String parentMsg = "PARENT-THROWABLE";
        final String causeMsg  = "THROWABLE-CAUSE";
        try {
            initCauseAndThrowException(parentMsg, causeMsg);
        } catch (final Throwable thrownParent) {
            assertEquals("Parent", parentMsg, thrownParent.getMessage());
    }
    }

    public void testInitCause_5_oe() {
        if (isPre14JVM()) {
            return;
        }
        final String parentMsg = "PARENT-THROWABLE";
        final String causeMsg  = "THROWABLE-CAUSE";
        try {
            initCauseAndThrowException(parentMsg, causeMsg);
        } catch (final Throwable thrownParent) {
            // removed other assertion
            try {
                // removed other assertion
                final Throwable thrownCause = getCause(thrownParent);
                // removed other assertion
                // removed other assertion
            } catch (final Throwable testError) {
                fail("If you're running JDK 1.3 then don't worry this should fail," + " if not then needs checking out: " + testError);
    }
    }
    }

}
