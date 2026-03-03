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

package org.apache.commons.configuration2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.configuration2.builder.FileBasedBuilderParametersImpl;
import org.apache.commons.configuration2.builder.combined.CombinedConfigurationBuilder;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Test case for the {@link SubsetConfiguration} class.
 *
 */
public class TestSubsetConfiguration_OE25Dev {
    static final String TEST_DIR = ConfigurationAssert.TEST_DIR_NAME;
    static final String TEST_FILE = "testDigesterConfiguration2.xml";

    /**
     * Tests whether the list delimiter handler from the parent configuration is used.
     */

    /**
     * Tries to create an instance without a parent configuration.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInitNoParent() {
        new SubsetConfiguration(null, "");
    }

    /**
     * Tests manipulating the interpolator.
     */
    @Test
    public void testInterpolator() {
        final BaseConfiguration config = new BaseConfiguration();
        final AbstractConfiguration subset = (AbstractConfiguration) config.subset("prefix");
        InterpolationTestHelper.testGetInterpolator(subset);
    }

    /**
     * Tests whether a list delimiter handler is used correctly.
     */

    /**
     * Tests whether the list delimiter handler is also set for the parent configuration.
     */

    /**
     * Tests the case that the parent configuration is not derived from AbstractConfiguration and thus does not support a
     * list delimiter handler.
     */

    @Test
    public void testClear_1_oe() {
        final Configuration config = new BaseConfiguration();
        config.setProperty("test.key1", "value1");
        config.setProperty("testing.key2", "value1");

        final Configuration subset = config.subset("test");
        subset.clear();

        assertTrue("the subset is not empty", subset.isEmpty());
    }

    @Test
    public void testClear_2_oe() {
        final Configuration config = new BaseConfiguration();
        config.setProperty("test.key1", "value1");
        config.setProperty("testing.key2", "value1");

        final Configuration subset = config.subset("test");
        subset.clear();

        assertFalse("the parent configuration is empty", config.isEmpty());
    }

    @Test
    public void testGetChildKey_1_oe() {
        final Configuration conf = new BaseConfiguration();
        SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");
        assertEquals("parent key for \"prefixkey\"", "key", subset.getChildKey("prefix.key"));
    }

    @Test
    public void testGetChildKey_2_oe() {
        final Configuration conf = new BaseConfiguration();
        SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");
        assertEquals("parent key for \"prefix\"", "", subset.getChildKey("prefix"));
    }

    @Test
    public void testGetChildKey_3_oe() {
        final Configuration conf = new BaseConfiguration();
        SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");

        subset = new SubsetConfiguration(conf, "prefix", null);
        assertEquals("parent key for \"prefixkey\"", "key", subset.getChildKey("prefixkey"));
    }

    @Test
    public void testGetChildKey_4_oe() {
        final Configuration conf = new BaseConfiguration();
        SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");

        subset = new SubsetConfiguration(conf, "prefix", null);
        assertEquals("parent key for \"prefix\"", "", subset.getChildKey("prefix"));
    }

    @Test
    public void testGetKeys_1_oe() {
        final Configuration conf = new BaseConfiguration();
        conf.setProperty("test", "value0");
        conf.setProperty("test.key1", "value1");
        conf.setProperty("testing.key2", "value1");

        final Configuration subset = new SubsetConfiguration(conf, "test", ".");

        final Iterator<String> it = subset.getKeys();
        assertEquals("1st key", "", it.next());
    }

    @Test
    public void testGetKeysWithPrefix_1_oe() {
        final Configuration conf = new BaseConfiguration();
        conf.setProperty("test.abc", "value0");
        conf.setProperty("test.abc.key1", "value1");
        conf.setProperty("test.abcdef.key2", "value1");

        final Configuration subset = new SubsetConfiguration(conf, "test", ".");

        final Iterator<String> it = subset.getKeys("abc");
        assertEquals("1st key", "abc", it.next());
    }

    @Test
    public void testGetList_1_oe() {
        final BaseConfiguration conf = new BaseConfiguration();
        conf.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        conf.setProperty("test.abc", "value0,value1");
        conf.addProperty("test.abc", "value3");

        final Configuration subset = new SubsetConfiguration(conf, "test", ".");
        final List<Object> list = subset.getList("abc", new ArrayList<>());
        assertEquals(3, list.size());
    }

    @Test
    public void testGetListDelimiterHandlerFromParent_1_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        final AbstractConfiguration subset = (AbstractConfiguration) config.subset("prefix");
        final ListDelimiterHandler listHandler = new DefaultListDelimiterHandler(',');
        config.setListDelimiterHandler(listHandler);
        assertSame("Not list handler from parent", listHandler, subset.getListDelimiterHandler());
    }

    @Test
    public void testGetParent_1_oe() {
        final Configuration conf = new BaseConfiguration();
        final SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");

        assertEquals("parent", conf, subset.getParent());
    }

    @Test
    public void testGetParentKey_1_oe() {
        final Configuration conf = new BaseConfiguration();
        SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");
        assertEquals("parent key for \"key\"", "prefix.key", subset.getParentKey("key"));
    }

    @Test
    public void testGetParentKey_2_oe() {
        final Configuration conf = new BaseConfiguration();
        SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");
        assertEquals("parent key for \"\"", "prefix", subset.getParentKey(""));
    }

    @Test
    public void testGetParentKey_3_oe() {
        final Configuration conf = new BaseConfiguration();
        SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");

        subset = new SubsetConfiguration(conf, "prefix", null);
        assertEquals("parent key for \"key\"", "prefixkey", subset.getParentKey("key"));
    }

    @Test
    public void testGetParentKey_4_oe() {
        final Configuration conf = new BaseConfiguration();
        SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");

        subset = new SubsetConfiguration(conf, "prefix", null);
        assertEquals("parent key for \"\"", "prefix", subset.getParentKey(""));
    }

    @Test
    public void testGetPrefix_1_oe() {
        final Configuration conf = new BaseConfiguration();
        final SubsetConfiguration subset = new SubsetConfiguration(conf, "prefix", ".");

        assertEquals("prefix", "prefix", subset.getPrefix());
    }

    @Test
    public void testGetProperty_1_oe() {
        final Configuration conf = new BaseConfiguration();
        conf.setProperty("test.key1", "value1");
        conf.setProperty("testing.key2", "value1");

        final Configuration subset = new SubsetConfiguration(conf, "test", ".");
        assertFalse("the subset is empty", subset.isEmpty());
    }

    @Test
    public void testGetProperty_2_oe() {
        final Configuration conf = new BaseConfiguration();
        conf.setProperty("test.key1", "value1");
        conf.setProperty("testing.key2", "value1");

        final Configuration subset = new SubsetConfiguration(conf, "test", ".");
        assertTrue("'key1' not found in the subset", subset.containsKey("key1"));
    }

    @Test
    public void testGetProperty_3_oe() {
        final Configuration conf = new BaseConfiguration();
        conf.setProperty("test.key1", "value1");
        conf.setProperty("testing.key2", "value1");

        final Configuration subset = new SubsetConfiguration(conf, "test", ".");
        assertFalse("'ng.key2' found in the subset", subset.containsKey("ng.key2"));
    }

    @Test
    public void testInterpolationForKeysOfTheParent_1_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        config.setProperty("test", "junit");
        config.setProperty("prefix.key", "${test}");
        final AbstractConfiguration subset = (AbstractConfiguration) config.subset("prefix");
        assertEquals("Interpolation does not resolve parent keys", "junit", subset.getString("key", ""));
    }

    @Test
    public void testListDelimiterHandling_1_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        final Configuration subset = config.subset("prefix");
        config.setListDelimiterHandler(new DefaultListDelimiterHandler('/'));
        subset.addProperty("list", "a/b/c");
        assertEquals("Wrong size of list", 3, config.getList("prefix.list").size());
    }

    @Test
    public void testListDelimiterHandling_2_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        final Configuration subset = config.subset("prefix");
        config.setListDelimiterHandler(new DefaultListDelimiterHandler('/'));
        subset.addProperty("list", "a/b/c");

        ((AbstractConfiguration) subset).setListDelimiterHandler(new DefaultListDelimiterHandler(';'));
        subset.addProperty("list2", "a;b;c");
        assertEquals("Wrong size of list2", 3, config.getList("prefix.list2").size());
    }

    @Test
    public void testLocalLookupsInInterpolatorAreInherited_1_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        final ConfigurationInterpolator interpolator = config.getInterpolator();
        interpolator.registerLookup("brackets", key -> "(" + key + ")");
        config.setProperty("prefix.var", "${brackets:x}");
        final AbstractConfiguration subset = (AbstractConfiguration) config.subset("prefix");
        assertEquals("Local lookup was not inherited", "(x)", subset.getString("var", ""));
    }

    @Test
    public void testNested_1_oe() throws Exception {
        final CombinedConfigurationBuilder builder = new CombinedConfigurationBuilder();
        builder.configure(new FileBasedBuilderParametersImpl().setFile(ConfigurationAssert.getTestFile(TEST_FILE)));
        final Configuration config = builder.getConfiguration();
        final Configuration subConf = config.subset("tables.table(0)");
        assertTrue(subConf.getKeys().hasNext());
    }

    @Test
    public void testNested_2_oe() throws Exception {
        final CombinedConfigurationBuilder builder = new CombinedConfigurationBuilder();
        builder.configure(new FileBasedBuilderParametersImpl().setFile(ConfigurationAssert.getTestFile(TEST_FILE)));
        final Configuration config = builder.getConfiguration();
        final Configuration subConf = config.subset("tables.table(0)");
        final Configuration subSubConf = subConf.subset("fields.field(1)");
        final Iterator<String> itKeys = subSubConf.getKeys();
        final Set<String> keys = new HashSet<>();
        keys.add("name");
        keys.add("type");
        while (itKeys.hasNext()) {
            final String k = itKeys.next();
            assertTrue(keys.contains(k));
    }
    }

    @Test
    public void testNested_3_oe() throws Exception {
        final CombinedConfigurationBuilder builder = new CombinedConfigurationBuilder();
        builder.configure(new FileBasedBuilderParametersImpl().setFile(ConfigurationAssert.getTestFile(TEST_FILE)));
        final Configuration config = builder.getConfiguration();
        final Configuration subConf = config.subset("tables.table(0)");
        final Configuration subSubConf = subConf.subset("fields.field(1)");
        final Iterator<String> itKeys = subSubConf.getKeys();
        final Set<String> keys = new HashSet<>();
        keys.add("name");
        keys.add("type");
        while (itKeys.hasNext()) {
            final String k = itKeys.next();
            keys.remove(k);
        }
        assertTrue(keys.isEmpty());
    }

    @Test
    public void testSetListDelimiterHandlerInParent_1_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        final AbstractConfiguration subset = (AbstractConfiguration) config.subset("prefix");
        final ListDelimiterHandler listHandler = new DefaultListDelimiterHandler(',');
        subset.setListDelimiterHandler(listHandler);
        assertSame("Handler not passed to parent", listHandler, config.getListDelimiterHandler());
    }

    @Test
    public void testSetListDelimiterHandlerParentNotSupported_1_oe() {
        final Configuration config = EasyMock.createNiceMock(Configuration.class);
        EasyMock.replay(config);
        final SubsetConfiguration subset = new SubsetConfiguration(config, "prefix");
        final ListDelimiterHandler listHandler = new DefaultListDelimiterHandler(',');
        subset.setListDelimiterHandler(listHandler);
        assertSame("List delimiter handler not set", listHandler, subset.getListDelimiterHandler());
    }

    @Test
    public void testSetPrefix_1_oe() {
        final Configuration conf = new BaseConfiguration();
        final SubsetConfiguration subset = new SubsetConfiguration(conf, null, ".");
        subset.setPrefix("prefix");

        assertEquals("prefix", "prefix", subset.getPrefix());
    }

    @Test
    public void testSetProperty_1_oe() {
        final Configuration conf = new BaseConfiguration();
        final Configuration subset = new SubsetConfiguration(conf, "test", ".");

        subset.setProperty("key1", "value1");
        assertEquals("key1 in the subset configuration", "value1", subset.getProperty("key1"));
    }

    @Test
    public void testSetProperty_2_oe() {
        final Configuration conf = new BaseConfiguration();
        final Configuration subset = new SubsetConfiguration(conf, "test", ".");

        subset.setProperty("key1", "value1");
        assertEquals("test.key1 in the parent configuration", "value1", conf.getProperty("test.key1"));
    }

    @Test
    public void testSetProperty_3_oe() {
        final Configuration conf = new BaseConfiguration();
        final Configuration subset = new SubsetConfiguration(conf, "test", ".");

        subset.setProperty("key1", "value1");

        conf.setProperty("test.key2", "value2");
        assertEquals("test.key2 in the parent configuration", "value2", conf.getProperty("test.key2"));
    }

    @Test
    public void testSetProperty_4_oe() {
        final Configuration conf = new BaseConfiguration();
        final Configuration subset = new SubsetConfiguration(conf, "test", ".");

        subset.setProperty("key1", "value1");

        conf.setProperty("test.key2", "value2");
        assertEquals("key2 in the subset configuration", "value2", subset.getProperty("key2"));
    }

    @Test
    public void testThrowExceptionOnMissing_2_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        config.setThrowExceptionOnMissing(true);

        final SubsetConfiguration subset = new SubsetConfiguration(config, "prefix");

        try {
            subset.getString("foo");
        } catch (final NoSuchElementException e) {
        }

        config.setThrowExceptionOnMissing(false);
        assertNull(subset.getString("foo"));
    }

}
