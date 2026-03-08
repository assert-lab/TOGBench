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
import org.apache.commons.dbutils.TestBean;

/**
 * BeanListHandlerTest_OE25Dev
 */
public class BeanListHandlerTest_OE25Dev extends BaseTestCase {

    public void testHandle() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        assertNotNull(results);
        assertEquals(ROWS, results.size());

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        assertTrue(iter.hasNext());
        row = iter.next();
        assertEquals("1", row.getOne());
        assertEquals("2", row.getTwo());
        assertEquals(TestBean.Ordinal.THREE, row.getThree());
        assertEquals("not set", row.getDoNotSet());

        assertTrue(iter.hasNext());
        row = iter.next();

        assertEquals("4", row.getOne());
        assertEquals("5", row.getTwo());
        assertEquals(TestBean.Ordinal.SIX, row.getThree());
        assertEquals("not set", row.getDoNotSet());

        assertFalse(iter.hasNext());
    }

    public void testEmptyResultSetHandle() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.emptyResultSet);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    public void testHandleToSuperClass() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        assertNotNull(results);
        assertEquals(ROWS, results.size());

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        assertTrue(iter.hasNext());
        row = iter.next();
        assertSame(SubTestBean.class, row.getClass());

        assertEquals("1", row.getOne());
        assertEquals("2", row.getTwo());
        assertEquals(TestBean.Ordinal.THREE, row.getThree());
        assertEquals("not set", row.getDoNotSet());

        assertTrue(iter.hasNext());
        row = iter.next();
        assertSame(SubTestBean.class, row.getClass());

        assertEquals("4", row.getOne());
        assertEquals("5", row.getTwo());
        assertEquals(TestBean.Ordinal.SIX, row.getThree());
        assertEquals("not set", row.getDoNotSet());

        assertFalse(iter.hasNext());
    }

    public void testHandleToInterface() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        assertNotNull(results);
        assertEquals(ROWS, results.size());

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        assertTrue(iter.hasNext());
        row = iter.next();
        assertSame(SubTestBean.class, row.getClass());

        assertEquals("1", row.getOne());
        assertEquals("2", row.getTwo());
        assertEquals(TestBean.Ordinal.THREE, row.getThree());
        assertEquals("not set", row.getDoNotSet());

        assertTrue(iter.hasNext());
        row = iter.next();
        assertSame(SubTestBean.class, row.getClass());

        assertEquals("4", row.getOne());
        assertEquals("5", row.getTwo());
        assertEquals(TestBean.Ordinal.SIX, row.getThree());
        assertEquals("not set", row.getDoNotSet());

        assertFalse(iter.hasNext());
    }

    public static interface SubTestBeanInterface {
        public String getOne();

        public TestBean.Ordinal getThree();

        public String getTwo();

        public String getDoNotSet();
    }

    public static class SubTestBean extends TestBean implements SubTestBeanInterface { }

    public void testHandle_1_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testHandle_2_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        assertEquals(ROWS, results.size());
    }

    public void testHandle_3_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        assertTrue(iter.hasNext());
    }

    public void testHandle_4_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        assertEquals("1", row.getOne());
    }

    public void testHandle_5_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        assertEquals("2", row.getTwo());
    }

    public void testHandle_6_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        assertEquals(TestBean.Ordinal.THREE, row.getThree());
    }

    public void testHandle_7_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("not set", row.getDoNotSet());
    }

    public void testHandle_8_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(iter.hasNext());
    }

    public void testHandle_9_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();

        assertEquals("4", row.getOne());
    }

    public void testHandle_10_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();

        // removed other assertion
        assertEquals("5", row.getTwo());
    }

    public void testHandle_11_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
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
        assertEquals(TestBean.Ordinal.SIX, row.getThree());
    }

    public void testHandle_12_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
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
        assertEquals("not set", row.getDoNotSet());
    }

    public void testHandle_13_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
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
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.emptyResultSet);

        assertNotNull(results);
    }

    public void testEmptyResultSetHandle_2_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(TestBean.class);
        List<TestBean> results = h.handle(this.emptyResultSet);

        // removed other assertion
        assertTrue(results.isEmpty());
    }

    public void testHandleToSuperClass_1_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testHandleToSuperClass_2_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        assertEquals(ROWS, results.size());
    }

    public void testHandleToSuperClass_3_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        assertTrue(iter.hasNext());
    }

    public void testHandleToSuperClass_4_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        assertSame(SubTestBean.class, row.getClass());
    }

    public void testHandleToSuperClass_5_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        assertEquals("1", row.getOne());
    }

    public void testHandleToSuperClass_6_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        assertEquals("2", row.getTwo());
    }

    public void testHandleToSuperClass_7_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(TestBean.Ordinal.THREE, row.getThree());
    }

    public void testHandleToSuperClass_8_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("not set", row.getDoNotSet());
    }

    public void testHandleToSuperClass_9_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(iter.hasNext());
    }

    public void testHandleToSuperClass_10_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        assertSame(SubTestBean.class, row.getClass());
    }

    public void testHandleToSuperClass_11_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        assertEquals("4", row.getOne());
    }

    public void testHandleToSuperClass_12_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        assertEquals("5", row.getTwo());
    }

    public void testHandleToSuperClass_13_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(TestBean.Ordinal.SIX, row.getThree());
    }

    public void testHandleToSuperClass_14_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

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
        assertEquals("not set", row.getDoNotSet());
    }

    public void testHandleToSuperClass_15_oe() throws SQLException {
        ResultSetHandler<List<TestBean>> h = new BeanListHandler<TestBean>(SubTestBean.class);
        List<TestBean> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<TestBean> iter = results.iterator();
        TestBean row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

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
        // removed other assertion

        assertFalse(iter.hasNext());
    }

    public void testHandleToInterface_1_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        assertNotNull(results);
    }

    public void testHandleToInterface_2_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        assertEquals(ROWS, results.size());
    }

    public void testHandleToInterface_3_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        assertTrue(iter.hasNext());
    }

    public void testHandleToInterface_4_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        assertSame(SubTestBean.class, row.getClass());
    }

    public void testHandleToInterface_5_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        assertEquals("1", row.getOne());
    }

    public void testHandleToInterface_6_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        assertEquals("2", row.getTwo());
    }

    public void testHandleToInterface_7_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(TestBean.Ordinal.THREE, row.getThree());
    }

    public void testHandleToInterface_8_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("not set", row.getDoNotSet());
    }

    public void testHandleToInterface_9_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(iter.hasNext());
    }

    public void testHandleToInterface_10_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        assertSame(SubTestBean.class, row.getClass());
    }

    public void testHandleToInterface_11_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        assertEquals("4", row.getOne());
    }

    public void testHandleToInterface_12_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        assertEquals("5", row.getTwo());
    }

    public void testHandleToInterface_13_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = iter.next();
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals(TestBean.Ordinal.SIX, row.getThree());
    }

    public void testHandleToInterface_14_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

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
        assertEquals("not set", row.getDoNotSet());
    }

    public void testHandleToInterface_15_oe() throws SQLException {
        ResultSetHandler<List<SubTestBeanInterface>> h = new BeanListHandler<SubTestBeanInterface>(SubTestBean.class);
        List<SubTestBeanInterface> results = h.handle(this.rs);

        // removed other assertion
        // removed other assertion

        Iterator<SubTestBeanInterface> iter = results.iterator();
        SubTestBeanInterface row = null;
        // removed other assertion
        row = iter.next();
        // removed other assertion

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
        // removed other assertion

        assertFalse(iter.hasNext());
    }

}
