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

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test Case for the {@link BeanUtilsBean2}.
 *
 * @version $Id$
 */
public class BeanUtils2TestCase_OE25Dev extends BeanUtilsTestCase {

    // ---------------------------------------------------------- Constructors

    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public BeanUtils2TestCase_OE25Dev(final String name) {
        super(name);
    }


    // -------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() {
        ConvertUtils.deregister();
        BeanUtilsBean.setInstance(new BeanUtilsBean2());
        setUpShared();
    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {
        return (new TestSuite(BeanUtils2TestCase.class));
    }

    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {
        bean = null;
    }

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
     * Test <code>getArrayProperty()</code> converting to a String.
     */

    /**
     * Test <code>getArrayProperty()</code> converting to a String.
     */

    /**
     * Test <code>getSimpleProperty()</code> converting to a String.
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
        assertEquals("java.util.Date --> String", testStringDate, bean.getStringProperty());
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
        assertEquals("java.util.Date[] --> String[] value ", testStringDate, bean.getStringArray()[0]);
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
        assertEquals("java.util.Date[] --> String[] length", 1, bean.getStringArray().length);
    }

    public void testCopyPropertyConvertToStringIndexed_3_oe() {
        try {
            bean.setStringArray(new String[1]);
            BeanUtils.copyProperty(bean, "stringArray[0]", testUtilDate);
        } catch (final Throwable t) {
            // removed other assertion
        }
        // removed other assertion
        assertEquals("java.util.Date[] --> String[] value ", testStringDate, bean.getStringArray()[0]);
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
        assertEquals("java.util.Date[] --> String[] value ", testStringDate, value[0]);
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
        assertEquals("java.util.Date[0] --> String", testStringDate, value);
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
        assertEquals("java.util.Date --> String", testStringDate, value);
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
        assertEquals("java.util.Date --> String", testStringDate, bean.getStringProperty());
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
        assertEquals("java.util.Date[] --> String[] value ", testStringDate, bean.getStringArray()[0]);
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
        assertEquals("java.util.Date --> String[]", testStringDate, bean.getStringArray()[0]);
    }

}
