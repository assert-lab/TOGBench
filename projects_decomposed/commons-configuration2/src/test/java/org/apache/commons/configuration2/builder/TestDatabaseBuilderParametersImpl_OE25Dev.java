/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.configuration2.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@code DatabaseBuilderParametersImpl}.
 *
 */
public class TestDatabaseBuilderParametersImpl_OE25Dev {
    /** The parameters object to be tested. */
    private DatabaseBuilderParametersImpl params;

    @Before
    public void setUp() throws Exception {
        params = new DatabaseBuilderParametersImpl();
    }

    /**
     * Tests whether properties can be set through BeanUtils.
     */

    /**
     * Tests whether the auto commit flag can be set.
     */

    /**
     * Tests whether the configuration name can be set.
     */

    /**
     * Tests whether the configuration name column can be set.
     */

    /**
     * Tests whether the data source property can be set.
     */

    /**
     * Tests whether the key column name can be set.
     */

    /**
     * Tests whether the table name can be set.
     */

    /**
     * Tests whether the value column name can be set.
     */

    @Test
    public void testBeanProperties_1_oe() throws Exception {
        BeanHelper.setProperty(params, "table", "testTable");
        BeanHelper.setProperty(params, "autoCommit", Boolean.FALSE);
        final Map<String, Object> map = params.getParameters();
        assertEquals("Wrong table name", "testTable", map.get("table"));
    }

    @Test
    public void testBeanProperties_2_oe() throws Exception {
        BeanHelper.setProperty(params, "table", "testTable");
        BeanHelper.setProperty(params, "autoCommit", Boolean.FALSE);
        final Map<String, Object> map = params.getParameters();
        assertEquals("Wrong auto commit", Boolean.FALSE, map.get("autoCommit"));
    }

    @Test
    public void testSetAutoCommit_1_oe() {
        assertSame("Wrong result", params, params.setAutoCommit(true));
    }

    @Test
    public void testSetConfigurationName_1_oe() {
        final String confName = "TestConfiguration";
        assertSame("Wrong result", params, params.setConfigurationName(confName));
    }

    @Test
    public void testSetConfigurationNameColumn_1_oe() {
        final String colName = "CONFIG_COLUMN";
        assertSame("Wrong result", params, params.setConfigurationNameColumn(colName));
    }

    @Test
    public void testSetDataSource_1_oe() {
        final DataSource src = EasyMock.createMock(DataSource.class);
        EasyMock.replay(src);
        assertSame("Wrong result", params, params.setDataSource(src));
    }

    @Test
    public void testSetKeyColumn_1_oe() {
        final String colName = "KEY_COLUMN";
        assertSame("Wrong result", params, params.setKeyColumn(colName));
    }

    @Test
    public void testSetTable_1_oe() {
        final String table = "TestTable";
        assertSame("Wrong result", params, params.setTable(table));
    }

    @Test
    public void testSetValueColumn_1_oe() {
        final String colName = "VALUE_COLUMN";
        assertSame("Wrong result", params, params.setValueColumn(colName));
    }

}
