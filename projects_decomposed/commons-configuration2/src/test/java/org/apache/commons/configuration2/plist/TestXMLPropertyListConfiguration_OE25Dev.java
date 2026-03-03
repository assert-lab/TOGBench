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

package org.apache.commons.configuration2.plist;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationAssert;
import org.apache.commons.configuration2.ConfigurationComparator;
import org.apache.commons.configuration2.StrictConfigurationComparator;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import junitx.framework.ArrayAssert;
import junitx.framework.ListAssert;
import junitx.framework.ObjectAssert;

/**
 */
public class TestXMLPropertyListConfiguration_OE25Dev {
    /**
     * Loads a test configuration.
     *
     * @param c the configuration object to be loaded
     * @param file the test file to be loaded
     * @throws ConfigurationException if an error occurs
     */
    private static void load(final XMLPropertyListConfiguration c, final File file) throws ConfigurationException {
        new FileHandler(c).load(file);
    }

    /** A helper object for dealing with temporary files. */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /** The test configuration. */
    private XMLPropertyListConfiguration config;

    /**
     * Checks whether the test configuration contains a key with an array value.
     *
     * @param expectedValues the expected values
     */
    private void checkArrayProperty(final List<?> expectedValues) throws ConfigurationException {
        final StringWriter out = new StringWriter();
        new FileHandler(config).save(out);
        final StringBuilder values = new StringBuilder();
        for (final Object v : expectedValues) {
            values.append("<string>").append(v).append("</string>");
        }
        final String content = out.toString().replaceAll("[ \n\r]", "");
        assertThat(content, containsString(String.format("<key>array</key><array>%s</array>", values)));
    }

    /**
     * Saves the test configuration to the specified file.
     *
     * @param file the target file
     * @throws ConfigurationException if an error occurs
     */
    private void save(final File file) throws ConfigurationException {
        new FileHandler(config).save(file);
    }

    @Before
    public void setUp() throws Exception {
        config = new XMLPropertyListConfiguration();
        load(config, ConfigurationAssert.getTestFile("test.plist.xml"));
    }

    /**
     * Tests whether an array can be added correctly. This test is related to CONFIGURATION-427.
     */
    @Test
    public void testAddArray() throws ConfigurationException {
        final Object[] elems = {"arrayElem1", "arrayElem2", "arrayElem3"};
        config = new XMLPropertyListConfiguration();
        config.addProperty("array", elems);

        checkArrayProperty(Arrays.asList(elems));
    }

    /**
     * Ensure that addProperty doesn't alter an array of byte
     */

    /**
     * Tests whether a list can be added correctly. This test is related to CONFIGURATION-427.
     */
    @Test
    public void testAddList() throws ConfigurationException {
        final List<String> elems = Arrays.asList("element1", "element2", "anotherElement");
        config = new XMLPropertyListConfiguration();
        config.addProperty("array", elems);

        checkArrayProperty(elems);
    }

    /**
     * Tests whether a configuration can be loaded that does not start with a {@code dict} element. This test case is
     * related to CONFIGURATION-405.
     */

    /**
     * Tests whether a configuration that does not start with a {@code dict} element can be loaded from a constructor. This
     * test case is related to CONFIGURATION-405.
     */

    /**
     * Tests the header of a saved file if no encoding is specified.
     */

    /**
     * Tests whether the encoding is written when saving a configuration.
     */

    /**
     * Tests whether an array can be set correctly. This test is related to CONFIGURATION-750.
     */
    @Test
    public void testSetArray() throws ConfigurationException {
        final Object[] elems = {"arrayElem1", "arrayElem2", "arrayElem3"};
        config = new XMLPropertyListConfiguration();
        config.setProperty("array", elems);

        checkArrayProperty(Arrays.asList(elems));
    }

    /**
     * Ensure that setProperty doesn't alter an array of byte since it's a first class type in plist file
     */

    /**
     * Tests a configuration file which contains an invalid date property value. This test is related to CONFIGURATION-501.
     */

    /**
     * Tests whether a list can be set correctly. This test is related to CONFIGURATION-750.
     */
    @Test
    public void testSetList() throws ConfigurationException {
        final List<String> elems = Arrays.asList("element1", "element2", "anotherElement");
        config = new XMLPropertyListConfiguration();
        config.setProperty("array", elems);

        checkArrayProperty(elems);
    }

    /**
     * Tests a direct invocation of the write() method. This test is related to CONFIGURATION-641.
     */

    @Test
    public void testAddDataProperty_1_oe() throws Exception {
        final File savedFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new XMLPropertyListConfiguration();
        config.addProperty("foo", expected);
        save(savedFile);

        final XMLPropertyListConfiguration config2 = new XMLPropertyListConfiguration();
        load(config2, savedFile);
        final Object array = config2.getProperty("foo");

        assertNotNull("data not found", array);
    }

    @Test
    public void testAddDataProperty_2_oe() throws Exception {
        final File savedFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new XMLPropertyListConfiguration();
        config.addProperty("foo", expected);
        save(savedFile);

        final XMLPropertyListConfiguration config2 = new XMLPropertyListConfiguration();
        load(config2, savedFile);
        final Object array = config2.getProperty("foo");

        assertEquals("property type", byte[].class, array.getClass());
    }

    @Test
    public void testAddDataProperty_3_oe() throws Exception {
        final File savedFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new XMLPropertyListConfiguration();
        config.addProperty("foo", expected);
        save(savedFile);

        final XMLPropertyListConfiguration config2 = new XMLPropertyListConfiguration();
        load(config2, savedFile);
        final Object array = config2.getProperty("foo");

        ArrayAssert.assertEquals(expected, (byte[]) array);
    }

    @Test
    public void testArray_1_oe() {
        final Object array = config.getProperty("array");

        assertNotNull("array not found", array);
    }

    @Test
    public void testArray_3_oe() {
        final Object array = config.getProperty("array");

        final List<?> list = config.getList("array");

        assertFalse("empty array", list.isEmpty());
    }

    @Test
    public void testArray_4_oe() {
        final Object array = config.getProperty("array");

        final List<?> list = config.getList("array");

        assertEquals("size", 3, list.size());
    }

    @Test
    public void testArray_5_oe() {
        final Object array = config.getProperty("array");

        final List<?> list = config.getList("array");

        assertEquals("1st element", "value1", list.get(0));
    }

    @Test
    public void testArray_6_oe() {
        final Object array = config.getProperty("array");

        final List<?> list = config.getList("array");

        assertEquals("2nd element", "value2", list.get(1));
    }

    @Test
    public void testArray_7_oe() {
        final Object array = config.getProperty("array");

        final List<?> list = config.getList("array");

        assertEquals("3rd element", "value3", list.get(2));
    }

    @Test
    public void testBoolean_1_oe() throws Exception {
        assertTrue("'boolean1' property", config.getBoolean("boolean1"));
    }

    @Test
    public void testBoolean_2_oe() throws Exception {
        assertFalse("'boolean2' property", config.getBoolean("boolean2"));
    }

    @Test
    public void testDate_1_oe() throws Exception {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(2005, Calendar.JANUARY, 1, 12, 0, 0);

        assertEquals("'date' property", calendar.getTime(), config.getProperty("date"));
    }

    @Test
    public void testDate_2_oe() throws Exception {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(2005, Calendar.JANUARY, 1, 12, 0, 0);


        calendar.setTimeZone(TimeZone.getTimeZone("CET"));
        calendar.set(2002, Calendar.MARCH, 22, 11, 30, 0);

        assertEquals("'date-gnustep' property", calendar.getTime(), config.getProperty("date-gnustep"));
    }

    @Test
    public void testDictionary_1_oe() {
        assertEquals("1st element", "value1", config.getProperty("dictionary.key1"));
    }

    @Test
    public void testDictionary_2_oe() {
        assertEquals("2nd element", "value2", config.getProperty("dictionary.key2"));
    }

    @Test
    public void testDictionary_3_oe() {
        assertEquals("3rd element", "value3", config.getProperty("dictionary.key3"));
    }

    @Test
    public void testDictionaryArray_1_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        assertNotNull("array not found", array);
    }

    @Test
    public void testDictionaryArray_3_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);

        assertFalse("empty array", list.isEmpty());
    }

    @Test
    public void testDictionaryArray_4_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);

        assertEquals("size", 2, list.size());
    }

    @Test
    public void testDictionaryArray_6_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final Configuration conf1 = (Configuration) list.get(0);
        assertFalse("configuration 1 is empty", conf1.isEmpty());
    }

    @Test
    public void testDictionaryArray_7_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final Configuration conf1 = (Configuration) list.get(0);
        assertEquals("configuration element", "bar", conf1.getProperty("foo"));
    }

    @Test
    public void testDictionaryArray_9_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final Configuration conf1 = (Configuration) list.get(0);

        final Configuration conf2 = (Configuration) list.get(1);
        assertFalse("configuration 2 is empty", conf2.isEmpty());
    }

    @Test
    public void testDictionaryArray_10_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final Configuration conf1 = (Configuration) list.get(0);

        final Configuration conf2 = (Configuration) list.get(1);
        assertEquals("configuration element", "value", conf2.getProperty("key"));
    }

    @Test
    public void testInitCopy_1_oe() {
        final XMLPropertyListConfiguration copy = new XMLPropertyListConfiguration(config);
        final StrictConfigurationComparator comp = new StrictConfigurationComparator();
        assertTrue("Configurations are not equal", comp.compare(config, copy));
    }

    @Test
    public void testInteger_1_oe() throws Exception {
        assertEquals("'integer' property", 12345678900L, config.getLong("integer"));
    }

    @Test
    public void testLoadNoDict_1_oe() throws ConfigurationException {
        final XMLPropertyListConfiguration plist = new XMLPropertyListConfiguration();
        load(plist, ConfigurationAssert.getTestFile("test2.plist.xml"));
        assertFalse("Configuration is empty", plist.isEmpty());
    }

    @Test
    public void testLoadNoDictConstr_1_oe() throws ConfigurationException {
        final XMLPropertyListConfiguration plist = new XMLPropertyListConfiguration();
        load(plist, ConfigurationAssert.getTestFile("test2.plist.xml"));
        assertFalse("Configuration is empty", plist.isEmpty());
    }

    @Test
    public void testNested_1_oe() {
        assertEquals("nested property", "value", config.getString("nested.node1.node2.node3"));
    }

    @Test
    public void testNestedArray_1_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        assertNotNull("array not found", array);
    }

    @Test
    public void testNestedArray_3_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);

        assertFalse("empty array", list.isEmpty());
    }

    @Test
    public void testNestedArray_4_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);

        assertEquals("size", 2, list.size());
    }

    @Test
    public void testNestedArray_6_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final List<?> list1 = (List<?>) list.get(0);
        assertFalse("nested array 1 is empty", list1.isEmpty());
    }

    @Test
    public void testNestedArray_7_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final List<?> list1 = (List<?>) list.get(0);
        assertEquals("size", 2, list1.size());
    }

    @Test
    public void testNestedArray_8_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final List<?> list1 = (List<?>) list.get(0);
        assertEquals("1st element", "a", list1.get(0));
    }

    @Test
    public void testNestedArray_9_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final List<?> list1 = (List<?>) list.get(0);
        assertEquals("2nd element", "b", list1.get(1));
    }

    @Test
    public void testNestedArray_11_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final List<?> list1 = (List<?>) list.get(0);

        final List<?> list2 = (List<?>) list.get(1);
        assertFalse("nested array 2 is empty", list2.isEmpty());
    }

    @Test
    public void testNestedArray_12_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final List<?> list1 = (List<?>) list.get(0);

        final List<?> list2 = (List<?>) list.get(1);
        assertEquals("size", 2, list2.size());
    }

    @Test
    public void testNestedArray_13_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final List<?> list1 = (List<?>) list.get(0);

        final List<?> list2 = (List<?>) list.get(1);
        assertEquals("1st element", "c", list2.get(0));
    }

    @Test
    public void testNestedArray_14_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final List<?> list1 = (List<?>) list.get(0);

        final List<?> list2 = (List<?>) list.get(1);
        assertEquals("2nd element", "d", list2.get(1));
    }

    @Test
    public void testReal_1_oe() throws Exception {
        assertEquals("'real' property", -12.345, config.getDouble("real"), 0);
    }

    @Test
    public void testSave_1_oe() throws Exception {
        final File savedFile = folder.newFile();

        /*
         * config.addProperty("string", "value1"); List list = new ArrayList(); for (int i = 1; i < 5; i++) { list.add("value" +
         * i); } config.addProperty("newarray", list);
         */

        /*
         * Map map = new HashMap(); map.put("foo", "bar"); map.put("int", new Integer(123)); config.addProperty("newmap", map);
         */

        save(savedFile);
        assertTrue("The saved file doesn't exist", savedFile.exists());
    }

    @Test
    public void testSave_2_oe() throws Exception {
        final File savedFile = folder.newFile();

        /*
         * config.addProperty("string", "value1"); List list = new ArrayList(); for (int i = 1; i < 5; i++) { list.add("value" +
         * i); } config.addProperty("newarray", list);
         */

        /*
         * Map map = new HashMap(); map.put("foo", "bar"); map.put("int", new Integer(123)); config.addProperty("newmap", map);
         */

        save(savedFile);

        final XMLPropertyListConfiguration checkConfig = new XMLPropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();
            assertTrue("The saved configuration doesn't contain the key '" + key + "'", checkConfig.containsKey(key));
    }
    }

    @Test
    public void testSave_3_oe() throws Exception {
        final File savedFile = folder.newFile();

        /*
         * config.addProperty("string", "value1"); List list = new ArrayList(); for (int i = 1; i < 5; i++) { list.add("value" +
         * i); } config.addProperty("newarray", list);
         */

        /*
         * Map map = new HashMap(); map.put("foo", "bar"); map.put("int", new Integer(123)); config.addProperty("newmap", map);
         */

        save(savedFile);

        final XMLPropertyListConfiguration checkConfig = new XMLPropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
                ArrayAssert.assertEquals("Value of the '" + key + "' property", (byte[]) config.getProperty(key), array);
    }
    }
    }

    @Test
    public void testSave_4_oe() throws Exception {
        final File savedFile = folder.newFile();

        /*
         * config.addProperty("string", "value1"); List list = new ArrayList(); for (int i = 1; i < 5; i++) { list.add("value" +
         * i); } config.addProperty("newarray", list);
         */

        /*
         * Map map = new HashMap(); map.put("foo", "bar"); map.put("int", new Integer(123)); config.addProperty("newmap", map);
         */

        save(savedFile);

        final XMLPropertyListConfiguration checkConfig = new XMLPropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;

                assertEquals("The size of the list for the key '" + key + "' doesn't match", list1.size(), list2.size());
    }
    }
    }

    @Test
    public void testSave_5_oe() throws Exception {
        final File savedFile = folder.newFile();

        /*
         * config.addProperty("string", "value1"); List list = new ArrayList(); for (int i = 1; i < 5; i++) { list.add("value" +
         * i); } config.addProperty("newarray", list);
         */

        /*
         * Map map = new HashMap(); map.put("foo", "bar"); map.put("int", new Integer(123)); config.addProperty("newmap", map);
         */

        save(savedFile);

        final XMLPropertyListConfiguration checkConfig = new XMLPropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;


                for (int i = 0; i < list2.size(); i++) {
                    final Object value1 = list1.get(i);
                    final Object value2 = list2.get(i);

                    if (value1 instanceof Configuration) {
                        final ConfigurationComparator comparator = new StrictConfigurationComparator();
                        assertTrue("The dictionnary at index " + i + " for the key '" + key + "' doesn't match",comparator.compare((Configuration)value1,(Configuration)value2));
    }
    }
    }
    }
    }

    @Test
    public void testSave_6_oe() throws Exception {
        final File savedFile = folder.newFile();

        /*
         * config.addProperty("string", "value1"); List list = new ArrayList(); for (int i = 1; i < 5; i++) { list.add("value" +
         * i); } config.addProperty("newarray", list);
         */

        /*
         * Map map = new HashMap(); map.put("foo", "bar"); map.put("int", new Integer(123)); config.addProperty("newmap", map);
         */

        save(savedFile);

        final XMLPropertyListConfiguration checkConfig = new XMLPropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;


                for (int i = 0; i < list2.size(); i++) {
                    final Object value1 = list1.get(i);
                    final Object value2 = list2.get(i);

                    if (value1 instanceof Configuration) {
                        final ConfigurationComparator comparator = new StrictConfigurationComparator();
                    } else {
                        assertEquals("Element at index " + i + " for the key '" + key + "'", value1, value2);
    }
    }
    }
    }
    }

    @Test
    public void testSave_7_oe() throws Exception {
        final File savedFile = folder.newFile();

        /*
         * config.addProperty("string", "value1"); List list = new ArrayList(); for (int i = 1; i < 5; i++) { list.add("value" +
         * i); } config.addProperty("newarray", list);
         */

        /*
         * Map map = new HashMap(); map.put("foo", "bar"); map.put("int", new Integer(123)); config.addProperty("newmap", map);
         */

        save(savedFile);

        final XMLPropertyListConfiguration checkConfig = new XMLPropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;


                for (int i = 0; i < list2.size(); i++) {
                    final Object value1 = list1.get(i);
                    final Object value2 = list2.get(i);

                    if (value1 instanceof Configuration) {
                        final ConfigurationComparator comparator = new StrictConfigurationComparator();
                    } else {
                    }
                }

                ListAssert.assertEquals("Value of the '" + key + "' property", (List<?>) config.getProperty(key), list1);
    }
    }
    }

    @Test
    public void testSave_8_oe() throws Exception {
        final File savedFile = folder.newFile();

        /*
         * config.addProperty("string", "value1"); List list = new ArrayList(); for (int i = 1; i < 5; i++) { list.add("value" +
         * i); } config.addProperty("newarray", list);
         */

        /*
         * Map map = new HashMap(); map.put("foo", "bar"); map.put("int", new Integer(123)); config.addProperty("newmap", map);
         */

        save(savedFile);

        final XMLPropertyListConfiguration checkConfig = new XMLPropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;


                for (int i = 0; i < list2.size(); i++) {
                    final Object value1 = list1.get(i);
                    final Object value2 = list2.get(i);

                    if (value1 instanceof Configuration) {
                        final ConfigurationComparator comparator = new StrictConfigurationComparator();
                    } else {
                    }
                }

            } else {
                assertEquals("Value of the '" + key + "' property", config.getProperty(key), checkConfig.getProperty(key));
    }
    }
    }

    @Test
    public void testSaveEmptyDictionary_1_oe() throws Exception {
        final File savedFile = folder.newFile();

        save(savedFile);
        assertTrue("The saved file doesn't exist", savedFile.exists());
    }

    @Test
    public void testSaveEmptyDictionary_2_oe() throws Exception {
        final File savedFile = folder.newFile();

        save(savedFile);

        final XMLPropertyListConfiguration checkConfig = new XMLPropertyListConfiguration();
        load(checkConfig, savedFile);

        assertNull(config.getProperty("empty-dictionary"));
    }

    @Test
    public void testSaveEmptyDictionary_3_oe() throws Exception {
        final File savedFile = folder.newFile();

        save(savedFile);

        final XMLPropertyListConfiguration checkConfig = new XMLPropertyListConfiguration();
        load(checkConfig, savedFile);

        assertNull(checkConfig.getProperty("empty-dictionary"));
    }

    @Test
    public void testSaveNoEncoding_1_oe() throws ConfigurationException {
        final StringWriter writer = new StringWriter();
        new FileHandler(config).save(writer);
        assertTrue("Wrong document header", writer.toString().indexOf("<?xml version=\"1.0\"?>") >= 0);
    }

    @Test
    public void testSaveWithEncoding_1_oe() throws ConfigurationException {
        final String encoding = "UTF-8";
        final FileHandler handler = new FileHandler(config);
        handler.setEncoding(encoding);
        final StringWriter writer = new StringWriter();
        handler.save(writer);
        assertTrue("Encoding not found", writer.toString().indexOf("<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>") >= 0);
    }

    @Test
    public void testSetDataProperty_1_oe() throws Exception {
        final File savedFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new XMLPropertyListConfiguration();
        config.setProperty("foo", expected);
        save(savedFile);

        final XMLPropertyListConfiguration config2 = new XMLPropertyListConfiguration();
        load(config2, savedFile);
        final Object array = config2.getProperty("foo");

        assertNotNull("data not found", array);
    }

    @Test
    public void testSetDataProperty_2_oe() throws Exception {
        final File savedFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new XMLPropertyListConfiguration();
        config.setProperty("foo", expected);
        save(savedFile);

        final XMLPropertyListConfiguration config2 = new XMLPropertyListConfiguration();
        load(config2, savedFile);
        final Object array = config2.getProperty("foo");

        assertEquals("property type", byte[].class, array.getClass());
    }

    @Test
    public void testSetDataProperty_3_oe() throws Exception {
        final File savedFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new XMLPropertyListConfiguration();
        config.setProperty("foo", expected);
        save(savedFile);

        final XMLPropertyListConfiguration config2 = new XMLPropertyListConfiguration();
        load(config2, savedFile);
        final Object array = config2.getProperty("foo");

        ArrayAssert.assertEquals(expected, (byte[]) array);
    }

    @Test
    public void testSetDatePropertyInvalid_1_oe() throws ConfigurationException {
        config.clear();
        load(config, ConfigurationAssert.getTestFile("test_invalid_date.plist.xml"));
        assertEquals("'string' property", "value1", config.getString("string"));
    }

    @Test
    public void testSetDatePropertyInvalid_2_oe() throws ConfigurationException {
        config.clear();
        load(config, ConfigurationAssert.getTestFile("test_invalid_date.plist.xml"));
        assertFalse("Date property was loaded", config.containsKey("date"));
    }

    @Test
    public void testString_1_oe() throws Exception {
        assertEquals("'string' property", "value1", config.getString("string"));
    }

    @Test
    public void testSubset_1_oe() {
        final Configuration subset = config.subset("dictionary");
        final Iterator<String> keys = subset.getKeys();

        String key = keys.next();
        assertEquals("1st key", "key1", key);
    }

    @Test
    public void testSubset_2_oe() {
        final Configuration subset = config.subset("dictionary");
        final Iterator<String> keys = subset.getKeys();

        String key = keys.next();
        assertEquals("1st value", "value1", subset.getString(key));
    }

    @Test
    public void testSubset_3_oe() {
        final Configuration subset = config.subset("dictionary");
        final Iterator<String> keys = subset.getKeys();

        String key = keys.next();

        key = keys.next();
        assertEquals("2nd key", "key2", key);
    }

    @Test
    public void testSubset_4_oe() {
        final Configuration subset = config.subset("dictionary");
        final Iterator<String> keys = subset.getKeys();

        String key = keys.next();

        key = keys.next();
        assertEquals("2nd value", "value2", subset.getString(key));
    }

    @Test
    public void testSubset_5_oe() {
        final Configuration subset = config.subset("dictionary");
        final Iterator<String> keys = subset.getKeys();

        String key = keys.next();

        key = keys.next();

        key = keys.next();
        assertEquals("3rd key", "key3", key);
    }

    @Test
    public void testSubset_6_oe() {
        final Configuration subset = config.subset("dictionary");
        final Iterator<String> keys = subset.getKeys();

        String key = keys.next();

        key = keys.next();

        key = keys.next();
        assertEquals("3rd value", "value3", subset.getString(key));
    }

    @Test
    public void testSubset_7_oe() {
        final Configuration subset = config.subset("dictionary");
        final Iterator<String> keys = subset.getKeys();

        String key = keys.next();

        key = keys.next();

        key = keys.next();

        assertFalse("more than 3 properties founds", keys.hasNext());
    }

    @Test
    public void testWriteCalledDirectly_2_oe() throws IOException {
        config = new XMLPropertyListConfiguration();
        config.addProperty("foo", "bar");

        try (Writer out = new FileWriter(folder.newFile())) {
            config.write(out);
        } catch (final ConfigurationException e) {
            assertThat(e.getMessage(), containsString("FileHandler"));
    }
    }

@Test
    public void testArray_2_oe() {
        final Object array = config.getProperty("array");

        ObjectAssert.assertInstanceOf("the array element is not parsed as a List", List.class, array);
    }

@Test
    public void testDictionaryArray_2_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        ObjectAssert.assertInstanceOf("the array element is not parsed as a List", List.class, array);
    }

@Test
    public void testDictionaryArray_5_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        ObjectAssert.assertInstanceOf("the dict element is not parsed as a Configuration", Configuration.class, list.get(0));
    }

@Test
    public void testDictionaryArray_8_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final Configuration conf1 = (Configuration) list.get(0);

        ObjectAssert.assertInstanceOf("the dict element is not parsed as a Configuration", Configuration.class, list.get(1));
    }

@Test
    public void testNestedArray_2_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        ObjectAssert.assertInstanceOf("the array element is not parsed as a List", List.class, array);
    }

@Test
    public void testNestedArray_5_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        ObjectAssert.assertInstanceOf("the array element is not parsed as a List", List.class, list.get(0));
    }

@Test
    public void testNestedArray_10_oe() {
        final String key = "nested-array";

        final Object array = config.getProperty(key);

        final List<?> list = config.getList(key);


        final List<?> list1 = (List<?>) list.get(0);

        ObjectAssert.assertInstanceOf("the array element is not parsed as a List", List.class, list.get(1));
    }

}
