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