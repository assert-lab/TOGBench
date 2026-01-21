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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ProxyFactoryTest performs simple type checking on proxy objects returned
 * from a ProxyFactory.
 */
public class ProxyFactoryTest_OE25Dev extends BaseTestCase {

    private static final InvocationHandler stub = new InvocationHandler() {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {

            return null;
        }
    };

    public void testCreateConnection_1_oe() {
        assertNotNull(ProxyFactory.instance().createConnection(stub));
    }

    public void testCreateDriver_1_oe() {
        assertNotNull(ProxyFactory.instance().createDriver(stub));
    }

    public void testCreatePreparedStatement_1_oe() {
        assertNotNull(ProxyFactory.instance().createPreparedStatement(stub));
    }

    public void testCreateResultSet_1_oe() {
        assertNotNull(ProxyFactory.instance().createResultSet(stub));
    }

    public void testCreateResultSetMetaData_1_oe() {
        assertNotNull(ProxyFactory.instance().createResultSetMetaData(stub));
    }

    public void testCreateStatement_1_oe() {
        assertNotNull(ProxyFactory.instance().createStatement(stub));
    }

    public void testCreateCallableStatement_1_oe() {
        assertNotNull(ProxyFactory.instance().createCallableStatement(stub));
    }

}
