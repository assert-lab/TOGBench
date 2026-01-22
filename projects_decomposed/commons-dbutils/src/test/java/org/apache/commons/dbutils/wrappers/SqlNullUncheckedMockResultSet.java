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

