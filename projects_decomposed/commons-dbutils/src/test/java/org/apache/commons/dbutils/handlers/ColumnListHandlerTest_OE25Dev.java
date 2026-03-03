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
package org.apache.commons.dbutils.handlers;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.BaseTestCase;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * ColumnListHandlerTest_OE25Dev
 */
public class ColumnListHandlerTest_OE25Dev extends BaseTestCase {

    public void testHandle_1_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>();
        List<String> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testHandle_2_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>();
        List<String> results = h.handle(this.rs);

        assertEquals(ROWS, results.size());
    }

    public void testHandle_3_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>();
        List<String> results = h.handle(this.rs);


        assertEquals("1", results.get(0));
    }

    public void testHandle_4_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>();
        List<String> results = h.handle(this.rs);


        assertEquals("4", results.get(1));
    }

    public void testColumnIndexHandle_1_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>(2);
        List<String> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testColumnIndexHandle_2_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>(2);
        List<String> results = h.handle(this.rs);

        assertEquals(ROWS, results.size());
    }

    public void testColumnIndexHandle_3_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>(2);
        List<String> results = h.handle(this.rs);


        assertEquals("2", results.get(0));
    }

    public void testColumnIndexHandle_4_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>(2);
        List<String> results = h.handle(this.rs);


        assertEquals("5", results.get(1));
    }

    public void testColumnNameHandle_1_oe() throws SQLException {
        ResultSetHandler<List<Integer>> h = new ColumnListHandler<Integer>("intTest");
        List<Integer> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testColumnNameHandle_2_oe() throws SQLException {
        ResultSetHandler<List<Integer>> h = new ColumnListHandler<Integer>("intTest");
        List<Integer> results = h.handle(this.rs);

        assertEquals(ROWS, results.size());
    }

    public void testColumnNameHandle_3_oe() throws SQLException {
        ResultSetHandler<List<Integer>> h = new ColumnListHandler<Integer>("intTest");
        List<Integer> results = h.handle(this.rs);


        assertEquals(new Integer(1), results.get(0));
    }

    public void testColumnNameHandle_4_oe() throws SQLException {
        ResultSetHandler<List<Integer>> h = new ColumnListHandler<Integer>("intTest");
        List<Integer> results = h.handle(this.rs);


        assertEquals(new Integer(3), results.get(1));
    }

    public void testEmptyResultSetHandle_1_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>();
        List<String> results = h.handle(this.emptyResultSet);

        assertNotNull(results);
    }

    public void testEmptyResultSetHandle_2_oe() throws SQLException {
        ResultSetHandler<List<String>> h = new ColumnListHandler<String>();
        List<String> results = h.handle(this.emptyResultSet);

        assertTrue(results.isEmpty());
    }

}
