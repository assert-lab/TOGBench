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
package org.apache.commons.dbutils.wrappers;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

import org.apache.commons.dbutils.BaseTestCase;
import org.apache.commons.dbutils.ProxyFactory;

/**
 * Test cases for <code>SqlNullCheckedResultSet</code> class.
 */
public class SqlNullCheckedResultSetTest_OE25Dev extends BaseTestCase {

    private SqlNullCheckedResultSet rs2 = null;

    /**
     * Sets up instance variables required by this test case.
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();

        rs2 =
            new SqlNullCheckedResultSet(
                ProxyFactory.instance().createResultSet(
                    new SqlNullUncheckedMockResultSet()));

        rs = ProxyFactory.instance().createResultSet(rs2); // Override superclass field
    }

    /**
     * Tests the getAsciiStream implementation.
     */

    /**
     * Tests the getBigDecimal implementation.
     */

    /**
     * Tests the getBinaryStream implementation.
     */

    /**
     * Tests the getBlob implementation.
     */

    /**
     * Tests the getBoolean implementation.
     */

    /**
     * Tests the getByte implementation.
     */

    /**
     * Tests the getByte implementation.
     */

    private static void assertArrayEquals(byte[] expected, byte[] actual) {
        if (expected == actual) {
            return;
        }
        if (expected.length != actual.length) {
            failNotEquals(null, Arrays.toString(expected), Arrays.toString(actual));
        }
        for (int i = 0; i < expected.length; i++) {
            byte expectedItem = expected[i];
            byte actualItem = actual[i];
            assertEquals("Array not equal at index " + i, expectedItem, actualItem);
        }
    }

    /**
     * Tests the getCharacterStream implementation.
     */

    /**
     * Tests the getClob implementation.
     */

    /**
     * Tests the getDate implementation.
     */

    /**
     * Tests the getDouble implementation.
     */

    /**
     * Tests the getFloat implementation.
     */

    /**
     * Tests the getInt implementation.
     */

    /**
     * Tests the getLong implementation.
     */

    /**
     * Tests the getObject implementation.
     */

    /**
     * Tests the getRef implementation.
     */

    /**
     * Tests the getShort implementation.
     */

    /**
     * Tests the getString implementation.
     */

    /**
     * Tests the getTime implementation.
     */

    /**
     * Tests the getTimestamp implementation.
     */

    /**
     * Tests the getURL and setNullURL implementations.
     *
     * Uses reflection to allow for building under JDK 1.3.
     */

    /**
     * Tests the setNullAsciiStream implementation.
     */

    /**
     * Tests the setNullBigDecimal implementation.
     */

    /**
     * Tests the setNullBinaryStream implementation.
     */

    /**
     * Tests the setNullBlob implementation.
     */

    /**
     * Tests the setNullBoolean implementation.
     */

    /**
     * Tests the setNullByte implementation.
     */

    /**
     * Tests the setNullByte implementation.
     */

    /**
     * Tests the setNullCharacterStream implementation.
     */

    /**
     * Tests the setNullClob implementation.
     */

    /**
     * Tests the setNullDate implementation.
     */

    /**
     * Tests the setNullDouble implementation.
     */

    /**
     * Tests the setNullFloat implementation.
     */

    /**
     * Tests the setNullInt implementation.
     */

    /**
     * Tests the setNullLong implementation.
     */

    /**
     * Tests the setNullObject implementation.
     */

    /**
     * Tests the setNullShort implementation.
     */

    /**
     * Tests the setNullString implementation.
     */

    /**
     * Tests the setNullRef implementation.
     */

    /**
     * Tests the setNullTime implementation.
     */

    /**
     * Tests the setNullTimestamp implementation.
     */

    public void testGetAsciiStream_1_oe() throws SQLException {

        assertNull(rs.getAsciiStream(1));
    }

    public void testGetAsciiStream_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetAsciiStream_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getAsciiStream("column"));
    }

    public void testGetAsciiStream_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetAsciiStream_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        assertNotNull(rs.getAsciiStream(1));
    }

    public void testGetAsciiStream_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        // removed other assertion
        assertEquals(stream, rs.getAsciiStream(1));
    }

    public void testGetAsciiStream_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getAsciiStream("column"));
    }

    public void testGetAsciiStream_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(stream, rs.getAsciiStream("column"));
    }

    public void testGetBigDecimal_1_oe() throws SQLException {

        assertNull(rs.getBigDecimal(1));
    }

    public void testGetBigDecimal_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBigDecimal_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getBigDecimal("column"));
    }

    public void testGetBigDecimal_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBigDecimal_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        assertNotNull(rs.getBigDecimal(1));
    }

    public void testGetBigDecimal_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        // removed other assertion
        assertEquals(bd, rs.getBigDecimal(1));
    }

    public void testGetBigDecimal_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getBigDecimal("column"));
    }

    public void testGetBigDecimal_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bd, rs.getBigDecimal("column"));
    }

    public void testGetBinaryStream_1_oe() throws SQLException {

        assertNull(rs.getBinaryStream(1));
    }

    public void testGetBinaryStream_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBinaryStream_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getBinaryStream("column"));
    }

    public void testGetBinaryStream_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBinaryStream_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        assertNotNull(rs.getBinaryStream(1));
    }

    public void testGetBinaryStream_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        // removed other assertion
        assertEquals(stream, rs.getBinaryStream(1));
    }

    public void testGetBinaryStream_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getBinaryStream("column"));
    }

    public void testGetBinaryStream_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(stream, rs.getBinaryStream("column"));
    }

    public void testGetBlob_1_oe() throws SQLException {

        assertNull(rs.getBlob(1));
    }

    public void testGetBlob_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBlob_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getBlob("column"));
    }

    public void testGetBlob_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBlob_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        assertNotNull(rs.getBlob(1));
    }

    public void testGetBlob_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        // removed other assertion
        assertEquals(blob, rs.getBlob(1));
    }

    public void testGetBlob_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getBlob("column"));
    }

    public void testGetBlob_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(blob, rs.getBlob("column"));
    }

    public void testGetBoolean_1_oe() throws SQLException {

        assertEquals(false, rs.getBoolean(1));
    }

    public void testGetBoolean_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBoolean_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertEquals(false, rs.getBoolean("column"));
    }

    public void testGetBoolean_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBoolean_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        rs2.setNullBoolean(true);
        assertEquals(true, rs.getBoolean(1));
    }

    public void testGetBoolean_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        rs2.setNullBoolean(true);
        // removed other assertion
        assertEquals(true, rs.getBoolean("column"));
    }

    public void testGetByte_1_oe() throws SQLException {

        assertEquals((byte) 0, rs.getByte(1));
    }

    public void testGetByte_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetByte_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertEquals((byte) 0, rs.getByte("column"));
    }

    public void testGetByte_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetByte_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        byte b = (byte) 10;
        rs2.setNullByte(b);
        assertEquals(b, rs.getByte(1));
    }

    public void testGetByte_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        byte b = (byte) 10;
        rs2.setNullByte(b);
        // removed other assertion
        assertEquals(b, rs.getByte("column"));
    }

    public void testGetBytes_1_oe() throws SQLException {

        assertNull(rs.getBytes(1));
    }

    public void testGetBytes_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBytes_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getBytes("column"));
    }

    public void testGetBytes_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetBytes_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        assertNotNull(rs.getBytes(1));
    }

    public void testGetBytes_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        // removed other assertion
        assertArrayEquals(b, rs.getBytes(1));
    }

    public void testGetBytes_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getBytes("column"));
    }

    public void testGetBytes_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(b, rs.getBytes("column"));
    }

    public void testGetCharacterStream_1_oe() throws SQLException {

        assertNull(rs.getCharacterStream(1));
    }

    public void testGetCharacterStream_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetCharacterStream_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getCharacterStream("column"));
    }

    public void testGetCharacterStream_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetCharacterStream_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        assertNotNull(rs.getCharacterStream(1));
    }

    public void testGetCharacterStream_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        // removed other assertion
        assertEquals(reader, rs.getCharacterStream(1));
    }

    public void testGetCharacterStream_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getCharacterStream("column"));
    }

    public void testGetCharacterStream_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(reader, rs.getCharacterStream("column"));
    }

    public void testGetClob_1_oe() throws SQLException {

        assertNull(rs.getClob(1));
    }

    public void testGetClob_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetClob_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getClob("column"));
    }

    public void testGetClob_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetClob_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        assertNotNull(rs.getClob(1));
    }

    public void testGetClob_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        // removed other assertion
        assertEquals(clob, rs.getClob(1));
    }

    public void testGetClob_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getClob("column"));
    }

    public void testGetClob_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(clob, rs.getClob("column"));
    }

    public void testGetDate_1_oe() throws SQLException {

        assertNull(rs.getDate(1));
    }

    public void testGetDate_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetDate_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getDate("column"));
    }

    public void testGetDate_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetDate_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(rs.getDate(1, Calendar.getInstance()));
    }

    public void testGetDate_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetDate_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(rs.getDate("column", Calendar.getInstance()));
    }

    public void testGetDate_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetDate_9_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        assertNotNull(rs.getDate(1));
    }

    public void testGetDate_10_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        assertEquals(date, rs.getDate(1));
    }

    public void testGetDate_11_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getDate("column"));
    }

    public void testGetDate_12_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(date, rs.getDate("column"));
    }

    public void testGetDate_13_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getDate(1, Calendar.getInstance()));
    }

    public void testGetDate_14_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(date, rs.getDate(1, Calendar.getInstance()));
    }

    public void testGetDate_15_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getDate("column", Calendar.getInstance()));
    }

    public void testGetDate_16_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(date, rs.getDate("column", Calendar.getInstance()));
    }

    public void testGetDouble_1_oe() throws SQLException {

        assertEquals(0.0, rs.getDouble(1), 0.0);
    }

    public void testGetDouble_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetDouble_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertEquals(0.0, rs.getDouble("column"), 0.0);
    }

    public void testGetDouble_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetDouble_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        double d = 10.0;
        rs2.setNullDouble(d);
        assertEquals(d, rs.getDouble(1), 0.0);
    }

    public void testGetDouble_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        double d = 10.0;
        rs2.setNullDouble(d);
        // removed other assertion
        assertEquals(d, rs.getDouble("column"), 0.0);
    }

    public void testGetFloat_1_oe() throws SQLException {
        assertEquals(0, rs.getFloat(1), 0.0);
    }

    public void testGetFloat_2_oe() throws SQLException {
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetFloat_3_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        assertEquals(0, rs.getFloat("column"), 0.0);
    }

    public void testGetFloat_4_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetFloat_5_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        float f = 10;
        rs2.setNullFloat(f);
        assertEquals(f, rs.getFloat(1), 0.0);
    }

    public void testGetFloat_6_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        float f = 10;
        rs2.setNullFloat(f);
        // removed other assertion
        assertEquals(f, rs.getFloat("column"), 0.0);
    }

    public void testGetInt_1_oe() throws SQLException {
        assertEquals(0, rs.getInt(1));
    }

    public void testGetInt_2_oe() throws SQLException {
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetInt_3_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        assertEquals(0, rs.getInt("column"));
    }

    public void testGetInt_4_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetInt_5_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        int i = 10;
        rs2.setNullInt(i);
        assertEquals(i, rs.getInt(1));
    }

    public void testGetInt_6_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        int i = 10;
        rs2.setNullInt(i);
        // removed other assertion
        assertEquals(i, rs.getInt("column"));
    }

    public void testGetLong_1_oe() throws SQLException {
        assertEquals(0, rs.getLong(1));
    }

    public void testGetLong_2_oe() throws SQLException {
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetLong_3_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        assertEquals(0, rs.getLong("column"));
    }

    public void testGetLong_4_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetLong_5_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        long l = 10;
        rs2.setNullLong(l);
        assertEquals(l, rs.getLong(1));
    }

    public void testGetLong_6_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        long l = 10;
        rs2.setNullLong(l);
        // removed other assertion
        assertEquals(l, rs.getLong("column"));
    }

    public void testGetObject_1_oe() throws SQLException {

        assertNull(rs.getObject(1));
    }

    public void testGetObject_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetObject_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getObject("column"));
    }

    public void testGetObject_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetObject_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(rs.getObject(1, (Map<String, Class<?>>) null));
    }

    public void testGetObject_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetObject_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(rs.getObject("column", (Map<String, Class<?>>) null));
    }

    public void testGetObject_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetObject_9_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        assertNotNull(rs.getObject(1));
    }

    public void testGetObject_10_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        assertEquals(o, rs.getObject(1));
    }

    public void testGetObject_11_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getObject("column"));
    }

    public void testGetObject_12_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(o, rs.getObject("column"));
    }

    public void testGetObject_13_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getObject(1, (Map<String, Class<?>>) null));
    }

    public void testGetObject_14_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(o, rs.getObject(1, (Map<String, Class<?>>) null));
    }

    public void testGetObject_15_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getObject("column", (Map<String, Class<?>>) null));
    }

    public void testGetObject_16_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(o, rs.getObject("column", (Map<String, Class<?>>) null));
    }

    public void testGetRef_1_oe() throws SQLException {

        assertNull(rs.getRef(1));
    }

    public void testGetRef_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetRef_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getRef("column"));
    }

    public void testGetRef_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetRef_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        assertNotNull(rs.getRef(1));
    }

    public void testGetRef_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        // removed other assertion
        assertEquals(ref, rs.getRef(1));
    }

    public void testGetRef_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getRef("column"));
    }

    public void testGetRef_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ref, rs.getRef("column"));
    }

    public void testGetShort_1_oe() throws SQLException {

        assertEquals((short) 0, rs.getShort(1));
    }

    public void testGetShort_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetShort_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertEquals((short) 0, rs.getShort("column"));
    }

    public void testGetShort_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetShort_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        short s = (short) 10;
        rs2.setNullShort(s);
        assertEquals(s, rs.getShort(1));
    }

    public void testGetShort_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        short s = (short) 10;
        rs2.setNullShort(s);
        // removed other assertion
        assertEquals(s, rs.getShort("column"));
    }

    public void testGetString_1_oe() throws SQLException {
        assertEquals(null, rs.getString(1));
    }

    public void testGetString_2_oe() throws SQLException {
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetString_3_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        assertEquals(null, rs.getString("column"));
    }

    public void testGetString_4_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetString_5_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        String s = "hello, world";
        rs2.setNullString(s);
        assertEquals(s, rs.getString(1));
    }

    public void testGetString_6_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        String s = "hello, world";
        rs2.setNullString(s);
        // removed other assertion
        assertEquals(s, rs.getString("column"));
    }

    public void testGetTime_1_oe() throws SQLException {

        assertNull(rs.getTime(1));
    }

    public void testGetTime_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetTime_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getTime("column"));
    }

    public void testGetTime_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetTime_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(rs.getTime(1, Calendar.getInstance()));
    }

    public void testGetTime_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetTime_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(rs.getTime("column", Calendar.getInstance()));
    }

    public void testGetTime_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetTime_9_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        assertNotNull(rs.getTime(1));
    }

    public void testGetTime_10_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        assertEquals(time, rs.getTime(1));
    }

    public void testGetTime_11_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTime("column"));
    }

    public void testGetTime_12_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(time, rs.getTime("column"));
    }

    public void testGetTime_13_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTime(1, Calendar.getInstance()));
    }

    public void testGetTime_14_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(time, rs.getTime(1, Calendar.getInstance()));
    }

    public void testGetTime_15_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTime("column", Calendar.getInstance()));
    }

    public void testGetTime_16_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(time, rs.getTime("column", Calendar.getInstance()));
    }

    public void testGetTimestamp_1_oe() throws SQLException {

        assertNull(rs.getTimestamp(1));
    }

    public void testGetTimestamp_2_oe() throws SQLException {

        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetTimestamp_3_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        assertNull(rs.getTimestamp("column"));
    }

    public void testGetTimestamp_4_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetTimestamp_5_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(rs.getTimestamp(1, Calendar.getInstance()));
    }

    public void testGetTimestamp_6_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetTimestamp_7_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull(rs.getTimestamp("column", Calendar.getInstance()));
    }

    public void testGetTimestamp_8_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testGetTimestamp_9_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        assertNotNull(rs.getTimestamp(1));
    }

    public void testGetTimestamp_10_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        assertEquals(ts, rs.getTimestamp(1));
    }

    public void testGetTimestamp_11_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTimestamp("column"));
    }

    public void testGetTimestamp_12_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ts, rs.getTimestamp("column"));
    }

    public void testGetTimestamp_13_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTimestamp(1, Calendar.getInstance()));
    }

    public void testGetTimestamp_14_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ts, rs.getTimestamp(1, Calendar.getInstance()));
    }

    public void testGetTimestamp_15_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTimestamp("column", Calendar.getInstance()));
    }

    public void testGetTimestamp_16_oe() throws SQLException {

        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ts, rs.getTimestamp("column", Calendar.getInstance()));
    }

    public void testURL_1_oe() throws SQLException, MalformedURLException,
            IllegalAccessException, IllegalArgumentException,
            java.lang.reflect.InvocationTargetException
    {
        Method getUrlInt = null;
        Method getUrlString = null;
        try {
            getUrlInt = ResultSet.class.getMethod("getURL",
                        new Class[] { Integer.TYPE } );
            getUrlString = ResultSet.class.getMethod("getURL",
                           new Class[] { String.class } );
        } catch(NoSuchMethodException e) {
            // ignore
        } catch(SecurityException e) {
            // ignore
        }
        if (getUrlInt != null && getUrlString != null) {
            assertEquals(null, getUrlInt.invoke(rs, new Object[] { Integer.valueOf(1) } ) );
    }
    }

    public void testURL_2_oe() throws SQLException, MalformedURLException,
            IllegalAccessException, IllegalArgumentException,
            java.lang.reflect.InvocationTargetException
    {
        Method getUrlInt = null;
        Method getUrlString = null;
        try {
            getUrlInt = ResultSet.class.getMethod("getURL",
                        new Class[] { Integer.TYPE } );
            getUrlString = ResultSet.class.getMethod("getURL",
                           new Class[] { String.class } );
        } catch(NoSuchMethodException e) {
            // ignore
        } catch(SecurityException e) {
            // ignore
        }
        if (getUrlInt != null && getUrlString != null) {
            // removed other assertion
            assertTrue(rs.wasNull());
    }
    }

    public void testURL_3_oe() throws SQLException, MalformedURLException,
            IllegalAccessException, IllegalArgumentException,
            java.lang.reflect.InvocationTargetException
    {
        Method getUrlInt = null;
        Method getUrlString = null;
        try {
            getUrlInt = ResultSet.class.getMethod("getURL",
                        new Class[] { Integer.TYPE } );
            getUrlString = ResultSet.class.getMethod("getURL",
                           new Class[] { String.class } );
        } catch(NoSuchMethodException e) {
            // ignore
        } catch(SecurityException e) {
            // ignore
        }
        if (getUrlInt != null && getUrlString != null) {
            // removed other assertion
            // removed other assertion
            assertEquals(null, getUrlString.invoke(rs, new Object[] { "column" } ) );
    }
    }

    public void testURL_4_oe() throws SQLException, MalformedURLException,
            IllegalAccessException, IllegalArgumentException,
            java.lang.reflect.InvocationTargetException
    {
        Method getUrlInt = null;
        Method getUrlString = null;
        try {
            getUrlInt = ResultSet.class.getMethod("getURL",
                        new Class[] { Integer.TYPE } );
            getUrlString = ResultSet.class.getMethod("getURL",
                           new Class[] { String.class } );
        } catch(NoSuchMethodException e) {
            // ignore
        } catch(SecurityException e) {
            // ignore
        }
        if (getUrlInt != null && getUrlString != null) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            assertTrue(rs.wasNull());
    }
    }

    public void testURL_5_oe() throws SQLException, MalformedURLException,
            IllegalAccessException, IllegalArgumentException,
            java.lang.reflect.InvocationTargetException
    {
        Method getUrlInt = null;
        Method getUrlString = null;
        try {
            getUrlInt = ResultSet.class.getMethod("getURL",
                        new Class[] { Integer.TYPE } );
            getUrlString = ResultSet.class.getMethod("getURL",
                           new Class[] { String.class } );
        } catch(NoSuchMethodException e) {
            // ignore
        } catch(SecurityException e) {
            // ignore
        }
        if (getUrlInt != null && getUrlString != null) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // Set what gets returned to something other than the default
            URL u = new URL("http://www.apache.org");
            rs2.setNullURL(u);
            assertEquals(u, getUrlInt.invoke(rs, new Object[] { Integer.valueOf(1) } ) );
    }
    }

    public void testURL_6_oe() throws SQLException, MalformedURLException,
            IllegalAccessException, IllegalArgumentException,
            java.lang.reflect.InvocationTargetException
    {
        Method getUrlInt = null;
        Method getUrlString = null;
        try {
            getUrlInt = ResultSet.class.getMethod("getURL",
                        new Class[] { Integer.TYPE } );
            getUrlString = ResultSet.class.getMethod("getURL",
                           new Class[] { String.class } );
        } catch(NoSuchMethodException e) {
            // ignore
        } catch(SecurityException e) {
            // ignore
        }
        if (getUrlInt != null && getUrlString != null) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // removed other assertion
            // Set what gets returned to something other than the default
            URL u = new URL("http://www.apache.org");
            rs2.setNullURL(u);
            // removed other assertion
            assertEquals(u, getUrlString.invoke(rs, new Object[] { "column" } ) );
    }
    }

    public void testSetNullAsciiStream_1_oe() throws SQLException {

        assertNull(rs2.getNullAsciiStream());
    }

    public void testSetNullAsciiStream_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        assertNotNull(rs.getAsciiStream(1));
    }

    public void testSetNullAsciiStream_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        // removed other assertion
        assertEquals(stream, rs.getAsciiStream(1));
    }

    public void testSetNullAsciiStream_4_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getAsciiStream("column"));
    }

    public void testSetNullAsciiStream_5_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullAsciiStream(stream);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(stream, rs.getAsciiStream("column"));
    }

    public void testSetNullBigDecimal_1_oe() throws SQLException {

        assertNull(rs2.getNullBigDecimal());
    }

    public void testSetNullBigDecimal_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        assertNotNull(rs.getBigDecimal(1));
    }

    public void testSetNullBigDecimal_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        // removed other assertion
        assertEquals(bd, rs.getBigDecimal(1));
    }

    public void testSetNullBigDecimal_4_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getBigDecimal("column"));
    }

    public void testSetNullBigDecimal_5_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        BigDecimal bd = new BigDecimal(5.0);
        rs2.setNullBigDecimal(bd);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(bd, rs.getBigDecimal("column"));
    }

    public void testSetNullBinaryStream_1_oe() throws SQLException {

        assertNull(rs2.getNullBinaryStream());
    }

    public void testSetNullBinaryStream_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        assertNotNull(rs.getBinaryStream(1));
    }

    public void testSetNullBinaryStream_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        // removed other assertion
        assertEquals(stream, rs.getBinaryStream(1));
    }

    public void testSetNullBinaryStream_4_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getBinaryStream("column"));
    }

    public void testSetNullBinaryStream_5_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        InputStream stream = new ByteArrayInputStream(new byte[0]);
        rs2.setNullBinaryStream(stream);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(stream, rs.getBinaryStream("column"));
    }

    public void testSetNullBlob_1_oe() throws SQLException {

        assertNull(rs2.getNullBlob());
    }

    public void testSetNullBlob_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        assertNotNull(rs.getBlob(1));
    }

    public void testSetNullBlob_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        // removed other assertion
        assertEquals(blob, rs.getBlob(1));
    }

    public void testSetNullBlob_4_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getBlob("column"));
    }

    public void testSetNullBlob_5_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Blob blob = new SqlNullCheckedResultSetMockBlob();
        rs2.setNullBlob(blob);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(blob, rs.getBlob("column"));
    }

    public void testSetNullBoolean_1_oe() throws SQLException {

        assertEquals(false, rs2.getNullBoolean());
    }

    public void testSetNullBoolean_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        rs2.setNullBoolean(true);
        assertEquals(true, rs.getBoolean(1));
    }

    public void testSetNullBoolean_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        rs2.setNullBoolean(true);
        // removed other assertion
        assertEquals(true, rs.getBoolean("column"));
    }

    public void testSetNullByte_1_oe() throws SQLException {

        assertEquals((byte) 0, rs2.getNullByte());
    }

    public void testSetNullByte_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        byte b = (byte) 10;
        rs2.setNullByte(b);
        assertEquals(b, rs.getByte(1));
    }

    public void testSetNullByte_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        byte b = (byte) 10;
        rs2.setNullByte(b);
        // removed other assertion
        assertEquals(b, rs.getByte("column"));
    }

    public void testSetNullBytes_1_oe() throws SQLException {

        assertNull(rs2.getNullBytes());
    }

    public void testSetNullBytes_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        assertNotNull(rs.getBytes(1));
    }

    public void testSetNullBytes_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        // removed other assertion
        assertArrayEquals(b, rs.getBytes(1));
    }

    public void testSetNullBytes_4_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getBytes("column"));
    }

    public void testSetNullBytes_5_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        byte[] b = new byte[5];
        for (int i = 0; i < 5; i++) {
            b[0] = (byte) i;
        }
        rs2.setNullBytes(b);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertArrayEquals(b, rs.getBytes("column"));
    }

    public void testSetNullCharacterStream_1_oe() throws SQLException {

        assertNull(rs2.getNullCharacterStream());
    }

    public void testSetNullCharacterStream_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        assertNotNull(rs.getCharacterStream(1));
    }

    public void testSetNullCharacterStream_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        // removed other assertion
        assertEquals(reader, rs.getCharacterStream(1));
    }

    public void testSetNullCharacterStream_4_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getCharacterStream("column"));
    }

    public void testSetNullCharacterStream_5_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Reader reader = new CharArrayReader("this is a string".toCharArray());
        rs2.setNullCharacterStream(reader);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(reader, rs.getCharacterStream("column"));
    }

    public void testSetNullClob_1_oe() throws SQLException {

        assertNull(rs2.getNullClob());
    }

    public void testSetNullClob_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        assertNotNull(rs.getClob(1));
    }

    public void testSetNullClob_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        // removed other assertion
        assertEquals(clob, rs.getClob(1));
    }

    public void testSetNullClob_4_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getClob("column"));
    }

    public void testSetNullClob_5_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        Clob clob = new SqlNullCheckedResultSetMockClob();
        rs2.setNullClob(clob);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(clob, rs.getClob("column"));
    }

    public void testSetNullDate_1_oe() throws SQLException {

        assertNull(rs2.getNullDate());
    }

    public void testSetNullDate_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        assertNotNull(rs.getDate(1));
    }

    public void testSetNullDate_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        assertEquals(date, rs.getDate(1));
    }

    public void testSetNullDate_4_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getDate("column"));
    }

    public void testSetNullDate_5_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(date, rs.getDate("column"));
    }

    public void testSetNullDate_6_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getDate(1, Calendar.getInstance()));
    }

    public void testSetNullDate_7_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(date, rs.getDate(1, Calendar.getInstance()));
    }

    public void testSetNullDate_8_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getDate("column", Calendar.getInstance()));
    }

    public void testSetNullDate_9_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
        rs2.setNullDate(date);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(date, rs.getDate("column", Calendar.getInstance()));
    }

    public void testSetNullDouble_1_oe() throws SQLException {
        assertEquals(0.0, rs2.getNullDouble(), 0.0);
    }

    public void testSetNullDouble_2_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        double d = 10.0;
        rs2.setNullDouble(d);
        assertEquals(d, rs.getDouble(1), 0.0);
    }

    public void testSetNullDouble_3_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        double d = 10.0;
        rs2.setNullDouble(d);
        // removed other assertion
        assertEquals(d, rs.getDouble("column"), 0.0);
    }

    public void testSetNullFloat_1_oe() throws SQLException {
        assertEquals((float) 0.0, rs2.getNullFloat(), 0.0);
    }

    public void testSetNullFloat_2_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        float f = (float) 10.0;
        rs2.setNullFloat(f);
        assertEquals(f, rs.getFloat(1), 0.0);
    }

    public void testSetNullFloat_3_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        float f = (float) 10.0;
        rs2.setNullFloat(f);
        // removed other assertion
        assertEquals(f, rs.getFloat("column"), 0.0);
    }

    public void testSetNullInt_1_oe() throws SQLException {
        assertEquals(0, rs2.getNullInt());
    }

    public void testSetNullInt_2_oe() throws SQLException {
        // removed other assertion
        assertEquals(0, rs.getInt(1));
    }

    public void testSetNullInt_3_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testSetNullInt_4_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(0, rs.getInt("column"));
    }

    public void testSetNullInt_5_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue(rs.wasNull());
    }

    public void testSetNullInt_6_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        int i = 10;
        rs2.setNullInt(i);
        assertEquals(i, rs.getInt(1));
    }

    public void testSetNullInt_7_oe() throws SQLException {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // Set what gets returned to something other than the default
        int i = 10;
        rs2.setNullInt(i);
        // removed other assertion
        assertEquals(i, rs.getInt("column"));
    }

    public void testSetNullLong_1_oe() throws SQLException {
        assertEquals(0, rs2.getNullLong());
    }

    public void testSetNullLong_2_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        long l = 10;
        rs2.setNullLong(l);
        assertEquals(l, rs.getLong(1));
    }

    public void testSetNullLong_3_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        long l = 10;
        rs2.setNullLong(l);
        // removed other assertion
        assertEquals(l, rs.getLong("column"));
    }

    public void testSetNullObject_1_oe() throws SQLException {
        assertNull(rs2.getNullObject());
    }

    public void testSetNullObject_2_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        assertNotNull(rs.getObject(1));
    }

    public void testSetNullObject_3_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        assertEquals(o, rs.getObject(1));
    }

    public void testSetNullObject_4_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getObject("column"));
    }

    public void testSetNullObject_5_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(o, rs.getObject("column"));
    }

    public void testSetNullObject_6_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getObject(1, (Map<String, Class<?>>) null));
    }

    public void testSetNullObject_7_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(o, rs.getObject(1, (Map<String, Class<?>>) null));
    }

    public void testSetNullObject_8_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getObject("column", (Map<String, Class<?>>) null));
    }

    public void testSetNullObject_9_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Object o = new Object();
        rs2.setNullObject(o);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(o, rs.getObject("column", (Map<String, Class<?>>) null));
    }

    public void testSetNullShort_1_oe() throws SQLException {

        assertEquals((short) 0, rs2.getNullShort());
    }

    public void testSetNullShort_2_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        short s = (short) 10;
        rs2.setNullShort(s);
        assertEquals(s, rs.getShort(1));
    }

    public void testSetNullShort_3_oe() throws SQLException {

        // removed other assertion
        // Set what gets returned to something other than the default
        short s = (short) 10;
        rs2.setNullShort(s);
        // removed other assertion
        assertEquals(s, rs.getShort("column"));
    }

    public void testSetNullString_1_oe() throws SQLException {
        assertEquals(null, rs2.getNullString());
    }

    public void testSetNullString_2_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        String s = "hello, world";
        rs2.setNullString(s);
        assertEquals(s, rs.getString(1));
    }

    public void testSetNullString_3_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        String s = "hello, world";
        rs2.setNullString(s);
        // removed other assertion
        assertEquals(s, rs.getString("column"));
    }

    public void testSetNullRef_1_oe() throws SQLException {
        assertNull(rs2.getNullRef());
    }

    public void testSetNullRef_2_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        assertNotNull(rs.getRef(1));
    }

    public void testSetNullRef_3_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        // removed other assertion
        assertEquals(ref, rs.getRef(1));
    }

    public void testSetNullRef_4_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getRef("column"));
    }

    public void testSetNullRef_5_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Ref ref = new SqlNullCheckedResultSetMockRef();
        rs2.setNullRef(ref);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ref, rs.getRef("column"));
    }

    public void testSetNullTime_1_oe() throws SQLException {
        assertEquals(null, rs2.getNullTime());
    }

    public void testSetNullTime_2_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        assertNotNull(rs.getTime(1));
    }

    public void testSetNullTime_3_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        assertEquals(time, rs.getTime(1));
    }

    public void testSetNullTime_4_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTime("column"));
    }

    public void testSetNullTime_5_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(time, rs.getTime("column"));
    }

    public void testSetNullTime_6_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTime(1, Calendar.getInstance()));
    }

    public void testSetNullTime_7_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(time, rs.getTime(1, Calendar.getInstance()));
    }

    public void testSetNullTime_8_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTime("column", Calendar.getInstance()));
    }

    public void testSetNullTime_9_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Time time = new Time(new java.util.Date().getTime());
        rs2.setNullTime(time);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(time, rs.getTime("column", Calendar.getInstance()));
    }

    public void testSetNullTimestamp_1_oe() throws SQLException {
        assertEquals(null, rs2.getNullTimestamp());
    }

    public void testSetNullTimestamp_2_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        assertNotNull(rs.getTimestamp(1));
    }

    public void testSetNullTimestamp_3_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        assertEquals(ts, rs.getTimestamp(1));
    }

    public void testSetNullTimestamp_4_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTimestamp("column"));
    }

    public void testSetNullTimestamp_5_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ts, rs.getTimestamp("column"));
    }

    public void testSetNullTimestamp_6_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTimestamp(1, Calendar.getInstance()));
    }

    public void testSetNullTimestamp_7_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ts, rs.getTimestamp(1, Calendar.getInstance()));
    }

    public void testSetNullTimestamp_8_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotNull(rs.getTimestamp("column", Calendar.getInstance()));
    }

    public void testSetNullTimestamp_9_oe() throws SQLException {
        // removed other assertion
        // Set what gets returned to something other than the default
        Timestamp ts = new Timestamp(new java.util.Date().getTime());
        rs2.setNullTimestamp(ts);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals(ts, rs.getTimestamp("column", Calendar.getInstance()));
    }

}

class SqlNullUncheckedMockResultSet implements InvocationHandler {

    /**
     * Always return false for booleans, 0 for numerics, and null for Objects.
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {

        Class<?> returnType = method.getReturnType();

        if (method.getName().equals("wasNull")) {
            return Boolean.TRUE;

        } else if (returnType.equals(Boolean.TYPE)) {
            return Boolean.FALSE;

        } else if (returnType.equals(Integer.TYPE)) {
            return Integer.valueOf(0);

        } else if (returnType.equals(Short.TYPE)) {
            return Short.valueOf((short) 0);

        } else if (returnType.equals(Double.TYPE)) {
            return new Double(0);

        } else if (returnType.equals(Long.TYPE)) {
            return Long.valueOf(0);

        } else if (returnType.equals(Byte.TYPE)) {
            return Byte.valueOf((byte) 0);

        } else if (returnType.equals(Float.TYPE)) {
            return new Float(0);

        } else {
            return null;
        }
    }
}

class SqlNullCheckedResultSetMockBlob implements Blob {

    @Override
    public InputStream getBinaryStream() throws SQLException {
        return new ByteArrayInputStream(new byte[0]);
    }

    @Override
    public byte[] getBytes(long param, int param1) throws SQLException {
        return new byte[0];
    }

    @Override
    public long length() throws SQLException {
        return 0;
    }

    @Override
    public long position(byte[] values, long param) throws SQLException {
        return 0;
    }

    @Override
    public long position(Blob blob, long param) throws SQLException {
        return 0;
    }

    @Override
    public void truncate(long len) throws SQLException {

    }

    @Override
    public int setBytes(long pos, byte[] bytes) throws SQLException {
        return 0;
    }

    @Override
    public int setBytes(long pos, byte[] bytes, int offset, int len)
        throws SQLException {
        return 0;
    }

    @Override
    public OutputStream setBinaryStream(long pos) throws SQLException {
        return null;
    }

    /**
     * @throws SQLException  
     */
    @Override
    public void free() throws SQLException {

    }

    /**
     * @throws SQLException  
     */
    @Override
    public InputStream getBinaryStream(long pos, long length) throws SQLException {
      return null;
    }

}

class SqlNullCheckedResultSetMockClob implements Clob {

    @Override
    public InputStream getAsciiStream() throws SQLException {
        return null;
    }

    @Override
    public Reader getCharacterStream() throws SQLException {
        return null;
    }

    @Override
    public String getSubString(long param, int param1) throws SQLException {
        return "";
    }

    @Override
    public long length() throws SQLException {
        return 0;
    }

    @Override
    public long position(Clob clob, long param) throws SQLException {
        return 0;
    }

    @Override
    public long position(String str, long param) throws SQLException {
        return 0;
    }

    @Override
    public void truncate(long len) throws SQLException {

    }

    @Override
    public OutputStream setAsciiStream(long pos) throws SQLException {
        return null;
    }

    @Override
    public Writer setCharacterStream(long pos) throws SQLException {
        return null;
    }

    @Override
    public int setString(long pos, String str) throws SQLException {
        return 0;
    }

    @Override
    public int setString(long pos, String str, int offset, int len)
        throws SQLException {
        return 0;
    }

    /**
     * @throws SQLException  
     */
    @Override
    public void free() throws SQLException {

    }

    /**
     * @throws SQLException  
     */
    @Override
    public Reader getCharacterStream(long pos, long length) throws SQLException {
      return null;
    }

}

class SqlNullCheckedResultSetMockRef implements Ref {

    @Override
    public String getBaseTypeName() throws SQLException {
        return "";
    }

    @Override
    public Object getObject() throws SQLException {
        return null;
    }

    @Override
    public void setObject(Object value) throws SQLException {

    }

    @Override
    public Object getObject(Map<String,Class<?>> map) throws SQLException {
        return null;
    }

}
