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
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.dbutils.BaseTestCase;
import org.apache.commons.dbutils.ResultSetHandler;

public class KeyedHandlerTest_OE25Dev extends BaseTestCase {

    public void testHandle_1_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();

        Map<String,Map<String,Object>> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testHandle_2_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();

        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        assertEquals(ROWS, results.size());
    }

    public void testHandle_3_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();

        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            assertNotNull(key);
    }
    }

    public void testHandle_4_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();

        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            assertNotNull(row);
    }
    }

    public void testHandle_5_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();

        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            assertEquals(COLS, row.keySet().size());
    }
    }

    public void testHandle_6_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();

        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            // removed other assertion
        }
        row = results.get("1");
        assertEquals("1", row.get("one"));
    }

    public void testHandle_7_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();

        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            // removed other assertion
        }
        row = results.get("1");
        // removed other assertion
        assertEquals("2", row.get("TWO"));
    }

    public void testHandle_8_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();

        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            // removed other assertion
        }
        row = results.get("1");
        // removed other assertion
        // removed other assertion
        assertEquals("THREE", row.get("Three"));
    }

    public void testColumnIndexHandle_1_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>(2);
        Map<String,Map<String,Object>> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testColumnIndexHandle_2_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>(2);
        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        assertEquals(ROWS, results.size());
    }

    public void testColumnIndexHandle_3_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>(2);
        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            assertNotNull(key);
    }
    }

    public void testColumnIndexHandle_4_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>(2);
        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            assertNotNull(row);
    }
    }

    public void testColumnIndexHandle_5_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>(2);
        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            assertEquals(COLS, row.keySet().size());
    }
    }

    public void testColumnIndexHandle_6_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>(2);
        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            // removed other assertion
        }
        row = results.get("5");
        assertEquals("4", row.get("one"));
    }

    public void testColumnIndexHandle_7_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>(2);
        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            // removed other assertion
        }
        row = results.get("5");
        // removed other assertion
        assertEquals("5", row.get("TWO"));
    }

    public void testColumnIndexHandle_8_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>(2);
        Map<String,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<String, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            // removed other assertion
        }
        row = results.get("5");
        // removed other assertion
        // removed other assertion
        assertEquals("SIX", row.get("Three"));
    }

    public void testColumnNameHandle_1_oe() throws SQLException {
        ResultSetHandler<Map<Integer,Map<String,Object>>> h = new KeyedHandler<Integer>("intTest");
        Map<Integer,Map<String,Object>> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testColumnNameHandle_2_oe() throws SQLException {
        ResultSetHandler<Map<Integer,Map<String,Object>>> h = new KeyedHandler<Integer>("intTest");
        Map<Integer,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        assertEquals(ROWS, results.size());
    }

    public void testColumnNameHandle_3_oe() throws SQLException {
        ResultSetHandler<Map<Integer,Map<String,Object>>> h = new KeyedHandler<Integer>("intTest");
        Map<Integer,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<Integer, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            assertNotNull(key);
    }
    }

    public void testColumnNameHandle_4_oe() throws SQLException {
        ResultSetHandler<Map<Integer,Map<String,Object>>> h = new KeyedHandler<Integer>("intTest");
        Map<Integer,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<Integer, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            assertNotNull(row);
    }
    }

    public void testColumnNameHandle_5_oe() throws SQLException {
        ResultSetHandler<Map<Integer,Map<String,Object>>> h = new KeyedHandler<Integer>("intTest");
        Map<Integer,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<Integer, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            assertEquals(COLS, row.keySet().size());
    }
    }

    public void testColumnNameHandle_6_oe() throws SQLException {
        ResultSetHandler<Map<Integer,Map<String,Object>>> h = new KeyedHandler<Integer>("intTest");
        Map<Integer,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<Integer, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            // removed other assertion
        }
        row = results.get(Integer.valueOf(3));
        assertEquals("4", row.get("one"));
    }

    public void testColumnNameHandle_7_oe() throws SQLException {
        ResultSetHandler<Map<Integer,Map<String,Object>>> h = new KeyedHandler<Integer>("intTest");
        Map<Integer,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<Integer, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            // removed other assertion
        }
        row = results.get(Integer.valueOf(3));
        // removed other assertion
        assertEquals("5", row.get("TWO"));
    }

    public void testColumnNameHandle_8_oe() throws SQLException {
        ResultSetHandler<Map<Integer,Map<String,Object>>> h = new KeyedHandler<Integer>("intTest");
        Map<Integer,Map<String,Object>> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Map<String,Object> row = null;
        for(Entry<Integer, Map<String, Object>> entry : results.entrySet())
        {
            Object key = entry.getKey();
            // removed other assertion
            row = entry.getValue();
            // removed other assertion
            // removed other assertion
        }
        row = results.get(Integer.valueOf(3));
        // removed other assertion
        // removed other assertion
        assertEquals("SIX", row.get("Three"));
    }

    public void testEmptyResultSetHandle_1_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();
        Map<String,Map<String,Object>> results = h.handle(this.emptyResultSet);
        assertNotNull(results);
    }

    public void testEmptyResultSetHandle_2_oe() throws SQLException {
        ResultSetHandler<Map<String,Map<String,Object>>> h = new KeyedHandler<String>();
        Map<String,Map<String,Object>> results = h.handle(this.emptyResultSet);
        // removed other assertion
        assertTrue(results.isEmpty());
    }

}
