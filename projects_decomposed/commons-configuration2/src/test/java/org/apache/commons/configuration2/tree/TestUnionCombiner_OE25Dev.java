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
package org.apache.commons.configuration2.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.apache.commons.configuration2.BaseHierarchicalConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Test;

/**
 * Test class for UnionCombiner.
 *
 */
public class TestUnionCombiner_OE25Dev extends AbstractCombinerTest {
    /**
     * Creates the combiner.
     *
     * @return the combiner
     */
    @Override
    protected NodeCombiner createCombiner() {
        return new UnionCombiner();
    }

    /**
     * Tests combination of attributes.
     */

    /**
     * Tests combination of lists.
     */

    /**
     * Tests combination of simple values (no lists).
     */

    /**
     * Tests combinations of elements with attributes.
     */

    /**
     * Tests combining a list of tables. Per default the table elements will be combined. But if they are defined as list
     * elements, the resulting tree should contain two table nodes.
     */

@Test
    public void testAttributes_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong number of attributes", 0, config.getMaxIndex("database.tables.table(0)[@id]"));
    }

@Test
    public void testAttributes_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        assertEquals("Wrong value of attribute", 1, config.getInt("database.tables.table(0)[@id](0)"));
    }

@Test
    public void testLists_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Too few list elements", 2, config.getMaxIndex("net.service.url"));
    }

@Test
    public void testLists_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        assertEquals("Wrong first service", "http://service1.org", config.getString("net.service.url(0)"));
    }

@Test
    public void testLists_3_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong second service", "http://service2.org", config.getString("net.service.url(1)"));
    }

@Test
    public void testLists_4_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong service attribute", 2, config.getInt("net.service.url(2)[@type]"));
    }

@Test
    public void testLists_5_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong number of server elements", 3, config.getMaxIndex("net.server.url"));
    }

@Test
    public void testSimpleValues_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Too few bgcolors", 1, config.getMaxIndex("gui.bgcolor"));
    }

@Test
    public void testSimpleValues_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        assertEquals("Wrong first color", "green", config.getString("gui.bgcolor(0)"));
    }

@Test
    public void testSimpleValues_3_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong second color", "black", config.getString("gui.bgcolor(1)"));
    }

@Test
    public void testSimpleValues_4_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong number of selcolors", 0, config.getMaxIndex("gui.selcolor"));
    }

@Test
    public void testSimpleValues_5_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong selcolor", "yellow", config.getString("gui.selcolor"));
    }

@Test
    public void testSimpleValuesWithAttributes_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Too few level elements", 1, config.getMaxIndex("gui.level"));
    }

@Test
    public void testSimpleValuesWithAttributes_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        assertEquals("Wrong value of first element", 1, config.getInt("gui.level(0)"));
    }

@Test
    public void testSimpleValuesWithAttributes_3_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong value of second element", 4, config.getInt("gui.level(1)"));
    }

@Test
    public void testSimpleValuesWithAttributes_4_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong value of first attribute", 2, config.getInt("gui.level(0)[@default]"));
    }

@Test
    public void testSimpleValuesWithAttributes_5_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Found wrong attribute", config.containsKey("gui.level(0)[@min]"));
    }

@Test
    public void testSimpleValuesWithAttributes_6_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong value of second attribute", 1, config.getInt("gui.level(1)[@min]"));
    }

@Test
    public void testTableList_1_oe() throws ConfigurationException {
        combiner.addListNode("table");
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong name of first table", "documents", config.getString("database.tables.table(0).name"));
    }

@Test
    public void testTableList_2_oe() throws ConfigurationException {
        combiner.addListNode("table");
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        assertEquals("Wrong id of first table", 1, config.getInt("database.tables.table(0)[@id]"));
    }

@Test
    public void testTableList_3_oe() throws ConfigurationException {
        combiner.addListNode("table");
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong name of second table", "tasks", config.getString("database.tables.table(1).name"));
    }

@Test
    public void testTableList_4_oe() throws ConfigurationException {
        combiner.addListNode("table");
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong id of second table", 2, config.getInt("database.tables.table(1)[@id]"));
    }

}
