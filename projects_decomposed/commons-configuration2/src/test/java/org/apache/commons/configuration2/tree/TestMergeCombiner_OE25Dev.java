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
import static org.junit.Assert.assertNull;

import java.util.List;

import org.apache.commons.configuration2.BaseHierarchicalConfiguration;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;
import org.junit.Test;

/**
 * Test class for MergeCombiner.
 *
 */
public class TestMergeCombiner_OE25Dev extends AbstractCombinerTest {
    /**
     * Helper method for checking the combined table structure.
     *
     * @param config the config
     * @return the node for the table element
     */
    private ImmutableNode checkTable(final HierarchicalConfiguration<ImmutableNode> config) {
        assertEquals("Wrong number of tables", 1, config.getMaxIndex("database.tables.table"));
        final HierarchicalConfiguration<ImmutableNode> c = config.configurationAt("database.tables.table(0)");
        assertEquals("Wrong table name", "documents", c.getString("name"));
        assertEquals("Wrong number of fields", 2, c.getMaxIndex("fields.field.name"));
        assertEquals("Wrong field", "docname", c.getString("fields.field(1).name"));

        final NodeHandler<ImmutableNode> nodeHandler = config.getNodeModel().getNodeHandler();
        final List<QueryResult<ImmutableNode>> nds = config.getExpressionEngine().query(nodeHandler.getRootNode(), "database.tables.table", nodeHandler);
        assertFalse("No node found", nds.isEmpty());
        assertFalse("Not a node result", nds.get(0).isAttributeResult());
        return nds.get(0).getNode();
    }

    /**
     * Creates the combiner.
     *
     * @return the combiner
     */
    @Override
    protected NodeCombiner createCombiner() {
        return new MergeCombiner();
    }

    /**
     * Tests combination of attributes.
     */

    /**
     * Tests the combination of the table structure. With the merge combiner both table 1 and table 2 should be present.
     */
    @Test
    public void testCombinedTable() throws ConfigurationException {
        checkTable(createCombinedConfiguration());
    }

    /**
     * Tests if a list from the first node structure overrides a list in the second structure.
     */

    /**
     * Tests if a list from the second structure is added if it is not defined in the first structure.
     */

    /**
     * Tests whether property values are correctly overridden.
     */

    /**
     * Tests combination of simple elements.
     */

    @Test
    public void testAttributes_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong value of min attribute", 1, config.getInt("gui.level[@min]"));
    }

    @Test
    public void testAttributes_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong value of default attribute", 2, config.getInt("gui.level[@default]"));
    }

    @Test
    public void testAttributes_3_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong number of id attributes", 0, config.getMaxIndex("database.tables.table(0)[@id]"));
    }

    @Test
    public void testAttributes_4_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong value of table id", 1, config.getInt("database.tables.table(0)[@id]"));
    }

    @Test
    public void testListFromFirstStructure_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong number of services", 0, config.getMaxIndex("net.service.url"));
    }

    @Test
    public void testListFromFirstStructure_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong service", "http://service1.org", config.getString("net.service.url"));
    }

    @Test
    public void testListFromFirstStructure_3_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertFalse("Type attribute available", config.containsKey("net.service.url[@type]"));
    }

    @Test
    public void testListFromSecondStructure_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong number of servers", 3, config.getMaxIndex("net.server.url"));
    }

    @Test
    public void testListFromSecondStructure_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong server", "http://testsvr.com", config.getString("net.server.url(2)"));
    }

    @Test
    public void testMerge_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        config.setExpressionEngine(new XPathExpressionEngine());
        assertEquals("Wrong number of Channels", 3, config.getMaxIndex("Channels/Channel"));
    }

    @Test
    public void testMerge_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        config.setExpressionEngine(new XPathExpressionEngine());
        assertEquals("Bad Channel 1 Name", "My Channel", config.getString("Channels/Channel[@id='1']/Name"));
    }

    @Test
    public void testMerge_3_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        config.setExpressionEngine(new XPathExpressionEngine());
        assertEquals("Bad Channel Type", "half", config.getString("Channels/Channel[@id='1']/@type"));
    }

    @Test
    public void testMerge_4_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        config.setExpressionEngine(new XPathExpressionEngine());
        assertEquals("Bad Channel 2 Name", "Channel 2", config.getString("Channels/Channel[@id='2']/Name"));
    }

    @Test
    public void testMerge_5_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        config.setExpressionEngine(new XPathExpressionEngine());
        assertEquals("Bad Channel Type", "full", config.getString("Channels/Channel[@id='2']/@type"));
    }

    @Test
    public void testMerge_6_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        config.setExpressionEngine(new XPathExpressionEngine());
        assertEquals("Bad Channel Data", "test 1 data", config.getString("Channels/Channel[@id='1']/ChannelData"));
    }

    @Test
    public void testMerge_7_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        config.setExpressionEngine(new XPathExpressionEngine());
        assertEquals("Bad Channel Data", "test 2 data", config.getString("Channels/Channel[@id='2']/ChannelData"));
    }

    @Test
    public void testMerge_8_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        config.setExpressionEngine(new XPathExpressionEngine());
        assertEquals("Bad Channel Data", "more test 2 data", config.getString("Channels/Channel[@id='2']/MoreChannelData"));
    }

    @Test
    public void testOverrideValues_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong user", "Admin", config.getString("base.services.security.login.user"));
    }

    @Test
    public void testOverrideValues_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong user type", "default", config.getString("base.services.security.login.user[@type]"));
    }

    @Test
    public void testOverrideValues_3_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertNull("Wrong password", config.getString("base.services.security.login.passwd"));
    }

    @Test
    public void testOverrideValues_4_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong password type", "secret", config.getString("base.services.security.login.passwd[@type]"));
    }

    @Test
    public void testSimpleValues_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong number of bgcolors", 0, config.getMaxIndex("gui.bgcolor"));
    }

    @Test
    public void testSimpleValues_2_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong bgcolor", "green", config.getString("gui.bgcolor"));
    }

    @Test
    public void testSimpleValues_3_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong selcolor", "yellow", config.getString("gui.selcolor"));
    }

    @Test
    public void testSimpleValues_4_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong fgcolor", "blue", config.getString("gui.fgcolor"));
    }

    @Test
    public void testSimpleValues_5_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration config = createCombinedConfiguration();
        assertEquals("Wrong level", 1, config.getInt("gui.level"));
    }

}
