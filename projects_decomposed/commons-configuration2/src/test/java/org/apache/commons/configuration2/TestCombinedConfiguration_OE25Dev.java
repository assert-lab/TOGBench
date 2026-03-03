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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.configuration2.SynchronizerTestImpl.Methods;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.event.ConfigurationEvent;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.ex.ConfigurationRuntimeException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.configuration2.sync.LockMode;
import org.apache.commons.configuration2.sync.ReadWriteSynchronizer;
import org.apache.commons.configuration2.sync.Synchronizer;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.configuration2.tree.NodeCombiner;
import org.apache.commons.configuration2.tree.NodeModel;
import org.apache.commons.configuration2.tree.OverrideCombiner;
import org.apache.commons.configuration2.tree.UnionCombiner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Test class for CombinedConfiguration.
 *
 */
public class TestCombinedConfiguration_OE25Dev {
    /**
     * Test event listener class for checking if the expected invalidate events are fired.
     */
    private static class CombinedListener implements EventListener<ConfigurationEvent> {
        int invalidateEvents;

        int otherEvents;

        /**
         * Checks if the expected number of events was fired.
         *
         * @param expectedInvalidate the expected number of invalidate events
         * @param expectedOthers the expected number of other events
         */
        public void checkEvent(final int expectedInvalidate, final int expectedOthers) {
            assertEquals("Wrong number of invalidate events", expectedInvalidate, invalidateEvents);
            assertEquals("Wrong number of other events", expectedOthers, otherEvents);
        }

        @Override
        public void onEvent(final ConfigurationEvent event) {
            if (event.getEventType() == CombinedConfiguration.COMBINED_INVALIDATE) {
                invalidateEvents++;
            } else {
                otherEvents++;
            }
        }
    }

    /**
     * A test thread performing reads on a combined configuration. This thread reads a certain property from the
     * configuration. If everything works well, this property should have at least one and at most two values.
     */
    private static class ReadThread extends Thread {
        /** The configuration to be accessed. */
        private final Configuration config;

        /** The latch for synchronizing thread start. */
        private final CountDownLatch startLatch;

        /** A counter for read errors. */
        private final AtomicInteger errorCount;

        /** The number of reads to be performed. */
        private final int numberOfReads;

        /**
         * Creates a new instance of {@code ReadThread}.
         *
         * @param readConfig the configuration to be read
         * @param latch the latch for synchronizing thread start
         * @param errCnt the counter for read errors
         * @param readCount the number of reads to be performed
         */
        public ReadThread(final Configuration readConfig, final CountDownLatch latch, final AtomicInteger errCnt, final int readCount) {
            config = readConfig;
            startLatch = latch;
            errorCount = errCnt;
            numberOfReads = readCount;
        }

        /**
         * Reads the test property from the associated configuration. Its values are checked.
         */
        private void readConfiguration() {
            final List<Object> values = config.getList(KEY_CONCURRENT);
            if (values.size() < 1 || values.size() > 2) {
                errorCount.incrementAndGet();
            } else {
                boolean ok = true;
                for (final Object value : values) {
                    if (!TEST_NAME.equals(value)) {
                        ok = false;
                    }
                }
                if (!ok) {
                    errorCount.incrementAndGet();
                }
            }
        }

        /**
         * Reads from the test configuration.
         */
        @Override
        public void run() {
            try {
                startLatch.await();
                for (int i = 0; i < numberOfReads; i++) {
                    readConfiguration();
                }
            } catch (final Exception e) {
                errorCount.incrementAndGet();
            }
        }
    }

    /**
     * A test thread performing updates on a test configuration. This thread modifies configurations which are children of a
     * combined configuration. Each update operation adds a value to one of the child configurations and removes it from
     * another one (which contained it before). So if concurrent reads are performed, the test property should always have
     * between 1 and 2 values.
     */
    private static class WriteThread extends Thread {
        /** The list with the child configurations. */
        private final List<Configuration> testConfigs;

        /** The latch for synchronizing thread start. */
        private final CountDownLatch startLatch;

        /** A counter for errors. */
        private final AtomicInteger errorCount;

        /** The number of write operations to be performed. */
        private final int numberOfWrites;

        /** The index of the child configuration containing the test property. */
        private int currentChildConfigIdx;

        /**
         * Creates a new instance of {@code WriteThread}.
         *
         * @param cc the test combined configuration
         * @param latch the latch for synchronizing test start
         * @param errCnt a counter for errors
         * @param writeCount the number of writes to be performed
         */
        public WriteThread(final CombinedConfiguration cc, final CountDownLatch latch, final AtomicInteger errCnt, final int writeCount) {
            testConfigs = cc.getConfigurations();
            startLatch = latch;
            errorCount = errCnt;
            numberOfWrites = writeCount;
        }

        @Override
        public void run() {
            try {
                startLatch.await();
                for (int i = 0; i < numberOfWrites; i++) {
                    updateConfigurations();
                }
            } catch (final InterruptedException e) {
                errorCount.incrementAndGet();
            }
        }

        /**
         * Performs the update operation.
         */
        private void updateConfigurations() {
            final int newIdx = (currentChildConfigIdx + 1) % testConfigs.size();
            testConfigs.get(newIdx).addProperty(KEY_CONCURRENT, TEST_NAME);
            testConfigs.get(currentChildConfigIdx).clearProperty(KEY_CONCURRENT);
            currentChildConfigIdx = newIdx;
        }
    }

    /** Constant for the name of a sub configuration. */
    private static final String TEST_NAME = "SUBCONFIG";

    /** Constant for a test key. */
    private static final String TEST_KEY = "test.value";

    /** Constant for a key to be used for a concurrent test. */
    private static final String KEY_CONCURRENT = "concurrent.access.test";

    /** Constant for the name of the first child configuration. */
    private static final String CHILD1 = TEST_NAME + "1";

    /** Constant for the name of the second child configuration. */
    private static final String CHILD2 = TEST_NAME + "2";

    /** Constant for the key for a sub configuration. */
    private static final String SUB_KEY = "test.sub.config";

    /**
     * Helper method for creating a test configuration to be added to the combined configuration.
     *
     * @return the test configuration
     */
    private static AbstractConfiguration setUpTestConfiguration() {
        final BaseHierarchicalConfiguration config = new BaseHierarchicalConfiguration();
        config.addProperty(TEST_KEY, Boolean.TRUE);
        config.addProperty("test.comment", "This is a test");
        return config;
    }

    /** Helper object for managing temporary files. */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /** The configuration to be tested. */
    private CombinedConfiguration config;

    /** The test event listener. */
    private CombinedListener listener;

    /**
     * Checks if a configuration was correctly added to the combined config.
     *
     * @param c the config to check
     */
    private void checkAddConfig(final AbstractConfiguration c) {
        final Collection<EventListener<? super ConfigurationEvent>> listeners = c.getEventListeners(ConfigurationEvent.ANY);
        assertEquals("Wrong number of configuration listeners", 1, listeners.size());
        assertTrue("Combined config is no listener", listeners.contains(config));
    }

    /**
     * Helper method for testing that the combined root node has not yet been constructed.
     */
    private void checkCombinedRootNotConstructed() {
        assertTrue("Root node was constructed", config.getModel().getNodeHandler().getRootNode().getChildren().isEmpty());
    }

    /**
     * Checks the configurationsAt() method.
     *
     * @param withUpdates flag whether updates are supported
     */
    private void checkConfigurationsAt(final boolean withUpdates) {
        setUpSubConfigTest();
        final List<HierarchicalConfiguration<ImmutableNode>> subs = config.configurationsAt(SUB_KEY, withUpdates);
        assertEquals("Wrong number of sub configurations", 1, subs.size());
        assertTrue("Wrong value in sub configuration", subs.get(0).getBoolean(TEST_KEY));
    }

    /**
     * Tests whether a configuration was completely removed.
     *
     * @param c the removed configuration
     */
    private void checkRemoveConfig(final AbstractConfiguration c) {
        assertTrue("Listener was not removed", c.getEventListeners(ConfigurationEvent.ANY).isEmpty());
        assertEquals("Wrong number of contained configs", 0, config.getNumberOfConfigurations());
        assertTrue("Name was not removed", config.getConfigurationNames().isEmpty());
        listener.checkEvent(2, 0);
    }

    @Before
    public void setUp() throws Exception {
        config = new CombinedConfiguration();
        listener = new CombinedListener();
        config.addEventListener(ConfigurationEvent.ANY, listener);
    }

    /**
     * Prepares a test of the getSource() method.
     */
    private void setUpSourceTest() {
        final BaseHierarchicalConfiguration c1 = new BaseHierarchicalConfiguration();
        final PropertiesConfiguration c2 = new PropertiesConfiguration();
        c1.addProperty(TEST_KEY, TEST_NAME);
        c2.addProperty("another.key", "test");
        config.addConfiguration(c1, CHILD1);
        config.addConfiguration(c2, CHILD2);
    }

    /**
     * Prepares the test configuration for a test for sub configurations. Some child configurations are added.
     *
     * @return the sub configuration at the test sub key
     */
    private AbstractConfiguration setUpSubConfigTest() {
        final AbstractConfiguration srcConfig = setUpTestConfiguration();
        config.addConfiguration(srcConfig, "source", SUB_KEY);
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), "otherTest", "other.prefix");
        return srcConfig;
    }

    /**
     * Prepares a test for synchronization. This method installs a test synchronizer and adds some test configurations.
     *
     * @return the test synchronizer
     */
    private SynchronizerTestImpl setUpSynchronizerTest() {
        setUpSourceTest();
        final SynchronizerTestImpl sync = new SynchronizerTestImpl();
        config.setSynchronizer(sync);
        return sync;
    }

    /**
     * Tests accessing properties if no configurations have been added.
     */

    /**
     * Tests accessing properties if multiple configurations have been added.
     */

    /**
     * Tests adding a configuration (without further information).
     */

    /**
     * Tests adding a configuration and specifying an at position.
     */

    /**
     * Tests adding a configuration with a complex at position. Here the at path contains a dot, which must be escaped.
     */

    /**
     * Tests whether adding a new configuration is synchronized.
     */
    @Test
    public void testAddConfigurationSynchronized() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        config.addConfiguration(new BaseHierarchicalConfiguration());
        sync.verify(Methods.BEGIN_WRITE, Methods.END_WRITE);
        checkCombinedRootNotConstructed();
    }

    /**
     * Tests adding a configuration with a name.
     */

    /**
     * Tests adding a configuration with a name when this name already exists. This should cause an exception.
     */
    @Test(expected = ConfigurationRuntimeException.class)
    public void testAddConfigurationWithNameTwice() {
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME);
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "prefix");
    }

    /**
     * Tests adding a null configuration. This should cause an exception to be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullConfiguration() {
        config.addConfiguration(null);
    }

    /**
     * Tests clearing a combined configuration. This should remove all contained configurations.
     */

    /**
     * Tests whether the combined configuration removes itself as change listener from the child configurations on a clear
     * operation. This test is related to CONFIGURATION-572.
     */

    /**
     * Tests cloning a combined configuration.
     */

    /**
     * Tests if the cloned configuration is decoupled from the original.
     */

    /**
     * Tests whether cloning of a configuration is correctly synchronized.
     */
    @Test
    public void testCloneSynchronized() {
        setUpSourceTest();
        config.lock(LockMode.READ); // Causes the root node to be constructed
        config.unlock(LockMode.READ);
        final SynchronizerTestImpl sync = new SynchronizerTestImpl();
        config.setSynchronizer(sync);
        config.clone();
        // clone() of base class is wrapped by another read lock
        sync.verifyStart(Methods.BEGIN_READ, Methods.BEGIN_READ);
        sync.verifyEnd(Methods.END_READ, Methods.END_READ);
    }

    /**
     * Tests whether a combined configuration can be copied to an XML configuration. This test is related to
     * CONFIGURATION-445.
     */

    /**
     * Tests concurrent read and write access on a combined configuration. There are multiple reader threads and a single
     * writer thread. It is checked that no inconsistencies occur.
     */

    /**
     * Tests whether sub configurations can be created from a key.
     */
    @Test
    public void testConfigurationsAt() {
        checkConfigurationsAt(false);
    }

    /**
     * Tests whether sub configurations can be created which are attached.
     */
    @Test
    public void testConfigurationsAtWithUpdates() {
        checkConfigurationsAt(true);
    }

    /**
     * Tests using a conversion expression engine for child configurations with strange keys. This test is related to
     * CONFIGURATION-336.
     */

    /**
     * Tests whether escaped list delimiters are treated correctly.
     */

    /**
     * Tests whether access to a configuration by index is correctly synchronized.
     */

    /**
     * Tests whether access to a configuration by name is correctly synchronized.
     */

    /**
     * Tests whether querying the name list of child configurations is synchronized.
     */

    /**
     * Tests whether querying the name set of child configurations is synchronized.
     */

    /**
     * Tests whether querying the list of child configurations is synchronized.
     */

    /**
     * Tests whether read access to the conversion expression engine is synchronized.
     */

    /**
     * Tests whether getNodeCombiner() is correctly synchronized.
     */

    /**
     * Tests whether querying the number of child configurations is synchronized.
     */

    /**
     * Tests the getSource() method when the passed in key belongs to the combined configuration itself.
     */

    /**
     * Tests the gestSource() method when the source property is defined in a hierarchical configuration.
     */

    /**
     * Tests the getSource() method when the passed in key refers to multiple values, which are all defined in the same
     * source configuration.
     */

    /**
     * Tests the getSource() method when the passed in key refers to multiple values defined by different sources. This
     * should cause an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSourceMultiSources() {
        setUpSourceTest();
        final String key = "list.key";
        config.getConfiguration(CHILD1).addProperty(key, "1,2,3");
        config.getConfiguration(CHILD2).addProperty(key, "a,b,c");
        config.getSource(key);
    }

    /**
     * Tests whether the source configuration can be detected for non hierarchical configurations.
     */

    /**
     * Tests the getSource() method when a null key is passed in. This should cause an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetSourceNull() {
        config.getSource(null);
    }

    /**
     * Tests whether multiple sources of a key can be retrieved.
     */

    /**
     * Tests getSources() for a non existing key.
     */

    /**
     * Tests whether getSource() is correctly synchronized.
     */

    /**
     * Tests the getSource() method when the passed in key is not contained. Result should be null in this case.
     */

    /**
     * Tests getSource() if a child configuration is again a combined configuration.
     */

    /**
     * Tests accessing a newly created combined configuration.
     */

    /**
     * Tests whether only a single invalidate event is fired for a change. This test is related to CONFIGURATION-315.
     */

    /**
     * Tests whether invalidate() performs correct synchronization.
     */
    @Test
    public void testInvalidateSynchronized() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        config.invalidate();
        sync.verify(Methods.BEGIN_WRITE, Methods.END_WRITE);
    }

    /**
     * Tests whether requested locks are freed correctly if an exception occurs while constructing the root node.
     */

    /**
     * Tests removing a configuration.
     */

    /**
     * Tests removing a configuration by index.
     */

    /**
     * Tests removing a configuration by name.
     */

    /**
     * Tests removing a configuration by name, which is not contained.
     */

    /**
     * Tests removing a configuration with a name.
     */
    @Test
    public void testRemoveNamedConfiguration() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, TEST_NAME);
        config.removeConfiguration(c);
        checkRemoveConfig(c);
    }

    /**
     * Tests removing a named configuration by index.
     */

    /**
     * Tests removing a configuration that was not added prior.
     */

    /**
     * Tests whether write access to the conversion expression engine is synchronized.
     */
    @Test
    public void testSetConversionExpressionEngineSynchronized() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        config.setConversionExpressionEngine(new DefaultExpressionEngine(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS));
        sync.verify(Methods.BEGIN_WRITE, Methods.END_WRITE);
        checkCombinedRootNotConstructed();
    }

    /**
     * Tests if setting a node combiner causes an invalidation.
     */

    /**
     * Tests whether setNodeCombiner() is correctly synchronized.
     */
    @Test
    public void testSetNodeCombinerSynchronized() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        config.setNodeCombiner(new UnionCombiner());
        sync.verify(Methods.BEGIN_WRITE, Methods.END_WRITE);
        checkCombinedRootNotConstructed();
    }

    /**
     * Tests setting a null node combiner. This should cause an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNullNodeCombiner() {
        config.setNodeCombiner(null);
    }

    /**
     * Tests whether a sub configuration survives updates of its parent.
     */

    /**
     * Tests if an update of a contained configuration leeds to an invalidation of the combined configuration.
     */

    @Test
    public void testAccessPropertyEmpty_1_oe() {
        assertFalse("Found a key", config.containsKey(TEST_KEY));
    }

    @Test
    public void testAccessPropertyEmpty_2_oe() {
        assertNull("Key has a value", config.getString("test.comment"));
    }

    @Test
    public void testAccessPropertyEmpty_3_oe() {
        assertTrue("Config is not empty", config.isEmpty());
    }

    @Test
    public void testAccessPropertyMulti_1_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), null, "prefix1");
        config.addConfiguration(setUpTestConfiguration(), null, "prefix2");
        assertTrue("Prop1 not found", config.getBoolean(TEST_KEY));
    }

    @Test
    public void testAccessPropertyMulti_2_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), null, "prefix1");
        config.addConfiguration(setUpTestConfiguration(), null, "prefix2");
        assertTrue("Prop 2 not found", config.getBoolean("prefix1." + TEST_KEY));
    }

    @Test
    public void testAccessPropertyMulti_3_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), null, "prefix1");
        config.addConfiguration(setUpTestConfiguration(), null, "prefix2");
        assertTrue("Prop 3 not found", config.getBoolean("prefix2." + TEST_KEY));
    }

    @Test
    public void testAccessPropertyMulti_4_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), null, "prefix1");
        config.addConfiguration(setUpTestConfiguration(), null, "prefix2");
        assertFalse("Configuration is empty", config.isEmpty());
    }

    @Test
    public void testAddConfiguration_1_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c);
        checkAddConfig(c);
        assertEquals("Wrong number of configs", 1, config.getNumberOfConfigurations());
    }

    @Test
    public void testAddConfiguration_2_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c);
        checkAddConfig(c);
        assertTrue("Name list is not empty", config.getConfigurationNames().isEmpty());
    }

    @Test
    public void testAddConfiguration_3_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c);
        checkAddConfig(c);
        assertSame("Added config not found", c, config.getConfiguration(0));
    }

    @Test
    public void testAddConfiguration_4_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c);
        checkAddConfig(c);
        assertTrue("Wrong property value", config.getBoolean(TEST_KEY));
    }

    @Test
    public void testAddConfigurationAt_1_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, null, "my");
        checkAddConfig(c);
        assertTrue("Wrong property value", config.getBoolean("my." + TEST_KEY));
    }

    @Test
    public void testAddConfigurationComplexAt_1_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, null, "This..is.a.complex");
        checkAddConfig(c);
        assertTrue("Wrong property value", config.getBoolean("This..is.a.complex." + TEST_KEY));
    }

    @Test
    public void testAddConfigurationWithName_1_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, TEST_NAME);
        checkAddConfig(c);
        assertEquals("Wrong number of configs", 1, config.getNumberOfConfigurations());
    }

    @Test
    public void testAddConfigurationWithName_2_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, TEST_NAME);
        checkAddConfig(c);
        assertSame("Added config not found", c, config.getConfiguration(0));
    }

    @Test
    public void testAddConfigurationWithName_3_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, TEST_NAME);
        checkAddConfig(c);
        assertSame("Added config not found by name", c, config.getConfiguration(TEST_NAME));
    }

    @Test
    public void testAddConfigurationWithName_4_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, TEST_NAME);
        checkAddConfig(c);
        final Set<String> names = config.getConfigurationNames();
        assertEquals("Wrong number of config names", 1, names.size());
    }

    @Test
    public void testAddConfigurationWithName_5_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, TEST_NAME);
        checkAddConfig(c);
        final Set<String> names = config.getConfigurationNames();
        assertTrue("Name not found", names.contains(TEST_NAME));
    }

    @Test
    public void testAddConfigurationWithName_6_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, TEST_NAME);
        checkAddConfig(c);
        final Set<String> names = config.getConfigurationNames();
        assertTrue("Wrong property value", config.getBoolean(TEST_KEY));
    }

    @Test
    public void testClear_1_oe() {
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "test");
        config.addConfiguration(setUpTestConfiguration());

        config.clear();
        assertEquals("Still configs contained", 0, config.getNumberOfConfigurations());
    }

    @Test
    public void testClear_2_oe() {
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "test");
        config.addConfiguration(setUpTestConfiguration());

        config.clear();
        assertTrue("Still names contained", config.getConfigurationNames().isEmpty());
    }

    @Test
    public void testClear_3_oe() {
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "test");
        config.addConfiguration(setUpTestConfiguration());

        config.clear();
        assertTrue("Config is not empty", config.isEmpty());
    }

    @Test
    public void testClearRemoveChildListener_1_oe() {
        final AbstractConfiguration child = setUpTestConfiguration();
        config.addConfiguration(child);

        config.clear();
        for (final EventListener<?> listener : child.getEventListeners(ConfigurationEvent.ANY)) {
            assertNotEquals("Still registered", config, listener);
    }
    }

    @Test
    public void testClone_1_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        config.addConfiguration(new PropertiesConfiguration(), "props");

        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();
        assertNotNull("No root node", cc2.getModel().getNodeHandler().getRootNode());
    }

    @Test
    public void testClone_2_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        config.addConfiguration(new PropertiesConfiguration(), "props");

        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();
        assertEquals("Wrong number of contained configurations", config.getNumberOfConfigurations(), cc2.getNumberOfConfigurations());
    }

    @Test
    public void testClone_3_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        config.addConfiguration(new PropertiesConfiguration(), "props");

        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();
        assertSame("Wrong node combiner", config.getNodeCombiner(), cc2.getNodeCombiner());
    }

    @Test
    public void testClone_4_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        config.addConfiguration(new PropertiesConfiguration(), "props");

        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();
        assertEquals("Wrong number of names", config.getConfigurationNames().size(), cc2.getConfigurationNames().size());
    }

    @Test
    public void testClone_5_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        config.addConfiguration(new PropertiesConfiguration(), "props");

        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();
        assertTrue("Found duplicate event listeners",Collections.disjoint(cc2.getEventListeners(ConfigurationEvent.ANY),config.getEventListeners(ConfigurationEvent.ANY)));
    }

    @Test
    public void testClone_6_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        config.addConfiguration(new PropertiesConfiguration(), "props");

        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();

        final StrictConfigurationComparator comp = new StrictConfigurationComparator();
        for (int i = 0; i < config.getNumberOfConfigurations(); i++) {
            assertNotSame("Configuration at " + i + " was not cloned", config.getConfiguration(i), cc2.getConfiguration(i));
    }
    }

    @Test
    public void testClone_7_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        config.addConfiguration(new PropertiesConfiguration(), "props");

        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();

        final StrictConfigurationComparator comp = new StrictConfigurationComparator();
        for (int i = 0; i < config.getNumberOfConfigurations(); i++) {
            assertEquals("Wrong config class at " + i, config.getConfiguration(i).getClass(), cc2.getConfiguration(i).getClass());
    }
    }

    @Test
    public void testClone_8_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        config.addConfiguration(new PropertiesConfiguration(), "props");

        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();

        final StrictConfigurationComparator comp = new StrictConfigurationComparator();
        for (int i = 0; i < config.getNumberOfConfigurations(); i++) {
            assertTrue("Configs not equal at " + i, comp.compare(config.getConfiguration(i), cc2.getConfiguration(i)));
    }
    }

    @Test
    public void testClone_9_oe() {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        config.addConfiguration(new PropertiesConfiguration(), "props");

        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();

        final StrictConfigurationComparator comp = new StrictConfigurationComparator();
        for (int i = 0; i < config.getNumberOfConfigurations(); i++) {
        }

        assertTrue("Combined configs not equal", comp.compare(config, cc2));
    }

    @Test
    public void testCloneModify_1_oe() {
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME);
        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();
        assertTrue("Name is missing", cc2.getConfigurationNames().contains(TEST_NAME));
    }

    @Test
    public void testCloneModify_2_oe() {
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME);
        final CombinedConfiguration cc2 = (CombinedConfiguration) config.clone();
        cc2.removeConfiguration(TEST_NAME);
        assertFalse("Names in original changed", config.getConfigurationNames().isEmpty());
    }

    @Test
    public void testCombinedCopyToXML_1_oe() throws ConfigurationException {
        final XMLConfiguration x1 = new XMLConfiguration();
        x1.addProperty("key1", "value1");
        x1.addProperty("key1[@override]", "USER1");
        x1.addProperty("key2", "value2");
        x1.addProperty("key2[@override]", "USER2");
        final XMLConfiguration x2 = new XMLConfiguration();
        x2.addProperty("key2", "value2.2");
        x2.addProperty("key2[@override]", "USER2");
        config.setNodeCombiner(new OverrideCombiner());
        config.addConfiguration(x2);
        config.addConfiguration(x1);
        XMLConfiguration x3 = new XMLConfiguration(config);
        assertEquals("Wrong element value", "value2.2", x3.getString("key2"));
    }

    @Test
    public void testCombinedCopyToXML_2_oe() throws ConfigurationException {
        final XMLConfiguration x1 = new XMLConfiguration();
        x1.addProperty("key1", "value1");
        x1.addProperty("key1[@override]", "USER1");
        x1.addProperty("key2", "value2");
        x1.addProperty("key2[@override]", "USER2");
        final XMLConfiguration x2 = new XMLConfiguration();
        x2.addProperty("key2", "value2.2");
        x2.addProperty("key2[@override]", "USER2");
        config.setNodeCombiner(new OverrideCombiner());
        config.addConfiguration(x2);
        config.addConfiguration(x1);
        XMLConfiguration x3 = new XMLConfiguration(config);
        assertEquals("Wrong attribute value", "USER2", x3.getString("key2[@override]"));
    }

    @Test
    public void testCombinedCopyToXML_3_oe() throws ConfigurationException {
        final XMLConfiguration x1 = new XMLConfiguration();
        x1.addProperty("key1", "value1");
        x1.addProperty("key1[@override]", "USER1");
        x1.addProperty("key2", "value2");
        x1.addProperty("key2[@override]", "USER2");
        final XMLConfiguration x2 = new XMLConfiguration();
        x2.addProperty("key2", "value2.2");
        x2.addProperty("key2[@override]", "USER2");
        config.setNodeCombiner(new OverrideCombiner());
        config.addConfiguration(x2);
        config.addConfiguration(x1);
        XMLConfiguration x3 = new XMLConfiguration(config);
        final StringWriter w = new StringWriter();
        new FileHandler(x3).save(w);
        final String s = w.toString();
        x3 = new XMLConfiguration();
        new FileHandler(x3).load(new StringReader(s));
        assertEquals("Wrong element value after load", "value2.2", x3.getString("key2"));
    }

    @Test
    public void testCombinedCopyToXML_4_oe() throws ConfigurationException {
        final XMLConfiguration x1 = new XMLConfiguration();
        x1.addProperty("key1", "value1");
        x1.addProperty("key1[@override]", "USER1");
        x1.addProperty("key2", "value2");
        x1.addProperty("key2[@override]", "USER2");
        final XMLConfiguration x2 = new XMLConfiguration();
        x2.addProperty("key2", "value2.2");
        x2.addProperty("key2[@override]", "USER2");
        config.setNodeCombiner(new OverrideCombiner());
        config.addConfiguration(x2);
        config.addConfiguration(x1);
        XMLConfiguration x3 = new XMLConfiguration(config);
        final StringWriter w = new StringWriter();
        new FileHandler(x3).save(w);
        final String s = w.toString();
        x3 = new XMLConfiguration();
        new FileHandler(x3).load(new StringReader(s));
        assertEquals("Wrong attribute value after load", "USER2", x3.getString("key2[@override]"));
    }

    @Test
    public void testConcurrentAccess_1_oe() throws ConfigurationException, InterruptedException {
        setUpSourceTest();
        final XMLConfiguration xmlConf = new XMLConfiguration();
        new FileHandler(xmlConf).load(ConfigurationAssert.getTestFile("test.xml"));
        config.addConfiguration(xmlConf);
        final PropertiesConfiguration propConf = new PropertiesConfiguration();
        new FileHandler(propConf).load(ConfigurationAssert.getTestFile("test.properties"));
        for (int i = 0; i < 8; i++) {
            config.addConfiguration(new BaseHierarchicalConfiguration());
        }
        config.getConfiguration(0).addProperty(KEY_CONCURRENT, TEST_NAME);

        final Synchronizer sync = new ReadWriteSynchronizer();
        config.setSynchronizer(sync);
        for (final Configuration c : config.getConfigurations()) {
            c.setSynchronizer(sync);
        }

        final int numberOfReaders = 3;
        final int readCount = 5000;
        final int writeCount = 3000;
        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicInteger errorCount = new AtomicInteger();
        final Collection<Thread> threads = new ArrayList<>(numberOfReaders + 1);
        final Thread writeThread = new WriteThread(config, latch, errorCount, writeCount);
        writeThread.start();
        threads.add(writeThread);
        for (int i = 0; i < numberOfReaders; i++) {
            final Thread readThread = new ReadThread(config, latch, errorCount, readCount);
            readThread.start();
            threads.add(readThread);
        }

        latch.countDown();
        for (final Thread t : threads) {
            t.join();
        }
        assertEquals("Got errors", 0, errorCount.get());
    }

    @Test
    public void testConversionExpressionEngine_1_oe() {
        final PropertiesConfiguration child = new PropertiesConfiguration();
        child.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        child.addProperty("test(a)", "1,2,3");
        config.addConfiguration(child);
        final DefaultExpressionEngine engineQuery = new DefaultExpressionEngine(
            new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS).setIndexStart("<").setIndexEnd(">").create());
        config.setExpressionEngine(engineQuery);
        final DefaultExpressionEngine engineConvert = new DefaultExpressionEngine(
            new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS).setIndexStart("[").setIndexEnd("]").create());
        config.setConversionExpressionEngine(engineConvert);
        assertEquals("Wrong property 1", "1", config.getString("test(a)<0>"));
    }

    @Test
    public void testConversionExpressionEngine_2_oe() {
        final PropertiesConfiguration child = new PropertiesConfiguration();
        child.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        child.addProperty("test(a)", "1,2,3");
        config.addConfiguration(child);
        final DefaultExpressionEngine engineQuery = new DefaultExpressionEngine(
            new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS).setIndexStart("<").setIndexEnd(">").create());
        config.setExpressionEngine(engineQuery);
        final DefaultExpressionEngine engineConvert = new DefaultExpressionEngine(
            new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS).setIndexStart("[").setIndexEnd("]").create());
        config.setConversionExpressionEngine(engineConvert);
        assertEquals("Wrong property 2", "2", config.getString("test(a)<1>"));
    }

    @Test
    public void testConversionExpressionEngine_3_oe() {
        final PropertiesConfiguration child = new PropertiesConfiguration();
        child.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        child.addProperty("test(a)", "1,2,3");
        config.addConfiguration(child);
        final DefaultExpressionEngine engineQuery = new DefaultExpressionEngine(
            new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS).setIndexStart("<").setIndexEnd(">").create());
        config.setExpressionEngine(engineQuery);
        final DefaultExpressionEngine engineConvert = new DefaultExpressionEngine(
            new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS).setIndexStart("[").setIndexEnd("]").create());
        config.setConversionExpressionEngine(engineConvert);
        assertEquals("Wrong property 3", "3", config.getString("test(a)<2>"));
    }

    @Test
    public void testEscapeListDelimiters_1_oe() {
        final PropertiesConfiguration sub = new PropertiesConfiguration();
        sub.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        sub.addProperty("test.pi", "3\\,1415");
        config.addConfiguration(sub);
        assertEquals("Wrong value", "3,1415", config.getString("test.pi"));
    }

    @Test
    public void testGetConfigurationByIdxSynchronized_1_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        assertNotNull("No configuration", config.getConfiguration(0));
    }

    @Test
    public void testGetConfigurationByNameSynchronized_1_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        assertNotNull("No configuration", config.getConfiguration(CHILD1));
    }

    @Test
    public void testGetConfigurationNameList_1_oe() throws Exception {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        final AbstractConfiguration pc = new PropertiesConfiguration();
        config.addConfiguration(pc, "props");
        final List<String> list = config.getConfigurationNameList();
        assertNotNull("No list of configurations returned", list);
    }

    @Test
    public void testGetConfigurationNameList_2_oe() throws Exception {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        final AbstractConfiguration pc = new PropertiesConfiguration();
        config.addConfiguration(pc, "props");
        final List<String> list = config.getConfigurationNameList();
        assertEquals("Incorrect number of configurations", 3, list.size());
    }

    @Test
    public void testGetConfigurationNameList_3_oe() throws Exception {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        final AbstractConfiguration pc = new PropertiesConfiguration();
        config.addConfiguration(pc, "props");
        final List<String> list = config.getConfigurationNameList();
        final String name = list.get(1);
        assertNotNull("No name returned", name);
    }

    @Test
    public void testGetConfigurationNameList_4_oe() throws Exception {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        final AbstractConfiguration pc = new PropertiesConfiguration();
        config.addConfiguration(pc, "props");
        final List<String> list = config.getConfigurationNameList();
        final String name = list.get(1);
        assertEquals("Incorrect configuration name", TEST_NAME, name);
    }

    @Test
    public void testGetConfigurationNameListSynchronized_1_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        assertFalse("No child names", config.getConfigurationNameList().isEmpty());
    }

    @Test
    public void testGetConfigurationNamesSynchronized_1_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        assertFalse("No child names", config.getConfigurationNames().isEmpty());
    }

    @Test
    public void testGetConfigurations_1_oe() throws Exception {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        final AbstractConfiguration pc = new PropertiesConfiguration();
        config.addConfiguration(pc, "props");
        final List<Configuration> list = config.getConfigurations();
        assertNotNull("No list of configurations returned", list);
    }

    @Test
    public void testGetConfigurations_2_oe() throws Exception {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        final AbstractConfiguration pc = new PropertiesConfiguration();
        config.addConfiguration(pc, "props");
        final List<Configuration> list = config.getConfigurations();
        assertEquals("Incorrect number of configurations", 3, list.size());
    }

    @Test
    public void testGetConfigurations_3_oe() throws Exception {
        config.addConfiguration(setUpTestConfiguration());
        config.addConfiguration(setUpTestConfiguration(), TEST_NAME, "conf2");
        final AbstractConfiguration pc = new PropertiesConfiguration();
        config.addConfiguration(pc, "props");
        final List<Configuration> list = config.getConfigurations();
        final Configuration c = list.get(2);
        assertSame("Incorrect configuration", c, pc);
    }

    @Test
    public void testGetConfigurationsSynchronized_1_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        assertFalse("No child configurations", config.getConfigurations().isEmpty());
    }

    @Test
    public void testGetConversionExpressionEngineSynchronized_1_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        assertNull("Got a conversion engine", config.getConversionExpressionEngine());
    }

    @Test
    public void testGetNodeCombinerSynchronized_1_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        assertNotNull("No node combiner", config.getNodeCombiner());
    }

    @Test
    public void testGetNumberOfConfigurationsSynchronized_1_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        assertEquals("Wrong number of configurations", 2, config.getNumberOfConfigurations());
    }

    @Test
    public void testGetSourceCombined_1_oe() {
        setUpSourceTest();
        final String key = "yet.another.key";
        config.addProperty(key, Boolean.TRUE);
        assertEquals("Wrong source for key", config, config.getSource(key));
    }

    @Test
    public void testGetSourceHierarchical_1_oe() {
        setUpSourceTest();
        assertEquals("Wrong source configuration", config.getConfiguration(CHILD1), config.getSource(TEST_KEY));
    }

    @Test
    public void testGetSourceMulti_1_oe() {
        setUpSourceTest();
        final String key = "list.key";
        config.getConfiguration(CHILD1).addProperty(key, "1,2,3");
        assertEquals("Wrong source for multi-value property", config.getConfiguration(CHILD1), config.getSource(key));
    }

    @Test
    public void testGetSourceNonHierarchical_1_oe() {
        setUpSourceTest();
        assertEquals("Wrong source configuration", config.getConfiguration(CHILD2), config.getSource("another.key"));
    }

    @Test
    public void testGetSourcesMultiSources_1_oe() {
        setUpSourceTest();
        final String key = "list.key";
        config.getConfiguration(CHILD1).addProperty(key, "1,2,3");
        config.getConfiguration(CHILD2).addProperty(key, "a,b,c");
        final Set<Configuration> sources = config.getSources(key);
        assertEquals("Wrong number of sources", 2, sources.size());
    }

    @Test
    public void testGetSourcesMultiSources_2_oe() {
        setUpSourceTest();
        final String key = "list.key";
        config.getConfiguration(CHILD1).addProperty(key, "1,2,3");
        config.getConfiguration(CHILD2).addProperty(key, "a,b,c");
        final Set<Configuration> sources = config.getSources(key);
        assertTrue("Source 1 not found", sources.contains(config.getConfiguration(CHILD1)));
    }

    @Test
    public void testGetSourcesMultiSources_3_oe() {
        setUpSourceTest();
        final String key = "list.key";
        config.getConfiguration(CHILD1).addProperty(key, "1,2,3");
        config.getConfiguration(CHILD2).addProperty(key, "a,b,c");
        final Set<Configuration> sources = config.getSources(key);
        assertTrue("Source 2 not found", sources.contains(config.getConfiguration(CHILD2)));
    }

    @Test
    public void testGetSourcesUnknownKey_1_oe() {
        setUpSourceTest();
        assertTrue("Got sources", config.getSources("non.existing,key").isEmpty());
    }

    @Test
    public void testGetSourceSynchronized_1_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        assertNotNull("No source found", config.getSource(TEST_KEY));
    }

    @Test
    public void testGetSourceUnknown_1_oe() {
        setUpSourceTest();
        assertNull("Wrong result for unknown key", config.getSource("an.unknown.key"));
    }

    @Test
    public void testGetSourceWithCombinedChildConfiguration_1_oe() {
        setUpSourceTest();
        final CombinedConfiguration cc = new CombinedConfiguration();
        cc.addConfiguration(config);
        assertEquals("Wrong source", config, cc.getSource(TEST_KEY));
    }

    @Test
    public void testInit_1_oe() {
        assertEquals("Already configurations contained", 0, config.getNumberOfConfigurations());
    }

    @Test
    public void testInit_2_oe() {
        assertTrue("Set of names is not empty", config.getConfigurationNames().isEmpty());
    }

    @Test
    public void testInit_3_oe() {
        assertTrue("Wrong node combiner", config.getNodeCombiner() instanceof UnionCombiner);
    }

    @Test
    public void testInit_4_oe() {
        assertNull("Test config was found", config.getConfiguration(TEST_NAME));
    }

    @Test
    public void testInvalidateEventBeforeAndAfterChange_1_oe() {
        ConfigurationEvent event = new ConfigurationEvent(config, ConfigurationEvent.ANY, null, null, true);
        config.onEvent(event);
        assertEquals("No invalidate event fired", 1, listener.invalidateEvents);
    }

    @Test
    public void testInvalidateEventBeforeAndAfterChange_2_oe() {
        ConfigurationEvent event = new ConfigurationEvent(config, ConfigurationEvent.ANY, null, null, true);
        config.onEvent(event);
        event = new ConfigurationEvent(config, ConfigurationEvent.ANY, null, null, false);
        config.onEvent(event);
        assertEquals("Another invalidate event fired", 1, listener.invalidateEvents);
    }

    @Test
    public void testLockHandlingWithExceptionWhenConstructingRootNode_2_oe() {
        final SynchronizerTestImpl sync = setUpSynchronizerTest();
        final RuntimeException testEx = new ConfigurationRuntimeException("Test exception");
        final BaseHierarchicalConfiguration childEx = new BaseHierarchicalConfiguration() {
            @Override
            public NodeModel<ImmutableNode> getModel() {
                throw testEx;
            }
        };
        config.addConfiguration(childEx);
        try {
            config.lock(LockMode.READ);
        } catch (final Exception ex) {
            assertEquals("Unexpected exception", testEx, ex);
    }
    }

    @Test
    public void testRemoveConfiguration_1_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c);
        checkAddConfig(c);
        assertTrue("Config could not be removed", config.removeConfiguration(c));
    }

    @Test
    public void testRemoveConfigurationAt_1_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c);
        assertSame("Wrong config removed", c, config.removeConfigurationAt(0));
    }

    @Test
    public void testRemoveConfigurationByName_1_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, TEST_NAME);
        assertSame("Wrong config removed", c, config.removeConfiguration(TEST_NAME));
    }

    @Test
    public void testRemoveConfigurationByUnknownName_1_oe() {
        assertNull("Could remove configuration by unknown name", config.removeConfiguration("unknownName"));
    }

    @Test
    public void testRemoveNamedConfigurationAt_1_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c, TEST_NAME);
        assertSame("Wrong config removed", c, config.removeConfigurationAt(0));
    }

    @Test
    public void testRemoveNonContainedConfiguration_1_oe() {
        assertFalse("Could remove non contained config", config.removeConfiguration(setUpTestConfiguration()));
    }

    @Test
    public void testSetNodeCombiner_1_oe() {
        final NodeCombiner combiner = new UnionCombiner();
        config.setNodeCombiner(combiner);
        assertSame("Node combiner was not set", combiner, config.getNodeCombiner());
    }

    @Test
    public void testSubConfigurationWithUpdates_1_oe() {
        final AbstractConfiguration srcConfig = setUpSubConfigTest();
        final HierarchicalConfiguration<ImmutableNode> sub = config.configurationAt(SUB_KEY, true);
        assertTrue("Wrong value before update", sub.getBoolean(TEST_KEY));
    }

    @Test
    public void testSubConfigurationWithUpdates_2_oe() {
        final AbstractConfiguration srcConfig = setUpSubConfigTest();
        final HierarchicalConfiguration<ImmutableNode> sub = config.configurationAt(SUB_KEY, true);
        srcConfig.setProperty(TEST_KEY, Boolean.FALSE);
        assertFalse("Wrong value after update", sub.getBoolean(TEST_KEY));
    }

    @Test
    public void testSubConfigurationWithUpdates_3_oe() {
        final AbstractConfiguration srcConfig = setUpSubConfigTest();
        final HierarchicalConfiguration<ImmutableNode> sub = config.configurationAt(SUB_KEY, true);
        srcConfig.setProperty(TEST_KEY, Boolean.FALSE);
        assertFalse("Wrong value from combined configuration", config.getBoolean(SUB_KEY + '.' + TEST_KEY));
    }

    @Test
    public void testUpdateContainedConfiguration_1_oe() {
        final AbstractConfiguration c = setUpTestConfiguration();
        config.addConfiguration(c);
        c.addProperty("test.otherTest", "yes");
        assertEquals("New property not found", "yes", config.getString("test.otherTest"));
    }

}
