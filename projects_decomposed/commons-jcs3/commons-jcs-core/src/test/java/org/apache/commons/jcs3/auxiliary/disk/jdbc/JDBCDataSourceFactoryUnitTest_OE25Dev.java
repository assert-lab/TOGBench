package org.apache.commons.jcs3.auxiliary.disk.jdbc;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.datasources.SharedPoolDataSource;
import org.apache.commons.jcs3.auxiliary.disk.jdbc.dsfactory.DataSourceFactory;
import org.apache.commons.jcs3.auxiliary.disk.jdbc.dsfactory.JndiDataSourceFactory;
import org.apache.commons.jcs3.auxiliary.disk.jdbc.dsfactory.SharedPoolDataSourceFactory;

import junit.framework.TestCase;

/** Unit tests for the data source factories */
public class JDBCDataSourceFactoryUnitTest_OE25Dev
    extends TestCase
{
    /** Verify that we can configure the object based on the props.
     *  @throws SQLException
     */

    /** Verify that we can configure the object based on the attributes.
     *  @throws SQLException
     */

    /** Verify that we can configure the object based on JNDI.
     *  @throws SQLException
     */

    /* For JNDI mocking */
    public static class MockInitialContextFactory implements InitialContextFactory
    {
        private static final Context context;

        static
        {
            try
            {
                context = new InitialContext(true)
                {
                    final Map<String, Object> bindings = new HashMap<>();

                    @Override
                    public void bind(final String name, final Object obj) throws NamingException
                    {
                        bindings.put(name, obj);
                    }

                    @Override
                    public Object lookup(final String name) throws NamingException
                    {
                        return bindings.get(name);
                    }

                    @Override
                    public Hashtable<?, ?> getEnvironment() throws NamingException
                    {
                        return new Hashtable<>();
                    }
                };
            }
            catch (final NamingException e)
            {
            	// can't happen.
                throw new RuntimeException(e);
            }
        }

        @Override
		public Context getInitialContext(final Hashtable<?, ?> environment) throws NamingException
        {
            return context;
        }

        public static void bind(final String name, final Object obj)
        {
            try
            {
                context.bind(name, obj);
            }
            catch (final NamingException e)
            {
            	// can't happen.
                throw new RuntimeException(e);
            }
        }
    }


}

