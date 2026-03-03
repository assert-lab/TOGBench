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


import java.math.BigDecimal;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Test accessing ResultSets via DynaBeans.
 *
 * @version $Id$
 */

public class DynaResultSetTestCase_OE25Dev extends TestCase {


    // ----------------------------------------------------- Instance Variables


    /**
     * The mock result set DynaClass to be tested.
     */
    protected ResultSetDynaClass dynaClass = null;


    /**
     * Names of the columns for this test.  Must match the order they are
     * defined in {@link TestResultSetMetaData}, and must be all lower case.
     */
    protected String columns[] =
    { "bigdecimalproperty", "booleanproperty",
      "byteproperty", "dateproperty",
      "doubleproperty", "floatproperty",
      "intproperty", "longproperty",
      "nullproperty", "shortproperty",
      "stringproperty", "timeproperty",
      "timestampproperty" };


    // ----------------------------------------------------------- Constructors


    /**
     * Construct a new instance of this test case.
     *
     * @param name Name of the test case
     */
    public DynaResultSetTestCase_OE25Dev(final String name) {

        super(name);

    }


    // --------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() throws Exception {

        dynaClass = new ResultSetDynaClass(TestResultSet.createProxy());

    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {

        return (new TestSuite(DynaResultSetTestCase_OE25Dev.class));

    }


    /**
     * Tear down instance variables required by this test case.
     */
    @Override
    public void tearDown() {

        dynaClass = null;

    }



    // ------------------------------------------------ Individual Test Methods


    /**
     * Test normal case column names (i.e. not converted to lower case)
     */


    public void testGetName_1_oe() {

        assertEquals("DynaClass name","org.apache.commons.beanutils.ResultSetDynaClass",dynaClass.getName());
    }

    public void testGetDynaProperty_2_oe() {

        try {
            dynaClass.getDynaProperty(null);
        } catch (final IllegalArgumentException e) {
        }

        DynaProperty dynaProp = dynaClass.getDynaProperty("unknownProperty");
        assertTrue("unknown property returns null",(dynaProp == null));
    }

    public void testGetDynaProperty_3_oe() {

        try {
            dynaClass.getDynaProperty(null);
        } catch (final IllegalArgumentException e) {
        }

        DynaProperty dynaProp = dynaClass.getDynaProperty("unknownProperty");

        dynaProp = dynaClass.getDynaProperty("stringproperty");
        assertNotNull("string property exists", dynaProp);
    }

    public void testGetDynaProperty_4_oe() {

        try {
            dynaClass.getDynaProperty(null);
        } catch (final IllegalArgumentException e) {
        }

        DynaProperty dynaProp = dynaClass.getDynaProperty("unknownProperty");

        dynaProp = dynaClass.getDynaProperty("stringproperty");
        assertEquals("string property name","stringproperty",dynaProp.getName());
    }

    public void testGetDynaProperty_5_oe() {

        try {
            dynaClass.getDynaProperty(null);
        } catch (final IllegalArgumentException e) {
        }

        DynaProperty dynaProp = dynaClass.getDynaProperty("unknownProperty");

        dynaProp = dynaClass.getDynaProperty("stringproperty");
        assertEquals("string property class",String.class,dynaProp.getType());
    }

    public void testGetDynaProperties_1_oe() {

        final DynaProperty dynaProps[] = dynaClass.getDynaProperties();
        assertNotNull("dynaProps exists", dynaProps);
    }

    public void testGetDynaProperties_2_oe() {

        final DynaProperty dynaProps[] = dynaClass.getDynaProperties();
        assertEquals("dynaProps length", columns.length, dynaProps.length);
    }

    public void testGetDynaProperties_3_oe() {

        final DynaProperty dynaProps[] = dynaClass.getDynaProperties();
        for (int i = 0; i < columns.length; i++) {
            assertEquals("Property " + columns[i],columns[i],dynaProps[i].getName());
    }
    }

    public void testNewInstance_2_oe() {

        try {
            dynaClass.newInstance();
        } catch (final UnsupportedOperationException e) {
        } catch (final Exception e) {
            fail("Threw exception " + e);
    }
    }

    public void testIteratorCount_1_oe() {

        final Iterator<?> rows = dynaClass.iterator();
        assertNotNull("iterator exists", rows);
    }

    public void testIteratorCount_2_oe() {

        final Iterator<?> rows = dynaClass.iterator();
        int n = 0;
        while (rows.hasNext()) {
            rows.next();
            n++;
            if (n > 10) {
                fail("Returned too many rows");
    }
    }
    }

    public void testIteratorCount_3_oe() {

        final Iterator<?> rows = dynaClass.iterator();
        int n = 0;
        while (rows.hasNext()) {
            rows.next();
            n++;
            if (n > 10) {
            }
        }
        assertEquals("iterator rows", 5, n);
    }

    public void testIteratorResults_2_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        assertNotNull("bigDecimalProperty exists", bigDecimalProperty);
    }

    public void testIteratorResults_3_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        assertTrue("bigDecimalProperty type",bigDecimalProperty instanceof BigDecimal);
    }

    public void testIteratorResults_4_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        assertEquals("bigDecimalProperty value",123.45,((BigDecimal)bigDecimalProperty).doubleValue(),0.005);
    }

    public void testIteratorResults_5_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");

        final Object intProperty = row.get("intproperty");
        assertNotNull("intProperty exists", intProperty);
    }

    public void testIteratorResults_6_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");

        final Object intProperty = row.get("intproperty");
        assertTrue("intProperty type",intProperty instanceof Integer);
    }

    public void testIteratorResults_7_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");

        final Object intProperty = row.get("intproperty");
        assertEquals("intProperty value",103,((Integer)intProperty).intValue());
    }

    public void testIteratorResults_8_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");

        final Object intProperty = row.get("intproperty");

        final Object nullProperty = row.get("nullproperty");
        assertNull("nullProperty null", nullProperty);
    }

    public void testIteratorResults_9_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");

        final Object intProperty = row.get("intproperty");

        final Object nullProperty = row.get("nullproperty");

        final Object stringProperty = row.get("stringproperty");
        assertNotNull("stringProperty exists", stringProperty);
    }

    public void testIteratorResults_10_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");

        final Object intProperty = row.get("intproperty");

        final Object nullProperty = row.get("nullproperty");

        final Object stringProperty = row.get("stringproperty");
        assertTrue("stringProperty type",stringProperty instanceof String);
    }

    public void testIteratorResults_11_oe() {

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigdecimalproperty");

        final Object intProperty = row.get("intproperty");

        final Object nullProperty = row.get("nullproperty");

        final Object stringProperty = row.get("stringproperty");
        assertEquals("stringProperty value","This is a string",(String)stringProperty);
    }

    public void testIteratorResultsNormalCase_1_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            fail("Error creating ResultSetDynaClass: " + e);
    }
    }

    public void testIteratorResultsNormalCase_3_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        assertNotNull("bigDecimalProperty exists", bigDecimalProperty);
    }

    public void testIteratorResultsNormalCase_4_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        assertTrue("bigDecimalProperty type",bigDecimalProperty instanceof BigDecimal);
    }

    public void testIteratorResultsNormalCase_5_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        assertEquals("bigDecimalProperty value",123.45,((BigDecimal)bigDecimalProperty).doubleValue(),0.005);
    }

    public void testIteratorResultsNormalCase_6_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");

        final Object intProperty = row.get("intProperty");
        assertNotNull("intProperty exists", intProperty);
    }

    public void testIteratorResultsNormalCase_7_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");

        final Object intProperty = row.get("intProperty");
        assertTrue("intProperty type",intProperty instanceof Integer);
    }

    public void testIteratorResultsNormalCase_8_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");

        final Object intProperty = row.get("intProperty");
        assertEquals("intProperty value",103,((Integer)intProperty).intValue());
    }

    public void testIteratorResultsNormalCase_9_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");

        final Object intProperty = row.get("intProperty");

        final Object nullProperty = row.get("nullProperty");
        assertNull("nullProperty null", nullProperty);
    }

    public void testIteratorResultsNormalCase_10_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");

        final Object intProperty = row.get("intProperty");

        final Object nullProperty = row.get("nullProperty");

        final Object stringProperty = row.get("stringProperty");
        assertNotNull("stringProperty exists", stringProperty);
    }

    public void testIteratorResultsNormalCase_11_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");

        final Object intProperty = row.get("intProperty");

        final Object nullProperty = row.get("nullProperty");

        final Object stringProperty = row.get("stringProperty");
        assertTrue("stringProperty type",stringProperty instanceof String);
    }

    public void testIteratorResultsNormalCase_12_oe() {
        ResultSetDynaClass dynaClass = null;
        try {
            dynaClass = new ResultSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
        }

        final Iterator<DynaBean> rows = dynaClass.iterator();
        rows.next();
        rows.next();
        final DynaBean row = rows.next();

        try {
            row.get("unknownProperty");
        } catch (final IllegalArgumentException e) {
        }


        final Object bigDecimalProperty = row.get("bigDecimalProperty");

        final Object intProperty = row.get("intProperty");

        final Object nullProperty = row.get("nullProperty");

        final Object stringProperty = row.get("stringProperty");
        assertEquals("stringProperty value","This is a string",(String)stringProperty);
    }

}
