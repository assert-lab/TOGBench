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

    public void testToBean_1_oe() throws SQLException, ParseException {

        TestBean row = null;
        assertTrue(this.rs.next());
    }

    public void testToBeanList_1_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        assertNotNull(list);
    }

    public void testToBeanList_2_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);
        assertEquals(ROWS, list.size());
    }

    public void testToBeanList_3_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        assertEquals("1", b.getOne());
    }

    public void testToBeanList_4_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        assertEquals("2", b.getTwo());
    }

    public void testToBeanList_5_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        assertEquals(TestBean.Ordinal.THREE, b.getThree());
    }

    public void testToBeanList_6_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        assertEquals("not set", b.getDoNotSet());
    }

    public void testToBeanList_7_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertEquals("4", b.getOne());
    }

    public void testToBeanList_8_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertEquals("5", b.getTwo());
    }

    public void testToBeanList_9_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertEquals(TestBean.Ordinal.SIX, b.getThree());
    }

    public void testToBeanList_10_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertEquals("not set", b.getDoNotSet());
    }

    public void testToBeanList_11_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertEquals(3, b.getIntTest());
    }

    public void testToBeanList_12_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertEquals(Integer.valueOf(4), b.getIntegerTest());
    }

    public void testToBeanList_13_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertEquals(null, b.getNullObjectTest());
    }

    public void testToBeanList_14_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertEquals(0, b.getNullPrimitiveTest());
    }

    public void testToBeanList_15_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertNotNull(b.getNotDate());
    }

    public void testToBeanList_16_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertTrue(!"not a date".equals(b.getNotDate()));
    }

    public void testToBeanList_17_oe() throws SQLException, ParseException {

        List<TestBean> list = processor.toBeanList(this.rs, TestBean.class);

        TestBean b = list.get(0);
        datef.parse(b.getNotDate());

        b = list.get(1);
        assertTrue(b.getNotDate().endsWith("789456123"));
    }

    public void testToMap_1_oe() throws SQLException {

        assertTrue(this.rs.next());
    }

    public void testToMapOrdering_1_oe() throws SQLException {

        assertTrue(this.rs.next());
    }

}
