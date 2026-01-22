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

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Test the BasicRowProcessor class.
 */
public class BasicRowProcessorTest_OE25Dev extends BaseTestCase {

    private static final RowProcessor processor = new BasicRowProcessor();

    /**
     * Format that matches Date.toString().
     * Sun Mar 14 15:19:15 MST 2004
     */
    private static final DateFormat datef =
        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

    public void testToArray_1_oe() throws SQLException {

        Object[] a = null;
        assertTrue(this.rs.next());
    }

    public void testToArray_2_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        assertEquals(COLS, a.length);
    }

    public void testToArray_3_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion
        assertEquals("1", a[0]);
    }

    public void testToArray_4_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion
        // removed other assertion
        assertEquals("2", a[1]);
    }

    public void testToArray_5_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("THREE", a[2]);
    }

    public void testToArray_6_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(this.rs.next());
    }

    public void testToArray_7_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        a = processor.toArray(this.rs);
        assertEquals(COLS, a.length);
    }

    public void testToArray_8_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion

        assertEquals("4", a[0]);
    }

    public void testToArray_9_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion

        // removed other assertion
        assertEquals("5", a[1]);
    }

    public void testToArray_10_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("SIX", a[2]);
    }

    public void testToArray_11_oe() throws SQLException {

        Object[] a = null;
        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        a = processor.toArray(this.rs);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(this.rs.next());
    }

    public void testToBean_1_oe() throws SQLException, ParseException {

        TestBean row = null;
        assertTrue(this.rs.next());
    }

    public void testToBean_2_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        assertEquals("1", row.getOne());
    }

    public void testToBean_3_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        assertEquals("2", row.getTwo());
    }

    public void testToBean_4_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        assertEquals(TestBean.Ordinal.THREE, row.getThree());
    }

    public void testToBean_5_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("not set", row.getDoNotSet());
    }

    public void testToBean_6_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(this.rs.next());
    }

    public void testToBean_7_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        assertEquals("4", row.getOne());
    }

    public void testToBean_8_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        assertEquals("5", row.getTwo());
    }

    public void testToBean_9_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        assertEquals(TestBean.Ordinal.SIX, row.getThree());
    }

    public void testToBean_10_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("not set", row.getDoNotSet());
    }

    public void testToBean_11_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, row.getIntTest());
    }

    public void testToBean_12_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(4), row.getIntegerTest());
    }

    public void testToBean_13_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, row.getNullObjectTest());
    }

    public void testToBean_14_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, row.getNullPrimitiveTest());
    }

    public void testToBean_15_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test date -> string handling
        assertNotNull(row.getNotDate());
    }

    public void testToBean_16_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test date -> string handling
        // removed other assertion
        assertTrue(!"not a date".equals(row.getNotDate()));
    }

    public void testToBean_17_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test date -> string handling
        // removed other assertion
        // removed other assertion
        assertTrue(row.getNotDate().endsWith("789456123"));
    }

    public void testToBean_18_oe() throws SQLException, ParseException {

        TestBean row = null;
        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        row = processor.toBean(this.rs, TestBean.class);

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test date -> string handling
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(this.rs.next());
    }

    public void testToBeanList_1_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        assertNotNull(list);
    }

    public void testToBeanList_2_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        assertEquals(ROWS, list.size());
    }

    public void testToBeanList_3_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        assertEquals("1", b.getOne());
    }

    public void testToBeanList_4_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        assertEquals("2", b.getTwo());
    }

    public void testToBeanList_5_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        assertEquals(TestBean.Ordinal.THREE, b.getThree());
    }

    public void testToBeanList_6_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("not set", b.getDoNotSet());
    }

    public void testToBeanList_7_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertEquals("4", b.getOne());
    }

    public void testToBeanList_8_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        assertEquals("5", b.getTwo());
    }

    public void testToBeanList_9_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        // removed other assertion
        assertEquals(TestBean.Ordinal.SIX, b.getThree());
    }

    public void testToBeanList_10_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("not set", b.getDoNotSet());
    }

    public void testToBeanList_11_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(3, b.getIntTest());
    }

    public void testToBeanList_12_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(Integer.valueOf(4), b.getIntegerTest());
    }

    public void testToBeanList_13_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(null, b.getNullObjectTest());
    }

    public void testToBeanList_14_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, b.getNullPrimitiveTest());
    }

    public void testToBeanList_15_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test date -> string handling
        assertNotNull(b.getNotDate());
    }

    public void testToBeanList_16_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test date -> string handling
        // removed other assertion
        assertTrue(!"not a date".equals(b.getNotDate()));
    }

    public void testToBeanList_17_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        // removed other assertion
        // removed other assertion

        TestBean b = list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        datef.parse(b.getNotDate());

        b = list.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // test date -> string handling
        // removed other assertion
        // removed other assertion
        assertTrue(b.getNotDate().endsWith("789456123"));
    }

    public void testToMap_1_oe() throws SQLException {

        assertTrue(this.rs.next());
    }

    public void testToMap_2_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        assertEquals(COLS, m.keySet().size());
    }

    public void testToMap_3_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        // removed other assertion
        assertEquals("1", m.get("one"));
    }

    public void testToMap_4_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        // removed other assertion
        // removed other assertion
        assertEquals("2", m.get("TWO"));
    }

    public void testToMap_5_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("THREE", m.get("Three"));
    }

    public void testToMap_6_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertTrue(this.rs.next());
    }

    public void testToMap_7_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        m = processor.toMap(this.rs);
        assertEquals(COLS, m.keySet().size());
    }

    public void testToMap_8_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        m = processor.toMap(this.rs);
        // removed other assertion

        assertEquals("4", m.get("One")); // case shouldn't matter;
    }

    public void testToMap_9_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        m = processor.toMap(this.rs);
        // removed other assertion

        // removed other assertion
        assertEquals("5", m.get("two"));
    }

    public void testToMap_10_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        m = processor.toMap(this.rs);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("SIX", m.get("THREE"));
    }

    public void testToMap_11_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        m = processor.toMap(this.rs);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(this.rs.next());
    }

    public void testToMapOrdering_1_oe() throws SQLException {

        assertTrue(this.rs.next());
    }

    public void testToMapOrdering_2_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        assertEquals("one", itr.next());
    }

    public void testToMapOrdering_3_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        assertEquals("two", itr.next());
    }

    public void testToMapOrdering_4_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        // removed other assertion
        assertEquals("three", itr.next());
    }

    public void testToMapOrdering_5_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("notInBean", itr.next());
    }

    public void testToMapOrdering_6_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("intTest", itr.next());
    }

    public void testToMapOrdering_7_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("integerTest", itr.next());
    }

    public void testToMapOrdering_8_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("nullObjectTest", itr.next());
    }

    public void testToMapOrdering_9_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("nullPrimitiveTest", itr.next());
    }

    public void testToMapOrdering_10_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("notDate", itr.next());
    }

    public void testToMapOrdering_11_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("columnProcessorDoubleTest", itr.next());
    }

    public void testToMapOrdering_12_oe() throws SQLException {

        // removed other assertion
        Map<String, Object> m = processor.toMap(this.rs);

        Iterator<String> itr = m.keySet().iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertFalse(itr.hasNext());
    }

}
