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
package org.apache.commons.dbutils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BeanProcessorTest_OE25Dev extends BaseTestCase {

    private static final BeanProcessor beanProc = new BeanProcessor();

    public static class MapColumnToPropertiesBean {
        private String one;

        private String two;

        private String three;

        private String four;

        public String getOne() {
            return one;
        }

        public void setOne(String one) {
            this.one = one;
        }

        public String getTwo() {
            return two;
        }

        public void setTwo(String two) {
            this.two = two;
        }

        public String getThree() {
            return three;
        }

        public void setThree(String three) {
            this.three = three;
        }

        public String getFour() {
            return four;
        }

        public void setFour(String four) {
            this.four = four;
        }
    }

    public void testProcessWithToBean_1_oe() throws SQLException {
        TestBean b = null;
        assertTrue(this.rs.next());
    }

    public void testProcessWithToBean_2_oe() throws SQLException {
        TestBean b = null;
        // removed other assertion
        b = beanProc.toBean(this.rs, TestBean.class);
        assertEquals(13.0, b.getColumnProcessorDoubleTest(), 0);
    }

    public void testProcessWithToBean_3_oe() throws SQLException {
        TestBean b = null;
        // removed other assertion
        b = beanProc.toBean(this.rs, TestBean.class);
        // removed other assertion
        assertEquals(b.getThree(), TestBean.Ordinal.THREE);
    }

    public void testProcessWithToBean_4_oe() throws SQLException {
        TestBean b = null;
        // removed other assertion
        b = beanProc.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        assertTrue(this.rs.next());
    }

    public void testProcessWithToBean_5_oe() throws SQLException {
        TestBean b = null;
        // removed other assertion
        b = beanProc.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        b = beanProc.toBean(this.rs, TestBean.class);
        assertEquals(13.0, b.getColumnProcessorDoubleTest(), 0);
    }

    public void testProcessWithToBean_6_oe() throws SQLException {
        TestBean b = null;
        // removed other assertion
        b = beanProc.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        b = beanProc.toBean(this.rs, TestBean.class);
        // removed other assertion
        assertEquals(b.getThree(), TestBean.Ordinal.SIX);
    }

    public void testProcessWithToBean_7_oe() throws SQLException {
        TestBean b = null;
        // removed other assertion
        b = beanProc.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        b = beanProc.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        assertFalse(this.rs.next());
    }

    public void testProcessWithPopulateBean_1_oe() throws SQLException {
        TestBean b = new TestBean();

        assertTrue(this.rs.next());
    }

    public void testProcessWithPopulateBean_2_oe() throws SQLException {
        TestBean b = new TestBean();

        // removed other assertion
        b = beanProc.populateBean(this.rs, b);
        assertEquals(13.0, b.getColumnProcessorDoubleTest(), 0);
    }

    public void testProcessWithPopulateBean_3_oe() throws SQLException {
        TestBean b = new TestBean();

        // removed other assertion
        b = beanProc.populateBean(this.rs, b);
        // removed other assertion
        assertEquals(b.getThree(), TestBean.Ordinal.THREE);
    }

    public void testProcessWithPopulateBean_4_oe() throws SQLException {
        TestBean b = new TestBean();

        // removed other assertion
        b = beanProc.populateBean(this.rs, b);
        // removed other assertion
        // removed other assertion

        assertTrue(this.rs.next());
    }

    public void testProcessWithPopulateBean_5_oe() throws SQLException {
        TestBean b = new TestBean();

        // removed other assertion
        b = beanProc.populateBean(this.rs, b);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        b = beanProc.populateBean(this.rs, b);
        assertEquals(13.0, b.getColumnProcessorDoubleTest(), 0);
    }

    public void testProcessWithPopulateBean_6_oe() throws SQLException {
        TestBean b = new TestBean();

        // removed other assertion
        b = beanProc.populateBean(this.rs, b);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        b = beanProc.populateBean(this.rs, b);
        // removed other assertion
        assertEquals(b.getThree(), TestBean.Ordinal.SIX);
    }

    public void testProcessWithPopulateBean_7_oe() throws SQLException {
        TestBean b = new TestBean();

        // removed other assertion
        b = beanProc.populateBean(this.rs, b);
        // removed other assertion
        // removed other assertion

        // removed other assertion
        b = beanProc.populateBean(this.rs, b);
        // removed other assertion
        // removed other assertion

        assertFalse(this.rs.next());
    }

    public void testMapColumnToProperties_1_oe() throws Exception {
        String[] columnNames = { "test", "test", "three" };
        String[] columnLabels = { "one", "two", null };
        ResultSetMetaData rsmd = ProxyFactory.instance().createResultSetMetaData(
                new MockResultSetMetaData(columnNames, columnLabels));
        PropertyDescriptor[] props = Introspector.getBeanInfo(MapColumnToPropertiesBean.class).getPropertyDescriptors();

        int[] columns = beanProc.mapColumnsToProperties(rsmd, props);
        for (int i = 1; i < columns.length; i++) {
            assertTrue(columns[i] != BeanProcessor.PROPERTY_NOT_FOUND);
    }
    }

    public void testMapColumnToPropertiesWithOverrides_1_oe() throws Exception {
        Map<String, String> columnToPropertyOverrides = new HashMap<String, String>();
        columnToPropertyOverrides.put("five", "four");
        BeanProcessor beanProc = new BeanProcessor(columnToPropertyOverrides);
        String[] columnNames = { "test", "test", "three", "five" };
        String[] columnLabels = { "one", "two", null, null };
        ResultSetMetaData rsmd = ProxyFactory.instance().createResultSetMetaData(
                new MockResultSetMetaData(columnNames, columnLabels));
        PropertyDescriptor[] props = Introspector.getBeanInfo(MapColumnToPropertiesBean.class).getPropertyDescriptors();

        int[] columns = beanProc.mapColumnsToProperties(rsmd, props);
        for (int i = 1; i < columns.length; i++) {
            assertTrue(columns[i] != BeanProcessor.PROPERTY_NOT_FOUND);
    }
    }

}
