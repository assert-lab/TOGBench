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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.apache.commons.configuration2.SynchronizerTestImpl.Methods;
import org.apache.commons.configuration2.builder.FileBasedBuilderParametersImpl;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.convert.DisabledListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.configuration2.resolver.CatalogResolver;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.configuration2.tree.NodeStructureHelper;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * test for loading and saving xml properties files
 *
 */
public class TestXMLConfiguration_OE25Dev {
    /**
     * A thread used for testing concurrent access to a builder.
     */
    private static class ReloadThread extends Thread {
        private final FileBasedConfigurationBuilder<?> builder;

        ReloadThread(final FileBasedConfigurationBuilder<?> confBulder) {
            builder = confBulder;
        }

        @Override
        public void run() {
            for (int i = 0; i < LOOP_COUNT; i++) {
                builder.resetResult();
            }
        }
    }

    /** XML Catalog */
    private static final String CATALOG_FILES = ConfigurationAssert.getTestFile("catalog.xml").getAbsolutePath();

    /** Constant for the used encoding. */
    static final String ENCODING = "ISO-8859-1";

    /** Constant for the test system ID. */
    static final String SYSTEM_ID = "properties.dtd";

    /** Constant for the test public ID. */
    static final String PUBLIC_ID = "-//Commons Configuration//DTD Test Configuration 1.3//EN";

    /** Constant for the DOCTYPE declaration. */
    static final String DOCTYPE_DECL = " PUBLIC \"" + PUBLIC_ID + "\" \"" + SYSTEM_ID + "\">";

    /** Constant for the DOCTYPE prefix. */
    static final String DOCTYPE = "<!DOCTYPE ";

    /** Constant for the transformer factory property. */
    static final String PROP_FACTORY = "javax.xml.transform.TransformerFactory";

    /** Constant for the number of test threads. */
    private static final int THREAD_COUNT = 5;
    /** Constant for the number of loops in tests with multiple threads. */
    private static final int LOOP_COUNT = 100;

    /**
     * Creates a new XMLConfiguration and loads the specified file.
     *
     * @param fileName the name of the file to be loaded
     * @return the newly created configuration instance
     * @throws ConfigurationException if an error occurs
     */
    private static XMLConfiguration createFromFile(final String fileName) throws ConfigurationException {
        final XMLConfiguration config = new XMLConfiguration();
        config.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        load(config, fileName);
        return config;
    }

    /**
     * Helper method for loading the specified configuration file.
     *
     * @param config the configuration
     * @param fileName the name of the file to be loaded
     * @throws ConfigurationException if an error occurs
     */
    private static void load(final XMLConfiguration config, final String fileName) throws ConfigurationException {
        final FileHandler handler = new FileHandler(config);
        handler.setFileName(fileName);
        handler.load();
    }

    /** Helper object for creating temporary files. */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /** The File that we test with */
    private final String testProperties = ConfigurationAssert.getTestFile("test.xml").getAbsolutePath();

    private final String testProperties2 = ConfigurationAssert.getTestFile("testDigesterConfigurationInclude1.xml").getAbsolutePath();

    private File testSaveConf;

    private File testSaveFile;

    private final String testFile2 = ConfigurationAssert.getTestFile("sample.xml").getAbsolutePath();

    private XMLConfiguration conf;

    /**
     * Helper method for testing whether a configuration was correctly saved to the default output file.
     *
     * @return the newly loaded configuration
     * @throws ConfigurationException if an error occurs
     */
    private XMLConfiguration checkSavedConfig() throws ConfigurationException {
        return checkSavedConfig(testSaveConf);
    }

    /**
     * Tests whether the saved configuration file matches the original data.
     *
     * @param saveFile the saved configuration file
     * @return the newly loaded configuration
     * @throws ConfigurationException if an error occurs
     */
    private XMLConfiguration checkSavedConfig(final File saveFile) throws ConfigurationException {
        final XMLConfiguration config = createFromFile(saveFile.getAbsolutePath());
        ConfigurationAssert.assertConfigurationEquals(conf, config);
        return config;
    }

    /**
     * Helper method for testing saving and loading a configuration when delimiter parsing is disabled.
     *
     * @param key the key to be checked
     * @throws ConfigurationException if an error occurs
     */
    private void checkSaveDelimiterParsingDisabled(final String key) throws ConfigurationException {
        conf.clear();
        conf.setListDelimiterHandler(new DisabledListDelimiterHandler());
        load(conf, testProperties);
        conf.setProperty(key, "C:\\Temp\\,C:\\Data\\");
        conf.addProperty(key, "a,b,c");
        saveTestConfig();
        final XMLConfiguration checkConf = new XMLConfiguration();
        checkConf.setListDelimiterHandler(conf.getListDelimiterHandler());
        load(checkConf, testSaveConf.getAbsolutePath());
        ConfigurationAssert.assertConfigurationEquals(conf, checkConf);
    }

    /**
     * Creates a validating document builder.
     *
     * @return the document builder
     * @throws ParserConfigurationException if an error occurs
     */
    private DocumentBuilder createValidatingDocBuilder() throws ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        final DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(final SAXParseException ex) throws SAXException {
                throw ex;
            }
        });
        return builder;
    }

    private Document parseXml(final String xml) throws SAXException, IOException, ParserConfigurationException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Removes the test output file if it exists.
     */
    private void removeTestFile() {
        if (testSaveConf.exists()) {
            assertTrue(testSaveConf.delete());
        }
    }

    /**
     * Helper method for saving the test configuration to the default output file.
     *
     * @throws ConfigurationException if an error occurs
     */
    private void saveTestConfig() throws ConfigurationException {
        final FileHandler handler = new FileHandler(conf);
        handler.save(testSaveConf);
    }

    @Before
    public void setUp() throws Exception {
        testSaveConf = folder.newFile("testsave.xml");
        testSaveFile = folder.newFile("testsample2.xml");
        conf = createFromFile(testProperties);
        removeTestFile();
    }

    /**
     * Tests saving a configuration after a node was added. Test for CONFIGURATION-294.
     */

    /**
     * Tests adding nodes from another configuration.
     */
    @Test
    public void testAddNodesCopy() throws ConfigurationException {
        final XMLConfiguration c2 = new XMLConfiguration();
        load(c2, testProperties2);
        conf.addNodes("copiedProperties", c2.getModel().getNodeHandler().getRootNode().getChildren());
        saveTestConfig();
        checkSavedConfig();
    }

    /**
     * Tests whether it is possible to add nodes to a XMLConfiguration through a SubnodeConfiguration and whether these
     * nodes have the correct type. This test is related to CONFIGURATION-472.
     */

    /**
     * Tests whether list properties are added correctly if delimiter parsing is disabled. This test is related to
     * CONFIGURATION-495.
     */

    /**
     * Tests if a second file can be appended to a first.
     */

    /**
     * Tries to create an attribute with multiple values. Only the first value is taken into account.
     */

    /**
     * Tests whether the addNodes() method triggers an auto save.
     */

    /**
     * Tests whether the auto save mechanism is triggered by changes at a subnode configuration.
     */

    /**
     * Tests whether a subnode configuration created from another subnode configuration of a XMLConfiguration can trigger
     * the auto save mechanism.
     */

    /**
     * Tests removing the text of the root element.
     */

    /**
     * Tests the clone() method.
     */

    /**
     * Tests saving a configuration after cloning to ensure that the clone and the original are completely detached.
     */

    /**
     * Tests access to tag names with delimiter characters.
     */

    /**
     * Tests the copy constructor for null input.
     */

    /**
     * Tests whether the name of the root element is copied when a configuration is created using the copy constructor.
     */

    /**
     * Tests whether the name of the root element is copied for a configuration for which not yet a document exists.
     */

    /**
     * Tests setting a custom document builder.
     */

    /**
     * Tests whether a validating document builder detects a validation error.
     */
    @Test(expected = ConfigurationException.class)
    public void testCustomDocBuilderValidationError() throws Exception {
        final DocumentBuilder builder = createValidatingDocBuilder();
        conf = new XMLConfiguration();
        conf.setDocumentBuilder(builder);
        load(conf, ConfigurationAssert.getTestFile("testValidateInvalid.xml").getAbsolutePath());
    }

    /**
     * Tests whether a valid document can be loaded with a validating document builder.
     */

    /**
     * Tests string properties with list delimiters when delimiter parsing is disabled
     */

    /**
     * Tests whether string properties with list delimiters can be accessed if delimiter parsing is disabled and the XPath
     * expression engine is used.
     */

    /**
     * Tests whether a DTD can be accessed.
     */

    /**
     * Tests whether an attribute can be set to an empty string. This test is related to CONFIGURATION-446.
     */

    /**
     * Tests handling of empty elements.
     */

    /**
     * Tests the isEmpty() method for an empty configuration that was reloaded.
     */

    /**
     * Tests the copy constructor.
     */

    /**
     * Tests list nodes with multiple values and attributes.
     */

    /**
     * Tests a list node with attributes that has multiple values separated by the list delimiter. In this scenario the
     * attribute should be added to all list nodes.
     */

    /**
     * Tests a list node with multiple values and multiple attributes. All attribute values should be assigned to all list
     * nodes.
     */

    /**
     * Tests constructing an XMLConfiguration from a non existing file and later saving to this file.
     */

    /**
     * Tests loading from a stream.
     */

    /**
     * Tests loading a non well formed XML from a string.
     */
    @Test(expected = ConfigurationException.class)
    public void testLoadInvalidXML() throws Exception {
        final String xml = "<?xml version=\"1.0\"?><config><test>1</rest></config>";
        conf = new XMLConfiguration();
        final FileHandler handler = new FileHandler(conf);
        handler.load(new StringReader(xml));
    }

    /**
     * Tests whether the encoding is correctly detected by the XML parser. This is done by loading an XML file with the
     * encoding "UTF-16". If this encoding is not detected correctly, an exception will be thrown that "Content is not
     * allowed in prolog". This test case is related to issue 34204.
     */

    /**
     * Tests that attribute values are not split.
     */

    /**
     * Tests whether an attribute value can be overridden.
     */

    /**
     * Tests whether spaces are preserved when the xml:space attribute is set.
     */

    /**
     * Tests an xml:space attribute with an invalid value. This will be interpreted as default.
     */

    /**
     * Tests whether the xml:space attribute works directly on the current element. This test is related to
     * CONFIGURATION-555.
     */

    /**
     * Tests whether the xml:space attribute can be overridden in nested elements.
     */

    /**
     * Tests whether the public ID is accessed in a synchronized manner.
     */

    /**
     * Tests a direct invocation of the read() method. This is not allowed because certain initializations have not been
     * done. This test is related to CONFIGURATION-641.
     */

    @Test
    public void testSave() throws Exception {
        // add an array of strings to the configuration
        conf.addProperty("string", "value1");
        for (int i = 1; i < 5; i++) {
            conf.addProperty("test.array", "value" + i);
        }

        // add comma delimited lists with escaped delimiters
        conf.addProperty("split.list5", "a\\,b\\,c");
        conf.setProperty("element3", "value\\,value1\\,value2");
        conf.setProperty("element3[@name]", "foo\\,bar");

        // save the configuration
        saveTestConfig();

        // read the configuration and compare the properties
        checkSavedConfig();
    }

    /**
     * Tests saving a configuration that was created from a hierarchical configuration. This test exposes bug
     * CONFIGURATION-301.
     */

    /**
     * Tests saving attributes (related to issue 34442).
     */

    /**
     * Tests saving and loading a configuration when delimiter parsing is disabled.
     */
    @Test
    public void testSaveDelimiterParsingDisabled() throws ConfigurationException {
        checkSaveDelimiterParsingDisabled("list.delimiter.test");
    }

    /**
     * Tests saving to a stream.
     */
    @Test
    public void testSaveToStream() throws ConfigurationException, IOException {
        final FileHandler handler = new FileHandler(conf);
        try (FileOutputStream out = new FileOutputStream(testSaveConf)) {
            handler.save(out, "UTF8");
        }

        checkSavedConfig(testSaveConf);
    }

    /**
     * Tests whether a configuration can be saved to a stream with a specific encoding.
     */
    @Test
    public void testSaveToStreamWithEncoding() throws ConfigurationException, IOException {
        final FileHandler handler = new FileHandler(conf);
        handler.setEncoding("UTF8");
        try (FileOutputStream out = new FileOutputStream(testSaveConf)) {
            handler.save(out);
        }

        checkSavedConfig(testSaveConf);
    }

    /**
     * Tests saving to a URL.
     */
    @Test
    public void testSaveToURL() throws Exception {
        final FileHandler handler = new FileHandler(conf);
        handler.save(testSaveConf.toURI().toURL());
        checkSavedConfig(testSaveConf);
    }

    /**
     * Tests whether a windows path can be saved correctly. This test is related to CONFIGURATION-428.
     */

    /**
     * Tests string properties with list delimiters when delimiter parsing is disabled
     */

    /**
     * Tests whether the DOCTYPE survives a save operation.
     */

    /**
     * Tests setting public and system IDs for the DOCTYPE and then saving the configuration. This should generate a DOCTYPE
     * declaration.
     */

    /**
     * Tests whether the encoding is written to the generated XML file.
     */

    /**
     * Tests saving a configuration if an invalid transformer factory is specified. In this case an error is thrown by the
     * transformer factory. XMLConfiguration should not catch this error.
     */
    @Test
    public void testSaveWithInvalidTransformerFactory() throws ConfigurationException {
        System.setProperty(PROP_FACTORY, "an.invalid.Class");
        try {
            saveTestConfig();
            fail("Could save with invalid TransformerFactory!");
        } catch (final TransformerFactoryConfigurationError cex) {
            // ok
        } finally {
            System.getProperties().remove(PROP_FACTORY);
        }
    }

    /**
     * Tests whether a default encoding is used if no specific encoding is set. According to the XSLT specification
     * (http://www.w3.org/TR/xslt#output) this should be either UTF-8 or UTF-16.
     */

    /**
     * Tests modifying an XML document and saving it with schema validation enabled.
     */

    /**
     * Tests modifying an XML document and validating it against the schema.
     */

    /**
     * Tests whether list properties are set correctly if delimiter parsing is disabled. This test is related to
     * CONFIGURATION-495.
     */

    /**
     * Tests setting an attribute on the root element.
     */

    /**
     * Tests setting text of the root element.
     */
    @Test
    public void testSetTextRootElement() throws ConfigurationException {
        conf.setProperty("", "Root text");
        saveTestConfig();
        checkSavedConfig();
    }

    /**
     * Tests string properties with list delimiters and escaped delimiters.
     */

    /**
     * Tests the subset() method. There was a bug that calling subset() had undesired side effects.
     */

    /**
     * Tests whether the system ID is accessed in a synchronized manner.
     */

    /**
     * Tests DTD validation using the setValidating() method.
     */

    /**
     * Tests whether an invalid file is detected when validating is enabled.
     */
    @Test(expected = ConfigurationException.class)
    public void testValidatingInvalidFile() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setValidating(true);
        load(conf, "testValidateInvalid.xml");
    }

    /**
     * Tests accessing properties when the XPATH expression engine is set.
     */

    @Test
    public void testAddList_1_oe() {
        conf.addProperty("test.array", "value1");
        conf.addProperty("test.array", "value2");

        final List<Object> list = conf.getList("test.array");
        assertNotNull("null list", list);
    }

    @Test
    public void testAddList_2_oe() {
        conf.addProperty("test.array", "value1");
        conf.addProperty("test.array", "value2");

        final List<Object> list = conf.getList("test.array");
        assertTrue("'value1' element missing", list.contains("value1"));
    }

    @Test
    public void testAddList_3_oe() {
        conf.addProperty("test.array", "value1");
        conf.addProperty("test.array", "value2");

        final List<Object> list = conf.getList("test.array");
        assertTrue("'value2' element missing", list.contains("value2"));
    }

    @Test
    public void testAddList_4_oe() {
        conf.addProperty("test.array", "value1");
        conf.addProperty("test.array", "value2");

        final List<Object> list = conf.getList("test.array");
        assertEquals("list size", 2, list.size());
    }

    @Test
    public void testAddNodesAndSave_1_oe() throws ConfigurationException {
        final ImmutableNode.Builder bldrNode = new ImmutableNode.Builder(1);
        bldrNode.addChild(NodeStructureHelper.createNode("child", null));
        bldrNode.addAttribute("attr", "");
        final ImmutableNode node2 = NodeStructureHelper.createNode("test2", null);
        conf.addNodes("add.nodes", Arrays.asList(bldrNode.name("test").create(), node2));
        saveTestConfig();
        conf.setProperty("add.nodes.test", "true");
        conf.setProperty("add.nodes.test.child", "yes");
        conf.setProperty("add.nodes.test[@attr]", "existing");
        conf.setProperty("add.nodes.test2", "anotherValue");
        saveTestConfig();
        final XMLConfiguration c2 = new XMLConfiguration();
        load(c2, testSaveConf.getAbsolutePath());
        assertEquals("Value was not saved", "true", c2.getString("add.nodes.test"));
    }

    @Test
    public void testAddNodesAndSave_2_oe() throws ConfigurationException {
        final ImmutableNode.Builder bldrNode = new ImmutableNode.Builder(1);
        bldrNode.addChild(NodeStructureHelper.createNode("child", null));
        bldrNode.addAttribute("attr", "");
        final ImmutableNode node2 = NodeStructureHelper.createNode("test2", null);
        conf.addNodes("add.nodes", Arrays.asList(bldrNode.name("test").create(), node2));
        saveTestConfig();
        conf.setProperty("add.nodes.test", "true");
        conf.setProperty("add.nodes.test.child", "yes");
        conf.setProperty("add.nodes.test[@attr]", "existing");
        conf.setProperty("add.nodes.test2", "anotherValue");
        saveTestConfig();
        final XMLConfiguration c2 = new XMLConfiguration();
        load(c2, testSaveConf.getAbsolutePath());
        assertEquals("Child value was not saved", "yes", c2.getString("add.nodes.test.child"));
    }

    @Test
    public void testAddNodesAndSave_3_oe() throws ConfigurationException {
        final ImmutableNode.Builder bldrNode = new ImmutableNode.Builder(1);
        bldrNode.addChild(NodeStructureHelper.createNode("child", null));
        bldrNode.addAttribute("attr", "");
        final ImmutableNode node2 = NodeStructureHelper.createNode("test2", null);
        conf.addNodes("add.nodes", Arrays.asList(bldrNode.name("test").create(), node2));
        saveTestConfig();
        conf.setProperty("add.nodes.test", "true");
        conf.setProperty("add.nodes.test.child", "yes");
        conf.setProperty("add.nodes.test[@attr]", "existing");
        conf.setProperty("add.nodes.test2", "anotherValue");
        saveTestConfig();
        final XMLConfiguration c2 = new XMLConfiguration();
        load(c2, testSaveConf.getAbsolutePath());
        assertEquals("Attr value was not saved", "existing", c2.getString("add.nodes.test[@attr]"));
    }

    @Test
    public void testAddNodesAndSave_4_oe() throws ConfigurationException {
        final ImmutableNode.Builder bldrNode = new ImmutableNode.Builder(1);
        bldrNode.addChild(NodeStructureHelper.createNode("child", null));
        bldrNode.addAttribute("attr", "");
        final ImmutableNode node2 = NodeStructureHelper.createNode("test2", null);
        conf.addNodes("add.nodes", Arrays.asList(bldrNode.name("test").create(), node2));
        saveTestConfig();
        conf.setProperty("add.nodes.test", "true");
        conf.setProperty("add.nodes.test.child", "yes");
        conf.setProperty("add.nodes.test[@attr]", "existing");
        conf.setProperty("add.nodes.test2", "anotherValue");
        saveTestConfig();
        final XMLConfiguration c2 = new XMLConfiguration();
        load(c2, testSaveConf.getAbsolutePath());
        assertEquals("Node2 not saved", "anotherValue", c2.getString("add.nodes.test2"));
    }

    @Test
    public void testAddNodesToSubnodeConfiguration_1_oe() throws Exception {
        final HierarchicalConfiguration<ImmutableNode> sub = conf.configurationAt("element2", true);
        sub.addProperty("newKey", "newvalue");
        assertEquals("Property not added", "newvalue", conf.getString("element2.newKey"));
    }

    @Test
    public void testAddObjectAttribute_1_oe() {
        conf.addProperty("test.boolean[@value]", Boolean.TRUE);
        assertTrue("test.boolean[@value]", conf.getBoolean("test.boolean[@value]"));
    }

    @Test
    public void testAddObjectProperty_1_oe() {
        conf.addProperty("test.boolean", Boolean.TRUE);
        assertTrue("'test.boolean'", conf.getBoolean("test.boolean"));
    }

    @Test
    public void testAddProperty_1_oe() {
        final XMLConfiguration config = new XMLConfiguration();
        config.addProperty("test.string", "hello");

        assertEquals("'test.string'", "hello", config.getString("test.string"));
    }

    @Test
    public void testAddPropertyListWithDelimiterParsingDisabled_1_oe() throws ConfigurationException {
        conf.clear();
        final String prop = "delimiterListProp";
        conf.setListDelimiterHandler(DisabledListDelimiterHandler.INSTANCE);
        final List<String> list = Arrays.asList("val", "val2", "val3");
        conf.addProperty(prop, list);
        saveTestConfig();
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testSaveConf.getAbsolutePath());
        assertEquals("Wrong list property", list, conf2.getProperty(prop));
    }

    @Test
    public void testAppend_1_oe() throws Exception {
        load(conf, testProperties2);
        assertEquals("value", conf.getString("element"));
    }

    @Test
    public void testAppend_2_oe() throws Exception {
        load(conf, testProperties2);
        assertEquals("tasks", conf.getString("table.name"));
    }

    @Test
    public void testAppend_3_oe() throws Exception {
        load(conf, testProperties2);

        saveTestConfig();
        conf = createFromFile(testSaveConf.getAbsolutePath());
        assertEquals("value", conf.getString("element"));
    }

    @Test
    public void testAppend_4_oe() throws Exception {
        load(conf, testProperties2);

        saveTestConfig();
        conf = createFromFile(testSaveConf.getAbsolutePath());
        assertEquals("tasks", conf.getString("table.name"));
    }

    @Test
    public void testAppend_5_oe() throws Exception {
        load(conf, testProperties2);

        saveTestConfig();
        conf = createFromFile(testSaveConf.getAbsolutePath());
        assertEquals("application", conf.getString("table[@tableType]"));
    }

    @Test
    public void testAttributeKeyWithMultipleValues_1_oe() throws ConfigurationException {
        conf.addProperty("errorTest[@multiAttr]", Arrays.asList("v1", "v2"));
        saveTestConfig();
        final XMLConfiguration checkConfig = new XMLConfiguration();
        load(checkConfig, testSaveConf.getAbsolutePath());
        assertEquals("Wrong attribute value", "v1", checkConfig.getString("errorTest[@multiAttr]"));
    }

    @Test
    public void testAutoSaveAddNodes_1_oe() throws ConfigurationException {
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class);
        builder.configure(new FileBasedBuilderParametersImpl().setFileName(testProperties));
        conf = builder.getConfiguration();
        builder.getFileHandler().setFile(testSaveConf);
        builder.setAutoSave(true);
        final ImmutableNode node = NodeStructureHelper.createNode("addNodesTest", Boolean.TRUE);
        final Collection<ImmutableNode> nodes = new ArrayList<>(1);
        nodes.add(node);
        conf.addNodes("test.autosave", nodes);
        final XMLConfiguration c2 = new XMLConfiguration();
        load(c2, testSaveConf.getAbsolutePath());
        assertTrue("Added nodes are not saved", c2.getBoolean("test.autosave.addNodesTest"));
    }

    @Test
    public void testAutoSaveWithSubnodeConfig_1_oe() throws ConfigurationException {
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class);
        builder.configure(new FileBasedBuilderParametersImpl().setFileName(testProperties));
        conf = builder.getConfiguration();
        builder.getFileHandler().setFile(testSaveConf);
        builder.setAutoSave(true);
        final String newValue = "I am autosaved";
        final Configuration sub = conf.configurationAt("element2.subelement", true);
        sub.setProperty("subsubelement", newValue);
        assertEquals("Change not visible to parent", newValue, conf.getString("element2.subelement.subsubelement"));
    }

    @Test
    public void testAutoSaveWithSubnodeConfig_2_oe() throws ConfigurationException {
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class);
        builder.configure(new FileBasedBuilderParametersImpl().setFileName(testProperties));
        conf = builder.getConfiguration();
        builder.getFileHandler().setFile(testSaveConf);
        builder.setAutoSave(true);
        final String newValue = "I am autosaved";
        final Configuration sub = conf.configurationAt("element2.subelement", true);
        sub.setProperty("subsubelement", newValue);
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testSaveConf.getAbsolutePath());
        assertEquals("Change was not saved", newValue, conf2.getString("element2.subelement.subsubelement"));
    }

    @Test
    public void testAutoSaveWithSubSubnodeConfig_1_oe() throws ConfigurationException {
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class);
        builder.configure(new FileBasedBuilderParametersImpl().setFileName(testProperties));
        conf = builder.getConfiguration();
        builder.getFileHandler().setFile(testSaveConf);
        builder.setAutoSave(true);
        final String newValue = "I am autosaved";
        final HierarchicalConfiguration<?> sub1 = conf.configurationAt("element2", true);
        final HierarchicalConfiguration<?> sub2 = sub1.configurationAt("subelement", true);
        sub2.setProperty("subsubelement", newValue);
        assertEquals("Change not visible to parent", newValue, conf.getString("element2.subelement.subsubelement"));
    }

    @Test
    public void testAutoSaveWithSubSubnodeConfig_2_oe() throws ConfigurationException {
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class);
        builder.configure(new FileBasedBuilderParametersImpl().setFileName(testProperties));
        conf = builder.getConfiguration();
        builder.getFileHandler().setFile(testSaveConf);
        builder.setAutoSave(true);
        final String newValue = "I am autosaved";
        final HierarchicalConfiguration<?> sub1 = conf.configurationAt("element2", true);
        final HierarchicalConfiguration<?> sub2 = sub1.configurationAt("subelement", true);
        sub2.setProperty("subsubelement", newValue);
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testSaveConf.getAbsolutePath());
        assertEquals("Change was not saved", newValue, conf2.getString("element2.subelement.subsubelement"));
    }

    @Test
    public void testClearAttributeMultipleDisjoined_1_oe() throws Exception {
        String key = "clear.list.item[@id]";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearAttributeMultipleDisjoined_2_oe() throws Exception {
        String key = "clear.list.item[@id]";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearAttributeMultipleDisjoined_3_oe() throws Exception {
        String key = "clear.list.item[@id]";
        conf.clearProperty(key);
        key = "clear.list.item";
        assertNotNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearAttributeMultipleDisjoined_4_oe() throws Exception {
        String key = "clear.list.item[@id]";
        conf.clearProperty(key);
        key = "clear.list.item";
        assertNotNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearAttributeNonExisting_1_oe() {
        final String key = "clear[@id]";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearAttributeNonExisting_2_oe() {
        final String key = "clear[@id]";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearAttributeSingle_1_oe() {
        String key = "clear.element2[@id]";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearAttributeSingle_2_oe() {
        String key = "clear.element2[@id]";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearAttributeSingle_3_oe() {
        String key = "clear.element2[@id]";
        conf.clearProperty(key);
        key = "clear.element2";
        assertNotNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearAttributeSingle_4_oe() {
        String key = "clear.element2[@id]";
        conf.clearProperty(key);
        key = "clear.element2";
        assertNotNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyCData_1_oe() {
        final String key = "clear.cdata";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyCData_2_oe() {
        final String key = "clear.cdata";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyMultipleDisjoined_1_oe() throws Exception {
        final String key = "list.item";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyMultipleDisjoined_2_oe() throws Exception {
        final String key = "list.item";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyMultipleSiblings_1_oe() {
        String key = "clear.list.item";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyMultipleSiblings_2_oe() {
        String key = "clear.list.item";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyMultipleSiblings_3_oe() {
        String key = "clear.list.item";
        conf.clearProperty(key);
        key = "clear.list.item[@id]";
        assertNotNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyMultipleSiblings_4_oe() {
        String key = "clear.list.item";
        conf.clearProperty(key);
        key = "clear.list.item[@id]";
        assertNotNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyNonText_1_oe() {
        final String key = "clear.comment";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyNonText_2_oe() {
        final String key = "clear.comment";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyNotExisting_1_oe() {
        final String key = "clearly";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertyNotExisting_2_oe() {
        final String key = "clearly";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertySingleElement_1_oe() {
        final String key = "clear.element";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertySingleElement_2_oe() {
        final String key = "clear.element";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertySingleElementWithAttribute_1_oe() {
        String key = "clear.element2";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertySingleElementWithAttribute_2_oe() {
        String key = "clear.element2";
        conf.clearProperty(key);
        assertNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertySingleElementWithAttribute_3_oe() {
        String key = "clear.element2";
        conf.clearProperty(key);
        key = "clear.element2[@id]";
        assertNotNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearPropertySingleElementWithAttribute_4_oe() {
        String key = "clear.element2";
        conf.clearProperty(key);
        key = "clear.element2[@id]";
        assertNotNull(key, conf.getProperty(key));
    }

    @Test
    public void testClearTextRootElement_1_oe() throws ConfigurationException {
        final String xml = "<e a=\"v\">text</e>";
        conf.clear();
        final StringReader in = new StringReader(xml);
        final FileHandler handler = new FileHandler(conf);
        handler.load(in);
        assertEquals("Wrong text of root", "text", conf.getString(""));
    }

    @Test
    public void testClone_1_oe() {
        final Configuration c = (Configuration) conf.clone();
        assertTrue(c instanceof XMLConfiguration);
    }

    @Test
    public void testClone_2_oe() {
        final Configuration c = (Configuration) conf.clone();
        final XMLConfiguration copy = (XMLConfiguration) c;
        assertNotNull(conf.getDocument());
    }

    @Test
    public void testClone_3_oe() {
        final Configuration c = (Configuration) conf.clone();
        final XMLConfiguration copy = (XMLConfiguration) c;
        assertNull(copy.getDocument());
    }

    @Test
    public void testClone_4_oe() {
        final Configuration c = (Configuration) conf.clone();
        final XMLConfiguration copy = (XMLConfiguration) c;

        copy.setProperty("element3", "clonedValue");
        assertEquals("value", conf.getString("element3"));
    }

    @Test
    public void testClone_5_oe() {
        final Configuration c = (Configuration) conf.clone();
        final XMLConfiguration copy = (XMLConfiguration) c;

        copy.setProperty("element3", "clonedValue");
        conf.setProperty("element3[@name]", "originalFoo");
        assertEquals("foo", copy.getString("element3[@name]"));
    }

    @Test
    public void testCloneWithSave_1_oe() throws ConfigurationException {
        final XMLConfiguration c = (XMLConfiguration) conf.clone();
        c.addProperty("test.newProperty", Boolean.TRUE);
        conf.addProperty("test.orgProperty", Boolean.TRUE);
        new FileHandler(c).save(testSaveConf);
        final XMLConfiguration c2 = new XMLConfiguration();
        load(c2, testSaveConf.getAbsolutePath());
        assertTrue("New property after clone() was not saved", c2.getBoolean("test.newProperty"));
    }

    @Test
    public void testCloneWithSave_2_oe() throws ConfigurationException {
        final XMLConfiguration c = (XMLConfiguration) conf.clone();
        c.addProperty("test.newProperty", Boolean.TRUE);
        conf.addProperty("test.orgProperty", Boolean.TRUE);
        new FileHandler(c).save(testSaveConf);
        final XMLConfiguration c2 = new XMLConfiguration();
        load(c2, testSaveConf.getAbsolutePath());
        assertFalse("Property of original config was saved", c2.containsKey("test.orgProperty"));
    }

    @Test
    public void testComplexNames_1_oe() {
        assertEquals("Name with dot", conf.getString("complexNames.my..elem"));
    }

    @Test
    public void testComplexNames_2_oe() {
        assertEquals("Another dot", conf.getString("complexNames.my..elem.sub..elem"));
    }

    @Test
    public void testConcurrentGetAndReload_1_oe() throws ConfigurationException, InterruptedException {
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class);
        builder.configure(new FileBasedBuilderParametersImpl().setFileName(testProperties));
        XMLConfiguration config = builder.getConfiguration();
        assertNotNull("Property not found", config.getProperty("test.short"));
    }

    @Test
    public void testConcurrentGetAndReload_2_oe() throws ConfigurationException, InterruptedException {
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class);
        builder.configure(new FileBasedBuilderParametersImpl().setFileName(testProperties));
        XMLConfiguration config = builder.getConfiguration();

        final Thread testThreads[] = new Thread[THREAD_COUNT];
        for (int i = 0; i < testThreads.length; ++i) {
            testThreads[i] = new ReloadThread(builder);
            testThreads[i].start();
        }

        for (int i = 0; i < LOOP_COUNT; i++) {
            config = builder.getConfiguration();
            assertNotNull("Property not found", config.getProperty("test.short"));
    }
    }

    @Test
    public void testCopyNull_1_oe() {
        conf = new XMLConfiguration(null);
        assertTrue("Not empty", conf.isEmpty());
    }

    @Test
    public void testCopyNull_2_oe() {
        conf = new XMLConfiguration(null);
        assertEquals("Wrong root element name", "configuration", conf.getRootElementName());
    }

    @Test
    public void testCopyRootName_1_oe() throws ConfigurationException {
        final String rootName = "rootElement";
        final String xml = "<" + rootName + "><test>true</test></" + rootName + ">";
        conf.clear();
        new FileHandler(conf).load(new StringReader(xml));
        XMLConfiguration copy = new XMLConfiguration(conf);
        assertEquals("Wrong name of root element", rootName, copy.getRootElementName());
    }

    @Test
    public void testCopyRootName_2_oe() throws ConfigurationException {
        final String rootName = "rootElement";
        final String xml = "<" + rootName + "><test>true</test></" + rootName + ">";
        conf.clear();
        new FileHandler(conf).load(new StringReader(xml));
        XMLConfiguration copy = new XMLConfiguration(conf);
        new FileHandler(copy).save(testSaveConf);
        copy = new XMLConfiguration();
        load(copy, testSaveConf.getAbsolutePath());
        assertEquals("Wrong name of root element after save", rootName, copy.getRootElementName());
    }

    @Test
    public void testCopyRootNameNoDocument_1_oe() throws ConfigurationException {
        final String rootName = "rootElement";
        conf = new XMLConfiguration();
        conf.setRootElementName(rootName);
        conf.setProperty("test", Boolean.TRUE);
        final XMLConfiguration copy = new XMLConfiguration(conf);
        assertEquals("Wrong name of root element", rootName, copy.getRootElementName());
    }

    @Test
    public void testCopyRootNameNoDocument_2_oe() throws ConfigurationException {
        final String rootName = "rootElement";
        conf = new XMLConfiguration();
        conf.setRootElementName(rootName);
        conf.setProperty("test", Boolean.TRUE);
        final XMLConfiguration copy = new XMLConfiguration(conf);
        new FileHandler(copy).save(testSaveConf);
        load(copy, testSaveConf.getAbsolutePath());
        assertEquals("Wrong name of root element after save", rootName, copy.getRootElementName());
    }

    @Test
    public void testCustomDocBuilder_1_oe() throws Exception {
        conf = new XMLConfiguration();
        load(conf, ConfigurationAssert.getTestFile("testValidateInvalid.xml").getAbsolutePath());
        assertEquals("customers", conf.getString("table.name"));
    }

    @Test
    public void testCustomDocBuilder_2_oe() throws Exception {
        conf = new XMLConfiguration();
        load(conf, ConfigurationAssert.getTestFile("testValidateInvalid.xml").getAbsolutePath());
        assertFalse(conf.containsKey("table.fields.field(1).type"));
    }

    @Test
    public void testCustomDocBuilderValidationSuccess_1_oe() throws Exception {
        final DocumentBuilder builder = createValidatingDocBuilder();
        conf = new XMLConfiguration();
        conf.setDocumentBuilder(builder);
        load(conf, ConfigurationAssert.getTestFile("testValidateValid.xml").getAbsolutePath());
        assertTrue(conf.containsKey("table.fields.field(1).type"));
    }

    @Test
    public void testDelimiterParsingDisabled_1_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testProperties);

        assertEquals("a,b,c", conf2.getString("split.list3[@values]"));
    }

    @Test
    public void testDelimiterParsingDisabled_2_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testProperties);

        assertEquals(0, conf2.getMaxIndex("split.list3[@values]"));
    }

    @Test
    public void testDelimiterParsingDisabled_3_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testProperties);

        assertEquals("a\\,b\\,c", conf2.getString("split.list4[@values]"));
    }

    @Test
    public void testDelimiterParsingDisabled_4_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testProperties);

        assertEquals("a,b,c", conf2.getString("split.list1"));
    }

    @Test
    public void testDelimiterParsingDisabled_5_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testProperties);

        assertEquals(0, conf2.getMaxIndex("split.list1"));
    }

    @Test
    public void testDelimiterParsingDisabled_6_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testProperties);

        assertEquals("a\\,b\\,c", conf2.getString("split.list2"));
    }

    @Test
    public void testDelimiterParsingDisabledXPath_1_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        conf2.setExpressionEngine(new XPathExpressionEngine());
        load(conf2, testProperties);

        assertEquals("a,b,c", conf2.getString("split/list3/@values"));
    }

    @Test
    public void testDelimiterParsingDisabledXPath_2_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        conf2.setExpressionEngine(new XPathExpressionEngine());
        load(conf2, testProperties);

        assertEquals(0, conf2.getMaxIndex("split/list3/@values"));
    }

    @Test
    public void testDelimiterParsingDisabledXPath_3_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        conf2.setExpressionEngine(new XPathExpressionEngine());
        load(conf2, testProperties);

        assertEquals("a\\,b\\,c", conf2.getString("split/list4/@values"));
    }

    @Test
    public void testDelimiterParsingDisabledXPath_4_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        conf2.setExpressionEngine(new XPathExpressionEngine());
        load(conf2, testProperties);

        assertEquals("a,b,c", conf2.getString("split/list1"));
    }

    @Test
    public void testDelimiterParsingDisabledXPath_5_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        conf2.setExpressionEngine(new XPathExpressionEngine());
        load(conf2, testProperties);

        assertEquals(0, conf2.getMaxIndex("split/list1"));
    }

    @Test
    public void testDelimiterParsingDisabledXPath_6_oe() throws ConfigurationException {
        final XMLConfiguration conf2 = new XMLConfiguration();
        conf2.setExpressionEngine(new XPathExpressionEngine());
        load(conf2, testProperties);

        assertEquals("a\\,b\\,c", conf2.getString("split/list2"));
    }

    @Test
    public void testDtd_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        load(conf, "testDtd.xml");
        assertEquals("value1", conf.getString("entry(0)"));
    }

    @Test
    public void testDtd_2_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        load(conf, "testDtd.xml");
        assertEquals("test2", conf.getString("entry(1)[@key]"));
    }

    @Test
    public void testEmptyAttribute_1_oe() throws ConfigurationException {
        final String key = "element3[@value]";
        conf.setProperty(key, "");
        assertTrue("Key not found", conf.containsKey(key));
    }

    @Test
    public void testEmptyAttribute_2_oe() throws ConfigurationException {
        final String key = "element3[@value]";
        conf.setProperty(key, "");
        assertEquals("Wrong value", "", conf.getString(key));
    }

    @Test
    public void testEmptyAttribute_3_oe() throws ConfigurationException {
        final String key = "element3[@value]";
        conf.setProperty(key, "");
        saveTestConfig();
        conf = new XMLConfiguration();
        load(conf, testSaveConf.getAbsolutePath());
        assertTrue("Key not found after save", conf.containsKey(key));
    }

    @Test
    public void testEmptyAttribute_4_oe() throws ConfigurationException {
        final String key = "element3[@value]";
        conf.setProperty(key, "");
        saveTestConfig();
        conf = new XMLConfiguration();
        load(conf, testSaveConf.getAbsolutePath());
        assertEquals("Wrong value after save", "", conf.getString(key));
    }

    @Test
    public void testEmptyElements_1_oe() throws ConfigurationException {
        assertTrue(conf.containsKey("empty"));
    }

    @Test
    public void testEmptyElements_2_oe() throws ConfigurationException {
        assertEquals("", conf.getString("empty"));
    }

    @Test
    public void testEmptyElements_3_oe() throws ConfigurationException {
        conf.addProperty("empty2", "");
        conf.setProperty("empty", "no more empty");
        saveTestConfig();

        conf = new XMLConfiguration();
        load(conf, testSaveConf.getAbsolutePath());
        assertEquals("no more empty", conf.getString("empty"));
    }

    @Test
    public void testEmptyElements_4_oe() throws ConfigurationException {
        conf.addProperty("empty2", "");
        conf.setProperty("empty", "no more empty");
        saveTestConfig();

        conf = new XMLConfiguration();
        load(conf, testSaveConf.getAbsolutePath());
        assertEquals("", conf.getProperty("empty2"));
    }

    @Test
    public void testEmptyReload_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        assertTrue("Newly created configuration not empty", conf.isEmpty());
    }

    @Test
    public void testEmptyReload_2_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        saveTestConfig();
        load(conf, testSaveConf.getAbsolutePath());
        assertTrue("Reloaded configuration not empty", conf.isEmpty());
    }

    @Test
    public void testGetAttribute_1_oe() {
        assertEquals("element3[@name]", "foo", conf.getProperty("element3[@name]"));
    }

    @Test
    public void testGetCommentedProperty_1_oe() {
        assertEquals("", conf.getProperty("test.comment"));
    }

    @Test
    public void testGetComplexProperty_1_oe() {
        assertEquals("I'm complex!", conf.getProperty("element2.subelement.subsubelement"));
    }

    @Test
    public void testgetProperty_1_oe() {
        Object property = conf.getProperty("clear");
        assertNull(property);
    }

    @Test
    public void testgetProperty_2_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");
        assertNull(property);
    }

    @Test
    public void testgetProperty_3_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");
        assertNull(property);
    }

    @Test
    public void testgetProperty_4_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");
        assertNotNull(property);
    }

    @Test
    public void testgetProperty_5_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");
        assertTrue(property instanceof String);
    }

    @Test
    public void testgetProperty_6_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");
        assertEquals("value", property);
    }

    @Test
    public void testgetProperty_7_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");
        assertNotNull(property);
    }

    @Test
    public void testgetProperty_8_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");
        assertTrue(property instanceof String);
    }

    @Test
    public void testgetProperty_9_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");
        assertEquals("foo", property);
    }

    @Test
    public void testgetProperty_10_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");
        assertEquals("", property);
    }

    @Test
    public void testgetProperty_11_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");
        assertNotNull(property);
    }

    @Test
    public void testgetProperty_12_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");
        assertTrue(property instanceof String);
    }

    @Test
    public void testgetProperty_13_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");
        assertEquals("<cdata value>", property);
    }

    @Test
    public void testgetProperty_14_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        assertNotNull(property);
    }

    @Test
    public void testgetProperty_15_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        assertTrue(property instanceof List);
    }

    @Test
    public void testgetProperty_16_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;
        assertEquals(2, list.size());
    }

    @Test
    public void testgetProperty_17_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;
        assertEquals("five", list.get(0));
    }

    @Test
    public void testgetProperty_18_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;
        assertEquals("six", list.get(1));
    }

    @Test
    public void testgetProperty_19_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        assertNotNull(property);
    }

    @Test
    public void testgetProperty_20_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        assertTrue(property instanceof List);
    }

    @Test
    public void testgetProperty_21_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;
        assertEquals(4, list.size());
    }

    @Test
    public void testgetProperty_22_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;
        assertEquals("one", list.get(0));
    }

    @Test
    public void testgetProperty_23_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;
        assertEquals("two", list.get(1));
    }

    @Test
    public void testgetProperty_24_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;
        assertEquals("three", list.get(2));
    }

    @Test
    public void testgetProperty_25_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;
        assertEquals("four", list.get(3));
    }

    @Test
    public void testgetProperty_26_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;

        property = conf.getProperty("list.item[@name]");
        assertNotNull(property);
    }

    @Test
    public void testgetProperty_27_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;

        property = conf.getProperty("list.item[@name]");
        assertTrue(property instanceof List);
    }

    @Test
    public void testgetProperty_28_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;

        property = conf.getProperty("list.item[@name]");
        list = (List<?>) property;
        assertEquals(2, list.size());
    }

    @Test
    public void testgetProperty_29_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;

        property = conf.getProperty("list.item[@name]");
        list = (List<?>) property;
        assertEquals("one", list.get(0));
    }

    @Test
    public void testgetProperty_30_oe() {
        Object property = conf.getProperty("clear");

        property = conf.getProperty("e");

        property = conf.getProperty("element3[@n]");

        property = conf.getProperty("element");

        property = conf.getProperty("element3[@name]");

        property = conf.getProperty("test.comment");

        property = conf.getProperty("test.cdata");

        property = conf.getProperty("list.sublist.item");
        List<?> list = (List<?>) property;

        property = conf.getProperty("list.item");
        list = (List<?>) property;

        property = conf.getProperty("list.item[@name]");
        list = (List<?>) property;
        assertEquals("three", list.get(1));
    }

    @Test
    public void testGetProperty_1_oe() {
        assertEquals("value", conf.getProperty("element"));
    }

    @Test
    public void testGetPropertyWithXMLEntity_1_oe() {
        assertEquals("1<2", conf.getProperty("test.entity"));
    }

    @Test
    public void testInitCopy_1_oe() throws ConfigurationException {
        final XMLConfiguration copy = new XMLConfiguration(conf);
        copy.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        assertEquals("value", copy.getProperty("element"));
    }

    @Test
    public void testInitCopy_2_oe() throws ConfigurationException {
        final XMLConfiguration copy = new XMLConfiguration(conf);
        copy.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        assertNull("Document was copied, too", copy.getDocument());
    }

    @Test
    public void testListWithAttributes_1_oe() {
        assertEquals("Wrong number of <a> elements", 6, conf.getList("attrList.a").size());
    }

    @Test
    public void testListWithAttributes_2_oe() {
        assertEquals("Wrong value of first element", "ABC", conf.getString("attrList.a(0)"));
    }

    @Test
    public void testListWithAttributes_3_oe() {
        assertEquals("Wrong value of first name attribute", "x", conf.getString("attrList.a(0)[@name]"));
    }

    @Test
    public void testListWithAttributes_4_oe() {
        assertEquals("Wrong number of name attributes", 6, conf.getList("attrList.a[@name]").size());
    }

    @Test
    public void testListWithAttributesMultiValue_1_oe() {
        assertEquals("Wrong value of 2nd element", "1", conf.getString("attrList.a(1)"));
    }

    @Test
    public void testListWithAttributesMultiValue_2_oe() {
        assertEquals("Wrong value of 2nd name attribute", "y", conf.getString("attrList.a(1)[@name]"));
    }

    @Test
    public void testListWithAttributesMultiValue_3_oe() {
        for (int i = 1; i <= 3; i++) {
            assertEquals("Wrong value of element " + (i + 1), i, conf.getInt("attrList.a(" + i + ")"));
    }
    }

    @Test
    public void testListWithAttributesMultiValue_4_oe() {
        for (int i = 1; i <= 3; i++) {
            assertEquals("Wrong name attribute for element " + i, "y", conf.getString("attrList.a(" + i + ")[@name]"));
    }
    }

    @Test
    public void testListWithMultipleAttributesMultiValue_1_oe() {
        for (int i = 1; i <= 2; i++) {
            final String idxStr = String.format("(%d)", Integer.valueOf(i + 3));
            final String nodeKey = "attrList.a" + idxStr;
            assertEquals("Wrong value of multi-valued node", "value" + i, conf.getString(nodeKey));
    }
    }

    @Test
    public void testListWithMultipleAttributesMultiValue_2_oe() {
        for (int i = 1; i <= 2; i++) {
            final String idxStr = String.format("(%d)", Integer.valueOf(i + 3));
            final String nodeKey = "attrList.a" + idxStr;
            assertEquals("Wrong name attribute at " + i, "u", conf.getString(nodeKey + "[@name]"));
    }
    }

    @Test
    public void testListWithMultipleAttributesMultiValue_3_oe() {
        for (int i = 1; i <= 2; i++) {
            final String idxStr = String.format("(%d)", Integer.valueOf(i + 3));
            final String nodeKey = "attrList.a" + idxStr;
            assertEquals("Wrong test attribute at " + i, "yes", conf.getString(nodeKey + "[@test]"));
    }
    }

    @Test
    public void testLoadAndSaveFromFile_1_oe() throws Exception {
        assertFalse("File exists", testSaveConf.exists());
    }

    @Test
    public void testLoadAndSaveFromFile_2_oe() throws Exception {
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class, null, true);
        builder.configure(new FileBasedBuilderParametersImpl().setFile(testSaveConf));
        conf = builder.getConfiguration();
        assertTrue(conf.isEmpty());
    }

    @Test
    public void testLoadAndSaveFromFile_3_oe() throws Exception {
        final FileBasedConfigurationBuilder<XMLConfiguration> builder = new FileBasedConfigurationBuilder<>(XMLConfiguration.class, null, true);
        builder.configure(new FileBasedBuilderParametersImpl().setFile(testSaveConf));
        conf = builder.getConfiguration();
        conf.addProperty("test", "yes");
        builder.save();

        final XMLConfiguration checkConfig = createFromFile(testSaveConf.getAbsolutePath());
        assertEquals("yes", checkConfig.getString("test"));
    }

    @Test
    public void testLoadChildNamespace_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        new FileHandler(conf).load(ConfigurationAssert.getTestFile("testChildNamespace.xml"));
        assertEquals("http://example.com/", conf.getString("foo:bar.[@xmlns:foo]"));
    }

    @Test
    public void testLoadFromStream_1_oe() throws Exception {
        final String xml = "<?xml version=\"1.0\"?><config><test>1</test></config>";
        conf = new XMLConfiguration();
        FileHandler handler = new FileHandler(conf);
        handler.load(new ByteArrayInputStream(xml.getBytes()));
        assertEquals(1, conf.getInt("test"));
    }

    @Test
    public void testLoadFromStream_2_oe() throws Exception {
        final String xml = "<?xml version=\"1.0\"?><config><test>1</test></config>";
        conf = new XMLConfiguration();
        FileHandler handler = new FileHandler(conf);
        handler.load(new ByteArrayInputStream(xml.getBytes()));

        conf = new XMLConfiguration();
        handler = new FileHandler(conf);
        handler.load(new ByteArrayInputStream(xml.getBytes()), "UTF8");
        assertEquals(1, conf.getInt("test"));
    }

    @Test
    public void testLoadWithEncoding_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        new FileHandler(conf).load(ConfigurationAssert.getTestFile("testEncoding.xml"));
        assertEquals("test3_yoge", conf.getString("yoge"));
    }

    @Test
    public void testLoadWithRootNamespace_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        new FileHandler(conf).load(ConfigurationAssert.getTestFile("testRootNamespace.xml"));
        assertEquals("http://example.com/", conf.getString("[@xmlns:foo]"));
    }

    @Test
    public void testNoDelimiterParsingInAttrValues_1_oe() throws ConfigurationException {
        conf.clear();
        load(conf, testProperties);
        final List<Object> expr = conf.getList("expressions[@value]");
        assertEquals("Wrong list size", 1, expr.size());
    }

    @Test
    public void testNoDelimiterParsingInAttrValues_2_oe() throws ConfigurationException {
        conf.clear();
        load(conf, testProperties);
        final List<Object> expr = conf.getList("expressions[@value]");
        assertEquals("Wrong element 1", "a || (b && c) | !d", expr.get(0));
    }

    @Test
    public void testOverrideAttribute_1_oe() {
        conf.addProperty("element3[@name]", "bar");

        final List<Object> list = conf.getList("element3[@name]");
        assertNotNull("null list", list);
    }

    @Test
    public void testOverrideAttribute_2_oe() {
        conf.addProperty("element3[@name]", "bar");

        final List<Object> list = conf.getList("element3[@name]");
        assertTrue("'bar' element missing", list.contains("bar"));
    }

    @Test
    public void testOverrideAttribute_3_oe() {
        conf.addProperty("element3[@name]", "bar");

        final List<Object> list = conf.getList("element3[@name]");
        assertEquals("list size", 1, list.size());
    }

    @Test
    public void testPreserveSpace_1_oe() {
        assertEquals("Wrong value of blank", " ", conf.getString("space.blank"));
    }

    @Test
    public void testPreserveSpace_2_oe() {
        assertEquals("Wrong value of stars", " * * ", conf.getString("space.stars"));
    }

    @Test
    public void testPreserveSpaceInvalid_1_oe() {
        assertEquals("Invalid not trimmed", "Some other text", conf.getString("space.testInvalid"));
    }

    @Test
    public void testPreserveSpaceOnElement_1_oe() {
        assertEquals("Wrong value spaceElement", " preserved ", conf.getString("spaceElement"));
    }

    @Test
    public void testPreserveSpaceOnElement_2_oe() {
        assertEquals("Wrong value of spaceBlankElement", "   ", conf.getString("spaceBlankElement"));
    }

    @Test
    public void testPreserveSpaceOverride_1_oe() {
        assertEquals("Not trimmed", "Some text", conf.getString("space.description"));
    }

    @Test
    public void testPublicIdSynchronized_1_oe() {
        final SynchronizerTestImpl sync = new SynchronizerTestImpl();
        conf.setSynchronizer(sync);
        conf.setPublicID(PUBLIC_ID);
        assertEquals("PublicID not set", PUBLIC_ID, conf.getPublicID());
    }

    @Test
    public void testReadCalledDirectly_2_oe() throws IOException {
        conf = new XMLConfiguration();
        final String content = "<configuration><test>1</test></configuration>";
        final ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes());
        try {
            conf.read(bis);
        } catch (final ConfigurationException e) {
            assertThat(e.getMessage(), containsString("FileHandler"));
    }
    }

    @Test
    public void testSaveAfterCreateWithCopyConstructor_1_oe() throws ConfigurationException {
        final HierarchicalConfiguration<ImmutableNode> hc = conf.configurationAt("element2");
        conf = new XMLConfiguration(hc);
        saveTestConfig();
        final XMLConfiguration checkConfig = checkSavedConfig();
        assertEquals("Wrong name of root element", "element2", checkConfig.getRootElementName());
    }

    @Test
    public void testSaveAttributes_1_oe() throws Exception {
        conf.clear();
        load(conf, testProperties);
        saveTestConfig();
        conf = new XMLConfiguration();
        load(conf, testSaveConf.getAbsolutePath());
        assertEquals("foo", conf.getString("element3[@name]"));
    }

    @Test
    public void testSaveWindowsPath_1_oe() throws ConfigurationException {
        conf.clear();
        conf.setListDelimiterHandler(new DisabledListDelimiterHandler());
        conf.addProperty("path", "C:\\Temp");
        final StringWriter writer = new StringWriter();
        new FileHandler(conf).save(writer);
        final String content = writer.toString();
        assertThat("Path not found: ", content, containsString("<path>C:\\Temp</path>"));
    }

    @Test
    public void testSaveWindowsPath_2_oe() throws ConfigurationException {
        conf.clear();
        conf.setListDelimiterHandler(new DisabledListDelimiterHandler());
        conf.addProperty("path", "C:\\Temp");
        final StringWriter writer = new StringWriter();
        new FileHandler(conf).save(writer);
        final String content = writer.toString();
        saveTestConfig();
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testSaveConf.getAbsolutePath());
        assertEquals("Wrong windows path", "C:\\Temp", conf2.getString("path"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        assertEquals("a,b,c", conf.getString("split/list3/@values"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_2_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        assertEquals(0, conf.getMaxIndex("split/list3/@values"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_3_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        assertEquals("a\\,b\\,c", conf.getString("split/list4/@values"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_4_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        assertEquals("a,b,c", conf.getString("split/list1"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_5_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        assertEquals(0, conf.getMaxIndex("split/list1"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_6_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        assertEquals("a\\,b\\,c", conf.getString("split/list2"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_7_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        saveTestConfig();

        XMLConfiguration config = new XMLConfiguration();
        load(config, testFile2);
        config.setProperty("Employee[@attr1]", "3,2,1");
        assertEquals("3,2,1", config.getString("Employee[@attr1]"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_8_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        saveTestConfig();

        XMLConfiguration config = new XMLConfiguration();
        load(config, testFile2);
        config.setProperty("Employee[@attr1]", "3,2,1");
        new FileHandler(config).save(testSaveFile);
        config = new XMLConfiguration();
        load(config, testSaveFile.getAbsolutePath());
        config.setProperty("Employee[@attr1]", "1,2,3");
        assertEquals("1,2,3", config.getString("Employee[@attr1]"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_9_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        saveTestConfig();

        XMLConfiguration config = new XMLConfiguration();
        load(config, testFile2);
        config.setProperty("Employee[@attr1]", "3,2,1");
        new FileHandler(config).save(testSaveFile);
        config = new XMLConfiguration();
        load(config, testSaveFile.getAbsolutePath());
        config.setProperty("Employee[@attr1]", "1,2,3");
        config.setProperty("Employee[@attr2]", "one, two, three");
        assertEquals("one, two, three", config.getString("Employee[@attr2]"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_10_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        saveTestConfig();

        XMLConfiguration config = new XMLConfiguration();
        load(config, testFile2);
        config.setProperty("Employee[@attr1]", "3,2,1");
        new FileHandler(config).save(testSaveFile);
        config = new XMLConfiguration();
        load(config, testSaveFile.getAbsolutePath());
        config.setProperty("Employee[@attr1]", "1,2,3");
        config.setProperty("Employee[@attr2]", "one, two, three");
        config.setProperty("Employee.text", "a,b,d");
        assertEquals("a,b,d", config.getString("Employee.text"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_11_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        saveTestConfig();

        XMLConfiguration config = new XMLConfiguration();
        load(config, testFile2);
        config.setProperty("Employee[@attr1]", "3,2,1");
        new FileHandler(config).save(testSaveFile);
        config = new XMLConfiguration();
        load(config, testSaveFile.getAbsolutePath());
        config.setProperty("Employee[@attr1]", "1,2,3");
        config.setProperty("Employee[@attr2]", "one, two, three");
        config.setProperty("Employee.text", "a,b,d");
        config.setProperty("Employee.Salary", "100,000");
        assertEquals("100,000", config.getString("Employee.Salary"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_12_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        saveTestConfig();

        XMLConfiguration config = new XMLConfiguration();
        load(config, testFile2);
        config.setProperty("Employee[@attr1]", "3,2,1");
        new FileHandler(config).save(testSaveFile);
        config = new XMLConfiguration();
        load(config, testSaveFile.getAbsolutePath());
        config.setProperty("Employee[@attr1]", "1,2,3");
        config.setProperty("Employee[@attr2]", "one, two, three");
        config.setProperty("Employee.text", "a,b,d");
        config.setProperty("Employee.Salary", "100,000");
        new FileHandler(config).save(testSaveFile);
        final XMLConfiguration checkConfig = new XMLConfiguration();
        checkConfig.setExpressionEngine(new XPathExpressionEngine());
        load(checkConfig, testSaveFile.getAbsolutePath());
        assertEquals("1,2,3", checkConfig.getString("Employee/@attr1"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_13_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        saveTestConfig();

        XMLConfiguration config = new XMLConfiguration();
        load(config, testFile2);
        config.setProperty("Employee[@attr1]", "3,2,1");
        new FileHandler(config).save(testSaveFile);
        config = new XMLConfiguration();
        load(config, testSaveFile.getAbsolutePath());
        config.setProperty("Employee[@attr1]", "1,2,3");
        config.setProperty("Employee[@attr2]", "one, two, three");
        config.setProperty("Employee.text", "a,b,d");
        config.setProperty("Employee.Salary", "100,000");
        new FileHandler(config).save(testSaveFile);
        final XMLConfiguration checkConfig = new XMLConfiguration();
        checkConfig.setExpressionEngine(new XPathExpressionEngine());
        load(checkConfig, testSaveFile.getAbsolutePath());
        assertEquals("one, two, three", checkConfig.getString("Employee/@attr2"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_14_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        saveTestConfig();

        XMLConfiguration config = new XMLConfiguration();
        load(config, testFile2);
        config.setProperty("Employee[@attr1]", "3,2,1");
        new FileHandler(config).save(testSaveFile);
        config = new XMLConfiguration();
        load(config, testSaveFile.getAbsolutePath());
        config.setProperty("Employee[@attr1]", "1,2,3");
        config.setProperty("Employee[@attr2]", "one, two, three");
        config.setProperty("Employee.text", "a,b,d");
        config.setProperty("Employee.Salary", "100,000");
        new FileHandler(config).save(testSaveFile);
        final XMLConfiguration checkConfig = new XMLConfiguration();
        checkConfig.setExpressionEngine(new XPathExpressionEngine());
        load(checkConfig, testSaveFile.getAbsolutePath());
        assertEquals("a,b,d", checkConfig.getString("Employee/text"));
    }

    @Test
    public void testSaveWithDelimiterParsingDisabled_15_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setExpressionEngine(new XPathExpressionEngine());
        load(conf, testProperties);

        saveTestConfig();

        XMLConfiguration config = new XMLConfiguration();
        load(config, testFile2);
        config.setProperty("Employee[@attr1]", "3,2,1");
        new FileHandler(config).save(testSaveFile);
        config = new XMLConfiguration();
        load(config, testSaveFile.getAbsolutePath());
        config.setProperty("Employee[@attr1]", "1,2,3");
        config.setProperty("Employee[@attr2]", "one, two, three");
        config.setProperty("Employee.text", "a,b,d");
        config.setProperty("Employee.Salary", "100,000");
        new FileHandler(config).save(testSaveFile);
        final XMLConfiguration checkConfig = new XMLConfiguration();
        checkConfig.setExpressionEngine(new XPathExpressionEngine());
        load(checkConfig, testSaveFile.getAbsolutePath());
        assertEquals("100,000", checkConfig.getString("Employee/Salary"));
    }

    @Test
    public void testSaveWithDoctype_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        load(conf, "testDtdPublic.xml");

        assertEquals("Wrong public ID", PUBLIC_ID, conf.getPublicID());
    }

    @Test
    public void testSaveWithDoctype_2_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        load(conf, "testDtdPublic.xml");

        assertEquals("Wrong system ID", SYSTEM_ID, conf.getSystemID());
    }

    @Test
    public void testSaveWithDoctype_3_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        load(conf, "testDtdPublic.xml");

        final StringWriter out = new StringWriter();
        new FileHandler(conf).save(out);
        assertThat("Did not find DOCTYPE", out.toString(), containsString(DOCTYPE));
    }

    @Test
    public void testSaveWithDoctypeIDs_1_oe() throws ConfigurationException {
        assertNull("A public ID was found", conf.getPublicID());
    }

    @Test
    public void testSaveWithDoctypeIDs_2_oe() throws ConfigurationException {
        assertNull("A system ID was found", conf.getSystemID());
    }

    @Test
    public void testSaveWithDoctypeIDs_3_oe() throws ConfigurationException {
        conf.setPublicID(PUBLIC_ID);
        conf.setSystemID(SYSTEM_ID);
        final StringWriter out = new StringWriter();
        new FileHandler(conf).save(out);
        assertThat("Did not find DOCTYPE", out.toString(), containsString(DOCTYPE + "testconfig" + DOCTYPE_DECL));
    }

    @Test
    public void testSaveWithEncoding_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setProperty("test", "a value");
        final FileHandler handler = new FileHandler(conf);
        handler.setEncoding(ENCODING);

        final StringWriter out = new StringWriter();
        handler.save(out);
        assertThat("Encoding was not written to file", out.toString(), containsString("encoding=\"" + ENCODING + "\""));
    }

    @Test
    public void testSaveWithNullEncoding_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.setProperty("testNoEncoding", "yes");
        final FileHandler handler = new FileHandler(conf);

        final StringWriter out = new StringWriter();
        handler.save(out);
        assertThat("Encoding was written to file", out.toString(), containsString("encoding=\"UTF-"));
    }

    @Test
    public void testSaveWithRootAttributes_1_oe() throws ConfigurationException {
        conf.setProperty("[@xmlns:ex]", "http://example.com/");
        assertEquals("Root attribute not set", "http://example.com/", conf.getString("[@xmlns:ex]"));
    }

    @Test
    public void testSaveWithRootAttributes_2_oe() throws ConfigurationException {
        conf.setProperty("[@xmlns:ex]", "http://example.com/");
        final FileHandler handler = new FileHandler(conf);

        final StringWriter out = new StringWriter();
        handler.save(out);
        assertThat("Encoding was not written to file", out.toString(), containsString("testconfig xmlns:ex=\"http://example.com/\""));
    }

    @Test
    public void testSaveWithRootAttributes_ByHand_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.addProperty("[@xmlns:foo]", "http://example.com/");
        assertEquals("Root attribute not set", "http://example.com/", conf.getString("[@xmlns:foo]"));
    }

    @Test
    public void testSaveWithRootAttributes_ByHand_2_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        conf.addProperty("[@xmlns:foo]", "http://example.com/");
        final FileHandler handler = new FileHandler(conf);

        final StringWriter out = new StringWriter();
        handler.save(out);
        assertThat("Encoding was not written to file", out.toString(), containsString("configuration xmlns:foo=\"http://example.com/\""));
    }

    @Test
    public void testSaveWithValidation_1_oe() throws Exception {
        final CatalogResolver resolver = new CatalogResolver();
        resolver.setCatalogFiles(CATALOG_FILES);
        conf = new XMLConfiguration();
        conf.setEntityResolver(resolver);
        conf.setSchemaValidation(true);
        load(conf, testFile2);
        conf.setProperty("Employee.SSN", "123456789");
        final SynchronizerTestImpl sync = new SynchronizerTestImpl();
        conf.setSynchronizer(sync);
        conf.validate();
        sync.verify(Methods.BEGIN_WRITE, Methods.END_WRITE);
        saveTestConfig();
        conf = new XMLConfiguration();
        load(conf, testSaveConf.getAbsolutePath());
        assertEquals("123456789", conf.getString("Employee.SSN"));
    }

    @Test
    public void testSaveWithValidationFailure_2_oe() throws Exception {
        final CatalogResolver resolver = new CatalogResolver();
        resolver.setCatalogFiles(CATALOG_FILES);
        conf = new XMLConfiguration();
        conf.setEntityResolver(resolver);
        conf.setSchemaValidation(true);
        load(conf, testFile2);
        conf.setProperty("Employee.Email", "JohnDoe@test.org");
        try {
            conf.validate();
        } catch (final Exception e) {
            final Throwable cause = e.getCause();
            assertNotNull("No cause for exception on save", cause);
    }
    }

    @Test
    public void testSaveWithValidationFailure_3_oe() throws Exception {
        final CatalogResolver resolver = new CatalogResolver();
        resolver.setCatalogFiles(CATALOG_FILES);
        conf = new XMLConfiguration();
        conf.setEntityResolver(resolver);
        conf.setSchemaValidation(true);
        load(conf, testFile2);
        conf.setProperty("Employee.Email", "JohnDoe@test.org");
        try {
            conf.validate();
        } catch (final Exception e) {
            final Throwable cause = e.getCause();
            assertTrue("Incorrect exception on save", cause instanceof SAXParseException);
    }
    }

    @Test
    public void testSetAttribute_1_oe() {
        conf.setProperty("element3[@name]", "bar");
        assertEquals("element3[@name]", "bar", conf.getProperty("element3[@name]"));
    }

    @Test
    public void testSetAttribute_2_oe() {
        conf.setProperty("element3[@name]", "bar");

        conf.setProperty("foo[@bar]", "value");
        assertEquals("foo[@bar]", "value", conf.getProperty("foo[@bar]"));
    }

    @Test
    public void testSetAttribute_3_oe() {
        conf.setProperty("element3[@name]", "bar");

        conf.setProperty("foo[@bar]", "value");

        conf.setProperty("name1", "value1");
        assertEquals("value1", conf.getProperty("name1"));
    }

    @Test
    public void testSetProperty_1_oe() throws Exception {
        conf.setProperty("element.string", "hello");

        assertEquals("'element.string'", "hello", conf.getString("element.string"));
    }

    @Test
    public void testSetProperty_2_oe() throws Exception {
        conf.setProperty("element.string", "hello");

        assertEquals("XML value of element.string", "hello", conf.getProperty("element.string"));
    }

    @Test
    public void testSetPropertyListWithDelimiterParsingDisabled_1_oe() throws ConfigurationException {
        final String prop = "delimiterListProp";
        final List<String> list = Arrays.asList("val", "val2", "val3");
        conf.setProperty(prop, list);
        saveTestConfig();
        final XMLConfiguration conf2 = new XMLConfiguration();
        load(conf2, testSaveConf.getAbsolutePath());
        assertEquals("Wrong list property", list, conf2.getProperty(prop));
    }

    @Test
    public void testSetRootAttribute_1_oe() throws ConfigurationException {
        conf.setProperty("[@test]", "true");
        assertEquals("Root attribute not set", "true", conf.getString("[@test]"));
    }

    @Test
    public void testSetRootAttribute_2_oe() throws ConfigurationException {
        conf.setProperty("[@test]", "true");
        saveTestConfig();
        XMLConfiguration checkConf = checkSavedConfig();
        assertTrue("Attribute not found after save", checkConf.containsKey("[@test]"));
    }

    @Test
    public void testSetRootAttribute_3_oe() throws ConfigurationException {
        conf.setProperty("[@test]", "true");
        saveTestConfig();
        XMLConfiguration checkConf = checkSavedConfig();
        checkConf.setProperty("[@test]", "newValue");
        conf = checkConf;
        saveTestConfig();
        checkConf = checkSavedConfig();
        assertEquals("Attribute not modified after save", "newValue", checkConf.getString("[@test]"));
    }

    @Test
    public void testSetRootNamespace_1_oe() throws ConfigurationException {
        conf.addProperty("[@xmlns:foo]", "http://example.com/");
        conf.addProperty("foo:bar", "foobar");
        assertEquals("Root attribute not set", "http://example.com/", conf.getString("[@xmlns:foo]"));
    }

    @Test
    public void testSetRootNamespace_2_oe() throws ConfigurationException {
        conf.addProperty("[@xmlns:foo]", "http://example.com/");
        conf.addProperty("foo:bar", "foobar");
        saveTestConfig();
        final XMLConfiguration checkConf = checkSavedConfig();
        assertTrue("Attribute not found after save", checkConf.containsKey("[@xmlns:foo]"));
    }

    @Test
    public void testSplitLists_1_oe() {
        assertEquals("a,b,c", conf.getString("split.list3[@values]"));
    }

    @Test
    public void testSplitLists_2_oe() {
        assertEquals(0, conf.getMaxIndex("split.list3[@values]"));
    }

    @Test
    public void testSplitLists_3_oe() {
        assertEquals("a\\,b\\,c", conf.getString("split.list4[@values]"));
    }

    @Test
    public void testSplitLists_4_oe() {
        assertEquals("a", conf.getString("split.list1"));
    }

    @Test
    public void testSplitLists_5_oe() {
        assertEquals(2, conf.getMaxIndex("split.list1"));
    }

    @Test
    public void testSplitLists_6_oe() {
        assertEquals("a,b,c", conf.getString("split.list2"));
    }

    @Test
    public void testSubset_1_oe() throws ConfigurationException {
        conf = new XMLConfiguration();
        load(conf, "testHierarchicalXMLConfiguration.xml");
        conf.subset("tables.table(0)");
        saveTestConfig();

        conf = new XMLConfiguration();
        load(conf, "testHierarchicalXMLConfiguration.xml");
        assertEquals("users", conf.getString("tables.table(0).name"));
    }

    @Test
    public void testSystemIdSynchronized_1_oe() {
        final SynchronizerTestImpl sync = new SynchronizerTestImpl();
        conf.setSynchronizer(sync);
        conf.setSystemID(SYSTEM_ID);
        assertEquals("SystemID not set", SYSTEM_ID, conf.getSystemID());
    }

    @Test
    public void testValidating_1_oe() throws ConfigurationException {
        final File nonValidFile = ConfigurationAssert.getTestFile("testValidateInvalid.xml");
        conf = new XMLConfiguration();
        assertFalse(conf.isValidating());
    }

    @Test
    public void testValidating_2_oe() throws ConfigurationException {
        final File nonValidFile = ConfigurationAssert.getTestFile("testValidateInvalid.xml");
        conf = new XMLConfiguration();

        load(conf, nonValidFile.getAbsolutePath());
        assertEquals("customers", conf.getString("table.name"));
    }

    @Test
    public void testValidating_3_oe() throws ConfigurationException {
        final File nonValidFile = ConfigurationAssert.getTestFile("testValidateInvalid.xml");
        conf = new XMLConfiguration();

        load(conf, nonValidFile.getAbsolutePath());
        assertFalse(conf.containsKey("table.fields.field(1).type"));
    }

    @Test
    public void testWrite_1_oe() throws Exception {
        final XMLConfiguration xmlConfig = new XMLConfiguration();
        xmlConfig.setRootElementName("IAmRoot");
        final StringWriter sw = new StringWriter();
        xmlConfig.write(sw);
        Assert.assertNotNull(parseXml(sw.toString()));
    }

    @Test
    public void testWriteIndentSize_1_oe() throws Exception {
        final XMLConfiguration xmlConfig = new XMLConfiguration();
        xmlConfig.setRootElementName("IAmRoot");
        final StringWriter sw = new StringWriter();
        xmlConfig.setProperty("Child", "Alexander");
        xmlConfig.write(sw);
        final String xml = sw.toString();
        Assert.assertNotNull(parseXml(xml));
    }

    @Test
    public void testWriteIndentSize_2_oe() throws Exception {
        final XMLConfiguration xmlConfig = new XMLConfiguration();
        xmlConfig.setRootElementName("IAmRoot");
        final StringWriter sw = new StringWriter();
        xmlConfig.setProperty("Child", "Alexander");
        xmlConfig.write(sw);
        final String xml = sw.toString();
        final String indent = StringUtils.repeat(' ', XMLConfiguration.DEFAULT_INDENT_SIZE);
        Assert.assertTrue(xml.contains(System.lineSeparator() + indent + "<Child>"));
    }

    @Test
    public void testWriteWithTransformer_1_oe() throws Exception {
        final XMLConfiguration xmlConfig = new XMLConfiguration();
        xmlConfig.setRootElementName("IAmRoot");
        xmlConfig.setProperty("Child", "Alexander");
        final StringWriter sw = new StringWriter();
        final Transformer transformer = xmlConfig.createTransformer();
        final int indentSize = 8;
        transformer.setOutputProperty(XMLConfiguration.INDENT_AMOUNT_PROPERTY, Integer.toString(indentSize));
        xmlConfig.write(sw, transformer);
        final String xml = sw.toString();
        Assert.assertNotNull(parseXml(xml));
    }

    @Test
    public void testWriteWithTransformer_2_oe() throws Exception {
        final XMLConfiguration xmlConfig = new XMLConfiguration();
        xmlConfig.setRootElementName("IAmRoot");
        xmlConfig.setProperty("Child", "Alexander");
        final StringWriter sw = new StringWriter();
        final Transformer transformer = xmlConfig.createTransformer();
        final int indentSize = 8;
        transformer.setOutputProperty(XMLConfiguration.INDENT_AMOUNT_PROPERTY, Integer.toString(indentSize));
        xmlConfig.write(sw, transformer);
        final String xml = sw.toString();
        final String indent = StringUtils.repeat(' ', indentSize);
        Assert.assertTrue(xml.contains(System.lineSeparator() + indent + "<Child>"));
    }

    @Test
    public void testXPathExpressionEngine_1_oe() {
        conf.setExpressionEngine(new XPathExpressionEngine());
        assertEquals("Wrong attribute value", "foo\"bar", conf.getString("test[1]/entity/@name"));
    }

    @Test
    public void testXPathExpressionEngine_2_oe() {
        conf.setExpressionEngine(new XPathExpressionEngine());
        conf.clear();
        assertNull(conf.getString("test[1]/entity/@name"));
    }

}
