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

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.configuration2.builder.FileBasedBuilderParametersImpl;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Test;

/**
 * A test class which tests functionality related to immutable configurations.
 *
 */
public class TestImmutableConfiguration_OE25Dev {
    /** Constant for the name of a test properties file. */
    private static final String TEST_FILE = "test.properties";

    /**
     * Creates a test configuration object filled with properties.
     *
     * @return the test configuration
     * @throws ConfigurationException if an error occurs
     */
    private static PropertiesConfiguration createTestConfig() throws ConfigurationException {
        return new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(new FileBasedBuilderParametersImpl().setFile(ConfigurationAssert.getTestFile(TEST_FILE))).getConfiguration();
    }

    /**
     * Obtains all keys from the given iteration.
     *
     * @param it the iterator
     * @return a set with all keys
     */
    private static Set<String> fetchKeys(final Iterator<String> it) {
        final Set<String> keys = new HashSet<>();
        while (it.hasNext()) {
            keys.add(it.next());
        }
        return keys;
    }

    /**
     * Tests that exceptions thrown by the wrapped configuration are handled correctly.
     */

    /**
     * Tests whether an immutable subset can be queried.
     */

    /**
     * Tests whether data can be accessed from an unmodifiable configuration.
     */

    /**
     * Tests that a cast to a mutable configuration is not possible.
     */
    @Test(expected = ClassCastException.class)
    public void testUnmodifiableConfigurationCast() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        final Configuration mutableConf = (Configuration) conf;
        mutableConf.clear();
    }

    /**
     * Tests an iteration over the keys of the immutable configuration.
     */

    /**
     * Tests that it is not possible to remove keys using the iterator.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableConfigurationIteratorRemove() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        final Iterator<String> it = conf.getKeys();
        it.next();
        it.remove();
    }

    /**
     * Tests whether an update of the original configuration is visible for the immutable view.
     */

    /**
     * Tries to create an immutable configuration from a null object.
     */
    @Test(expected = NullPointerException.class)
    public void testUnmodifiableConfigurationNull() {
        ConfigurationUtils.unmodifiableConfiguration(null);
    }

    /**
     * Tests different access methods for properties.
     */

    /**
     * Tests whether an unmodifiable hierarchical configuration can be created.
     */

    @Test
    public void testExceptionHandling_2_oe() {
        final PropertiesConfiguration config = new PropertiesConfiguration();
        final String property = "nonExistingProperty";
        config.setThrowExceptionOnMissing(true);
        final ImmutableConfiguration ic = ConfigurationUtils.unmodifiableConfiguration(config);
        try {
            ic.getString(property);
            // removed other assertion
        } catch (final NoSuchElementException e) {
            assertThat("Wrong message", e.getMessage(), containsString(property));
    }
    }

    @Test
    public void testImmutableSubset_1_oe() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        final ImmutableConfiguration subset = conf.immutableSubset("test");
        assertFalse("No content", subset.isEmpty());
    }

    @Test
    public void testImmutableSubset_2_oe() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        final ImmutableConfiguration subset = conf.immutableSubset("test");
        // removed other assertion
        assertEquals("Wrong value", 1000000, subset.getLong("long"));
    }

    @Test
    public void testUnmodifiableConfigurationAccess_1_oe() throws ConfigurationException {
        final Configuration confOrg = createTestConfig();
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(confOrg);
        assertFalse("Empty", conf.isEmpty());
    }

    @Test
    public void testUnmodifiableConfigurationAccess_2_oe() throws ConfigurationException {
        final Configuration confOrg = createTestConfig();
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(confOrg);
        // removed other assertion
        for (final Iterator<String> it = confOrg.getKeys(); it.hasNext();) {
            final String key = it.next();
            assertTrue("Key not contained: " + key, conf.containsKey(key));
    }
    }

    @Test
    public void testUnmodifiableConfigurationAccess_3_oe() throws ConfigurationException {
        final Configuration confOrg = createTestConfig();
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(confOrg);
        // removed other assertion
        for (final Iterator<String> it = confOrg.getKeys(); it.hasNext();) {
            final String key = it.next();
            // removed other assertion
            assertEquals("Wrong value for " + key, confOrg.getProperty(key), conf.getProperty(key));
    }
    }

    @Test
    public void testUnmodifiableConfigurationIterate_1_oe() throws ConfigurationException {
        final Configuration confOrg = createTestConfig();
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(confOrg);
        assertEquals("Different keys", fetchKeys(confOrg.getKeys()), fetchKeys(conf.getKeys()));
    }

    @Test
    public void testUnmodifiableConfigurationLiveUpdate_1_oe() throws ConfigurationException {
        final Configuration confOrg = createTestConfig();
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(confOrg);
        final String key = "new.property";
        final String value = "new value";
        confOrg.addProperty(key, value);
        assertEquals("Value not set", value, conf.getString(key));
    }

    @Test
    public void testUnmodifiableConfigurationOtherTypes_1_oe() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        assertEquals("Wrong byte", (byte) 10, conf.getByte("test.byte"));
    }

    @Test
    public void testUnmodifiableConfigurationOtherTypes_2_oe() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        // removed other assertion
        assertTrue("Wrong boolean", conf.getBoolean("test.boolean"));
    }

    @Test
    public void testUnmodifiableConfigurationOtherTypes_3_oe() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong double", 10.25, conf.getDouble("test.double"), .05);
    }

    @Test
    public void testUnmodifiableConfigurationOtherTypes_4_oe() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong float", 20.25f, conf.getFloat("test.float"), .05);
    }

    @Test
    public void testUnmodifiableConfigurationOtherTypes_5_oe() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong int", 10, conf.getInt("test.integer"));
    }

    @Test
    public void testUnmodifiableConfigurationOtherTypes_6_oe() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong long", 1000000L, conf.getLong("test.long"));
    }

    @Test
    public void testUnmodifiableConfigurationOtherTypes_7_oe() throws ConfigurationException {
        final ImmutableConfiguration conf = ConfigurationUtils.unmodifiableConfiguration(createTestConfig());
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong short", (short) 1, conf.getShort("test.short"));
    }

    @Test
    public void testUnmodifiableHierarchicalConfiguration_1_oe() {
        final HierarchicalConfiguration<?> conf = new BaseHierarchicalConfiguration();
        final String key = "test";
        conf.addProperty(key, Boolean.TRUE);
        final ImmutableHierarchicalConfiguration ihc = ConfigurationUtils.unmodifiableConfiguration(conf);
        assertTrue("Property not found", ihc.getBoolean(key));
    }

    @Test
    public void testUnmodifiableHierarchicalConfiguration_2_oe() {
        final HierarchicalConfiguration<?> conf = new BaseHierarchicalConfiguration();
        final String key = "test";
        conf.addProperty(key, Boolean.TRUE);
        final ImmutableHierarchicalConfiguration ihc = ConfigurationUtils.unmodifiableConfiguration(conf);
        // removed other assertion
        assertEquals("Wrong max index", 0, ihc.getMaxIndex(key));
    }

}
