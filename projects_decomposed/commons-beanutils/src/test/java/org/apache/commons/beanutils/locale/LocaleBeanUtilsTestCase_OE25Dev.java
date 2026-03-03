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
package org.apache.commons.beanutils.locale;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.beanutils.TestBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test Case for {@link LocaleBeanUtils}.
 *
 * @version $Id$
 */
public class LocaleBeanUtilsTestCase_OE25Dev extends TestCase {

    private static Log log = LogFactory.getLog(LocaleBeanUtilsTestCase_OE25Dev.class);

    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public LocaleBeanUtilsTestCase_OE25Dev(final String name) {
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
     * @return Test Suite
     */
    public static Test suite() {
        return (new TestSuite(LocaleBeanUtilsTestCase_OE25Dev.class));
    }


    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {
    }


    // ------------------------------------------------ Individual Test Methods

    /**
     * Test setting a nested simple property
     */

    /**
     * Test setting a nested indexed property
     */

    public void testSetNestedPropertySimple_1_oe() {
        final TestBean bean = new TestBean();
        bean.getNested().setIntProperty(5);
        assertEquals("Initial value 5", 5, bean.getNested().getIntProperty());
    }

    public void testSetNestedPropertySimple_2_oe() {
        final TestBean bean = new TestBean();
        bean.getNested().setIntProperty(5);
        try {
            LocaleBeanUtils.setProperty(bean, "nested.intProperty", "123", null);
        } catch (final Throwable t) {
            log.error(t);
            fail("Threw " + t);
    }
    }

    public void testSetNestedPropertySimple_3_oe() {
        final TestBean bean = new TestBean();
        bean.getNested().setIntProperty(5);
        try {
            LocaleBeanUtils.setProperty(bean, "nested.intProperty", "123", null);
        } catch (final Throwable t) {
            log.error(t);
        }
        assertEquals("Check Set Value", 123, bean.getNested().getIntProperty());
    }

    public void testSetNestedPropertyIndexed_1_oe() {
        final TestBean bean = new TestBean();
        bean.getNested().setIntIndexed(1, 51);
        assertEquals("Initial value[1] 51", 51, bean.getNested().getIntIndexed(1));
    }

    public void testSetNestedPropertyIndexed_2_oe() {
        final TestBean bean = new TestBean();
        bean.getNested().setIntIndexed(1, 51);
        try {
            LocaleBeanUtils.setProperty(bean, "nested.intIndexed[1]", "123", null);
        } catch (final Throwable t) {
            log.error(t);
            fail("Threw " + t);
    }
    }

    public void testSetNestedPropertyIndexed_3_oe() {
        final TestBean bean = new TestBean();
        bean.getNested().setIntIndexed(1, 51);
        try {
            LocaleBeanUtils.setProperty(bean, "nested.intIndexed[1]", "123", null);
        } catch (final Throwable t) {
            log.error(t);
        }
        assertEquals("Check Set Value", 123, bean.getNested().getIntIndexed(1));
    }

}

