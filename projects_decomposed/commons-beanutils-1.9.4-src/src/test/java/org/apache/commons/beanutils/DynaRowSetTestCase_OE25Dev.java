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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Test accessing RowSets via DynaBeans.
 *
 * @version $Id$
 */

public class DynaRowSetTestCase_OE25Dev extends TestCase {


    // ----------------------------------------------------- Instance Variables


    /**
     * The mock result set DynaClass to be tested.
     */
    protected RowSetDynaClass dynaClass = null;


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
    public DynaRowSetTestCase_OE25Dev(final String name) {

        super(name);

    }


    // --------------------------------------------------- Overall Test Methods


    /**
     * Set up instance variables required by this test case.
     */
    @Override
    public void setUp() throws Exception {

        dynaClass = new RowSetDynaClass(TestResultSet.createProxy());

    }


    /**
     * Return the tests included in this test suite.
     */
    public static Test suite() {

        return (new TestSuite(DynaRowSetTestCase.class));

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

    /**
     * Test issues associated with Oracle JDBC driver.
     *
     * See issue# https://issues.apache.org/jira/browse/BEANUTILS-142
     *
     * @throws Exception if an error occurs
     */

    /**
     * A proxy ResultSet implementation that returns Timstamp for a date column.
     *
     * See issue# https://issues.apache.org/jira/browse/BEANUTILS-142
     */
    private static class TestResultSetInconsistent extends  TestResultSet {

        public TestResultSetInconsistent(final ResultSetMetaData metaData) {
            super(metaData);
        }
        /**
         * Get an columns's value
         * @param columnName Name of the column
         * @return the column value
         * @throws SQLException if an error occurs
         */
        @Override
        public Object getObject(final String columnName) throws SQLException {
            if ("timestampProperty".equals(columnName)) {
                return new CustomTimestamp();
            } else {
                return super.getObject(columnName);
            }
        }

    }

    /**
     * A proxy ResultSetMetaData implementation that returns a class name that
     * is inconsistent with the type returned by the ResultSet.getObject() method.
     *
     * See issue# https://issues.apache.org/jira/browse/BEANUTILS-142
     */
    private static class TestResultSetMetaDataInconsistent extends  TestResultSetMetaData {

        /**
         * This method substitues class names of "java.sql.Timestamp" with
         * "java.sql.Date" to test inconsistent JDBC drivers.
         *
         * @param columnIndex The column index
         * @return The column class name
         * @throws SQLException if an error occurs
         */
        @Override
        public String getColumnClassName(final int columnIndex) throws SQLException {
            final String columnName = getColumnName(columnIndex);
            if (columnName.equals("dateProperty")) {
                return java.sql.Timestamp.class.getName();
            } else if (columnName.equals("timestampProperty")) {
                return CustomTimestamp.class.getName();
            } else {
                return super.getColumnClassName(columnIndex);
            }
        }
    }
    private static class CustomTimestamp {
        private final long timestamp = new java.util.Date().getTime();
        @Override
        public String toString() {
            return "CustomTimestamp[" + timestamp + "]";
        }
    }

    public void testGetName_1_oe() {

        assertEquals("DynaClass name", "org.apache.commons.beanutils.RowSetDynaClass", dynaClass.getName());
    }

    public void testGetDynaProperty_2_oe() {

        // Invalid argument test
        try {
            dynaClass.getDynaProperty(null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Negative test
        DynaProperty dynaProp = dynaClass.getDynaProperty("unknownProperty");
        assertTrue("unknown property returns null", (dynaProp == null));
    }

    public void testGetDynaProperty_3_oe() {

        // Invalid argument test
        try {
            dynaClass.getDynaProperty(null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Negative test
        DynaProperty dynaProp = dynaClass.getDynaProperty("unknownProperty");
        // removed other assertion

        // Positive test
        dynaProp = dynaClass.getDynaProperty("stringproperty");
        assertNotNull("string property exists", dynaProp);
    }

    public void testGetDynaProperty_4_oe() {

        // Invalid argument test
        try {
            dynaClass.getDynaProperty(null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Negative test
        DynaProperty dynaProp = dynaClass.getDynaProperty("unknownProperty");
        // removed other assertion

        // Positive test
        dynaProp = dynaClass.getDynaProperty("stringproperty");
        // removed other assertion
        assertEquals("string property name", "stringproperty", dynaProp.getName());
    }

    public void testGetDynaProperty_5_oe() {

        // Invalid argument test
        try {
            dynaClass.getDynaProperty(null);
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Negative test
        DynaProperty dynaProp = dynaClass.getDynaProperty("unknownProperty");
        // removed other assertion

        // Positive test
        dynaProp = dynaClass.getDynaProperty("stringproperty");
        // removed other assertion
        // removed other assertion
        assertEquals("string property class", String.class, dynaProp.getType());
    }

    public void testGetDynaProperties_1_oe() {

        final DynaProperty dynaProps[] = dynaClass.getDynaProperties();
        assertNotNull("dynaProps exists", dynaProps);
    }

    public void testGetDynaProperties_2_oe() {

        final DynaProperty dynaProps[] = dynaClass.getDynaProperties();
        // removed other assertion
        assertEquals("dynaProps length", columns.length, dynaProps.length);
    }

    public void testGetDynaProperties_3_oe() {

        final DynaProperty dynaProps[] = dynaClass.getDynaProperties();
        // removed other assertion
        // removed other assertion
        for (int i = 0; i < columns.length; i++) {
            assertEquals("Property " + columns[i], columns[i], dynaProps[i].getName());
    }
    }

    public void testNewInstance_2_oe() {

        try {
            dynaClass.newInstance();
            // removed other assertion
        } catch (final UnsupportedOperationException e) {
            // Expected result
        } catch (final Exception e) {
            fail("Threw exception " + e);
    }
    }

    public void testListCount_1_oe() {

        final List<DynaBean> rows = dynaClass.getRows();
        assertNotNull("list exists", rows);
    }

    public void testListCount_2_oe() {

        final List<DynaBean> rows = dynaClass.getRows();
        // removed other assertion
        assertEquals("list row count", 5, rows.size());
    }

    public void testListResults_2_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        assertNotNull("bigDecimalProperty exists", bigDecimalProperty);
    }

    public void testListResults_3_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        // removed other assertion
        assertTrue("bigDecimalProperty type", bigDecimalProperty instanceof BigDecimal);
    }

    public void testListResults_4_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        // removed other assertion
        // removed other assertion
        assertEquals("bigDecimalProperty value", 123.45, ((BigDecimal) bigDecimalProperty).doubleValue(), 0.005);
    }

    public void testListResults_5_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intproperty");
        assertNotNull("intProperty exists", intProperty);
    }

    public void testListResults_6_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intproperty");
        // removed other assertion
        assertTrue("intProperty type", intProperty instanceof Integer);
    }

    public void testListResults_7_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intproperty");
        // removed other assertion
        // removed other assertion
        assertEquals("intProperty value", 103, ((Integer) intProperty).intValue());
    }

    public void testListResults_8_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object nullProperty = row.get("nullproperty");
        assertNull("nullProperty null", nullProperty);
    }

    public void testListResults_9_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object nullProperty = row.get("nullproperty");
        // removed other assertion

        final Object stringProperty = row.get("stringproperty");
        assertNotNull("stringProperty exists", stringProperty);
    }

    public void testListResults_10_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object nullProperty = row.get("nullproperty");
        // removed other assertion

        final Object stringProperty = row.get("stringproperty");
        // removed other assertion
        assertTrue("stringProperty type", stringProperty instanceof String);
    }

    public void testListResults_11_oe() {

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigdecimalproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intproperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object nullProperty = row.get("nullproperty");
        // removed other assertion

        final Object stringProperty = row.get("stringproperty");
        // removed other assertion
        // removed other assertion
        assertEquals("stringProperty value", "This is a string", (String) stringProperty);
    }

    public void testListResultsNormalCase_1_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            fail("Error creating RowSetDynaClass: " + e);
    }
    }

    public void testListResultsNormalCase_3_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        assertNotNull("bigDecimalProperty exists", bigDecimalProperty);
    }

    public void testListResultsNormalCase_4_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        // removed other assertion
        assertTrue("bigDecimalProperty type", bigDecimalProperty instanceof BigDecimal);
    }

    public void testListResultsNormalCase_5_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        // removed other assertion
        // removed other assertion
        assertEquals("bigDecimalProperty value", 123.45, ((BigDecimal) bigDecimalProperty).doubleValue(), 0.005);
    }

    public void testListResultsNormalCase_6_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intProperty");
        assertNotNull("intProperty exists", intProperty);
    }

    public void testListResultsNormalCase_7_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intProperty");
        // removed other assertion
        assertTrue("intProperty type", intProperty instanceof Integer);
    }

    public void testListResultsNormalCase_8_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intProperty");
        // removed other assertion
        // removed other assertion
        assertEquals("intProperty value", 103, ((Integer) intProperty).intValue());
    }

    public void testListResultsNormalCase_9_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object nullProperty = row.get("nullProperty");
        assertNull("nullProperty null", nullProperty);
    }

    public void testListResultsNormalCase_10_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object nullProperty = row.get("nullProperty");
        // removed other assertion

        final Object stringProperty = row.get("stringProperty");
        assertNotNull("stringProperty exists", stringProperty);
    }

    public void testListResultsNormalCase_11_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object nullProperty = row.get("nullProperty");
        // removed other assertion

        final Object stringProperty = row.get("stringProperty");
        // removed other assertion
        assertTrue("stringProperty type", stringProperty instanceof String);
    }

    public void testListResultsNormalCase_12_oe() {
        RowSetDynaClass dynaClass = null;
        try {
            dynaClass = new RowSetDynaClass(TestResultSet.createProxy(), false);
        } catch (final Exception e) {
            // removed other assertion
        }

        // Grab the third row
        final List<DynaBean> rows = dynaClass.getRows();
        final DynaBean row = rows.get(2);

        // Invalid argument test
        try {
            row.get("unknownProperty");
            // removed other assertion
        } catch (final IllegalArgumentException e) {
            // Expected result
        }

        // Verify property values

        final Object bigDecimalProperty = row.get("bigDecimalProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object intProperty = row.get("intProperty");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final Object nullProperty = row.get("nullProperty");
        // removed other assertion

        final Object stringProperty = row.get("stringProperty");
        // removed other assertion
        // removed other assertion
        assertEquals("stringProperty value", "This is a string", (String) stringProperty);
    }

    public void testLimitedRows_1_oe() throws Exception {

        // created one with low limit
        final RowSetDynaClass limitedDynaClass = new RowSetDynaClass(TestResultSet.createProxy(), 3);
        final List<DynaBean> rows = limitedDynaClass.getRows();
        assertNotNull("list exists", rows);
    }

    public void testLimitedRows_2_oe() throws Exception {

        // created one with low limit
        final RowSetDynaClass limitedDynaClass = new RowSetDynaClass(TestResultSet.createProxy(), 3);
        final List<DynaBean> rows = limitedDynaClass.getRows();
        // removed other assertion
        assertEquals("limited row count", 3, rows.size());
    }

    public void testInconsistentOracleDriver_1_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        assertEquals("Date Meta Name",       "dateProperty",       metaData.getColumnName(dateColIdx));
    }

    public void testInconsistentOracleDriver_2_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        assertEquals("Date Meta Class",      "java.sql.Timestamp", metaData.getColumnClassName(dateColIdx));
    }

    public void testInconsistentOracleDriver_3_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        assertEquals("Date Meta Type",       java.sql.Types.DATE,  metaData.getColumnType(dateColIdx));
    }

    public void testInconsistentOracleDriver_4_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Date ResultSet Value", java.sql.Date.class,  resultSet.getObject("dateProperty").getClass());
    }

    public void testInconsistentOracleDriver_5_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Timestamp column class returns a custom Timestamp impl for the column class name and ResultSet getObject
        final int timestampColIdx = 13;
        assertEquals("Timestamp Meta Name",       "timestampProperty",             metaData.getColumnName(timestampColIdx));
    }

    public void testInconsistentOracleDriver_6_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Timestamp column class returns a custom Timestamp impl for the column class name and ResultSet getObject
        final int timestampColIdx = 13;
        // removed other assertion
        assertEquals("Timestamp Meta Class",      CustomTimestamp.class.getName(), metaData.getColumnClassName(timestampColIdx));
    }

    public void testInconsistentOracleDriver_7_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Timestamp column class returns a custom Timestamp impl for the column class name and ResultSet getObject
        final int timestampColIdx = 13;
        // removed other assertion
        // removed other assertion
        assertEquals("Timestamp Meta Type",       java.sql.Types.TIMESTAMP,        metaData.getColumnType(timestampColIdx));
    }

    public void testInconsistentOracleDriver_8_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Timestamp column class returns a custom Timestamp impl for the column class name and ResultSet getObject
        final int timestampColIdx = 13;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Timestamp ResultSet Value", CustomTimestamp.class,           resultSet.getObject("timestampProperty").getClass());
    }

    public void testInconsistentOracleDriver_9_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Timestamp column class returns a custom Timestamp impl for the column class name and ResultSet getObject
        final int timestampColIdx = 13;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RowSetDynaClass inconsistentDynaClass = new RowSetDynaClass(resultSet);
        final DynaBean firstRow = inconsistentDynaClass.getRows().get(0);
        Class<?> expectedType = null;
        DynaProperty property = null;

        // Test Date
        property = firstRow.getDynaClass().getDynaProperty("dateproperty");
        expectedType = java.sql.Date.class;
        assertEquals("Date Class", expectedType, property.getType());
    }

    public void testInconsistentOracleDriver_10_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Timestamp column class returns a custom Timestamp impl for the column class name and ResultSet getObject
        final int timestampColIdx = 13;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RowSetDynaClass inconsistentDynaClass = new RowSetDynaClass(resultSet);
        final DynaBean firstRow = inconsistentDynaClass.getRows().get(0);
        Class<?> expectedType = null;
        DynaProperty property = null;

        // Test Date
        property = firstRow.getDynaClass().getDynaProperty("dateproperty");
        expectedType = java.sql.Date.class;
        // removed other assertion
        assertEquals("Date Value", expectedType, firstRow.get(property.getName()).getClass());
    }

    public void testInconsistentOracleDriver_11_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Timestamp column class returns a custom Timestamp impl for the column class name and ResultSet getObject
        final int timestampColIdx = 13;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RowSetDynaClass inconsistentDynaClass = new RowSetDynaClass(resultSet);
        final DynaBean firstRow = inconsistentDynaClass.getRows().get(0);
        Class<?> expectedType = null;
        DynaProperty property = null;

        // Test Date
        property = firstRow.getDynaClass().getDynaProperty("dateproperty");
        expectedType = java.sql.Date.class;
        // removed other assertion
        // removed other assertion

        // Test Timestamp
        property = firstRow.getDynaClass().getDynaProperty("timestampproperty");
        expectedType = java.sql.Timestamp.class;
        assertEquals("Timestamp Class", expectedType, property.getType());
    }

    public void testInconsistentOracleDriver_12_oe() throws Exception {

        final ResultSetMetaData metaData = TestResultSetMetaData.createProxy(new TestResultSetMetaDataInconsistent());
        final ResultSet resultSet = TestResultSet.createProxy(new TestResultSetInconsistent(metaData));

        // Date Column returns "java.sql.Timestamp" for the column class name but ResultSet getObject
        // returns a java.sql.Date value
        final int dateColIdx = 4;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // Timestamp column class returns a custom Timestamp impl for the column class name and ResultSet getObject
        final int timestampColIdx = 13;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final RowSetDynaClass inconsistentDynaClass = new RowSetDynaClass(resultSet);
        final DynaBean firstRow = inconsistentDynaClass.getRows().get(0);
        Class<?> expectedType = null;
        DynaProperty property = null;

        // Test Date
        property = firstRow.getDynaClass().getDynaProperty("dateproperty");
        expectedType = java.sql.Date.class;
        // removed other assertion
        // removed other assertion

        // Test Timestamp
        property = firstRow.getDynaClass().getDynaProperty("timestampproperty");
        expectedType = java.sql.Timestamp.class;
        // removed other assertion
        assertEquals("Timestamp Value", expectedType, firstRow.get(property.getName()).getClass());
    }

}
