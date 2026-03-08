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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration2.convert.LegacyListDelimiterHandler;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.io.FileHandler;
import org.junit.Before;
import org.junit.Test;

/**
 * Test loading multiple configurations.
 *
 */
public class TestNullCompositeConfiguration_OE25Dev {
    protected PropertiesConfiguration conf1;
    protected PropertiesConfiguration conf2;
    protected XMLConfiguration xmlConf;
    protected CompositeConfiguration cc;

    /** The File that we test with */
    private final String testProperties = ConfigurationAssert.getTestFile("test.properties").getAbsolutePath();
    private final String testProperties2 = ConfigurationAssert.getTestFile("test2.properties").getAbsolutePath();
    private final String testPropertiesXML = ConfigurationAssert.getTestFile("test.xml").getAbsolutePath();

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

        cc.setThrowExceptionOnMissing(false);
    }

    /**
     * Tests adding values. Make sure they _DON'T_ override any other properties but add to the existing properties and keep
     * sequence
     */

    /**
     * Tests setting values. These are set in memory mode only!
     */

    /**
     * Tests getting a default when the key doesn't exist
     */

    /**
     * Tests {@code getKeys(String key)} preserves the order
     */

    /**
     * Tests {@code getKeys()} preserves the order
     */

    /**
     * Tests retrieving subsets of configurations
     */

    /**
     * Tests {@code List} parsing.
     */

    /**
     * Tests setting values. These are set in memory mode only!
     */

    /**
     * Tests {@code String} array parsing.
     */

    /**
     * Tests subsets and still can resolve elements
     */

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
        assertEquals(2, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddRemoveConfigurations_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf1);
        assertEquals(2, cc.getNumberOfConfigurations());
    }

    @Test
    public void testAddRemoveConfigurations_3_oe() throws Exception {
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf1);
        // removed other assertion
        cc.addConfiguration(conf2);
        assertEquals(3, cc.getNumberOfConfigurations());
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
        assertEquals(2, cc.getNumberOfConfigurations());
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
        assertEquals(1, cc.getNumberOfConfigurations());
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
    public void testGetPropertyMissing_1_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        assertNull("Bogus property is not null!", cc.getString("bogus.property"));
    }

    @Test
    public void testGetPropertyMissing_2_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // removed other assertion

        assertFalse("Should be false", cc.getBoolean("test.missing.boolean", false));
    }

    @Test
    public void testGetPropertyMissing_3_oe() throws Exception {
        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // removed other assertion

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
    public void testGetStringWithDefaults_1_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final Configuration c = new CompositeConfiguration(defaults);

        c.addProperty("string", "test string");

        assertEquals("test string", c.getString("string"));
    }

    @Test
    public void testGetStringWithDefaults_2_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final Configuration c = new CompositeConfiguration(defaults);

        c.addProperty("string", "test string");

        // removed other assertion

        assertNull("XXX should have been null!", c.getString("XXX"));
    }

    @Test
    public void testGetStringWithDefaults_3_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final Configuration c = new CompositeConfiguration(defaults);

        c.addProperty("string", "test string");

        // removed other assertion

        // removed other assertion

        // test defaults
        assertEquals("test string", c.getString("string", "some default value"));
    }

    @Test
    public void testGetStringWithDefaults_4_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final Configuration c = new CompositeConfiguration(defaults);

        c.addProperty("string", "test string");

        // removed other assertion

        // removed other assertion

        // test defaults
        // removed other assertion
        assertEquals("default string", c.getString("default"));
    }

    @Test
    public void testGetStringWithDefaults_5_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final Configuration c = new CompositeConfiguration(defaults);

        c.addProperty("string", "test string");

        // removed other assertion

        // removed other assertion

        // test defaults
        // removed other assertion
        // removed other assertion
        assertEquals("default string", c.getString("default", "some default value"));
    }

    @Test
    public void testGetStringWithDefaults_6_oe() {
        final BaseConfiguration defaults = new BaseConfiguration();
        defaults.addProperty("default", "default string");

        final Configuration c = new CompositeConfiguration(defaults);

        c.addProperty("string", "test string");

        // removed other assertion

        // removed other assertion

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
    public void testGetVector_1_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // add an element to the vector in the composite configuration
        cc.addProperty("array", "value5");

        final List<Object> list = cc.getList("array");
        assertEquals("Wrong number of elements", 3, list.size());
    }

    @Test
    public void testGetVector_2_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // add an element to the vector in the composite configuration
        cc.addProperty("array", "value5");

        final List<Object> list = cc.getList("array");
        // removed other assertion
        assertEquals("Wrong element 1", "value1", list.get(0));
    }

    @Test
    public void testGetVector_3_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // add an element to the vector in the composite configuration
        cc.addProperty("array", "value5");

        final List<Object> list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong element 2", "value2", list.get(1));
    }

    @Test
    public void testGetVector_4_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("array", "value1");
        conf1.addProperty("array", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("array", "value3");
        conf2.addProperty("array", "value4");

        cc.addConfiguration(conf1);
        cc.addConfiguration(conf2);

        // add an element to the vector in the composite configuration
        cc.addProperty("array", "value5");

        final List<Object> list = cc.getList("array");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong element 3", "value5", list.get(2));
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
        assertFalse("Throw Exception Property is set!", cc.isThrowExceptionOnMissing());
    }

}
