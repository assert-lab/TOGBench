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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import junitx.framework.ArrayAssert;
import junitx.framework.ListAssert;
import junitx.framework.ObjectAssert;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationAssert;
import org.apache.commons.configuration2.ConfigurationComparator;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.StrictConfigurationComparator;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.configuration2.tree.NodeHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 */
public class TestPropertyListConfiguration_OE25Dev {
    /**
     * Returns a list with the children of the given configuration's root note with the specified name.
     *
     * @param config the configuration
     * @param name the name of the desired children
     * @return the list with the corresponding child nodes
     */
    private static List<ImmutableNode> getNamedChildren(final HierarchicalConfiguration<ImmutableNode> config, final String name) {
        final NodeHandler<ImmutableNode> handler = config.getNodeModel().getNodeHandler();
        return handler.getChildren(handler.getRootNode(), name);
    }

    /**
     * Loads a configuration from the specified test file.
     *
     * @param c the configuration to be loaded
     * @param f the file to be loaded
     * @throws ConfigurationException if an error occurs
     */
    private static void load(final PropertyListConfiguration c, final File f) throws ConfigurationException {
        new FileHandler(c).load(f);
    }

    /** A helper object for dealing with temporary files. */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private PropertyListConfiguration config;

    private final File testProperties = ConfigurationAssert.getTestFile("test.plist");

    /**
     * Saves the test configuration to the specified file.
     *
     * @param file the target file
     * @throws ConfigurationException if an error occurs
     */
    private void saveConfig(final File file) throws ConfigurationException {
        new FileHandler(config).save(file);
    }

    @Before
    public void setUp() throws Exception {
        config = new PropertyListConfiguration();
        load(config, testProperties);
    }

    /**
     * Ensure that addProperty doesn't alter an array of byte
     */

    /**
     * Tests formatting a date.
     */

    /**
     * Tests parsing a date that contains an invalid separator character.
     */
    @Test(expected = ParseException.class)
    public void testParseDateInvalidChar() throws ParseException {
        PropertyListConfiguration.parseDate("<*D2002+03-22 11:30:00 +0100>");
    }

    /**
     * Tests parsing a date with an invalid numeric value.
     */
    @Test(expected = ParseException.class)
    public void testParseDateNoNumber() throws ParseException {
        PropertyListConfiguration.parseDate("<*D2002-03-22 1c:30:00 +0100>");
    }

    /**
     * Tries parsing a null date. This should cause an exception.n
     */
    @Test(expected = ParseException.class)
    public void testParseDateNull() throws ParseException {
        PropertyListConfiguration.parseDate(null);
    }

    /**
     * Tests parsing a date that is not long enough.
     */
    @Test(expected = ParseException.class)
    public void testParseDateTooShort() throws ParseException {
        PropertyListConfiguration.parseDate("<*D2002-03-22 11:3>");
    }

    /**
     * Ensure that setProperty doesn't alter an array of byte since it's a first class type in plist file
     */

    @Test
    public void testAddDataProperty_1_oe() throws Exception {
        final File saveFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new PropertyListConfiguration();
        config.addProperty("foo", expected);
        saveConfig(saveFile);

        final PropertyListConfiguration config2 = new PropertyListConfiguration();
        load(config2, saveFile);
        final Object array = config2.getProperty("foo");

        assertNotNull("data not found", array);
    }

    @Test
    public void testAddDataProperty_2_oe() throws Exception {
        final File saveFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new PropertyListConfiguration();
        config.addProperty("foo", expected);
        saveConfig(saveFile);

        final PropertyListConfiguration config2 = new PropertyListConfiguration();
        load(config2, saveFile);
        final Object array = config2.getProperty("foo");

        // removed other assertion
        assertEquals("property type", byte[].class, array.getClass());
    }

    @Test
    public void testAddDataProperty_3_oe() throws Exception {
        final File saveFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new PropertyListConfiguration();
        config.addProperty("foo", expected);
        saveConfig(saveFile);

        final PropertyListConfiguration config2 = new PropertyListConfiguration();
        load(config2, saveFile);
        final Object array = config2.getProperty("foo");

        // removed other assertion
        // removed other assertion
        ArrayAssert.assertEquals(expected, (byte[]) array);
    }

    @Test
    public void testArray_1_oe() {
        final String key = "array";
        assertNotNull("array null", config.getProperty(key));
    }

    @Test
    public void testArray_2_oe() {
        final String key = "array";
        // removed other assertion

        final List<?> list = (List<?>) config.getProperty(key);
        assertFalse("array is empty", list.isEmpty());
    }

    @Test
    public void testArray_3_oe() {
        final String key = "array";
        // removed other assertion

        final List<?> list = (List<?>) config.getProperty(key);
        // removed other assertion

        assertEquals("1st value", "value1", list.get(0));
    }

    @Test
    public void testArray_4_oe() {
        final String key = "array";
        // removed other assertion

        final List<?> list = (List<?>) config.getProperty(key);
        // removed other assertion

        // removed other assertion
        assertEquals("2nd value", "value2", list.get(1));
    }

    @Test
    public void testArray_5_oe() {
        final String key = "array";
        // removed other assertion

        final List<?> list = (List<?>) config.getProperty(key);
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("3rd value", "value3", list.get(2));
    }

    @Test
    public void testData_2_oe() {
        // removed other assertion
        ArrayAssert.assertEquals("data", "foo bar".getBytes(), (byte[]) config.getProperty("data"));
    }

    @Test
    public void testDate_1_oe() throws Exception {
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2002, Calendar.MARCH, 22, 11, 30, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT+0100"));
        final Date date = cal.getTime();

        assertEquals("date", date, config.getProperty("date"));
    }

    @Test
    public void testDictionary_1_oe() {
        assertEquals("1st element in dictionary", "bar1", config.getProperty("dictionary.foo1"));
    }

    @Test
    public void testDictionary_2_oe() {
        // removed other assertion
        assertEquals("2nd element in dictionary", "bar2", config.getProperty("dictionary.foo2"));
    }

    @Test
    public void testDictionaryArray_1_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        assertNotNull("array not found", array);
    }

    @Test
    public void testDictionaryArray_3_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        assertFalse("empty array", list.isEmpty());
    }

    @Test
    public void testDictionaryArray_4_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        assertEquals("size", 2, list.size());
    }

    @Test
    public void testDictionaryArray_6_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st dictionary
        // removed other assertion
        final Configuration conf1 = (Configuration) list.get(0);
        assertFalse("configuration 1 is empty", conf1.isEmpty());
    }

    @Test
    public void testDictionaryArray_7_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st dictionary
        // removed other assertion
        final Configuration conf1 = (Configuration) list.get(0);
        // removed other assertion
        assertEquals("configuration element", "bar", conf1.getProperty("foo"));
    }

    @Test
    public void testDictionaryArray_9_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st dictionary
        // removed other assertion
        final Configuration conf1 = (Configuration) list.get(0);
        // removed other assertion
        // removed other assertion

        // 2nd dictionary
        // removed other assertion
        final Configuration conf2 = (Configuration) list.get(1);
        assertFalse("configuration 2 is empty", conf2.isEmpty());
    }

    @Test
    public void testDictionaryArray_10_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st dictionary
        // removed other assertion
        final Configuration conf1 = (Configuration) list.get(0);
        // removed other assertion
        // removed other assertion

        // 2nd dictionary
        // removed other assertion
        final Configuration conf2 = (Configuration) list.get(1);
        // removed other assertion
        assertEquals("configuration element", "value", conf2.getProperty("key"));
    }

    @Test
    public void testEmptyArray_1_oe() {
        final String key = "empty-array";
        assertNotNull("array null", config.getProperty(key));
    }

    @Test
    public void testEmptyArray_2_oe() {
        final String key = "empty-array";
        // removed other assertion

        final List<?> list = (List<?>) config.getProperty(key);
        assertTrue("array is not empty", list.isEmpty());
    }

    @Test
    public void testFormatDate_1_oe() {
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2007, Calendar.OCTOBER, 29, 23, 4, 30);
        cal.setTimeZone(TimeZone.getTimeZone("GMT-0230"));
        assertEquals("Wrong date literal (1)", "<*D2007-10-29 23:04:30 -0230>", PropertyListConfiguration.formatDate(cal));
    }

    @Test
    public void testFormatDate_2_oe() {
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2007, Calendar.OCTOBER, 29, 23, 4, 30);
        cal.setTimeZone(TimeZone.getTimeZone("GMT-0230"));
        // removed other assertion
        cal.clear();
        cal.set(2007, Calendar.OCTOBER, 30, 22, 2, 15);
        cal.setTimeZone(TimeZone.getTimeZone("GMT+1111"));
        assertEquals("Wrong date literal (2)", "<*D2007-10-30 22:02:15 +1111>", PropertyListConfiguration.formatDate(cal));
    }

    @Test
    public void testInitCopy_1_oe() {
        final PropertyListConfiguration copy = new PropertyListConfiguration(config);
        assertFalse("Nothing was copied", copy.isEmpty());
    }

    @Test
    public void testLoad_1_oe() {
        assertFalse("the configuration is empty", config.isEmpty());
    }

    @Test
    public void testLoadWithError_2_oe() {
        config = new PropertyListConfiguration();
        try {
            new FileHandler(config).load(new StringReader(""));
            // removed other assertion
        } catch (final ConfigurationException e) {
            // expected
            assertNotNull(e.getMessage());
    }
    }

    @Test
    public void testNestedArrays_1_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        assertNotNull("array not found", array);
    }

    @Test
    public void testNestedArrays_3_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        assertFalse("empty array", list.isEmpty());
    }

    @Test
    public void testNestedArrays_4_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        assertEquals("size", 2, list.size());
    }

    @Test
    public void testNestedArrays_6_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        // removed other assertion
        final List<?> list1 = (List<?>) list.get(0);
        assertFalse("nested array 1 is empty", list1.isEmpty());
    }

    @Test
    public void testNestedArrays_7_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        // removed other assertion
        final List<?> list1 = (List<?>) list.get(0);
        // removed other assertion
        assertEquals("size", 2, list1.size());
    }

    @Test
    public void testNestedArrays_8_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        // removed other assertion
        final List<?> list1 = (List<?>) list.get(0);
        // removed other assertion
        // removed other assertion
        assertEquals("1st element", "a", list1.get(0));
    }

    @Test
    public void testNestedArrays_9_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        // removed other assertion
        final List<?> list1 = (List<?>) list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2nd element", "b", list1.get(1));
    }

    @Test
    public void testNestedArrays_11_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        // removed other assertion
        final List<?> list1 = (List<?>) list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 2nd array
        // removed other assertion
        final List<?> list2 = (List<?>) list.get(1);
        assertFalse("nested array 2 is empty", list2.isEmpty());
    }

    @Test
    public void testNestedArrays_12_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        // removed other assertion
        final List<?> list1 = (List<?>) list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 2nd array
        // removed other assertion
        final List<?> list2 = (List<?>) list.get(1);
        // removed other assertion
        assertEquals("size", 2, list2.size());
    }

    @Test
    public void testNestedArrays_13_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        // removed other assertion
        final List<?> list1 = (List<?>) list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 2nd array
        // removed other assertion
        final List<?> list2 = (List<?>) list.get(1);
        // removed other assertion
        // removed other assertion
        assertEquals("1st element", "c", list2.get(0));
    }

    @Test
    public void testNestedArrays_14_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        // removed other assertion
        final List<?> list1 = (List<?>) list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 2nd array
        // removed other assertion
        final List<?> list2 = (List<?>) list.get(1);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("2nd element", "d", list2.get(1));
    }

    @Test
    public void testNestedDictionaries_1_oe() {
        assertEquals("nested property", "value", config.getString("nested-dictionaries.foo.bar.key"));
    }

    @Test
    public void testQuotedString_1_oe() {
        assertEquals("quoted-string", "string2", config.getProperty("quoted-string"));
    }

    @Test
    public void testQuotedString_2_oe() {
        // removed other assertion
        assertEquals("quoted-string2", "this is a string", config.getProperty("quoted-string2"));
    }

    @Test
    public void testQuotedString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("complex-string", "this is a \"complex\" string {(=,;)}", config.getProperty("complex-string"));
    }

    @Test
    public void testQuoteString_1_oe() {
        assertNull("null string", config.quoteString(null));
    }

    @Test
    public void testQuoteString_2_oe() {
        // removed other assertion
        assertEquals("simple string", "abcd", config.quoteString("abcd"));
    }

    @Test
    public void testQuoteString_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("string with a space", "\"ab cd\"", config.quoteString("ab cd"));
    }

    @Test
    public void testQuoteString_4_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("string with a quote", "\"foo\\\"bar\"", config.quoteString("foo\"bar"));
    }

    @Test
    public void testQuoteString_5_oe() {
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("string with a special char", "\"foo;bar\"", config.quoteString("foo;bar"));
    }

    @Test
    public void testSave_1_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        assertTrue("The saved file doesn't exist", savedFile.exists());
    }

    @Test
    public void testSave_2_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        // removed other assertion

        // read the configuration and compare the properties
        final PropertyListConfiguration checkConfig = new PropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();
            assertTrue("The saved configuration doesn't contain the key '" + key + "'", checkConfig.containsKey(key));
    }
    }

    @Test
    public void testSave_3_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        // removed other assertion

        // read the configuration and compare the properties
        final PropertyListConfiguration checkConfig = new PropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();
            // removed other assertion

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
                ArrayAssert.assertEquals("Value of the '" + key + "' property", (byte[]) config.getProperty(key), array);
    }
    }
    }

    @Test
    public void testSave_4_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        // removed other assertion

        // read the configuration and compare the properties
        final PropertyListConfiguration checkConfig = new PropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();
            // removed other assertion

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
                // removed other assertion
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;

                assertEquals("The size of the list for the key '" + key + "' doesn't match", list1.size(), list2.size());
    }
    }
    }

    @Test
    public void testSave_5_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        // removed other assertion

        // read the configuration and compare the properties
        final PropertyListConfiguration checkConfig = new PropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();
            // removed other assertion

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
                // removed other assertion
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;

                // removed other assertion

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
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        // removed other assertion

        // read the configuration and compare the properties
        final PropertyListConfiguration checkConfig = new PropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();
            // removed other assertion

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
                // removed other assertion
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;

                // removed other assertion

                for (int i = 0; i < list2.size(); i++) {
                    final Object value1 = list1.get(i);
                    final Object value2 = list2.get(i);

                    if (value1 instanceof Configuration) {
                        final ConfigurationComparator comparator = new StrictConfigurationComparator();
                        // removed other assertion
                    } else {
                        assertEquals("Element at index " + i + " for the key '" + key + "'", value1, value2);
    }
    }
    }
    }
    }

    @Test
    public void testSave_7_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        // removed other assertion

        // read the configuration and compare the properties
        final PropertyListConfiguration checkConfig = new PropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();
            // removed other assertion

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
                // removed other assertion
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;

                // removed other assertion

                for (int i = 0; i < list2.size(); i++) {
                    final Object value1 = list1.get(i);
                    final Object value2 = list2.get(i);

                    if (value1 instanceof Configuration) {
                        final ConfigurationComparator comparator = new StrictConfigurationComparator();
                        // removed other assertion
                    } else {
                        // removed other assertion
                    }
                }

                ListAssert.assertEquals("Value of the '" + key + "' property", (List<?>) config.getProperty(key), list1);
    }
    }
    }

    @Test
    public void testSave_8_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        // removed other assertion

        // read the configuration and compare the properties
        final PropertyListConfiguration checkConfig = new PropertyListConfiguration();
        load(checkConfig, savedFile);

        final Iterator<String> it = config.getKeys();
        while (it.hasNext()) {
            final String key = it.next();
            // removed other assertion

            final Object value = checkConfig.getProperty(key);
            if (value instanceof byte[]) {
                final byte[] array = (byte[]) value;
                // removed other assertion
            } else if (value instanceof List) {
                final List<?> list1 = (List<?>) config.getProperty(key);
                final List<?> list2 = (List<?>) value;

                // removed other assertion

                for (int i = 0; i < list2.size(); i++) {
                    final Object value1 = list1.get(i);
                    final Object value2 = list2.get(i);

                    if (value1 instanceof Configuration) {
                        final ConfigurationComparator comparator = new StrictConfigurationComparator();
                        // removed other assertion
                    } else {
                        // removed other assertion
                    }
                }

                // removed other assertion
            } else {
                assertEquals("Value of the '" + key + "' property", config.getProperty(key), checkConfig.getProperty(key));
    }
    }
    }

    @Test
    public void testSaveEmptyDictionary_1_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        assertTrue("The saved file doesn't exist", savedFile.exists());
    }

    @Test
    public void testSaveEmptyDictionary_2_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        // removed other assertion

        // read the configuration and compare the properties
        final PropertyListConfiguration checkConfig = new PropertyListConfiguration();
        load(checkConfig, savedFile);

        assertFalse(getNamedChildren(config, "empty-dictionary").isEmpty());
    }

    @Test
    public void testSaveEmptyDictionary_3_oe() throws Exception {
        final File savedFile = folder.newFile("testsave.plist");

        // save the configuration
        saveConfig(savedFile);
        // removed other assertion

        // read the configuration and compare the properties
        final PropertyListConfiguration checkConfig = new PropertyListConfiguration();
        load(checkConfig, savedFile);

        // removed other assertion
        assertFalse(getNamedChildren(checkConfig, "empty-dictionary").isEmpty());
    }

    @Test
    public void testSetDataProperty_1_oe() throws Exception {
        final File saveFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new PropertyListConfiguration();
        config.setProperty("foo", expected);
        saveConfig(saveFile);

        final PropertyListConfiguration config2 = new PropertyListConfiguration();
        load(config2, saveFile);
        final Object array = config2.getProperty("foo");

        assertNotNull("data not found", array);
    }

    @Test
    public void testSetDataProperty_2_oe() throws Exception {
        final File saveFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new PropertyListConfiguration();
        config.setProperty("foo", expected);
        saveConfig(saveFile);

        final PropertyListConfiguration config2 = new PropertyListConfiguration();
        load(config2, saveFile);
        final Object array = config2.getProperty("foo");

        // removed other assertion
        assertEquals("property type", byte[].class, array.getClass());
    }

    @Test
    public void testSetDataProperty_3_oe() throws Exception {
        final File saveFile = folder.newFile();
        final byte[] expected = {1, 2, 3, 4};
        config = new PropertyListConfiguration();
        config.setProperty("foo", expected);
        saveConfig(saveFile);

        final PropertyListConfiguration config2 = new PropertyListConfiguration();
        load(config2, saveFile);
        final Object array = config2.getProperty("foo");

        // removed other assertion
        // removed other assertion
        ArrayAssert.assertEquals(expected, (byte[]) array);
    }

    @Test
    public void testString_1_oe() {
        assertEquals("simple-string", "string1", config.getProperty("simple-string"));
    }

@Test
    public void testData_1_oe() {
        ObjectAssert.assertInstanceOf("data", ArrayUtils.EMPTY_BYTE_ARRAY.getClass(), config.getProperty("data"));
    }

@Test
    public void testDictionaryArray_2_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        ObjectAssert.assertInstanceOf("the array element is not parsed as a List", List.class, array);
    }

@Test
    public void testDictionaryArray_5_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st dictionary
        ObjectAssert.assertInstanceOf("the dict element is not parsed as a Configuration", Configuration.class, list.get(0));
    }

@Test
    public void testDictionaryArray_8_oe() {
        final String key = "dictionary-array";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st dictionary
        // removed other assertion
        final Configuration conf1 = (Configuration) list.get(0);
        // removed other assertion
        // removed other assertion

        // 2nd dictionary
        ObjectAssert.assertInstanceOf("the dict element is not parsed as a Configuration", Configuration.class, list.get(1));
    }

@Test
    public void testNestedArrays_2_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        ObjectAssert.assertInstanceOf("the array element is not parsed as a List", List.class, array);
    }

@Test
    public void testNestedArrays_5_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        ObjectAssert.assertInstanceOf("the array element is not parsed as a List", List.class, list.get(0));
    }

@Test
    public void testNestedArrays_10_oe() {
        final String key = "nested-arrays";

        final Object array = config.getProperty(key);

        // root array
        // removed other assertion
        // removed other assertion
        final List<?> list = config.getList(key);

        // removed other assertion
        // removed other assertion

        // 1st array
        // removed other assertion
        final List<?> list1 = (List<?>) list.get(0);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // 2nd array
        ObjectAssert.assertInstanceOf("the array element is not parsed as a List", List.class, list.get(1));
    }

}
