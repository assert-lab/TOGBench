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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.configuration2.SynchronizerTestImpl.Methods;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.convert.LegacyListDelimiterHandler;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.event.ConfigurationEvent;
import org.apache.commons.configuration2.event.EventListenerTestImpl;
import org.apache.commons.configuration2.ex.ConfigurationRuntimeException;
import org.apache.commons.configuration2.io.FileHandler;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@code CompositeConfiguration}.
 *
 */
public class TestCompositeConfiguration_OE25Dev {
    /** Constant for a test property to be checked. */
    private static final String TEST_PROPERTY = "test.source.property";

    protected PropertiesConfiguration conf1;
    protected PropertiesConfiguration conf2;
    protected XMLConfiguration xmlConf;
    protected CompositeConfiguration cc;

    /**
     * The File that we test with
     */
    private final String testProperties = ConfigurationAssert.getTestFile("test.properties").getAbsolutePath();
    private final String testProperties2 = ConfigurationAssert.getTestFile("test2.properties").getAbsolutePath();
    private final String testPropertiesXML = ConfigurationAssert.getTestFile("test.xml").getAbsolutePath();

    /**
     * Helper method for testing whether the list delimiter is correctly handled.
     */
    private void checkSetListDelimiterHandler() {
        cc.addProperty("test.list", "a/b/c");
        cc.addProperty("test.property", "a,b,c");
        assertEquals("Wrong number of list elements", 3, cc.getList("test.list").size());
        assertEquals("Wrong value of property", "a,b,c", cc.getString("test.property"));

        final AbstractConfiguration config = (AbstractConfiguration) cc.getInMemoryConfiguration();
        final DefaultListDelimiterHandler listHandler = (DefaultListDelimiterHandler) config.getListDelimiterHandler();
        assertEquals("Wrong list delimiter", '/', listHandler.getDelimiter());
    }

    /**
     * Creates a test synchronizer and installs it at the test configuration.
     *
     * @return the test synchronizer
     */
    private SynchronizerTestImpl installSynchronizer() {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);
        final SynchronizerTestImpl sync = new SynchronizerTestImpl();
        cc.setSynchronizer(sync);
        return sync;
    }

    /**
     * Prepares a test for interpolation with multiple configurations and similar properties.
     */
    private void prepareInterpolationTest() {
        final PropertiesConfiguration p = new PropertiesConfiguration();
        p.addProperty("foo", "initial");
        p.addProperty("bar", "${foo}");
        p.addProperty("prefix.foo", "override");

        cc.addConfiguration(p.subset("prefix"));
        cc.addConfiguration(p);
        assertEquals("Wrong value on direct access", "override", cc.getString("bar"));
    }

    @Before
    public void setUp() throws Exception {
        cc = new CompositeConfiguration();
        final ListDelimiterHandler listHandler = new LegacyListDelimiterHandler(',');
        conf1 = new PropertiesConfiguration();
        conf1.setListDelimiterHandler(listHandler);
        final FileHandler handler1 = new FileHandler(conf1);
        handler1.setFileName(testProperties);
        handler1.load();
        conf2 = new PropertiesConfiguration();
        conf2.setListDelimiterHandler(listHandler);
        final FileHandler handler2 = new FileHandler(conf2);
        handler2.setFileName(testProperties2);
        handler2.load();
        xmlConf = new XMLConfiguration();
        final FileHandler handler3 = new FileHandler(xmlConf);
        handler3.load(new File(testPropertiesXML));

        cc.setThrowExceptionOnMissing(true);
    }

    /**
     * Prepares a test of the getSource() method.
     */
    private void setUpSourceTest() {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);
    }

    /**
     * Tests whether adding a child configuration is synchronized.
     */
    @Test
    public void testAddConfigurationSynchronized() {
        final SynchronizerTestImpl sync = installSynchronizer();
        cc.addConfiguration(xmlConf);
        sync.verify(Methods.BEGIN_WRITE, Methods.END_WRITE);
    }

    /**
     * Tests adding values. Make sure they _DON'T_ override any other properties but add to the existing properties and keep
     * sequence
     */

    /**
     * Tests setting values. These are set in memory mode only!
     */

    /**
     * Ensures that event listeners are not cloned.
     */

    /**
     * Tests whether interpolation works as expected after cloning.
     */

    /**
     * Tests cloning if one of the contained configurations does not support this operation. This should cause an exception.
     */
    @Test(expected = ConfigurationRuntimeException.class)
    public void testCloneNotSupported() {
        cc.addConfiguration(new NonCloneableConfiguration());
        cc.clone();
    }

    /**
     * Tests getting a default when the key doesn't exist
     */

    /**
     * Tests whether add property events are triggered.
     */
    @Test
    public void testEventAddProperty() {
        final EventListenerTestImpl listener = new EventListenerTestImpl(cc);
        cc.addEventListener(ConfigurationEvent.ANY, listener);
        cc.addProperty("test", "value");
        listener.checkEvent(ConfigurationEvent.ADD_PROPERTY, "test", "value", true);
        listener.checkEvent(ConfigurationEvent.ADD_PROPERTY, "test", "value", false);
        listener.done();
    }

    /**
     * Tests whether clear property events are triggered.
     */

    /**
     * Tests whether set property events are triggered.
     */
    @Test
    public void testEventSetProperty() {
        final EventListenerTestImpl listener = new EventListenerTestImpl(cc);
        cc.addEventListener(ConfigurationEvent.ANY, listener);
        cc.setProperty("test", "value");
        listener.checkEvent(ConfigurationEvent.SET_PROPERTY, "test", "value", true);
        listener.checkEvent(ConfigurationEvent.SET_PROPERTY, "test", "value", false);
        listener.done();
    }

    /**
     * Tests whether access to a configuration by index is synchronized.
     */

    /**
     * Tests whether access to the in-memory configuration is synchronized.
     */
    @Test
    public void testGetInMemoryConfigurationSynchronized() {
        final SynchronizerTestImpl sync = installSynchronizer();
        cc.getInMemoryConfiguration();
        sync.verify(Methods.BEGIN_READ, Methods.END_READ);
    }

    /**
     * Tests {@code getKeys(String key)} preserves the order
     */

    /**
     * Tests {@code getKeys} preserves the order
     */

    /**
     * Tests querying a list when a tricky interpolation is involved. This is related to CONFIGURATION-339.
     */

    /**
     * Tests whether querying the number of child configurations is synchronized.
     */

    /**
     * Tests the getSource() method for a property contained in the in memory configuration.
     */

    /**
     * Tests the getSource() method if the property is defined by multiple child configurations. In this case an exception
     * should be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSourceMultiple() {
        setUpSourceTest();
        conf1.addProperty(TEST_PROPERTY, Boolean.TRUE);
        cc.addProperty(TEST_PROPERTY, "a value");
        cc.getSource(TEST_PROPERTY);
    }

    /**
     * Tests the getSource() method for a null key. This should cause an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSourceNull() {
        cc.getSource(null);
    }

    /**
     * Tests the getSource() method if the property is defined in a single child configuration.
     */

    /**
     * Tests the getSource() method for an unknown property key.
     */

    /**
     * Tests querying a string array when a tricky interpolation is involved.
     */

    /**
     * Tests retrieving subsets of configurations
     */

    /**
     * Tests whether interpolation works if a variable references a property with multiple values. This test is related to
     * CONFIGURATION-632.
     */

    /**
     * Tests whether interpolation works if multiple configurations are involved. This test is related to CONFIGURATION-441.
     */

    /**
     * Tests {@code List} parsing.
     */

    /**
     * Tests whether global interpolation works with lists.
     */

    /**
     * Tests {@code List} parsing.
     */

    /**
     * Tests whether removing a child configuration is synchronized.
     */
    @Test
    public void testRemoveConfigurationSynchronized() {
        final SynchronizerTestImpl sync = installSynchronizer();
        cc.removeConfiguration(conf1);
        sync.verify(Methods.BEGIN_WRITE, Methods.END_WRITE);
    }

    /**
     * Tests whether the in-memory configuration can be replaced by a new child configuration.
     */

    /**
     * Tests changing the list delimiter handler.
     */
    @Test
    public void testSetListDelimiter() {
        cc.setListDelimiterHandler(new DefaultListDelimiterHandler('/'));
        checkSetListDelimiterHandler();
    }

    /**
     * Tests whether the correct list delimiter handler is set after a clear operation.
     */
    @Test
    public void testSetListDelimiterAfterClear() {
        cc.setListDelimiterHandler(new DefaultListDelimiterHandler('/'));
        cc.clear();
        checkSetListDelimiterHandler();
    }

    /**
     * Tests the behavior of setListDelimiterHandler() if the in-memory configuration is not derived from BaseConfiguration.
     * This test is related to CONFIGURATION-476.
     */
    @Test
    public void testSetListDelimiterInMemoryConfigNonBaseConfig() {
        final Configuration inMemoryConfig = EasyMock.createMock(Configuration.class);
        EasyMock.replay(inMemoryConfig);
        cc = new CompositeConfiguration(inMemoryConfig);
        cc.setListDelimiterHandler(new DefaultListDelimiterHandler(';'));
    }

    /**
     * Tests setting values. These are set in memory mode only!
     */

    /**
     * Tests {@code String} array parsing.
     */

    /**
     * Tests subsets and still can resolve elements
     */

    /**
     * Tests whether a configuration can act as both regular child configuration and in-memory configuration. This test is
     * related to CONFIGURATION-471.
     */

    @Test
    public void testAddFirstRemoveConfigurations_1_oe() throws Exception {
        cc.addConfigurationFirst(conf1);
        assertEquals("Number of configurations", 2, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddFirstRemoveConfigurations_2_oe() throws Exception {
        cc.addConfigurationFirst(conf1);
        // removed other assertion
        cc.addConfigurationFirst(conf1);
        assertEquals("Number of configurations", 2, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddFirstRemoveConfigurations_3_oe() throws Exception {
        cc.addConfigurationFirst(conf1);
        // removed other assertion
        cc.addConfigurationFirst(conf1);
        // removed other assertion
        cc.addConfigurationFirst(conf2);
        assertEquals("Number of configurations", 3, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddFirstRemoveConfigurations_4_oe() throws Exception {
        cc.addConfigurationFirst(conf1);
        // removed other assertion
        cc.addConfigurationFirst(conf1);
        // removed other assertion
        cc.addConfigurationFirst(conf2);
        // removed other assertion
        cc.removeConfiguration(conf1);
        assertEquals("Number of configurations", 2, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddFirstRemoveConfigurations_5_oe() throws Exception {
        cc.addConfigurationFirst(conf1);
        // removed other assertion
        cc.addConfigurationFirst(conf1);
        // removed other assertion
        cc.addConfigurationFirst(conf2);
        // removed other assertion
        cc.removeConfiguration(conf1);
        // removed other assertion
        cc.clear();
        assertEquals("Number of configurations", 1, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddingProperty_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        String[] values = cc.getStringArray("test.short");

        assertEquals("Number of values before add is wrong!", 1, values.length);
    }

    @Test
    public void testAddingProperty_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        String[] values = cc.getStringArray("test.short");

        // removed other assertion
        assertEquals("First Value before add is wrong", "1", values[0]);
    }

    @Test
    public void testAddingProperty_3_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        String[] values = cc.getStringArray("test.short");

        // removed other assertion
        // removed other assertion

        cc.addProperty("test.short", "88");

        values = cc.getStringArray("test.short");

        assertEquals("Number of values is wrong!", 2, values.length);
    }

    @Test
    public void testAddingProperty_4_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        String[] values = cc.getStringArray("test.short");

        // removed other assertion
        // removed other assertion

        cc.addProperty("test.short", "88");

        values = cc.getStringArray("test.short");

        // removed other assertion
        assertEquals("First Value is wrong", "1", values[0]);
    }

    @Test
    public void testAddingProperty_5_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        String[] values = cc.getStringArray("test.short");

        // removed other assertion
        // removed other assertion

        cc.addProperty("test.short", "88");

        values = cc.getStringArray("test.short");

        // removed other assertion
        // removed other assertion
        assertEquals("Third Value is wrong", "88", values[1]);
    }

    @Test
    public void testAddRemoveConfigurations_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        assertEquals("Number of configurations", 2, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddRemoveConfigurations_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf1);
        assertEquals("Number of configurations", 2, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddRemoveConfigurations_3_oe() throws Exception {
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf2);
        assertEquals("Number of configurations", 3, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddRemoveConfigurations_4_oe() throws Exception {
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf2);
        // removed other assertion
        cc.removeConfiguration(conf1);
        assertEquals("Number of configurations", 2, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddRemoveConfigurations_5_oe() throws Exception {
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf2);
        // removed other assertion
        cc.removeConfiguration(conf1);
        // removed other assertion
        cc.clear();
        assertEquals("Number of configurations", 1, cc.getNumberOfConfigurations());
    }

    @Test
    public void testCantRemoveMemoryConfig_1_oe() throws Exception {
        cc.clear();
        assertEquals(1, cc.getNumberOfConfigurations());
    }

    @Test
    public void testCantRemoveMemoryConfig_2_oe() throws Exception {
        cc.clear();
        // removed other assertion

        final Configuration internal = cc.getConfiguration(0);
        cc.removeConfiguration(internal);

        assertEquals(1, cc.getNumberOfConfigurations());
    }

    @Test
    public void testCheckingInMemoryConfiguration_1_oe() throws Exception {
        final String TEST_KEY = "testKey";
        final Configuration defaults = new PropertiesConfiguration();
        defaults.setProperty(TEST_KEY, "testValue");
        final Configuration testConfiguration = new CompositeConfiguration(defaults);
        assertTrue(testConfiguration.containsKey(TEST_KEY));
    }

    @Test
    public void testCheckingInMemoryConfiguration_2_oe() throws Exception {
        final String TEST_KEY = "testKey";
        final Configuration defaults = new PropertiesConfiguration();
        defaults.setProperty(TEST_KEY, "testValue");
        final Configuration testConfiguration = new CompositeConfiguration(defaults);
        // removed other assertion
        assertFalse(testConfiguration.isEmpty());
    }

    @Test
    public void testCheckingInMemoryConfiguration_3_oe() throws Exception {
        final String TEST_KEY = "testKey";
        final Configuration defaults = new PropertiesConfiguration();
        defaults.setProperty(TEST_KEY, "testValue");
        final Configuration testConfiguration = new CompositeConfiguration(defaults);
        // removed other assertion
        // removed other assertion
        boolean foundTestKey = false;
        final Iterator<String> i = testConfiguration.getKeys();
        // assertTrue(i instanceof IteratorChain);
        // IteratorChain ic = (IteratorChain)i;
        // assertEquals(2,i.size());
        for (; i.hasNext();) {
            final String key = i.next();
            if (key.equals(TEST_KEY)) {
                foundTestKey = true;
            }
        }
        assertTrue(foundTestKey);
    }

    @Test
    public void testCheckingInMemoryConfiguration_4_oe() throws Exception {
        final String TEST_KEY = "testKey";
        final Configuration defaults = new PropertiesConfiguration();
        defaults.setProperty(TEST_KEY, "testValue");
        final Configuration testConfiguration = new CompositeConfiguration(defaults);
        // removed other assertion
        // removed other assertion
        boolean foundTestKey = false;
        final Iterator<String> i = testConfiguration.getKeys();
        // assertTrue(i instanceof IteratorChain);
        // IteratorChain ic = (IteratorChain)i;
        // assertEquals(2,i.size());
        for (; i.hasNext();) {
            final String key = i.next();
            if (key.equals(TEST_KEY)) {
                foundTestKey = true;
            }
        }
        // removed other assertion
        testConfiguration.clearProperty(TEST_KEY);
        assertFalse(testConfiguration.containsKey(TEST_KEY));
    }

    @Test
    public void testClearingProperty_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        cc.clearProperty("test.short");
        assertFalse("Make sure test.short is gone!", cc.containsKey("test.short"));
    }

    @Test
    public void testClone_1_oe() {
        final CompositeConfiguration cc2 = (CompositeConfiguration) cc.clone();
        assertEquals("Wrong number of contained configurations", cc.getNumberOfConfigurations(), cc2.getNumberOfConfigurations());
    }

    @Test
    public void testClone_2_oe() {
        final CompositeConfiguration cc2 = (CompositeConfiguration) cc.clone();
        // removed other assertion

        final StrictConfigurationComparator comp = new StrictConfigurationComparator();
        for (int i = 0; i < cc.getNumberOfConfigurations(); i++) {
            assertEquals("Wrong configuration class at " + i, cc.getConfiguration(i).getClass(), cc2.getConfiguration(i).getClass());
    }
    }

    @Test
    public void testClone_3_oe() {
        final CompositeConfiguration cc2 = (CompositeConfiguration) cc.clone();
        // removed other assertion

        final StrictConfigurationComparator comp = new StrictConfigurationComparator();
        for (int i = 0; i < cc.getNumberOfConfigurations(); i++) {
            // removed other assertion
            assertNotSame("Configuration was not cloned", cc.getConfiguration(i), cc2.getConfiguration(i));
    }
    }

    @Test
    public void testClone_4_oe() {
        final CompositeConfiguration cc2 = (CompositeConfiguration) cc.clone();
        // removed other assertion

        final StrictConfigurationComparator comp = new StrictConfigurationComparator();
        for (int i = 0; i < cc.getNumberOfConfigurations(); i++) {
            // removed other assertion
            // removed other assertion
            assertTrue("Configurations at " + i + " not equal", comp.compare(cc.getConfiguration(i), cc2.getConfiguration(i)));
    }
    }

    @Test
    public void testClone_5_oe() {
        final CompositeConfiguration cc2 = (CompositeConfiguration) cc.clone();
        // removed other assertion

        final StrictConfigurationComparator comp = new StrictConfigurationComparator();
        for (int i = 0; i < cc.getNumberOfConfigurations(); i++) {
            // removed other assertion
            // removed other assertion
            // removed other assertion
        }

        assertTrue("Configurations are not equal", comp.compare(cc, cc2));
    }

    @Test
    public void testCloneEventListener_1_oe() {
        cc.addEventListener(ConfigurationEvent.ANY, new EventListenerTestImpl(null));
        final CompositeConfiguration cc2 = (CompositeConfiguration) cc.clone();
        assertTrue("Listeners have been cloned", cc2.getEventListeners(ConfigurationEvent.ANY).isEmpty());
    }

    @Test
    public void testCloneInterpolation_1_oe() {
        final CompositeConfiguration cc2 = (CompositeConfiguration) cc.clone();
        assertNotSame("Interpolator was not cloned", cc.getInterpolator(), cc2.getInterpolator());
    }

    @Test
    public void testDefaultValueWhenKeyMissing_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        assertEquals("default", cc.getString("bogus", "default"));
    }

    @Test
    public void testDefaultValueWhenKeyMissing_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        // removed other assertion
        assertEquals(1.4, cc.getDouble("bogus", 1.4), 0.0);
    }

    @Test
    public void testDefaultValueWhenKeyMissing_3_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        // removed other assertion
        // removed other assertion
        assertEquals(1.4, cc.getDouble("bogus", 1.4), 0.0);
    }

    @Test
    public void testEventClearProperty_1_oe() {
        cc.addConfiguration(conf1);
        final String key = "configuration.loaded";
        assertTrue("Wrong value for property", cc.getBoolean(key));
    }

    @Test
    public void testEventClearProperty_2_oe() {
        cc.addConfiguration(conf1);
        final String key = "configuration.loaded";
        // removed other assertion
        final EventListenerTestImpl listener = new EventListenerTestImpl(cc);
        cc.addEventListener(ConfigurationEvent.ANY, listener);
        cc.clearProperty(key);
        assertFalse("Key still present", cc.containsKey(key));
    }

    @Test
    public void testGetConfigurationSynchronized_1_oe() {
        final SynchronizerTestImpl sync = installSynchronizer();
        assertEquals("Wrong result", conf1, cc.getConfiguration(0));
    }

    @Test
    public void testGetKeys2PreservesOrder_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        final List<String> orderedList = new ArrayList<>();
        for (final Iterator<String> keys = conf1.getKeys("test"); keys.hasNext();) {
            orderedList.add(keys.next());
        }
        final List<String> iteratedList = new ArrayList<>();
        for (final Iterator<String> keys = cc.getKeys("test"); keys.hasNext();) {
            iteratedList.add(keys.next());
        }
        assertEquals(orderedList.size(), iteratedList.size());
    }

    @Test
    public void testGetKeys2PreservesOrder_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        final List<String> orderedList = new ArrayList<>();
        for (final Iterator<String> keys = conf1.getKeys("test"); keys.hasNext();) {
            orderedList.add(keys.next());
        }
        final List<String> iteratedList = new ArrayList<>();
        for (final Iterator<String> keys = cc.getKeys("test"); keys.hasNext();) {
            iteratedList.add(keys.next());
        }
        // removed other assertion
        for (int i = 0; i < orderedList.size(); i++) {
            assertEquals(orderedList.get(i), iteratedList.get(i));
    }
    }

    @Test
    public void testGetKeysPreservesOrder_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        final List<String> orderedList = new ArrayList<>();
        for (final Iterator<String> keys = conf1.getKeys(); keys.hasNext();) {
            orderedList.add(keys.next());
        }
        final List<String> iteratedList = new ArrayList<>();
        for (final Iterator<String> keys = cc.getKeys(); keys.hasNext();) {
            iteratedList.add(keys.next());
        }
        assertEquals(orderedList.size(), iteratedList.size());
    }

    @Test
    public void testGetKeysPreservesOrder_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        final List<String> orderedList = new ArrayList<>();
        for (final Iterator<String> keys = conf1.getKeys(); keys.hasNext();) {
            orderedList.add(keys.next());
        }
        final List<String> iteratedList = new ArrayList<>();
        for (final Iterator<String> keys = cc.getKeys(); keys.hasNext();) {
            iteratedList.add(keys.next());
        }
        // removed other assertion
        for (int i = 0; i < orderedList.size(); i++) {
            assertEquals(orderedList.get(i), iteratedList.get(i));
    }
    }

    @Test
    public void testGetList_1_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // check the composite 'array' property
        List<Object> list = cc.getList("array");
        assertNotNull("null list", list);
    }

    @Test
    public void testGetList_2_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // check the composite 'array' property
        List<Object> list = cc.getList("array");
        // removed other assertion
        assertEquals("list size", 2, list.size());
    }

    @Test
    public void testGetList_3_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // check the composite 'array' property
        List<Object> list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        assertTrue("'value1' not found in the list", list.contains("value1"));
    }

    @Test
    public void testGetList_4_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // check the composite 'array' property
        List<Object> list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("'value2' not found in the list", list.contains("value2"));
    }

    @Test
    public void testGetList_5_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // check the composite 'array' property
        List<Object> list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // add an element to the list in the composite configuration
        cc.addProperty("array", "value5");

        // test the new list
        list = cc.getList("array");
        assertNotNull("null list", list);
    }

    @Test
    public void testGetList_6_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // check the composite 'array' property
        List<Object> list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // add an element to the list in the composite configuration
        cc.addProperty("array", "value5");

        // test the new list
        list = cc.getList("array");
        // removed other assertion
        assertEquals("list size", 3, list.size());
    }

    @Test
    public void testGetList_7_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // check the composite 'array' property
        List<Object> list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // add an element to the list in the composite configuration
        cc.addProperty("array", "value5");

        // test the new list
        list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        assertTrue("'value1' not found in the list", list.contains("value1"));
    }

    @Test
    public void testGetList_8_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // check the composite 'array' property
        List<Object> list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // add an element to the list in the composite configuration
        cc.addProperty("array", "value5");

        // test the new list
        list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("'value2' not found in the list", list.contains("value2"));
    }

    @Test
    public void testGetList_9_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // check the composite 'array' property
        List<Object> list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // add an element to the list in the composite configuration
        cc.addProperty("array", "value5");

        // test the new list
        list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("'value5' not found in the list", list.contains("value5"));
    }

    @Test
    public void testGetListWithInterpolation_1_oe() {
        prepareInterpolationTest();
        final List<Object> lst = cc.getList("bar");
        assertEquals("Wrong number of values", 1, lst.size());
    }

    @Test
    public void testGetListWithInterpolation_2_oe() {
        prepareInterpolationTest();
        final List<Object> lst = cc.getList("bar");
        // removed other assertion
        assertEquals("Wrong value in list", "override", lst.get(0));
    }

    @Test
    public void testGetNumberOfConfigurationsSynchronized_1_oe() {
        final SynchronizerTestImpl sync = installSynchronizer();
        assertEquals("Wrong number of configurations", 3, cc.getNumberOfConfigurations());
    }

    @Test
    public void testGetProperty_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);
        assertEquals("Make sure we get the property from conf1 first", "test.properties", cc.getString("propertyInOrder"));
    }

    @Test
    public void testGetProperty_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);
        // removed other assertion
        cc.clear();

        cc.addConfiguration(conf2);
        cc.addConfiguration(conf1);
        assertEquals("Make sure we get the property from conf2 first", "test2.properties", cc.getString("propertyInOrder"));
    }

    @Test
    public void testGetProperty_3_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);
        // removed other assertion
        cc.clear();

        cc.addConfiguration(conf2);
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.clear();

        cc.addConfiguration(conf1);
        cc.addConfigurationFirst(conf2);
        assertEquals("Make sure we get the property from conf2 first", "test2.properties", cc.getString("propertyInOrder"));
    }

    @Test
    public void testGetPropertyMissing_3_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);
        try {
            // removed other assertion
            // removed other assertion
        } catch (final NoSuchElementException nsee) {
            assertTrue(nsee.getMessage().contains("bogus.property"));
    }
    }

    @Test
    public void testGetPropertyMissing_4_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);
        try {
            // removed other assertion
            // removed other assertion
        } catch (final NoSuchElementException nsee) {
            // removed other assertion
        }

        assertFalse("Should be false", cc.getBoolean("test.missing.boolean", false));
    }

    @Test
    public void testGetPropertyMissing_5_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);
        try {
            // removed other assertion
            // removed other assertion
        } catch (final NoSuchElementException nsee) {
            // removed other assertion
        }

        // removed other assertion
        assertTrue("Should be true", cc.getBoolean("test.missing.boolean.true", true));
    }

    @Test
    public void testGetPropertyWIncludes_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);
        final List<Object> l = cc.getList("packages");
        assertTrue(l.contains("packagea"));
    }

    @Test
    public void testGetSourceInMemory_1_oe() {
        setUpSourceTest();
        cc.addProperty(TEST_PROPERTY, Boolean.TRUE);
        assertSame("Source not found in in-memory config", cc.getInMemoryConfiguration(), cc.getSource(TEST_PROPERTY));
    }

    @Test
    public void testGetSourceSingle_1_oe() {
        setUpSourceTest();
        conf1.addProperty(TEST_PROPERTY, Boolean.TRUE);
        assertSame("Wrong source configuration", conf1, cc.getSource(TEST_PROPERTY));
    }

    @Test
    public void testGetSourceUnknown_1_oe() {
        setUpSourceTest();
        assertNull("Wrong source for unknown key", cc.getSource(TEST_PROPERTY));
    }

    @Test
    public void testGetStringArrayWithInterpolation_1_oe() {
        prepareInterpolationTest();
        final String[] values = cc.getStringArray("bar");
        assertEquals("Wrong number of values", 1, values.length);
    }

    @Test
    public void testGetStringArrayWithInterpolation_2_oe() {
        prepareInterpolationTest();
        final String[] values = cc.getStringArray("bar");
        // removed other assertion
        assertEquals("Wrong value in array", "override", values[0]);
    }

    @Test
    public void testGetStringWithDefaults_1_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final CompositeConfiguration c = new CompositeConfiguration(defaults);
        c.setThrowExceptionOnMissing(cc.isThrowExceptionOnMissing());
        c.addProperty("string", "test string");

        assertEquals("test string", c.getString("string"));
    }

    @Test
    public void testGetStringWithDefaults_3_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final CompositeConfiguration c = new CompositeConfiguration(defaults);
        c.setThrowExceptionOnMissing(cc.isThrowExceptionOnMissing());
        c.addProperty("string", "test string");

        // removed other assertion
        try {
            c.getString("XXX");
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // ok
        } catch (final Exception e) {
            fail("Should throw NoSuchElementException exception, not " + e);
    }
    }

    @Test
    public void testGetStringWithDefaults_4_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final CompositeConfiguration c = new CompositeConfiguration(defaults);
        c.setThrowExceptionOnMissing(cc.isThrowExceptionOnMissing());
        c.addProperty("string", "test string");

        // removed other assertion
        try {
            c.getString("XXX");
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // ok
        } catch (final Exception e) {
            // removed other assertion
        }

        // test defaults
        assertEquals("test string", c.getString("string", "some default value"));
    }

    @Test
    public void testGetStringWithDefaults_5_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final CompositeConfiguration c = new CompositeConfiguration(defaults);
        c.setThrowExceptionOnMissing(cc.isThrowExceptionOnMissing());
        c.addProperty("string", "test string");

        // removed other assertion
        try {
            c.getString("XXX");
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // ok
        } catch (final Exception e) {
            // removed other assertion
        }

        // test defaults
        // removed other assertion
        assertEquals("default string", c.getString("default"));
    }

    @Test
    public void testGetStringWithDefaults_6_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final CompositeConfiguration c = new CompositeConfiguration(defaults);
        c.setThrowExceptionOnMissing(cc.isThrowExceptionOnMissing());
        c.addProperty("string", "test string");

        // removed other assertion
        try {
            c.getString("XXX");
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // ok
        } catch (final Exception e) {
            // removed other assertion
        }

        // test defaults
        // removed other assertion
        // removed other assertion
        assertEquals("default string", c.getString("default", "some default value"));
    }

    @Test
    public void testGetStringWithDefaults_7_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final CompositeConfiguration c = new CompositeConfiguration(defaults);
        c.setThrowExceptionOnMissing(cc.isThrowExceptionOnMissing());
        c.addProperty("string", "test string");

        // removed other assertion
        try {
            c.getString("XXX");
            // removed other assertion
        } catch (final NoSuchElementException e) {
            // ok
        } catch (final Exception e) {
            // removed other assertion
        }

        // test defaults
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("some default value", c.getString("XXX", "some default value"));
    }

    @Test
    public void testGettingConfiguration_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        assertEquals(PropertiesConfiguration.class, cc.getConfiguration(0).getClass());
    }

    @Test
    public void testGettingConfiguration_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        // removed other assertion
        assertEquals(XMLConfiguration.class, cc.getConfiguration(1).getClass());
    }

    @Test
    public void testGettingSubset_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        Configuration subset = cc.subset("test");
        assertNotNull(subset);
    }

    @Test
    public void testGettingSubset_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        Configuration subset = cc.subset("test");
        // removed other assertion
        assertFalse("Shouldn't be empty", subset.isEmpty());
    }

    @Test
    public void testGettingSubset_3_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        Configuration subset = cc.subset("test");
        // removed other assertion
        // removed other assertion
        assertEquals("Make sure the initial loaded configs subset overrides any later add configs subset", "1", subset.getString("short"));
    }

    @Test
    public void testGettingSubset_4_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        Configuration subset = cc.subset("test");
        // removed other assertion
        // removed other assertion
        // removed other assertion

        cc.setProperty("test.short", "43");
        subset = cc.subset("test");
        assertEquals("Make sure the initial loaded configs subset overrides any later add configs subset", "43", subset.getString("short"));
    }

    @Test
    public void testInstanciateWithCollection_1_oe() {
        final Collection<Configuration> configs = new ArrayList<>();
        configs.add(xmlConf);
        configs.add(conf1);
        configs.add(conf2);

        final CompositeConfiguration config = new CompositeConfiguration(configs);
        assertEquals("Number of configurations", 4, config.getNumberOfConfigurations());
    }

    @Test
    public void testInstanciateWithCollection_2_oe() {
        final Collection<Configuration> configs = new ArrayList<>();
        configs.add(xmlConf);
        configs.add(conf1);
        configs.add(conf2);

        final CompositeConfiguration config = new CompositeConfiguration(configs);
        // removed other assertion
        assertTrue("The in memory configuration is not empty", config.getInMemoryConfiguration().isEmpty());
    }

    @Test
    public void testInterpolationArrayReference_1_oe() {
        final Configuration props = new PropertiesConfiguration();
        final String[] values = {"a", "property", "with", "multiple", "values"};
        props.addProperty("keyMultiValues", values);
        props.addProperty("keyReference", "${keyMultiValues}");
        cc.addConfiguration(props);
        assertArrayEquals("Wrong interpolated value", values, cc.getStringArray("keyReference"));
    }

    @Test
    public void testInterpolationInMultipleConfigs_1_oe() {
        final Configuration c1 = new PropertiesConfiguration();
        c1.addProperty("property.one", "one");
        c1.addProperty("property.two", "two");
        final Configuration c2 = new PropertiesConfiguration();
        c2.addProperty("property.one.ref", "${property.one}");
        cc.addConfiguration(c1);
        cc.addConfiguration(c2);
        assertEquals("Wrong interpolated value", "one", cc.getString("property.one.ref"));
    }

    @Test
    public void testList_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        List<Object> packages = cc.getList("packages");
        // we should get 3 packages here
        assertEquals(3, packages.size());
    }

    @Test
    public void testList_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        List<Object> packages = cc.getList("packages");
        // we should get 3 packages here
        // removed other assertion

        final List<Object> defaultList = new ArrayList<>();
        defaultList.add("1");
        defaultList.add("2");

        packages = cc.getList("packages.which.dont.exist", defaultList);
        // we should get 2 packages here
        assertEquals(2, packages.size());
    }

    @Test
    public void testListInterpolation_1_oe() {
        final PropertiesConfiguration c1 = new PropertiesConfiguration();
        c1.addProperty("c1.value", "test1");
        c1.addProperty("c1.value", "${c2.value}");
        cc.addConfiguration(c1);
        final PropertiesConfiguration c2 = new PropertiesConfiguration();
        c2.addProperty("c2.value", "test2");
        cc.addConfiguration(c2);
        final List<Object> lst = cc.getList("c1.value");
        assertEquals("Wrong list size", 2, lst.size());
    }

    @Test
    public void testListInterpolation_2_oe() {
        final PropertiesConfiguration c1 = new PropertiesConfiguration();
        c1.addProperty("c1.value", "test1");
        c1.addProperty("c1.value", "${c2.value}");
        cc.addConfiguration(c1);
        final PropertiesConfiguration c2 = new PropertiesConfiguration();
        c2.addProperty("c2.value", "test2");
        cc.addConfiguration(c2);
        final List<Object> lst = cc.getList("c1.value");
        // removed other assertion
        assertEquals("Wrong first element", "test1", lst.get(0));
    }

    @Test
    public void testListInterpolation_3_oe() {
        final PropertiesConfiguration c1 = new PropertiesConfiguration();
        c1.addProperty("c1.value", "test1");
        c1.addProperty("c1.value", "${c2.value}");
        cc.addConfiguration(c1);
        final PropertiesConfiguration c2 = new PropertiesConfiguration();
        c2.addProperty("c2.value", "test2");
        cc.addConfiguration(c2);
        final List<Object> lst = cc.getList("c1.value");
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong second element", "test2", lst.get(1));
    }

    @Test
    public void testMultipleTypesOfConfigs_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        assertEquals("Make sure we get the property from conf1 first", 1, cc.getInt("test.short"));
    }

    @Test
    public void testMultipleTypesOfConfigs_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        // removed other assertion
        cc.clear();

        cc.addConfiguration(xmlConf);
        cc.addConfiguration(conf1);
        assertEquals("Make sure we get the property from xml", 8, cc.getInt("test.short"));
    }

    @Test
    public void testPropertyExistsInOnlyOneConfig_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        assertEquals("value", cc.getString("element"));
    }

    @Test
    public void testReplaceInMemoryConfig_1_oe() {
        conf1.setProperty(TEST_PROPERTY, "conf1");
        conf2.setProperty(TEST_PROPERTY, "conf2");
        cc.addConfiguration(conf1, true);
        cc.addProperty("newProperty1", "newValue1");
        cc.addConfiguration(conf2, true);
        cc.addProperty("newProperty2", "newValue2");
        assertEquals("Wrong property", "conf1", cc.getString(TEST_PROPERTY));
    }

    @Test
    public void testReplaceInMemoryConfig_2_oe() {
        conf1.setProperty(TEST_PROPERTY, "conf1");
        conf2.setProperty(TEST_PROPERTY, "conf2");
        cc.addConfiguration(conf1, true);
        cc.addProperty("newProperty1", "newValue1");
        cc.addConfiguration(conf2, true);
        cc.addProperty("newProperty2", "newValue2");
        // removed other assertion
        assertEquals("Not added to in-memory config", "newValue1", conf1.getString("newProperty1"));
    }

    @Test
    public void testReplaceInMemoryConfig_3_oe() {
        conf1.setProperty(TEST_PROPERTY, "conf1");
        conf2.setProperty(TEST_PROPERTY, "conf2");
        cc.addConfiguration(conf1, true);
        cc.addProperty("newProperty1", "newValue1");
        cc.addConfiguration(conf2, true);
        cc.addProperty("newProperty2", "newValue2");
        // removed other assertion
        // removed other assertion
        assertEquals("In-memory config not changed", "newValue2", conf2.getString("newProperty2"));
    }

    @Test
    public void testSettingMissingProperty_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);
        cc.setProperty("my.new.property", "supernew");
        assertEquals("supernew", cc.getString("my.new.property"));
    }

    @Test
    public void testStringArray_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        String[] packages = cc.getStringArray("packages");
        // we should get 3 packages here
        assertEquals(3, packages.length);
    }

    @Test
    public void testStringArray_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(xmlConf);

        String[] packages = cc.getStringArray("packages");
        // we should get 3 packages here
        // removed other assertion

        packages = cc.getStringArray("packages.which.dont.exist");
        // we should get 0 packages here
        assertEquals(0, packages.length);
    }

    @Test
    public void testStringArrayInterpolation_1_oe() {
        final CompositeConfiguration config = new CompositeConfiguration();
        config.addProperty("base", "foo");
        config.addProperty("list", "${base}.bar1");
        config.addProperty("list", "${base}.bar2");
        config.addProperty("list", "${base}.bar3");

        final String[] array = config.getStringArray("list");
        assertEquals("size", 3, array.length);
    }

    @Test
    public void testStringArrayInterpolation_2_oe() {
        final CompositeConfiguration config = new CompositeConfiguration();
        config.addProperty("base", "foo");
        config.addProperty("list", "${base}.bar1");
        config.addProperty("list", "${base}.bar2");
        config.addProperty("list", "${base}.bar3");

        final String[] array = config.getStringArray("list");
        // removed other assertion
        assertEquals("1st element", "foo.bar1", array[0]);
    }

    @Test
    public void testStringArrayInterpolation_3_oe() {
        final CompositeConfiguration config = new CompositeConfiguration();
        config.addProperty("base", "foo");
        config.addProperty("list", "${base}.bar1");
        config.addProperty("list", "${base}.bar2");
        config.addProperty("list", "${base}.bar3");

        final String[] array = config.getStringArray("list");
        // removed other assertion
        // removed other assertion
        assertEquals("2nd element", "foo.bar2", array[1]);
    }

    @Test
    public void testStringArrayInterpolation_4_oe() {
        final CompositeConfiguration config = new CompositeConfiguration();
        config.addProperty("base", "foo");
        config.addProperty("list", "${base}.bar1");
        config.addProperty("list", "${base}.bar2");
        config.addProperty("list", "${base}.bar3");

        final String[] array = config.getStringArray("list");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("3rd element", "foo.bar3", array[2]);
    }

    @Test
    public void testSubsetCanResolve_1_oe() throws Exception {
        cc = new CompositeConfiguration();
        final BaseConfiguration config = new BaseConfiguration();
        config.addProperty("subset.tempfile", "${java.io.tmpdir}/file.tmp");
        cc.addConfiguration(config);
        cc.addConfiguration(ConfigurationConverter.getConfiguration(System.getProperties()));

        final Configuration subset = cc.subset("subset");
        assertEquals(System.getProperty("java.io.tmpdir") + "/file.tmp", subset.getString("tempfile"));
    }

    @Test
    public void testThrowExceptionOnMissing_1_oe() {
        assertTrue("Throw Exception Property is not set!", cc.isThrowExceptionOnMissing());
    }

    @Test
    public void testUseChildConfigAsInMemoryConfig_1_oe() {
        conf1.setProperty(TEST_PROPERTY, "conf1");
        conf2.setProperty(TEST_PROPERTY, "conf2");
        cc.addConfiguration(conf1, true);
        cc.addConfiguration(conf2);
        assertEquals("Wrong number of configurations", 2, cc.getNumberOfConfigurations());
    }

    @Test
    public void testUseChildConfigAsInMemoryConfig_2_oe() {
        conf1.setProperty(TEST_PROPERTY, "conf1");
        conf2.setProperty(TEST_PROPERTY, "conf2");
        cc.addConfiguration(conf1, true);
        cc.addConfiguration(conf2);
        // removed other assertion
        assertEquals("Wrong property", "conf1", cc.getString(TEST_PROPERTY));
    }

    @Test
    public void testUseChildConfigAsInMemoryConfig_3_oe() {
        conf1.setProperty(TEST_PROPERTY, "conf1");
        conf2.setProperty(TEST_PROPERTY, "conf2");
        cc.addConfiguration(conf1, true);
        cc.addConfiguration(conf2);
        // removed other assertion
        // removed other assertion
        cc.addProperty("newProperty", "newValue");
        assertEquals("Not added to in-memory config", "newValue", conf1.getString("newProperty"));
    }

}
