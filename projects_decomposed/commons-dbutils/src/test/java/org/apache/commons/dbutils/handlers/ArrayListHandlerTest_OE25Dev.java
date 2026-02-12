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
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.BaseTestCase;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * ArrayListHandlerTest_OE25Dev
 */
public class ArrayListHandlerTest_OE25Dev extends BaseTestCase {

    public void testHandle_1_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testHandle_2_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        assertEquals(ROWS, results.size());
    }

    public void testHandle_3_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        assertTrue(iter.hasNext());
    }

    public void testHandle_4_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        assertEquals(COLS, row.length);
    }

    public void testHandle_5_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        assertEquals("1", row[0]);
    }

    public void testHandle_6_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        assertEquals("2", row[1]);
    }

    public void testHandle_7_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("THREE", row[2]);
    }

    public void testHandle_8_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(iter.hasNext());
    }

    public void testHandle_9_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        assertEquals(COLS, row.length);
    }

    public void testHandle_10_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        assertEquals("4", row[0]);
    }

    public void testHandle_11_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        assertEquals("5", row[1]);
    }

    public void testHandle_12_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("SIX", row[2]);
    }

    public void testHandle_13_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<Object[]> iter = results.iterator();
        Object[] row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(iter.hasNext());
    }

    public void testEmptyResultSetHandle_1_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.emptyResultSet);

        assertNotNull(results);
    }

    public void testEmptyResultSetHandle_2_oe() throws SQLException {
        ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
        List<Object[]> results = h.handle(this.emptyResultSet);

        // removed other assertion
        assertTrue(results.isEmpty());
    }

}
