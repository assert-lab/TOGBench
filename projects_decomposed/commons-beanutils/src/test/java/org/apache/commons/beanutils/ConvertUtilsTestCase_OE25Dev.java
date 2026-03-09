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


import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.converters.DateConverter;


/**
 * <p>
 *  Test Case for the ConvertUtils class.
 * </p>
 *
 * @version $Id$
 */

public class ConvertUtilsTestCase_OE25Dev extends TestCase {

    // ---------------------------------------------------- Instance Variables


    // ---------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public ConvertUtilsTestCase_OE25Dev(final String name) {
        super(name);
    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() {

        ConvertUtils.deregister();

    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(ConvertUtilsTestCase_OE25Dev.class));
    }


    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {
        // No action required
    }


    // ------------------------------------------------ Individual Test Methods


    /**
     * Negative String to primitive integer array tests.
     */
    public void testNegativeIntegerArray() {

        Object value = null;
        final int intArray[] = new int[0];

        value = ConvertUtils.convert((String) null, intArray.getClass());
        checkIntegerArray(value, intArray);
        value = ConvertUtils.convert("a", intArray.getClass());
        checkIntegerArray(value, intArray);
        value = ConvertUtils.convert("{ a }", intArray.getClass());
        checkIntegerArray(value, intArray);
        value = ConvertUtils.convert("1a3", intArray.getClass());
        checkIntegerArray(value, intArray);
        value = ConvertUtils.convert("{ 1a3 }", intArray.getClass());
        checkIntegerArray(value, intArray);
        value = ConvertUtils.convert("0,1a3", intArray.getClass());
        checkIntegerArray(value, intArray);
        value = ConvertUtils.convert("{ 0, 1a3 }", intArray.getClass());
        checkIntegerArray(value, intArray);


    }


    /**
     * Negative scalar conversion tests.  These rely on the standard
     * default value conversions in ConvertUtils.
     */
    public void testNegativeScalar() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("foo", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("foo", Byte.TYPE);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 0);

        value = ConvertUtils.convert("foo", Byte.class);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 0);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
            fail("Should have thrown conversion exception");
        } catch (final ConversionException e) {
            // Expected result
        }

        value = ConvertUtils.convert("foo", Double.TYPE);
        assertTrue(value instanceof Double);
        assertEquals(((Double)value).doubleValue(),0.0,0.005);

        value = ConvertUtils.convert("foo", Double.class);
        assertTrue(value instanceof Double);
        assertEquals(((Double) value).doubleValue(), 0.0, 0.005);

        value = ConvertUtils.convert("foo", Float.TYPE);
        assertTrue(value instanceof Float);
        assertEquals(((Float)value).floatValue(),(float)0.0,(float)0.005);

        value = ConvertUtils.convert("foo", Float.class);
        assertTrue(value instanceof Float);
        assertEquals(((Float)value).floatValue(),(float)0.0,(float)0.005);

        value = ConvertUtils.convert("foo", Integer.TYPE);
        assertTrue(value instanceof Integer);
        assertEquals(((Integer) value).intValue(), 0);

        value = ConvertUtils.convert("foo", Integer.class);
        assertTrue(value instanceof Integer);
        assertEquals(((Integer) value).intValue(), 0);

        value = ConvertUtils.convert("foo", Byte.TYPE);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 0);

        value = ConvertUtils.convert("foo", Long.class);
        assertTrue(value instanceof Long);
        assertEquals(((Long) value).longValue(), 0);

        value = ConvertUtils.convert("foo", Short.TYPE);
        assertTrue(value instanceof Short);
        assertEquals(((Short) value).shortValue(), (short) 0);

        value = ConvertUtils.convert("foo", Short.class);
        assertTrue(value instanceof Short);
        assertEquals(((Short) value).shortValue(), (short) 0);

    }


    /**
     * Negative String to String array tests.
     */
    public void testNegativeStringArray() {

        Object value = null;
        final String stringArray[] = new String[0];

        value = ConvertUtils.convert((String) null, stringArray.getClass());
        checkStringArray(value, stringArray);

    }


    /**
     * Test conversion of object to string for arrays.
     */
    public void testObjectToStringArray() {

        final int intArray0[] = new int[0];
        final int intArray1[] = { 123 };
        final int intArray2[] = { 123, 456 };
        final String stringArray0[] = new String[0];
        final String stringArray1[] = { "abc" };
        final String stringArray2[] = { "abc", "def" };

        assertEquals("intArray0",null,ConvertUtils.convert(intArray0));
        assertEquals("intArray1","123",ConvertUtils.convert(intArray1));
        assertEquals("intArray2","123",ConvertUtils.convert(intArray2));

        assertEquals("stringArray0",null,ConvertUtils.convert(stringArray0));
        assertEquals("stringArray1","abc",ConvertUtils.convert(stringArray1));
        assertEquals("stringArray2","abc",ConvertUtils.convert(stringArray2));

    }


    /**
     * Test conversion of object to string for scalars.
     */
    public void testObjectToStringScalar() {

        assertEquals("Boolean->String","false",ConvertUtils.convert(Boolean.FALSE));
        assertEquals("Boolean->String","true",ConvertUtils.convert(Boolean.TRUE));
        assertEquals("Byte->String","123",ConvertUtils.convert(new Byte((byte)123)));
        assertEquals("Character->String","a",ConvertUtils.convert(new Character('a')));
        assertEquals("Double->String","123.0",ConvertUtils.convert(new Double(123.0)));
        assertEquals("Float->String","123.0",ConvertUtils.convert(new Float((float)123.0)));
        assertEquals("Integer->String","123",ConvertUtils.convert(new Integer(123)));
        assertEquals("Long->String","123",ConvertUtils.convert(new Long(123)));
        assertEquals("Short->String","123",ConvertUtils.convert(new Short((short)123)));
        assertEquals("String->String","abc",ConvertUtils.convert("abc"));
        assertEquals("String->String null",null,ConvertUtils.convert(null));

    }


    /**
     * Positive array conversion tests.
     */
    public void testPositiveArray() {

        final String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        final int shape[] = new int[0];
        assertEquals(shape.getClass(), value.getClass());
        final int results1[] = (int[]) value;
        assertEquals(results1[0], 10);
        assertEquals(results1[1], 20);
        assertEquals(results1[2], 30);

        final String values2[] = { "100", "200", "300" };
        value = ConvertUtils.convert(values2, shape.getClass());
        assertEquals(shape.getClass(), value.getClass());
        final int results2[] = (int[]) value;
        assertEquals(results2[0], 100);
        assertEquals(results2[1], 200);
        assertEquals(results2[2], 300);

    }


    /**
     * Positive String to primitive integer array tests.
     */
    public void testPositiveIntegerArray() {

        Object value = null;
        final int intArray[] = new int[0];
        final int intArray1[] = new int[] { 0 };
        final int intArray2[] = new int[] { 0, 10 };

        value = ConvertUtils.convert("{  }", intArray.getClass());
        checkIntegerArray(value, intArray);

        value = ConvertUtils.convert("0", intArray.getClass());
        checkIntegerArray(value, intArray1);
        value = ConvertUtils.convert(" 0 ", intArray.getClass());
        checkIntegerArray(value, intArray1);
        value = ConvertUtils.convert("{ 0 }", intArray.getClass());
        checkIntegerArray(value, intArray1);

        value = ConvertUtils.convert("0,10", intArray.getClass());
        checkIntegerArray(value, intArray2);
        value = ConvertUtils.convert("0 10", intArray.getClass());
        checkIntegerArray(value, intArray2);
        value = ConvertUtils.convert("{0,10}", intArray.getClass());
        checkIntegerArray(value, intArray2);
        value = ConvertUtils.convert("{0 10}", intArray.getClass());
        checkIntegerArray(value, intArray2);
        value = ConvertUtils.convert("{ 0, 10 }", intArray.getClass());
        checkIntegerArray(value, intArray2);
        value = ConvertUtils.convert("{ 0 10 }", intArray.getClass());
        checkIntegerArray(value, intArray2);

    }


    /**
     * Positive scalar conversion tests.
     */
    public void testPositiveScalar() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("true", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("yes", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("yes", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("y", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("y", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("on", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("on", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), true);

        value = ConvertUtils.convert("false", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("false", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("no", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("no", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("n", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("n", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("off", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("off", Boolean.class);
        assertTrue(value instanceof Boolean);
        assertEquals(((Boolean) value).booleanValue(), false);

        value = ConvertUtils.convert("123", Byte.TYPE);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 123);

        value = ConvertUtils.convert("123", Byte.class);
        assertTrue(value instanceof Byte);
        assertEquals(((Byte) value).byteValue(), (byte) 123);

        value = ConvertUtils.convert("a", Character.TYPE);
        assertTrue(value instanceof Character);
        assertEquals(((Character) value).charValue(), 'a');

        value = ConvertUtils.convert("a", Character.class);
        assertTrue(value instanceof Character);
        assertEquals(((Character) value).charValue(), 'a');

        value = ConvertUtils.convert("java.lang.String", Class.class);
        assertTrue(value instanceof Class);
        assertEquals(String.class, value);

        value = ConvertUtils.convert("123.456", Double.TYPE);
        assertTrue(value instanceof Double);
        assertEquals(((Double) value).doubleValue(), 123.456, 0.005);

        value = ConvertUtils.convert("123.456", Double.class);
        assertTrue(value instanceof Double);
        assertEquals(((Double) value).doubleValue(), 123.456, 0.005);

        value = ConvertUtils.convert("123.456", Float.TYPE);
        assertTrue(value instanceof Float);
        assertEquals(((Float)value).floatValue(),(float)123.456,(float)0.005);

        value = ConvertUtils.convert("123.456", Float.class);
        assertTrue(value instanceof Float);
        assertEquals(((Float)value).floatValue(),(float)123.456,(float)0.005);

        value = ConvertUtils.convert("123", Integer.TYPE);
        assertTrue(value instanceof Integer);
        assertEquals(((Integer) value).intValue(), 123);

        value = ConvertUtils.convert("123", Integer.class);
        assertTrue(value instanceof Integer);
        assertEquals(((Integer) value).intValue(), 123);

        value = ConvertUtils.convert("123", Long.TYPE);
        assertTrue(value instanceof Long);
        assertEquals(((Long) value).longValue(), 123);

        value = ConvertUtils.convert("123", Long.class);
        assertTrue(value instanceof Long);
        assertEquals(((Long) value).longValue(), 123);

        value = ConvertUtils.convert("123", Short.TYPE);
        assertTrue(value instanceof Short);
        assertEquals(((Short) value).shortValue(), (short) 123);

        value = ConvertUtils.convert("123", Short.class);
        assertTrue(value instanceof Short);
        assertEquals(((Short) value).shortValue(), (short) 123);

        String input = null;

        input = "2002-03-17";
        value = ConvertUtils.convert(input, Date.class);
        assertTrue(value instanceof Date);
        assertEquals(input, value.toString());

        input = "20:30:40";
        value = ConvertUtils.convert(input, Time.class);
        assertTrue(value instanceof Time);
        assertEquals(input, value.toString());

        input = "2002-03-17 20:30:40.0";
        value = ConvertUtils.convert(input, Timestamp.class);
        assertTrue(value instanceof Timestamp);
        assertEquals(input, value.toString());

    }


    /**
     * Positive String to String array tests.
     */
    public void testPositiveStringArray() {

        Object value = null;
        final String stringArray[] = new String[0];
        final String stringArray1[] = new String[]
            { "abc" };
        final String stringArray2[] = new String[]
            { "abc", "de,f" };

        value = ConvertUtils.convert("", stringArray.getClass());
        checkStringArray(value, stringArray);
        value = ConvertUtils.convert(" ", stringArray.getClass());
        checkStringArray(value, stringArray);
        value = ConvertUtils.convert("{}", stringArray.getClass());
        checkStringArray(value, stringArray);
        value = ConvertUtils.convert("{  }", stringArray.getClass());
        checkStringArray(value, stringArray);

        value = ConvertUtils.convert("abc", stringArray.getClass());
        checkStringArray(value, stringArray1);
        value = ConvertUtils.convert("{abc}", stringArray.getClass());
        checkStringArray(value, stringArray1);
        value = ConvertUtils.convert("\"abc\"", stringArray.getClass());
        checkStringArray(value, stringArray1);
        value = ConvertUtils.convert("{\"abc\"}", stringArray.getClass());
        checkStringArray(value, stringArray1);
        value = ConvertUtils.convert("'abc'", stringArray.getClass());
        checkStringArray(value, stringArray1);
        value = ConvertUtils.convert("{'abc'}", stringArray.getClass());
        checkStringArray(value, stringArray1);

        value = ConvertUtils.convert("abc 'de,f'",
                                     stringArray.getClass());
        checkStringArray(value, stringArray2);
        value = ConvertUtils.convert("{abc, 'de,f'}",
                                     stringArray.getClass());
        checkStringArray(value, stringArray2);
        value = ConvertUtils.convert("\"abc\",\"de,f\"",
                                     stringArray.getClass());
        checkStringArray(value, stringArray2);
        value = ConvertUtils.convert("{\"abc\" 'de,f'}",
                                     stringArray.getClass());
        checkStringArray(value, stringArray2);
        value = ConvertUtils.convert("'abc' 'de,f'",
                                     stringArray.getClass());
        checkStringArray(value, stringArray2);
        value = ConvertUtils.convert("{'abc', \"de,f\"}",
                                     stringArray.getClass());
        checkStringArray(value, stringArray2);


    }

    public void testSeparateConvertInstances() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        // make sure that the test work ok before anything's changed
        Object
        value = utilsOne.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals("Standard conversion failed(1)",((Boolean)value).booleanValue(),true);

        value = utilsTwo.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals("Standard conversion failed(2)",((Boolean)value).booleanValue(),true);

        // now register a test

        utilsOne.register(new ThrowExceptionConverter(), Boolean.TYPE);
        try {

            utilsOne.convert("true", Boolean.TYPE);
            fail("Register converter failed.");

        } catch (final PassTestException e) { /* This shows that the registration has worked */ }

        try {
            // nothing should have changed
            value = utilsTwo.convert("true", Boolean.TYPE);
            assertTrue(value instanceof Boolean);
            assertEquals("Standard conversion failed(3)",((Boolean)value).booleanValue(),true);

        } catch (final PassTestException e) {
            // This is a failure since utilsTwo should still have
            // standard converters registered
            fail("Registering a converter for an instance should not effect another instance.");
        }

        // nothing we'll test deregister
        utilsOne.deregister();
        value = utilsOne.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals("Instance deregister failed.", ((Boolean) value).booleanValue(), true);

        value = utilsTwo.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals("Standard conversion failed(4)",((Boolean)value).booleanValue(),true);
    }

    public void testDeregisteringSingleConverter() throws Exception {
        // make sure that the test work ok before anything's changed
        final Object
        value = ConvertUtils.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
        assertEquals("Standard conversion failed(1)",((Boolean)value).booleanValue(),true);

        // we'll test deregister
        ConvertUtils.deregister(Boolean.TYPE);
        assertNull("Converter should be null",ConvertUtils.lookup(Boolean.TYPE));

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    // We need to use raw types in order to test legacy converters
    public void testConvertToString() throws Exception {
        final Converter dummyConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return value;
            }
        };

        final Converter fooConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return "Foo-Converter";
            }
        };

        final DateConverter dateConverter = new DateConverter();
        dateConverter.setLocale(Locale.US);

        final ConvertUtilsBean utils = new ConvertUtilsBean();
        utils.register(dateConverter, java.util.Date.class);
        utils.register(fooConverter, String.class);

        // Convert using registerd DateConverter
        final java.util.Date today = new java.util.Date();
        final DateFormat fmt = new SimpleDateFormat("M/d/yy"); /* US Short Format */
        final String expected = fmt.format(today);
        assertEquals("DateConverter M/d/yy", expected, utils.convert(today, String.class));

        // Date converter doesn't do String conversion - use String Converter
        utils.register(dummyConverter, java.util.Date.class);
        assertEquals("Date Converter doesn't do String conversion", "Foo-Converter", utils.convert(today, String.class));

        // No registered Date converter - use String Converter
        utils.deregister(java.util.Date.class);
        assertEquals("No registered Date converter", "Foo-Converter", utils.convert(today, String.class));

        // String Converter doesn't do Strings!!!
        utils.register(dummyConverter, String.class);
        assertEquals("String Converter doesn't do Strings!!!", today.toString(), utils.convert(today, String.class));

        // No registered Date or String converter - use Object's toString()
        utils.deregister(String.class);
        assertEquals("Object's toString()", today.toString(), utils.convert(today, String.class));

    }

    /**
     * Tests a conversion to an unsupported target type.
     */
    public void testConvertUnsupportedTargetType() {
        final ConvertUtilsBean utils = new ConvertUtilsBean();
        final Object value = "A test value";
        assertSame("Got different object",value,utils.convert(value,getClass()));
    }

    // -------------------------------------------------------- Private Methods


    private void checkIntegerArray(final Object value, final int intArray[]) {

        assertNotNull("Returned value is not null", value);
        assertEquals("Returned value is int[]",intArray.getClass(),value.getClass());
        final int results[] = (int[]) value;
        assertEquals("Returned array length", intArray.length, results.length);
        for (int i = 0; i < intArray.length; i++) {
            assertEquals("Returned array value " + i,intArray[i],results[i]);
        }

    }


    private void checkStringArray(final Object value, final String stringArray[]) {

        assertNotNull("Returned value is not null", value);
        assertEquals("Returned value is String[]",stringArray.getClass(),value.getClass());
        final String results[] = (String[]) value;
        assertEquals("Returned array length",stringArray.length,results.length);
        for (int i = 0; i < stringArray.length; i++) {
            assertEquals("Returned array value " + i,stringArray[i],results[i]);
        }

    }


    public void testNegativeScalar_1_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testNegativeScalar_2_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testNegativeScalar_3_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);
        assertTrue(value instanceof Boolean);
    }

    public void testNegativeScalar_4_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testNegativeScalar_5_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);
        assertTrue(value instanceof Byte);
    }

    public void testNegativeScalar_6_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);
        assertEquals(((Byte) value).byteValue(), (byte) 0);
    }

    public void testNegativeScalar_7_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);
        assertTrue(value instanceof Byte);
    }

    public void testNegativeScalar_8_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);
        assertEquals(((Byte) value).byteValue(), (byte) 0);
    }

    public void testNegativeScalar_10_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);
        assertTrue(value instanceof Double);
    }

    public void testNegativeScalar_11_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);
        assertEquals(((Double)value).doubleValue(),0.0,0.005);
    }

    public void testNegativeScalar_12_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);
        assertTrue(value instanceof Double);
    }

    public void testNegativeScalar_13_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);
        assertEquals(((Double) value).doubleValue(), 0.0, 0.005);
    }

    public void testNegativeScalar_14_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);
        assertTrue(value instanceof Float);
    }

    public void testNegativeScalar_15_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);
        assertEquals(((Float)value).floatValue(),(float)0.0,(float)0.005);
    }

    public void testNegativeScalar_16_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);
        assertTrue(value instanceof Float);
    }

    public void testNegativeScalar_17_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);
        assertEquals(((Float)value).floatValue(),(float)0.0,(float)0.005);
    }

    public void testNegativeScalar_18_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);
        assertTrue(value instanceof Integer);
    }

    public void testNegativeScalar_19_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);
        assertEquals(((Integer) value).intValue(), 0);
    }

    public void testNegativeScalar_20_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);
        assertTrue(value instanceof Integer);
    }

    public void testNegativeScalar_21_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);
        assertEquals(((Integer) value).intValue(), 0);
    }

    public void testNegativeScalar_22_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);
        assertTrue(value instanceof Byte);
    }

    public void testNegativeScalar_23_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);
        assertEquals(((Byte) value).byteValue(), (byte) 0);
    }

    public void testNegativeScalar_24_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Long.class);
        assertTrue(value instanceof Long);
    }

    public void testNegativeScalar_25_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Long.class);
        assertEquals(((Long) value).longValue(), 0);
    }

    public void testNegativeScalar_26_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Long.class);

        value = ConvertUtils.convert("foo", Short.TYPE);
        assertTrue(value instanceof Short);
    }

    public void testNegativeScalar_27_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Long.class);

        value = ConvertUtils.convert("foo", Short.TYPE);
        assertEquals(((Short) value).shortValue(), (short) 0);
    }

    public void testNegativeScalar_28_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Long.class);

        value = ConvertUtils.convert("foo", Short.TYPE);

        value = ConvertUtils.convert("foo", Short.class);
        assertTrue(value instanceof Short);
    }

    public void testNegativeScalar_29_oe() {

        Object value = null;

        value = ConvertUtils.convert("foo", Boolean.TYPE);

        value = ConvertUtils.convert("foo", Boolean.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Byte.class);

        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
        } catch (final ConversionException e) {
        }

        value = ConvertUtils.convert("foo", Double.TYPE);

        value = ConvertUtils.convert("foo", Double.class);

        value = ConvertUtils.convert("foo", Float.TYPE);

        value = ConvertUtils.convert("foo", Float.class);

        value = ConvertUtils.convert("foo", Integer.TYPE);

        value = ConvertUtils.convert("foo", Integer.class);

        value = ConvertUtils.convert("foo", Byte.TYPE);

        value = ConvertUtils.convert("foo", Long.class);

        value = ConvertUtils.convert("foo", Short.TYPE);

        value = ConvertUtils.convert("foo", Short.class);
        assertEquals(((Short) value).shortValue(), (short) 0);
    }

    public void testObjectToStringArray_1_oe() {

        final int intArray0[] = new int[0];
        final int intArray1[] = { 123 };
        final int intArray2[] = { 123, 456 };
        final String stringArray0[] = new String[0];
        final String stringArray1[] = { "abc" };
        final String stringArray2[] = { "abc", "def" };

        assertEquals("intArray0",null,ConvertUtils.convert(intArray0));
    }

    public void testObjectToStringArray_2_oe() {

        final int intArray0[] = new int[0];
        final int intArray1[] = { 123 };
        final int intArray2[] = { 123, 456 };
        final String stringArray0[] = new String[0];
        final String stringArray1[] = { "abc" };
        final String stringArray2[] = { "abc", "def" };

        assertEquals("intArray1","123",ConvertUtils.convert(intArray1));
    }

    public void testObjectToStringArray_3_oe() {

        final int intArray0[] = new int[0];
        final int intArray1[] = { 123 };
        final int intArray2[] = { 123, 456 };
        final String stringArray0[] = new String[0];
        final String stringArray1[] = { "abc" };
        final String stringArray2[] = { "abc", "def" };

        assertEquals("intArray2","123",ConvertUtils.convert(intArray2));
    }

    public void testObjectToStringArray_4_oe() {

        final int intArray0[] = new int[0];
        final int intArray1[] = { 123 };
        final int intArray2[] = { 123, 456 };
        final String stringArray0[] = new String[0];
        final String stringArray1[] = { "abc" };
        final String stringArray2[] = { "abc", "def" };


        assertEquals("stringArray0",null,ConvertUtils.convert(stringArray0));
    }

    public void testObjectToStringArray_5_oe() {

        final int intArray0[] = new int[0];
        final int intArray1[] = { 123 };
        final int intArray2[] = { 123, 456 };
        final String stringArray0[] = new String[0];
        final String stringArray1[] = { "abc" };
        final String stringArray2[] = { "abc", "def" };


        assertEquals("stringArray1","abc",ConvertUtils.convert(stringArray1));
    }

    public void testObjectToStringArray_6_oe() {

        final int intArray0[] = new int[0];
        final int intArray1[] = { 123 };
        final int intArray2[] = { 123, 456 };
        final String stringArray0[] = new String[0];
        final String stringArray1[] = { "abc" };
        final String stringArray2[] = { "abc", "def" };


        assertEquals("stringArray2","abc",ConvertUtils.convert(stringArray2));
    }

    public void testObjectToStringScalar_1_oe() {

        String a = "false";
        assertEquals("Boolean->String", a, ConvertUtils.convert(Boolean.FALSE));
    }

    public void testObjectToStringScalar_2_oe() {

        String a = "true";
        assertEquals("Boolean->String", a, ConvertUtils.convert(Boolean.TRUE));
    }

    public void testObjectToStringScalar_3_oe() {

        String a = "123";
        assertEquals("Byte->String", a, ConvertUtils.convert(new Byte((byte)123)));
    }

    public void testObjectToStringScalar_4_oe() {

        String a = "a";
        assertEquals("Character->String", a, ConvertUtils.convert(new Character('a')));
    }

    public void testObjectToStringScalar_5_oe() {

        String a = "123.0";
        assertEquals("Double->String", a, ConvertUtils.convert(new Double(123.0)));
    }

    public void testObjectToStringScalar_6_oe() {

        String a = "123.0";
        assertEquals("Float->String", a, ConvertUtils.convert(new Float((float)123.0)));
    }

    public void testObjectToStringScalar_7_oe() {

        String a = "123";
        assertEquals("Integer->String", a, ConvertUtils.convert(new Integer(123)));
    }

    public void testObjectToStringScalar_8_oe() {

        String a = "123";
        assertEquals("Long->String", a, ConvertUtils.convert(new Long(123)));
    }

    public void testObjectToStringScalar_9_oe() {

        String a = "123";
        assertEquals("Short->String", a, ConvertUtils.convert(new Short((short)123)));
    }

    public void testObjectToStringScalar_10_oe() {

        String a = "abc";
        assertEquals("String->String", a, ConvertUtils.convert("abc"));
    }

    public void testObjectToStringScalar_11_oe() {

        Object a = null;
        assertEquals("String->String null", a, ConvertUtils.convert(null));
    }

    public void testPositiveArray_1_oe() {

        final String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        final int shape[] = new int[0];
        assertEquals(shape.getClass(), value.getClass());
    }

    public void testPositiveArray_2_oe() {

        final String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        final int shape[] = new int[0];
        final int results1[] = (int[]) value;
        assertEquals(results1[0], 10);
    }

    public void testPositiveArray_3_oe() {

        final String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        final int shape[] = new int[0];
        final int results1[] = (int[]) value;
        assertEquals(results1[1], 20);
    }

    public void testPositiveArray_4_oe() {

        final String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        final int shape[] = new int[0];
        final int results1[] = (int[]) value;
        assertEquals(results1[2], 30);
    }

    public void testPositiveArray_5_oe() {

        final String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        final int shape[] = new int[0];
        final int results1[] = (int[]) value;

        final String values2[] = { "100", "200", "300" };
        value = ConvertUtils.convert(values2, shape.getClass());
        assertEquals(shape.getClass(), value.getClass());
    }

    public void testPositiveArray_6_oe() {

        final String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        final int shape[] = new int[0];
        final int results1[] = (int[]) value;

        final String values2[] = { "100", "200", "300" };
        value = ConvertUtils.convert(values2, shape.getClass());
        final int results2[] = (int[]) value;
        assertEquals(results2[0], 100);
    }

    public void testPositiveArray_7_oe() {

        final String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        final int shape[] = new int[0];
        final int results1[] = (int[]) value;

        final String values2[] = { "100", "200", "300" };
        value = ConvertUtils.convert(values2, shape.getClass());
        final int results2[] = (int[]) value;
        assertEquals(results2[1], 200);
    }

    public void testPositiveArray_8_oe() {

        final String values1[] = { "10", "20", "30" };
        Object value = ConvertUtils.convert(values1, Integer.TYPE);
        final int shape[] = new int[0];
        final int results1[] = (int[]) value;

        final String values2[] = { "100", "200", "300" };
        value = ConvertUtils.convert(values2, shape.getClass());
        final int results2[] = (int[]) value;
        assertEquals(results2[2], 300);
    }

    public void testPositiveScalar_1_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_2_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);
        assertEquals(((Boolean) value).booleanValue(), true);
    }

    public void testPositiveScalar_3_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_4_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);
        assertEquals(((Boolean) value).booleanValue(), true);
    }

    public void testPositiveScalar_5_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_6_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);
        assertEquals(((Boolean) value).booleanValue(), true);
    }

    public void testPositiveScalar_7_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_8_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);
        assertEquals(((Boolean) value).booleanValue(), true);
    }

    public void testPositiveScalar_9_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_10_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);
        assertEquals(((Boolean) value).booleanValue(), true);
    }

    public void testPositiveScalar_11_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_12_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);
        assertEquals(((Boolean) value).booleanValue(), true);
    }

    public void testPositiveScalar_13_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_14_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);
        assertEquals(((Boolean) value).booleanValue(), true);
    }

    public void testPositiveScalar_15_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_16_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);
        assertEquals(((Boolean) value).booleanValue(), true);
    }

    public void testPositiveScalar_17_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_18_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testPositiveScalar_19_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_20_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testPositiveScalar_21_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_22_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testPositiveScalar_23_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_24_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testPositiveScalar_25_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_26_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testPositiveScalar_27_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_28_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testPositiveScalar_29_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_30_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testPositiveScalar_31_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);
        assertTrue(value instanceof Boolean);
    }

    public void testPositiveScalar_32_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);
        assertEquals(((Boolean) value).booleanValue(), false);
    }

    public void testPositiveScalar_33_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);
        assertTrue(value instanceof Byte);
    }

    public void testPositiveScalar_34_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);
        assertEquals(((Byte) value).byteValue(), (byte) 123);
    }

    public void testPositiveScalar_35_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);
        assertTrue(value instanceof Byte);
    }

    public void testPositiveScalar_36_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);
        assertEquals(((Byte) value).byteValue(), (byte) 123);
    }

    public void testPositiveScalar_37_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);
        assertTrue(value instanceof Character);
    }

    public void testPositiveScalar_38_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);
        assertEquals(((Character) value).charValue(), 'a');
    }

    public void testPositiveScalar_39_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);
        assertTrue(value instanceof Character);
    }

    public void testPositiveScalar_40_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);
        assertEquals(((Character) value).charValue(), 'a');
    }

    public void testPositiveScalar_41_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);
        assertTrue(value instanceof Class);
    }

    public void testPositiveScalar_42_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);
        assertEquals(String.class, value);
    }

    public void testPositiveScalar_43_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);
        assertTrue(value instanceof Double);
    }

    public void testPositiveScalar_44_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);
        assertEquals(((Double) value).doubleValue(), 123.456, 0.005);
    }

    public void testPositiveScalar_45_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);
        assertTrue(value instanceof Double);
    }

    public void testPositiveScalar_46_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);
        assertEquals(((Double) value).doubleValue(), 123.456, 0.005);
    }

    public void testPositiveScalar_47_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);
        assertTrue(value instanceof Float);
    }

    public void testPositiveScalar_48_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);
        assertEquals(((Float)value).floatValue(),(float)123.456,(float)0.005);
    }

    public void testPositiveScalar_49_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);
        assertTrue(value instanceof Float);
    }

    public void testPositiveScalar_50_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);
        assertEquals(((Float)value).floatValue(),(float)123.456,(float)0.005);
    }

    public void testPositiveScalar_51_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);
        assertTrue(value instanceof Integer);
    }

    public void testPositiveScalar_52_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);
        assertEquals(((Integer) value).intValue(), 123);
    }

    public void testPositiveScalar_53_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);
        assertTrue(value instanceof Integer);
    }

    public void testPositiveScalar_54_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);
        assertEquals(((Integer) value).intValue(), 123);
    }

    public void testPositiveScalar_55_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);
        assertTrue(value instanceof Long);
    }

    public void testPositiveScalar_56_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);
        assertEquals(((Long) value).longValue(), 123);
    }

    public void testPositiveScalar_57_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);
        assertTrue(value instanceof Long);
    }

    public void testPositiveScalar_58_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);
        assertEquals(((Long) value).longValue(), 123);
    }

    public void testPositiveScalar_59_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);
        assertTrue(value instanceof Short);
    }

    public void testPositiveScalar_60_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);
        assertEquals(((Short) value).shortValue(), (short) 123);
    }

    public void testPositiveScalar_61_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);

        value = ConvertUtils.convert("123", Short.class);
        assertTrue(value instanceof Short);
    }

    public void testPositiveScalar_62_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);

        value = ConvertUtils.convert("123", Short.class);
        assertEquals(((Short) value).shortValue(), (short) 123);
    }

    public void testPositiveScalar_63_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);

        value = ConvertUtils.convert("123", Short.class);

        String input = null;

        input = "2002-03-17";
        value = ConvertUtils.convert(input, Date.class);
        assertTrue(value instanceof Date);
    }

    public void testPositiveScalar_64_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);

        value = ConvertUtils.convert("123", Short.class);

        String input = null;

        input = "2002-03-17";
        value = ConvertUtils.convert(input, Date.class);
        assertEquals(input, value.toString());
    }

    public void testPositiveScalar_65_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);

        value = ConvertUtils.convert("123", Short.class);

        String input = null;

        input = "2002-03-17";
        value = ConvertUtils.convert(input, Date.class);

        input = "20:30:40";
        value = ConvertUtils.convert(input, Time.class);
        assertTrue(value instanceof Time);
    }

    public void testPositiveScalar_66_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);

        value = ConvertUtils.convert("123", Short.class);

        String input = null;

        input = "2002-03-17";
        value = ConvertUtils.convert(input, Date.class);

        input = "20:30:40";
        value = ConvertUtils.convert(input, Time.class);
        assertEquals(input, value.toString());
    }

    public void testPositiveScalar_67_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);

        value = ConvertUtils.convert("123", Short.class);

        String input = null;

        input = "2002-03-17";
        value = ConvertUtils.convert(input, Date.class);

        input = "20:30:40";
        value = ConvertUtils.convert(input, Time.class);

        input = "2002-03-17 20:30:40.0";
        value = ConvertUtils.convert(input, Timestamp.class);
        assertTrue(value instanceof Timestamp);
    }

    public void testPositiveScalar_68_oe() {

        Object value = null;

        value = ConvertUtils.convert("true", Boolean.TYPE);

        value = ConvertUtils.convert("true", Boolean.class);

        value = ConvertUtils.convert("yes", Boolean.TYPE);

        value = ConvertUtils.convert("yes", Boolean.class);

        value = ConvertUtils.convert("y", Boolean.TYPE);

        value = ConvertUtils.convert("y", Boolean.class);

        value = ConvertUtils.convert("on", Boolean.TYPE);

        value = ConvertUtils.convert("on", Boolean.class);

        value = ConvertUtils.convert("false", Boolean.TYPE);

        value = ConvertUtils.convert("false", Boolean.class);

        value = ConvertUtils.convert("no", Boolean.TYPE);

        value = ConvertUtils.convert("no", Boolean.class);

        value = ConvertUtils.convert("n", Boolean.TYPE);

        value = ConvertUtils.convert("n", Boolean.class);

        value = ConvertUtils.convert("off", Boolean.TYPE);

        value = ConvertUtils.convert("off", Boolean.class);

        value = ConvertUtils.convert("123", Byte.TYPE);

        value = ConvertUtils.convert("123", Byte.class);

        value = ConvertUtils.convert("a", Character.TYPE);

        value = ConvertUtils.convert("a", Character.class);

        value = ConvertUtils.convert("java.lang.String", Class.class);

        value = ConvertUtils.convert("123.456", Double.TYPE);

        value = ConvertUtils.convert("123.456", Double.class);

        value = ConvertUtils.convert("123.456", Float.TYPE);

        value = ConvertUtils.convert("123.456", Float.class);

        value = ConvertUtils.convert("123", Integer.TYPE);

        value = ConvertUtils.convert("123", Integer.class);

        value = ConvertUtils.convert("123", Long.TYPE);

        value = ConvertUtils.convert("123", Long.class);

        value = ConvertUtils.convert("123", Short.TYPE);

        value = ConvertUtils.convert("123", Short.class);

        String input = null;

        input = "2002-03-17";
        value = ConvertUtils.convert(input, Date.class);

        input = "20:30:40";
        value = ConvertUtils.convert(input, Time.class);

        input = "2002-03-17 20:30:40.0";
        value = ConvertUtils.convert(input, Timestamp.class);
        assertEquals(input, value.toString());
    }

    public void testSeparateConvertInstances_1_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        Object
        value = utilsOne.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testSeparateConvertInstances_2_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        Object
        value = utilsOne.convert("true", Boolean.TYPE);
        assertEquals("Standard conversion failed(1)",((Boolean)value).booleanValue(),true);
    }

    public void testSeparateConvertInstances_3_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        Object
        value = utilsOne.convert("true", Boolean.TYPE);

        value = utilsTwo.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testSeparateConvertInstances_4_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        Object
        value = utilsOne.convert("true", Boolean.TYPE);

        value = utilsTwo.convert("true", Boolean.TYPE);
        assertEquals("Standard conversion failed(2)",((Boolean)value).booleanValue(),true);
    }

    public void testSeparateConvertInstances_8_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        Object
        value = utilsOne.convert("true", Boolean.TYPE);

        value = utilsTwo.convert("true", Boolean.TYPE);


        utilsOne.register(new ThrowExceptionConverter(), Boolean.TYPE);
        try {

            utilsOne.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) { /* This shows that the registration has worked */ }

        try {
            value = utilsTwo.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) {
            fail("Registering a converter for an instance should not effect another instance.");
    }
    }

    public void testSeparateConvertInstances_9_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        Object
        value = utilsOne.convert("true", Boolean.TYPE);

        value = utilsTwo.convert("true", Boolean.TYPE);


        utilsOne.register(new ThrowExceptionConverter(), Boolean.TYPE);
        try {

            utilsOne.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) { /* This shows that the registration has worked */ }

        try {
            value = utilsTwo.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) {
        }

        utilsOne.deregister();
        value = utilsOne.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testSeparateConvertInstances_10_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        Object
        value = utilsOne.convert("true", Boolean.TYPE);

        value = utilsTwo.convert("true", Boolean.TYPE);


        utilsOne.register(new ThrowExceptionConverter(), Boolean.TYPE);
        try {

            utilsOne.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) { /* This shows that the registration has worked */ }

        try {
            value = utilsTwo.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) {
        }

        utilsOne.deregister();
        value = utilsOne.convert("true", Boolean.TYPE);
        assertEquals("Instance deregister failed.", ((Boolean) value).booleanValue(), true);
    }

    public void testSeparateConvertInstances_11_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        Object
        value = utilsOne.convert("true", Boolean.TYPE);

        value = utilsTwo.convert("true", Boolean.TYPE);


        utilsOne.register(new ThrowExceptionConverter(), Boolean.TYPE);
        try {

            utilsOne.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) { /* This shows that the registration has worked */ }

        try {
            value = utilsTwo.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) {
        }

        utilsOne.deregister();
        value = utilsOne.convert("true", Boolean.TYPE);

        value = utilsTwo.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testSeparateConvertInstances_12_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();

        Object
        value = utilsOne.convert("true", Boolean.TYPE);

        value = utilsTwo.convert("true", Boolean.TYPE);


        utilsOne.register(new ThrowExceptionConverter(), Boolean.TYPE);
        try {

            utilsOne.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) { /* This shows that the registration has worked */ }

        try {
            value = utilsTwo.convert("true", Boolean.TYPE);

        } catch (final PassTestException e) {
        }

        utilsOne.deregister();
        value = utilsOne.convert("true", Boolean.TYPE);

        value = utilsTwo.convert("true", Boolean.TYPE);
        assertEquals("Standard conversion failed(4)",((Boolean)value).booleanValue(),true);
    }

    public void testDeregisteringSingleConverter_1_oe() throws Exception {
        final Object value = ConvertUtils.convert("true", Boolean.TYPE);
        assertTrue(value instanceof Boolean);
    }

    public void testDeregisteringSingleConverter_2_oe() throws Exception {
        final Object value = ConvertUtils.convert("true", Boolean.TYPE);
        assertEquals("Standard conversion failed(1)",((Boolean)value).booleanValue(),true);
    }

    public void testDeregisteringSingleConverter_3_oe() throws Exception {
        final Object value = ConvertUtils.convert("true", Boolean.TYPE);

        ConvertUtils.deregister(Boolean.TYPE);
        assertNull("Converter should be null",ConvertUtils.lookup(Boolean.TYPE));
    }

    public void testConvertToString_1_oe() throws Exception {
        final Converter dummyConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return value;
            }
        };

        final Converter fooConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return "Foo-Converter";
            }
        };

        final DateConverter dateConverter = new DateConverter();
        dateConverter.setLocale(Locale.US);

        final ConvertUtilsBean utils = new ConvertUtilsBean();
        utils.register(dateConverter, java.util.Date.class);
        utils.register(fooConverter, String.class);

        final java.util.Date today = new java.util.Date();
        final DateFormat fmt = new SimpleDateFormat("M/d/yy"); /* US Short Format */
        final String expected = fmt.format(today);
        assertEquals("DateConverter M/d/yy", expected, utils.convert(today, String.class));
    }

    public void testConvertToString_2_oe() throws Exception {
        final Converter dummyConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return value;
            }
        };

        final Converter fooConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return "Foo-Converter";
            }
        };

        final DateConverter dateConverter = new DateConverter();
        dateConverter.setLocale(Locale.US);

        final ConvertUtilsBean utils = new ConvertUtilsBean();
        utils.register(dateConverter, java.util.Date.class);
        utils.register(fooConverter, String.class);

        final java.util.Date today = new java.util.Date();
        final DateFormat fmt = new SimpleDateFormat("M/d/yy"); /* US Short Format */
        final String expected = fmt.format(today);

        utils.register(dummyConverter, java.util.Date.class);
        assertEquals("Date Converter doesn't do String conversion", "Foo-Converter", utils.convert(today, String.class));
    }

    public void testConvertToString_3_oe() throws Exception {
        final Converter dummyConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return value;
            }
        };

        final Converter fooConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return "Foo-Converter";
            }
        };

        final DateConverter dateConverter = new DateConverter();
        dateConverter.setLocale(Locale.US);

        final ConvertUtilsBean utils = new ConvertUtilsBean();
        utils.register(dateConverter, java.util.Date.class);
        utils.register(fooConverter, String.class);

        final java.util.Date today = new java.util.Date();
        final DateFormat fmt = new SimpleDateFormat("M/d/yy"); /* US Short Format */
        final String expected = fmt.format(today);

        utils.register(dummyConverter, java.util.Date.class);

        utils.deregister(java.util.Date.class);
        assertEquals("No registered Date converter", "Foo-Converter", utils.convert(today, String.class));
    }

    public void testConvertToString_4_oe() throws Exception {
        final Converter dummyConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return value;
            }
        };

        final Converter fooConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return "Foo-Converter";
            }
        };

        final DateConverter dateConverter = new DateConverter();
        dateConverter.setLocale(Locale.US);

        final ConvertUtilsBean utils = new ConvertUtilsBean();
        utils.register(dateConverter, java.util.Date.class);
        utils.register(fooConverter, String.class);

        final java.util.Date today = new java.util.Date();
        final DateFormat fmt = new SimpleDateFormat("M/d/yy"); /* US Short Format */
        final String expected = fmt.format(today);

        utils.register(dummyConverter, java.util.Date.class);

        utils.deregister(java.util.Date.class);

        utils.register(dummyConverter, String.class);
        assertEquals("String Converter doesn't do Strings!!!", today.toString(), utils.convert(today, String.class));
    }

    public void testConvertToString_5_oe() throws Exception {
        final Converter dummyConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return value;
            }
        };

        final Converter fooConverter = new Converter() {
            public Object convert(final Class type, final Object value) {
                return "Foo-Converter";
            }
        };

        final DateConverter dateConverter = new DateConverter();
        dateConverter.setLocale(Locale.US);

        final ConvertUtilsBean utils = new ConvertUtilsBean();
        utils.register(dateConverter, java.util.Date.class);
        utils.register(fooConverter, String.class);

        final java.util.Date today = new java.util.Date();
        final DateFormat fmt = new SimpleDateFormat("M/d/yy"); /* US Short Format */
        final String expected = fmt.format(today);

        utils.register(dummyConverter, java.util.Date.class);

        utils.deregister(java.util.Date.class);

        utils.register(dummyConverter, String.class);

        utils.deregister(String.class);
        assertEquals("Object's toString()", today.toString(), utils.convert(today, String.class));
    }

    public void testConvertUnsupportedTargetType_1_oe() {
        final ConvertUtilsBean utils = new ConvertUtilsBean();
        final Object value = "A test value";
        assertSame("Got different object",value,utils.convert(value,getClass()));
    }

public void testNegativeScalar_oe_101_oe() {
        Object value = null;
        value = ConvertUtils.convert("foo", Boolean.TYPE);
        value = ConvertUtils.convert("foo", Boolean.class);
        value = ConvertUtils.convert("foo", Byte.TYPE);
        value = ConvertUtils.convert("foo", Byte.class);
        try {
            value = ConvertUtils.convert
                ("org.apache.commons.beanutils.Undefined", Class.class);
            fail("Should have thrown conversion exception");
        } catch (final ConversionException e) {
        }
}

public void testSeparateConvertInstances_oe_101_oe() throws Exception {
        final ConvertUtilsBean utilsOne = new ConvertUtilsBean();
        final ConvertUtilsBean utilsTwo = new ConvertUtilsBean();
        Object
        value = utilsOne.convert("true", Boolean.TYPE);
        value = utilsTwo.convert("true", Boolean.TYPE);
        utilsOne.register(new ThrowExceptionConverter(), Boolean.TYPE);
        try {

            utilsOne.convert("true", Boolean.TYPE);
            fail("Register converter failed.");

        } catch (final PassTestException e) { /* This shows that the registration has worked */ }
}

}

