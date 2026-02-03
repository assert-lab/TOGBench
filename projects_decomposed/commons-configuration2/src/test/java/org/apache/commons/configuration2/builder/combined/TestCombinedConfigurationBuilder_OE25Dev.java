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
package org.apache.commons.configuration2.builder.combined;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.configuration2.BaseHierarchicalConfiguration;
import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.ConfigurationAssert;
import org.apache.commons.configuration2.ConfigurationDecoder;
import org.apache.commons.configuration2.DynamicCombinedConfiguration;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.XMLPropertiesConfiguration;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.BuilderEventListenerImpl;
import org.apache.commons.configuration2.builder.ConfigurationBuilder;
import org.apache.commons.configuration2.builder.ConfigurationBuilderEvent;
import org.apache.commons.configuration2.builder.CopyObjectDefaultHandler;
import org.apache.commons.configuration2.builder.FileBasedBuilderParametersImpl;
import org.apache.commons.configuration2.builder.FileBasedBuilderProperties;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.PropertiesBuilderParametersImpl;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.XMLBuilderParametersImpl;
import org.apache.commons.configuration2.builder.XMLBuilderProperties;
import org.apache.commons.configuration2.builder.fluent.CombinedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.XMLBuilderParameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.event.ConfigurationEvent;
import org.apache.commons.configuration2.event.Event;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.configuration2.interpol.Lookup;
import org.apache.commons.configuration2.io.DefaultFileSystem;
import org.apache.commons.configuration2.io.FileHandler;
import org.apache.commons.configuration2.io.FileLocatorUtils;
import org.apache.commons.configuration2.io.FileSystem;
import org.apache.commons.configuration2.reloading.ReloadingController;
import org.apache.commons.configuration2.reloading.ReloadingControllerSupport;
import org.apache.commons.configuration2.resolver.CatalogResolver;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.commons.configuration2.tree.xpath.XPathExpressionEngine;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@code CombinedConfigurationBuilder}.
 *
 */
public class TestCombinedConfigurationBuilder_OE25Dev {
    /**
     * A test builder provider implementation for testing whether providers can be defined in the definition file.
     */
    public static class BuilderProviderTestImpl implements ConfigurationBuilderProvider {
        /** The test property key of the configuration to be created. */
        private String propertyKey;

        @Override
        public ConfigurationBuilder<? extends Configuration> getConfigurationBuilder(final ConfigurationDeclaration decl) throws ConfigurationException {
            final BaseHierarchicalConfiguration config = new BaseHierarchicalConfiguration();
            config.addProperty(getPropertyKey(), Boolean.TRUE);
            return new ConstantConfigurationBuilder(config);
        }

        public String getPropertyKey() {
            return propertyKey;
        }

        public void setPropertyKey(final String propertyKey) {
            this.propertyKey = propertyKey;
        }
    }

    /**
     * A test combined configuration class for testing whether a specific result configuration class can be declared in the
     * definition configuration.
     */
    public static class CombinedConfigurationTestImpl extends CombinedConfiguration {
    }

    /**
     * A test builder class which always returns the same configuration.
     */
    private static class ConstantConfigurationBuilder extends BasicConfigurationBuilder<BaseHierarchicalConfiguration> {
        private final BaseHierarchicalConfiguration configuration;

        public ConstantConfigurationBuilder(final BaseHierarchicalConfiguration conf) {
            super(BaseHierarchicalConfiguration.class);
            configuration = conf;
        }

        @Override
        public BaseHierarchicalConfiguration getConfiguration() throws ConfigurationException {
            return configuration;
        }
    }

    /**
     * A specialized entity resolver implementation for testing whether properties of a catalog resolver are correctly set.
     */
    public static class EntityResolverWithPropertiesTestImpl extends CatalogResolver {
        /** The base directory. */
        private String baseDirectory;

        /** The file system. */
        private FileSystem fileSystem;

        /** The ConfigurationInterpolator. */
        private ConfigurationInterpolator interpolator;

        public String getBaseDir() {
            return baseDirectory;
        }

        public FileSystem getFileSystem() {
            return fileSystem;
        }

        public ConfigurationInterpolator getInterpolator() {
            return interpolator;
        }

        @Override
        public void setBaseDir(final String baseDir) {
            super.setBaseDir(baseDir);
            baseDirectory = baseDir;
        }

        @Override
        public void setFileSystem(final FileSystem fileSystem) {
            super.setFileSystem(fileSystem);
            this.fileSystem = fileSystem;
        }

        @Override
        public void setInterpolator(final ConfigurationInterpolator interpolator) {
            super.setInterpolator(interpolator);
            this.interpolator = interpolator;
        }
    }

    /**
     * A test file system implementation for testing whether a custom file system class can be specified in the
     * configuration definition file.
     */
    public static class FileSystemTestImpl extends DefaultFileSystem {
    }

    /**
     * A thread class for testing concurrent read access to a newly created configuration.
     */
    private static class ReadThread extends Thread {
        /** The configuration to access. */
        private final CombinedConfiguration config;

        /** The start latch. */
        private final CountDownLatch startLatch;

        /** The value read from the configuration. */
        private Boolean value;

        public ReadThread(final CombinedConfiguration cc, final CountDownLatch latch) {
            config = cc;
            startLatch = latch;
        }

        @Override
        public void run() {
            try {
                startLatch.await();
                value = config.getBoolean("configuration.loaded");
            } catch (final InterruptedException iex) {
                // ignore
            }
        }

        /**
         * Verifies that the correct value was read.
         */
        public void verify() {
            try {
                join();
            } catch (final InterruptedException iex) {
                fail("Waiting was interrupted: " + iex);
            }
            assertEquals("Wrong value read", Boolean.TRUE, value);
        }
    }

    /**
     * A custom Lookup implementation for testing whether lookups can be defined in the definition configuration. This
     * lookup supports some variables referencing test files.
     */
    public static class TestLookup implements Lookup {
        private final Map<String, String> map = new HashMap<>();

        public TestLookup() {
            map.put("test_file_xml", "test.xml");
            map.put("test_file_combine", "testcombine1.xml");
            map.put("test_key", "test.value");
        }

        @Override
        public String lookup(final String key) {
            return map.get(key);
        }
    }

    /** Test configuration definition file. */
    private static final File TEST_FILE = ConfigurationAssert.getTestFile("testDigesterConfiguration.xml");

    /** Test file name for a sub configuration. */
    private static final String TEST_SUB_XML = "test.xml";

    /** Constant for a named builder. */
    private static final String BUILDER_NAME = "subBuilderName";

    /**
     * The name of the system property for selecting a file managed by a MultiFileConfigurationBuilder.
     */
    private static final String MULTI_FILE_PROPERTY = "Id";

    /**
     * Helper method for testing the attributes of a combined configuration created by the builder.
     *
     * @param cc the configuration to be checked
     */
    private static void checkCombinedConfigAttrs(final CombinedConfiguration cc) {
        final ListDelimiterHandler handler = cc.getListDelimiterHandler();
        assertTrue("Wrong delimiter handler: " + handler, handler instanceof DefaultListDelimiterHandler);
        assertEquals("Wrong list delimiter character", ',', ((DefaultListDelimiterHandler) handler).getDelimiter());
    }

    /**
     * Creates a configuration builder for the definition configuration which always returns the passed in definition
     * configuration.
     *
     * @param defConfig the definition configuration
     * @return the definition builder
     */
    protected static BasicConfigurationBuilder<? extends BaseHierarchicalConfiguration> createDefinitionBuilder(final BaseHierarchicalConfiguration defConfig) {
        return new ConstantConfigurationBuilder(defConfig);
    }

    /**
     * Convenience method for creating a definition configuration. This method creates a configuration containing a tag
     * referring to a configuration source. The tag has attributes defined by the given map.
     *
     * @param tag the name of the tag to create
     * @param attrs the attributes of this tag
     * @return the definition configuration
     */
    protected static BaseHierarchicalConfiguration createDefinitionConfig(final String tag, final Map<String, Object> attrs) {
        final BaseHierarchicalConfiguration defConfig = new BaseHierarchicalConfiguration();
        final String prefix = "override." + tag;
        for (final Map.Entry<String, Object> e : attrs.entrySet()) {
            defConfig.addProperty(prefix + "[@" + e.getKey() + "]", e.getValue());
        }
        return defConfig;
    }

    /**
     * Prepares a parameters object for a test for properties inheritance.
     *
     * @param params the {@code Parameters} object
     * @return the builder parameters
     */
    private static XMLBuilderParameters prepareParamsForInheritanceTest(final Parameters params) {
        final DefaultExpressionEngineSymbols symbols = new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS)
            .setPropertyDelimiter("/").create();
        final DefaultExpressionEngine engine = new DefaultExpressionEngine(symbols);
        final DefaultListDelimiterHandler listDelimiterHandler = new DefaultListDelimiterHandler(',');
        return params.xml().setExpressionEngine(engine).setListDelimiterHandler(listDelimiterHandler).setFile(TEST_FILE);
    }

    /**
     * Sets the system property which selects a specific file managed by a MultiFileConfigurationBuilder.
     *
     * @param key the key to select the desired file
     */
    private static void switchToMultiFile(final String key) {
        System.setProperty(MULTI_FILE_PROPERTY, key);
    }

    /** A helper object for creating builder parameters. */
    protected Parameters parameters;

    /** Stores the object to be tested. */
    protected CombinedConfigurationBuilder builder;

    /**
     * Tests if the configuration was correctly created by the builder.
     *
     * @return the combined configuration obtained from the builder
     */
    private CombinedConfiguration checkConfiguration() throws ConfigurationException {
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();

        assertEquals("Number of configurations", 3, compositeConfiguration.getNumberOfConfigurations());
        assertEquals(PropertiesConfiguration.class, compositeConfiguration.getConfiguration(0).getClass());
        assertEquals(XMLPropertiesConfiguration.class, compositeConfiguration.getConfiguration(1).getClass());
        assertEquals(XMLConfiguration.class, compositeConfiguration.getConfiguration(2).getClass());

        // check the first configuration
        final PropertiesConfiguration pc = (PropertiesConfiguration) compositeConfiguration.getConfiguration(0);
        assertNotNull("No properties configuration", pc);

        // check some properties
        checkProperties(compositeConfiguration);
        return compositeConfiguration;
    }

    /**
     * Helper method for testing whether the file system can be customized in the configuration definition file.
     *
     * @param fsFile the file to be processed
     * @throws ConfigurationException if an error occurs
     */
    private void checkFileSystem(final File fsFile) throws ConfigurationException {
        builder.configure(createParameters().setFile(fsFile));
        builder.getConfiguration();
        final FileBasedConfigurationBuilder<? extends Configuration> xmlBuilder = (FileBasedConfigurationBuilder<? extends Configuration>) builder
            .getNamedBuilder("xml");
        assertTrue("Wrong file system: " + xmlBuilder.getFileHandler().getFileSystem(),xmlBuilder.getFileHandler().getFileSystem()instanceof FileSystemTestImpl);
    }

    /**
     * Helper method for testing whether properties of a MultiFileConfiguration can be obtained.
     *
     * @param key the key of the file to be accessed
     * @param config the configuration to check
     * @param rows the expected value of the test property
     */
    private void checkMultiFile(final String key, final CombinedConfiguration config, final int rows) {
        switchToMultiFile(key);
        assertEquals("Wrong property value", rows, config.getInt("rowsPerPage"));
    }

    /**
     * Checks if the passed in configuration contains the expected properties.
     *
     * @param compositeConfiguration the configuration to check
     */
    private void checkProperties(final Configuration compositeConfiguration) {
        assertTrue("Make sure we have loaded our key", compositeConfiguration.getBoolean("test.boolean"));
        assertEquals("I'm complex!", compositeConfiguration.getProperty("element2.subelement.subsubelement"));
        assertEquals("property in the XMLPropertiesConfiguration", "value1", compositeConfiguration.getProperty("key1"));
    }

    /**
     * Loads a test file which includes a MultiFileConfigurationBuilder declaration and returns the resulting configuration.
     *
     * @param fileName the name of the file to be loaded
     * @return the resulting combined configuration
     * @throws ConfigurationException if an error occurs
     */
    private CombinedConfiguration createMultiFileConfig(final String fileName) throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile(fileName);
        builder.configure(createParameters().setFile(testFile));
        final CombinedConfiguration config = builder.getConfiguration();
        assertTrue("Incorrect result configuration", config instanceof DynamicCombinedConfiguration);
        return config;
    }

    /**
     * Creates an object with parameters for defining the file to be loaded.
     *
     * @return the parameters object
     */
    protected FileBasedBuilderParameters createParameters() {
        return parameters.fileBased();
    }

    /**
     * Prepares a test with a combined configuration that uses a single sub builder. This method adds some default
     * attributes to the given map, creates the corresponding definition builder and configures the combined builder.
     *
     * @param attrs the map with attributes
     * @return the definition builder
     */
    private BasicConfigurationBuilder<? extends HierarchicalConfiguration<ImmutableNode>> prepareSubBuilderTest(final Map<String, Object> attrs) {
        attrs.put("fileName", TEST_SUB_XML);
        attrs.put("config-name", BUILDER_NAME);
        final BaseHierarchicalConfiguration defConfig = createDefinitionConfig("xml", attrs);
        final BasicConfigurationBuilder<? extends HierarchicalConfiguration<ImmutableNode>> defBuilder = createDefinitionBuilder(defConfig);
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilder(defBuilder));
        return defBuilder;
    }

    @Before
    public void setUp() throws Exception {
        System.setProperty("java.naming.factory.initial", "org.apache.commons.configuration2.MockInitialContextFactory");
        System.setProperty("test_file_xml", TEST_SUB_XML);
        System.setProperty("test_file_combine", "testcombine1.xml");
        parameters = new Parameters();
        builder = new CombinedConfigurationBuilder();
    }

    @After
    public void tearDown() throws Exception {
        System.getProperties().remove(MULTI_FILE_PROPERTY);
    }

    /**
     * Tests if the base path is correctly evaluated.
     */

    /**
     * Tests whether the names of sub builders can be queried.
     */

    /**
     * Tests the behavior of builderNames() before the result configuration has been created.
     */

    /**
     * Tests that the collection with builder names cannot be manipulated.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testBuilderNamesManipulate() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        builder.getConfiguration();
        final Set<String> names = builder.builderNames();
        names.add(BUILDER_NAME);
    }

    /**
     * Tests that child configuration builders are not initialized multiple times. This test is releated to
     * CONFIGURATION-687.
     */

    /**
     * Tests whether attributes are correctly set on the combined configurations for the override and additional sections.
     */
    @Test
    public void testCombinedConfigurationAttributes() throws ConfigurationException {
        final File initFile = ConfigurationAssert.getTestFile("testCCResultInitialization.xml");
        builder.configure(createParameters().setFile(initFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        checkCombinedConfigAttrs(cc);
        final CombinedConfiguration cc2 = (CombinedConfiguration) cc.getConfiguration(CombinedConfigurationBuilder.ADDITIONAL_NAME);
        checkCombinedConfigAttrs(cc2);
    }

    /**
     * Tests whether the list node definition was correctly processed.
     */

    /**
     * Tests the structure of the returned combined configuration if there is no additional section.
     */

    /**
     * Tests whether a newly created instance can be read concurrently without a special synchronizer.
     */
    @Test
    public void testConcurrentReadAccessWithoutSynchronizer() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        final CombinedConfiguration config = builder.getConfiguration();
        final int threadCount = 32;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final ReadThread[] threads = new ReadThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new ReadThread(config, startLatch);
            threads[i].start();
        }

        startLatch.countDown();
        for (final ReadThread t : threads) {
            t.verify();
        }
    }

    /**
     * Tests whether a configuration builder can itself be declared in a configuration definition file.
     */

    /**
     * Tests whether the base path can be inherited to child combined configuration builders.
     */

    /**
     * Tests whether basic properties defined for the combined configuration are inherited by a child combined configuration
     * builder.
     */

    /**
     * Tests whether custom builder providers are inherited to child combined configuration builder providers.
     */

    /**
     * Tests whether a child configuration builder inherits the event listeners from its parent.
     */

    /**
     * Tests whether the entity resolver is initialized with other XML-related properties.
     */

    /**
     * Tests that the return value of configure() is overloaded.
     */

    /**
     * Tests whether a custom provider can be registered.
     */

    /**
     * Tests whether an entity resolver can be defined in the definition file.
     */
    @Test
    public void testCustomEntityResolver() throws ConfigurationException {
        final File resolverFile = ConfigurationAssert.getTestFile("testCCEntityResolver.xml");
        builder.configure(createParameters().setFile(resolverFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        final XMLConfiguration xmlConf = (XMLConfiguration) cc.getConfiguration("xml");
        final EntityResolverWithPropertiesTestImpl resolver = (EntityResolverWithPropertiesTestImpl) xmlConf.getEntityResolver();
        assertFalse("No lookups", resolver.getInterpolator().getLookups().isEmpty());
    }

    /**
     * Tests whether a default file system can be configured in the definition file.
     */
    @Test
    public void testCustomFileSystem() throws ConfigurationException {
        checkFileSystem(ConfigurationAssert.getTestFile("testCCFileSystem.xml"));
    }

    /**
     * Tests whether a specific file system can be applied to a sub configuration.
     */
    @Test
    public void testCustomFileSystemForSubConfig() throws ConfigurationException {
        checkFileSystem(ConfigurationAssert.getTestFile("testCCFileSystemSubConfig.xml"));
    }

    /**
     * Tests whether a Lookup object can be declared in the definition configuration.
     */

    /**
     * Tests whether the resulting combined configuration can be customized.
     */

    /**
     * Tests whether the default base path for file-based configurations is derived from the configuration definition
     * builder.
     */

    /**
     * Tests whether a default base path for all file-based child configurations can be set in the builder parameters.
     */

    /**
     * Tests whether environment properties can be added as a configuration source.
     */

    /**
     * Tests whether all child builders can be obtained.
     */

    /**
     * Tests whether named builders can be accessed.
     */

    /**
     * Tries to query a named builder before the result configuration has been created.
     */
    @Test(expected = ConfigurationException.class)
    public void testGetNamedBuilderBeforeConfigurationAccess() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        builder.getNamedBuilder("nonExistingBuilder");
    }

    /**
     * Tries to query a non-existing builder by name.
     */
    @Test(expected = ConfigurationException.class)
    public void testGetNamedBuilderUnknown() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        builder.getConfiguration();
        builder.getNamedBuilder("nonExistingBuilder");
    }

    /**
     * Tests whether builder properties can be inherited by child builders.
     */

    /**
     * Tests whether an INI configuration source can be added to the combined configuration.
     */

    /**
     * Tests whether default child properties in the combined builder's configuration are inherited by child parameter
     * objects.
     */

    /**
     * Tests whether variable substitution works across multiple child configurations and also in the definition
     * configuration.
     */

    /**
     * Tests whether a JNDI configuration can be integrated into the combined configuration.
     */

    /**
     * Tests loading a configuration definition file with an additional section.
     */

    /**
     * Tests loading a simple configuration definition file.
     */
    @Test
    public void testLoadConfiguration() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        checkConfiguration();
    }

    /**
     * Tests loading a definition file that contains optional configurations.
     */

    /**
     * Tests whether the force-create attribute is taken into account.
     */

    /**
     * Tests loading a definition file with optional and non optional configuration sources. One non optional does not
     * exist, so this should cause an exception.
     */
    @Test(expected = ConfigurationException.class)
    public void testLoadOptionalWithException() throws ConfigurationException {
        final File optionalExFile = ConfigurationAssert.getTestFile("testDigesterOptionalConfigurationEx.xml");
        builder.configure(createParameters().setFile(optionalExFile));
        builder.getConfiguration();
    }

    /**
     * Tests whether a MultiFileConfigurationBuilder can be integrated into a combined configuration definition.
     */
    @Test
    public void testMultiTenentConfiguration() throws ConfigurationException {
        final CombinedConfiguration config = createMultiFileConfig("testCCMultiTenent.xml");
        checkMultiFile("1001", config, 15);
        checkMultiFile("1002", config, 25);
        checkMultiFile("1003", config, 35);
        checkMultiFile("1004", config, 50);
    }

    /**
     * Tests whether a configuration created by a MultiFileConfigurationBuilder has been initialized correctly.
     */

    /**
     * Tests whether reloading support works for MultiFileConfigurationBuilder.
     */

    /**
     * Tries to build a configuration if no definition builder is provided.
     */
    @Test(expected = ConfigurationException.class)
    public void testNoDefinitionBuilder() throws ConfigurationException {
        builder.getConfiguration();
    }

    /**
     * Tests whether a custom provider can be defined in the definition file.
     */

    /**
     * Tests whether a reset of one of the sub builders causes the combined configuration to be re-created.
     */

    /**
     * Tests whether a reloading sub builder can be created.
     */

    /**
     * Tests that change listeners registered at sub builders are removed on a reset.
     */

    /**
     * Tests a reset of the builder. The configuration instance should be created anew.
     */

    /**
     * Tests that the combined configuration has been fully constructed (including its root node) when it is returned from
     * the builder.
     */

    /**
     * Tests whether the inheritance of builder properties can be disabled.
     */

    /**
     * Tests whether a file with system properties can be specified in the configuration definition file and that system
     * properties can be added to the resulting configuration.
     */

    @Test
    public void testBasePathForChildConfigurations_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration defConfig = new BaseHierarchicalConfiguration();
        defConfig.addProperty("properties[@fileName]", "test.properties");
        final File deepDir = new File(ConfigurationAssert.TEST_DIR, "config/deep");
        builder.configure(
            new CombinedBuilderParametersImpl().setBasePath(deepDir.getAbsolutePath()).setDefinitionBuilder(new ConstantConfigurationBuilder(defConfig)));
        final CombinedConfiguration config = builder.getConfiguration();
        assertEquals("Wrong property value", "somevalue", config.getString("somekey"));
    }

    @Test
    public void testBuilderNames_1_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        builder.getConfiguration();
        final Set<String> names = builder.builderNames();
        final List<String> expected = Arrays.asList("props", "xml");
        assertEquals("Wrong number of named builders", expected.size(), names.size());
    }

    @Test
    public void testBuilderNames_2_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        builder.getConfiguration();
        final Set<String> names = builder.builderNames();
        final List<String> expected = Arrays.asList("props", "xml");
        // removed other assertion
        assertTrue("Wrong builder names: " + names, names.containsAll(expected));
    }

    @Test
    public void testBuilderNamesBeforeConfigurationAccess_1_oe() {
        assertTrue("Got builders (1)", builder.builderNames().isEmpty());
    }

    @Test
    public void testBuilderNamesBeforeConfigurationAccess_2_oe() {
        // removed other assertion
        builder.configure(createParameters().setFile(TEST_FILE));
        assertTrue("Got builders (2)", builder.builderNames().isEmpty());
    }

    @Test
    public void testChildBuildersAreInitializedOnlyOnce_1_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        builder.getConfiguration();
        builder.resetResult();
        builder.getConfiguration();
        final Collection<ConfigurationBuilder<? extends Configuration>> childBuilders = builder.getChildBuilders();
        assertEquals("Wrong number of child builders", 3, childBuilders.size());
    }

    @Test
    public void testCombinedConfigurationListNodes_1_oe() throws ConfigurationException {
        final File initFile = ConfigurationAssert.getTestFile("testCCResultInitialization.xml");
        builder.configure(createParameters().setFile(initFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        Set<String> listNodes = cc.getNodeCombiner().getListNodes();
        assertEquals("Wrong number of list nodes", 2, listNodes.size());
    }

    @Test
    public void testCombinedConfigurationListNodes_2_oe() throws ConfigurationException {
        final File initFile = ConfigurationAssert.getTestFile("testCCResultInitialization.xml");
        builder.configure(createParameters().setFile(initFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        Set<String> listNodes = cc.getNodeCombiner().getListNodes();
        // removed other assertion
        assertTrue("table node not a list node", listNodes.contains("table"));
    }

    @Test
    public void testCombinedConfigurationListNodes_3_oe() throws ConfigurationException {
        final File initFile = ConfigurationAssert.getTestFile("testCCResultInitialization.xml");
        builder.configure(createParameters().setFile(initFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        Set<String> listNodes = cc.getNodeCombiner().getListNodes();
        // removed other assertion
        // removed other assertion
        assertTrue("list node not a list node", listNodes.contains("list"));
    }

    @Test
    public void testCombinedConfigurationListNodes_4_oe() throws ConfigurationException {
        final File initFile = ConfigurationAssert.getTestFile("testCCResultInitialization.xml");
        builder.configure(createParameters().setFile(initFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        Set<String> listNodes = cc.getNodeCombiner().getListNodes();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final CombinedConfiguration cca = (CombinedConfiguration) cc.getConfiguration(CombinedConfigurationBuilder.ADDITIONAL_NAME);
        listNodes = cca.getNodeCombiner().getListNodes();
        assertTrue("Found list nodes for additional combiner", listNodes.isEmpty());
    }

    @Test
    public void testCombinedConfigurationNoAdditional_1_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertNull("Additional configuration was found", cc.getConfiguration(CombinedConfigurationBuilder.ADDITIONAL_NAME));
    }

    @Test
    public void testConfigurationBuilderProvider_1_oe() throws ConfigurationException {
        final BaseHierarchicalConfiguration defConfig = new BaseHierarchicalConfiguration();
        defConfig.addProperty("override.configuration[@fileName]", TEST_FILE.getAbsolutePath());
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilder(new ConstantConfigurationBuilder(defConfig)));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertEquals("Wrong number of configurations", 1, cc.getNumberOfConfigurations());
    }

    @Test
    public void testConfigurationBuilderProviderInheritBasePath_1_oe() throws ConfigurationException {
        final File envFile = ConfigurationAssert.getTestFile("testCCEnvProperties.xml");
        final String basePath = ConfigurationAssert.OUT_DIR.getAbsolutePath();
        builder.configure(new CombinedBuilderParametersImpl().setBasePath(basePath).setDefinitionBuilderParameters(createParameters().setFile(envFile)));
        builder.getConfiguration();
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        builder.initChildBuilderParameters(params);
        assertEquals("Base path not set", basePath, params.getBasePath());
    }

    @Test
    public void testConfigurationBuilderProviderInheritBasicProperties_1_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCCombinedChildBuilder.xml");
        final ListDelimiterHandler listHandler = new DefaultListDelimiterHandler('*');
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilderParameters(new XMLBuilderParametersImpl().setFile(testFile))
            .setListDelimiterHandler(listHandler).setConfigurationDecoder(decoder));
        final CombinedConfiguration cc = builder.getConfiguration();
        final CombinedConfiguration cc2 = (CombinedConfiguration) cc.getConfiguration("subcc");
        assertFalse("Wrong exception flag", cc2.isThrowExceptionOnMissing());
    }

    @Test
    public void testConfigurationBuilderProviderInheritBasicProperties_2_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCCombinedChildBuilder.xml");
        final ListDelimiterHandler listHandler = new DefaultListDelimiterHandler('*');
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilderParameters(new XMLBuilderParametersImpl().setFile(testFile))
            .setListDelimiterHandler(listHandler).setConfigurationDecoder(decoder));
        final CombinedConfiguration cc = builder.getConfiguration();
        final CombinedConfiguration cc2 = (CombinedConfiguration) cc.getConfiguration("subcc");
        // removed other assertion
        assertEquals("Wrong list delimiter handler", listHandler, cc2.getListDelimiterHandler());
    }

    @Test
    public void testConfigurationBuilderProviderInheritBasicProperties_3_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCCombinedChildBuilder.xml");
        final ListDelimiterHandler listHandler = new DefaultListDelimiterHandler('*');
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilderParameters(new XMLBuilderParametersImpl().setFile(testFile))
            .setListDelimiterHandler(listHandler).setConfigurationDecoder(decoder));
        final CombinedConfiguration cc = builder.getConfiguration();
        final CombinedConfiguration cc2 = (CombinedConfiguration) cc.getConfiguration("subcc");
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong decoder", decoder, cc2.getConfigurationDecoder());
    }

    @Test
    public void testConfigurationBuilderProviderInheritCustomProviders_1_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(ConfigurationAssert.getTestFile("testCCCustomProvider.xml")));
        builder.getConfiguration();
        final CombinedBuilderParametersImpl ccparams = new CombinedBuilderParametersImpl();
        builder.initChildBuilderParameters(ccparams);
        assertNotNull("Custom provider not found", ccparams.providerForTag("test"));
    }

    @Test
    public void testConfigurationBuilderProviderInheritEventListeners_1_oe() throws ConfigurationException {
        final EventListener<Event> l1 = EasyMock.createNiceMock(EventListener.class);
        final EventListener<ConfigurationEvent> l2 = EasyMock.createNiceMock(EventListener.class);
        EasyMock.replay(l1, l2);
        final File testFile = ConfigurationAssert.getTestFile("testCCCombinedChildBuilder.xml");
        builder.configure(new XMLBuilderParametersImpl().setFile(testFile));
        builder.addEventListener(Event.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY, l2);
        final CombinedConfiguration cc = builder.getConfiguration();
        final CombinedConfiguration cc2 = (CombinedConfiguration) cc.getConfiguration("subcc");
        final Collection<EventListener<? super ConfigurationEvent>> listeners = cc2.getEventListeners(ConfigurationEvent.ANY);
        assertTrue("Listener 1 not found", listeners.contains(l1));
    }

    @Test
    public void testConfigurationBuilderProviderInheritEventListeners_2_oe() throws ConfigurationException {
        final EventListener<Event> l1 = EasyMock.createNiceMock(EventListener.class);
        final EventListener<ConfigurationEvent> l2 = EasyMock.createNiceMock(EventListener.class);
        EasyMock.replay(l1, l2);
        final File testFile = ConfigurationAssert.getTestFile("testCCCombinedChildBuilder.xml");
        builder.configure(new XMLBuilderParametersImpl().setFile(testFile));
        builder.addEventListener(Event.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY, l2);
        final CombinedConfiguration cc = builder.getConfiguration();
        final CombinedConfiguration cc2 = (CombinedConfiguration) cc.getConfiguration("subcc");
        final Collection<EventListener<? super ConfigurationEvent>> listeners = cc2.getEventListeners(ConfigurationEvent.ANY);
        // removed other assertion
        assertTrue("Listener 2 not found", listeners.contains(l2));
    }

    @Test
    public void testConfigurationBuilderProviderInheritEventListeners_3_oe() throws ConfigurationException {
        final EventListener<Event> l1 = EasyMock.createNiceMock(EventListener.class);
        final EventListener<ConfigurationEvent> l2 = EasyMock.createNiceMock(EventListener.class);
        EasyMock.replay(l1, l2);
        final File testFile = ConfigurationAssert.getTestFile("testCCCombinedChildBuilder.xml");
        builder.configure(new XMLBuilderParametersImpl().setFile(testFile));
        builder.addEventListener(Event.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY, l2);
        final CombinedConfiguration cc = builder.getConfiguration();
        final CombinedConfiguration cc2 = (CombinedConfiguration) cc.getConfiguration("subcc");
        final Collection<EventListener<? super ConfigurationEvent>> listeners = cc2.getEventListeners(ConfigurationEvent.ANY);
        // removed other assertion
        // removed other assertion
        final Collection<EventListener<? super Event>> eventListeners = cc2.getEventListeners(Event.ANY);
        assertEquals("Wrong number of event listeners", 1, eventListeners.size());
    }

    @Test
    public void testConfigurationBuilderProviderInheritEventListeners_4_oe() throws ConfigurationException {
        final EventListener<Event> l1 = EasyMock.createNiceMock(EventListener.class);
        final EventListener<ConfigurationEvent> l2 = EasyMock.createNiceMock(EventListener.class);
        EasyMock.replay(l1, l2);
        final File testFile = ConfigurationAssert.getTestFile("testCCCombinedChildBuilder.xml");
        builder.configure(new XMLBuilderParametersImpl().setFile(testFile));
        builder.addEventListener(Event.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY, l2);
        final CombinedConfiguration cc = builder.getConfiguration();
        final CombinedConfiguration cc2 = (CombinedConfiguration) cc.getConfiguration("subcc");
        final Collection<EventListener<? super ConfigurationEvent>> listeners = cc2.getEventListeners(ConfigurationEvent.ANY);
        // removed other assertion
        // removed other assertion
        final Collection<EventListener<? super Event>> eventListeners = cc2.getEventListeners(Event.ANY);
        // removed other assertion
        assertTrue("Wrong listener", eventListeners.contains(l1));
    }

    @Test
    public void testConfigureEntityResolverWithProperties_1_oe() throws ConfigurationException {
        final HierarchicalConfiguration<ImmutableNode> config = new BaseHierarchicalConfiguration();
        config.addProperty("header.entity-resolver[@config-class]", EntityResolverWithPropertiesTestImpl.class.getName());
        final XMLBuilderParametersImpl xmlParams = new XMLBuilderParametersImpl();
        final FileSystem fs = EasyMock.createMock(FileSystem.class);
        final String baseDir = ConfigurationAssert.OUT_DIR_NAME;
        xmlParams.setBasePath(baseDir);
        xmlParams.setFileSystem(fs);
        builder.configureEntityResolver(config, xmlParams);
        final EntityResolverWithPropertiesTestImpl resolver = (EntityResolverWithPropertiesTestImpl) xmlParams.getEntityResolver();
        assertSame("File system not set", fs, resolver.getFileSystem());
    }

    @Test
    public void testConfigureEntityResolverWithProperties_2_oe() throws ConfigurationException {
        final HierarchicalConfiguration<ImmutableNode> config = new BaseHierarchicalConfiguration();
        config.addProperty("header.entity-resolver[@config-class]", EntityResolverWithPropertiesTestImpl.class.getName());
        final XMLBuilderParametersImpl xmlParams = new XMLBuilderParametersImpl();
        final FileSystem fs = EasyMock.createMock(FileSystem.class);
        final String baseDir = ConfigurationAssert.OUT_DIR_NAME;
        xmlParams.setBasePath(baseDir);
        xmlParams.setFileSystem(fs);
        builder.configureEntityResolver(config, xmlParams);
        final EntityResolverWithPropertiesTestImpl resolver = (EntityResolverWithPropertiesTestImpl) xmlParams.getEntityResolver();
        // removed other assertion
        assertSame("Base directory not set", baseDir, resolver.getBaseDir());
    }

    @Test
    public void testConfigureResult_1_oe() {
        final CombinedConfigurationBuilder configuredBuilder = builder.configure(createParameters().setFile(TEST_FILE));
        assertSame("Wrong instance returned", builder, configuredBuilder);
    }

    @Test
    public void testCustomBuilderProvider_1_oe() throws ConfigurationException {
        final String tagName = "myTestTag";
        final BaseHierarchicalConfiguration dataConf = new BaseHierarchicalConfiguration();
        dataConf.addProperty(tagName, Boolean.TRUE);
        final Map<String, Object> attrs = new HashMap<>();
        attrs.put("config-name", BUILDER_NAME);
        attrs.put("config-at", "tests");
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilder(createDefinitionBuilder(createDefinitionConfig(tagName, attrs)))
            .registerProvider(tagName, decl -> new ConstantConfigurationBuilder(dataConf)));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertEquals("Configuration not added", dataConf, cc.getConfiguration(BUILDER_NAME));
    }

    @Test
    public void testCustomBuilderProvider_2_oe() throws ConfigurationException {
        final String tagName = "myTestTag";
        final BaseHierarchicalConfiguration dataConf = new BaseHierarchicalConfiguration();
        dataConf.addProperty(tagName, Boolean.TRUE);
        final Map<String, Object> attrs = new HashMap<>();
        attrs.put("config-name", BUILDER_NAME);
        attrs.put("config-at", "tests");
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilder(createDefinitionBuilder(createDefinitionConfig(tagName, attrs)))
            .registerProvider(tagName, decl -> new ConstantConfigurationBuilder(dataConf)));
        final CombinedConfiguration cc = builder.getConfiguration();
        // removed other assertion
        assertEquals("Property not set", Boolean.TRUE, cc.getProperty("tests." + tagName));
    }

    @Test
    public void testCustomLookup_1_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCLookup.xml");
        builder.configure(createParameters().setFile(testFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertTrue("Lookup not registered in CC", cc.getInterpolator().getLookups().containsKey("test"));
    }

    @Test
    public void testCustomLookup_2_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCLookup.xml");
        builder.configure(createParameters().setFile(testFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        // removed other assertion
        final Configuration xmlConf = cc.getConfiguration("xml");
        assertTrue("Lookup not registered in sub config", xmlConf.getInterpolator().getLookups().containsKey("test"));
    }

    @Test
    public void testCustomResultConfiguration_2_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCResultClass.xml");
        final ListDelimiterHandler listHandler = new DefaultListDelimiterHandler('.');
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilderParameters(new XMLBuilderParametersImpl().setFile(testFile))
            .setListDelimiterHandler(listHandler).setThrowExceptionOnMissing(false));
        final CombinedConfiguration cc = builder.getConfiguration();
        // removed other assertion
        assertTrue("Wrong exception flag", cc.isThrowExceptionOnMissing());
    }

    @Test
    public void testCustomResultConfiguration_3_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCResultClass.xml");
        final ListDelimiterHandler listHandler = new DefaultListDelimiterHandler('.');
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilderParameters(new XMLBuilderParametersImpl().setFile(testFile))
            .setListDelimiterHandler(listHandler).setThrowExceptionOnMissing(false));
        final CombinedConfiguration cc = builder.getConfiguration();
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong list delimiter handler", listHandler, cc.getListDelimiterHandler());
    }

    @Test
    public void testDefaultBasePathFromDefinitionBuilder_1_oe() throws ConfigurationException, IOException {
        final String testFile = "testCCSystemProperties.xml";
        builder.configure(new CombinedBuilderParametersImpl()
            .setDefinitionBuilderParameters(createParameters().setBasePath(ConfigurationAssert.TEST_DIR.getAbsolutePath()).setFileName(testFile)));
        builder.getConfiguration();
        final XMLBuilderParametersImpl xmlParams = new XMLBuilderParametersImpl();
        builder.initChildBuilderParameters(xmlParams);
        final File basePathFile = FileLocatorUtils.fileFromURL(new URL(xmlParams.getFileHandler().getBasePath()));
        assertEquals("Wrong base path", ConfigurationAssert.getTestFile(testFile).getAbsoluteFile(), basePathFile);
    }

    @Test
    public void testDefaultBasePathInParameters_1_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testCCSystemProperties.xml");
        final String basePath = ConfigurationAssert.OUT_DIR.getAbsolutePath();
        builder.configure(new CombinedBuilderParametersImpl().setBasePath(basePath).setDefinitionBuilderParameters(createParameters().setFile(testFile)));
        builder.getConfiguration();
        final XMLBuilderParametersImpl xmlParams = new XMLBuilderParametersImpl();
        builder.initChildBuilderParameters(xmlParams);
        assertEquals("Base path not set", basePath, xmlParams.getFileHandler().getBasePath());
    }

    @Test
    public void testEnvironmentProperties_1_oe() throws ConfigurationException {
        final File envFile = ConfigurationAssert.getTestFile("testCCEnvProperties.xml");
        builder.configure(createParameters().setFile(envFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertFalse("Configuration is empty", cc.isEmpty());
    }

    @Test
    public void testEnvironmentProperties_2_oe() throws ConfigurationException {
        final File envFile = ConfigurationAssert.getTestFile("testCCEnvProperties.xml");
        builder.configure(createParameters().setFile(envFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        // removed other assertion

        // The environment may contain settings with values that
        // are altered by interpolation. Disable this for direct access
        // to the String associated with the environment property name.
        cc.setInterpolator(null);

        // Test the environment is available through the configuration
        for (final Map.Entry<String, String> e : System.getenv().entrySet()) {
            assertEquals("Wrong value for property: " + e.getKey(), e.getValue(), cc.getString(e.getKey()));
    }
    }

    @Test
    public void testGetChildBuilders_1_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        builder.getConfiguration();
        final Collection<ConfigurationBuilder<? extends Configuration>> childBuilders = builder.getChildBuilders();
        assertEquals("Wrong number of child builders", 3, childBuilders.size());
    }

    @Test
    public void testGetNamedBuilder_1_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        builder.getConfiguration();
        final ConfigurationBuilder<? extends Configuration> propBuilder = builder.getNamedBuilder("props");
        assertTrue("Wrong builder class", propBuilder instanceof FileBasedConfigurationBuilder);
    }

    @Test
    public void testGetNamedBuilder_2_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        builder.getConfiguration();
        final ConfigurationBuilder<? extends Configuration> propBuilder = builder.getNamedBuilder("props");
        // removed other assertion
        assertTrue("Wrong sub configuration", propBuilder.getConfiguration() instanceof PropertiesConfiguration);
    }

    @Test
    public void testInheritProperties_1_oe() throws ConfigurationException {
        final Parameters params = new Parameters();
        final XMLBuilderParameters xmlParams = prepareParamsForInheritanceTest(params);
        builder.configure(xmlParams);
        final CombinedConfiguration config = builder.getConfiguration();

        List<String> list = config.getList(String.class, "test/mixed/array");
        assertTrue("Wrong number of elements in list", list.size() > 2);
    }

    @Test
    public void testInheritProperties_2_oe() throws ConfigurationException {
        final Parameters params = new Parameters();
        final XMLBuilderParameters xmlParams = prepareParamsForInheritanceTest(params);
        builder.configure(xmlParams);
        final CombinedConfiguration config = builder.getConfiguration();

        List<String> list = config.getList(String.class, "test/mixed/array");
        // removed other assertion
        final String[] stringArray = config.getStringArray("test/mixed/array");
        assertTrue("Wrong number of elements in array", stringArray.length > 2);
    }

    @Test
    public void testInheritProperties_3_oe() throws ConfigurationException {
        final Parameters params = new Parameters();
        final XMLBuilderParameters xmlParams = prepareParamsForInheritanceTest(params);
        builder.configure(xmlParams);
        final CombinedConfiguration config = builder.getConfiguration();

        List<String> list = config.getList(String.class, "test/mixed/array");
        // removed other assertion
        final String[] stringArray = config.getStringArray("test/mixed/array");
        // removed other assertion
        final XMLConfiguration xmlConfig = (XMLConfiguration) config.getConfiguration("xml");
        list = xmlConfig.getList(String.class, "split/list1");
        assertEquals("Wrong number of elements in XML list", 3, list.size());
    }

    @Test
    public void testINIConfiguration_1_oe() throws ConfigurationException {
        final File multiFile = ConfigurationAssert.getTestFile("testDigesterConfiguration3.xml");
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilderParameters(createParameters().setFile(multiFile)));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertEquals("Property from ini file not found", "yes", cc.getString("testini.loaded"));
    }

    @Test
    public void testInitChildBuilderParametersDefaultChildProperties_1_oe() throws ConfigurationException {
        final Long defRefresh = 60000L;
        final Long xmlRefresh = 30000L;
        builder.configure(parameters.combined().setDefinitionBuilderParameters(parameters.fileBased().setFile(TEST_FILE))
            .registerChildDefaultsHandler(FileBasedBuilderProperties.class,
                new CopyObjectDefaultHandler(new FileBasedBuilderParametersImpl().setReloadingRefreshDelay(defRefresh).setThrowExceptionOnMissing(true)))
            .registerChildDefaultsHandler(XMLBuilderProperties.class, new CopyObjectDefaultHandler(
                new XMLBuilderParametersImpl().setValidating(false).setExpressionEngine(new XPathExpressionEngine()).setReloadingRefreshDelay(xmlRefresh))));
        builder.getConfiguration();
        final XMLBuilderParametersImpl params = new XMLBuilderParametersImpl();
        builder.initChildBuilderParameters(params);
        assertTrue("Wrong expression engine", params.getParameters().get("expressionEngine") instanceof XPathExpressionEngine);
    }

    @Test
    public void testInitChildBuilderParametersDefaultChildProperties_2_oe() throws ConfigurationException {
        final Long defRefresh = 60000L;
        final Long xmlRefresh = 30000L;
        builder.configure(parameters.combined().setDefinitionBuilderParameters(parameters.fileBased().setFile(TEST_FILE))
            .registerChildDefaultsHandler(FileBasedBuilderProperties.class,
                new CopyObjectDefaultHandler(new FileBasedBuilderParametersImpl().setReloadingRefreshDelay(defRefresh).setThrowExceptionOnMissing(true)))
            .registerChildDefaultsHandler(XMLBuilderProperties.class, new CopyObjectDefaultHandler(
                new XMLBuilderParametersImpl().setValidating(false).setExpressionEngine(new XPathExpressionEngine()).setReloadingRefreshDelay(xmlRefresh))));
        builder.getConfiguration();
        final XMLBuilderParametersImpl params = new XMLBuilderParametersImpl();
        builder.initChildBuilderParameters(params);
        // removed other assertion
        assertEquals("Validating flag not set", Boolean.FALSE, params.getParameters().get("validating"));
    }

    @Test
    public void testInitChildBuilderParametersDefaultChildProperties_3_oe() throws ConfigurationException {
        final Long defRefresh = 60000L;
        final Long xmlRefresh = 30000L;
        builder.configure(parameters.combined().setDefinitionBuilderParameters(parameters.fileBased().setFile(TEST_FILE))
            .registerChildDefaultsHandler(FileBasedBuilderProperties.class,
                new CopyObjectDefaultHandler(new FileBasedBuilderParametersImpl().setReloadingRefreshDelay(defRefresh).setThrowExceptionOnMissing(true)))
            .registerChildDefaultsHandler(XMLBuilderProperties.class, new CopyObjectDefaultHandler(
                new XMLBuilderParametersImpl().setValidating(false).setExpressionEngine(new XPathExpressionEngine()).setReloadingRefreshDelay(xmlRefresh))));
        builder.getConfiguration();
        final XMLBuilderParametersImpl params = new XMLBuilderParametersImpl();
        builder.initChildBuilderParameters(params);
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong XML refresh", xmlRefresh, params.getReloadingRefreshDelay());
    }

    @Test
    public void testInitChildBuilderParametersDefaultChildProperties_4_oe() throws ConfigurationException {
        final Long defRefresh = 60000L;
        final Long xmlRefresh = 30000L;
        builder.configure(parameters.combined().setDefinitionBuilderParameters(parameters.fileBased().setFile(TEST_FILE))
            .registerChildDefaultsHandler(FileBasedBuilderProperties.class,
                new CopyObjectDefaultHandler(new FileBasedBuilderParametersImpl().setReloadingRefreshDelay(defRefresh).setThrowExceptionOnMissing(true)))
            .registerChildDefaultsHandler(XMLBuilderProperties.class, new CopyObjectDefaultHandler(
                new XMLBuilderParametersImpl().setValidating(false).setExpressionEngine(new XPathExpressionEngine()).setReloadingRefreshDelay(xmlRefresh))));
        builder.getConfiguration();
        final XMLBuilderParametersImpl params = new XMLBuilderParametersImpl();
        builder.initChildBuilderParameters(params);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Basic flag not set", Boolean.TRUE, params.getParameters().get("throwExceptionOnMissing"));
    }

    @Test
    public void testInitChildBuilderParametersDefaultChildProperties_5_oe() throws ConfigurationException {
        final Long defRefresh = 60000L;
        final Long xmlRefresh = 30000L;
        builder.configure(parameters.combined().setDefinitionBuilderParameters(parameters.fileBased().setFile(TEST_FILE))
            .registerChildDefaultsHandler(FileBasedBuilderProperties.class,
                new CopyObjectDefaultHandler(new FileBasedBuilderParametersImpl().setReloadingRefreshDelay(defRefresh).setThrowExceptionOnMissing(true)))
            .registerChildDefaultsHandler(XMLBuilderProperties.class, new CopyObjectDefaultHandler(
                new XMLBuilderParametersImpl().setValidating(false).setExpressionEngine(new XPathExpressionEngine()).setReloadingRefreshDelay(xmlRefresh))));
        builder.getConfiguration();
        final XMLBuilderParametersImpl params = new XMLBuilderParametersImpl();
        builder.initChildBuilderParameters(params);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        final PropertiesBuilderParametersImpl params2 = new PropertiesBuilderParametersImpl();
        builder.initChildBuilderParameters(params2);
        assertEquals("Wrong default refresh", defRefresh, params2.getReloadingRefreshDelay());
    }

    @Test
    public void testInterpolationOverMultipleSources_1_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testInterpolationBuilder.xml");
        builder.configure(createParameters().setFile(testFile));
        final CombinedConfiguration combConfig = builder.getConfiguration();
        assertEquals("Wrong value", "abc-product", combConfig.getString("products.product.desc"));
    }

    @Test
    public void testInterpolationOverMultipleSources_2_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testInterpolationBuilder.xml");
        builder.configure(createParameters().setFile(testFile));
        final CombinedConfiguration combConfig = builder.getConfiguration();
        // removed other assertion
        final XMLConfiguration xmlConfig = (XMLConfiguration) combConfig.getConfiguration("test");
        assertEquals("Wrong value from XML config", "abc-product", xmlConfig.getString("products/product/desc"));
    }

    @Test
    public void testInterpolationOverMultipleSources_3_oe() throws ConfigurationException {
        final File testFile = ConfigurationAssert.getTestFile("testInterpolationBuilder.xml");
        builder.configure(createParameters().setFile(testFile));
        final CombinedConfiguration combConfig = builder.getConfiguration();
        // removed other assertion
        final XMLConfiguration xmlConfig = (XMLConfiguration) combConfig.getConfiguration("test");
        // removed other assertion
        final HierarchicalConfiguration<ImmutableNode> subConfig = xmlConfig.configurationAt("products/product[@name='abc']", true);
        assertEquals("Wrong value from sub config", "abc-product", subConfig.getString("desc"));
    }

    @Test
    public void testJndiConfiguration_1_oe() throws ConfigurationException {
        final File multiFile = ConfigurationAssert.getTestFile("testDigesterConfiguration3.xml");
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilderParameters(createParameters().setFile(multiFile)));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertTrue("JNDI property not found", cc.getBoolean("test.onlyinjndi"));
    }

    @Test
    public void testLoadAdditional_1_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        assertEquals("Verify how many configs", 2, compositeConfiguration.getNumberOfConfigurations());
    }

    @Test
    public void testLoadAdditional_2_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        assertTrue(prop instanceof Collection);
    }

    @Test
    public void testLoadAdditional_3_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        assertEquals(3, ((Collection<?>) prop).size());
    }

    @Test
    public void testLoadAdditional_4_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        assertEquals("users", compositeConfiguration.getProperty("tables.table(0).name"));
    }

    @Test
    public void testLoadAdditional_5_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("documents", compositeConfiguration.getProperty("tables.table(1).name"));
    }

    @Test
    public void testLoadAdditional_6_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("tasks", compositeConfiguration.getProperty("tables.table(2).name"));
    }

    @Test
    public void testLoadAdditional_7_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        prop = compositeConfiguration.getProperty("tables.table.fields.field.name");
        assertTrue(prop instanceof Collection);
    }

    @Test
    public void testLoadAdditional_8_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        prop = compositeConfiguration.getProperty("tables.table.fields.field.name");
        // removed other assertion
        assertEquals(17, ((Collection<?>) prop).size());
    }

    @Test
    public void testLoadAdditional_9_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        prop = compositeConfiguration.getProperty("tables.table.fields.field.name");
        // removed other assertion
        // removed other assertion

        assertEquals("smtp.mydomain.org", compositeConfiguration.getString("mail.host.smtp"));
    }

    @Test
    public void testLoadAdditional_10_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        prop = compositeConfiguration.getProperty("tables.table.fields.field.name");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("pop3.mydomain.org", compositeConfiguration.getString("mail.host.pop"));
    }

    @Test
    public void testLoadAdditional_11_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        prop = compositeConfiguration.getProperty("tables.table.fields.field.name");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // This was overriden
        assertEquals("masterOfPost", compositeConfiguration.getString("mail.account.user"));
    }

    @Test
    public void testLoadAdditional_12_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        prop = compositeConfiguration.getProperty("tables.table.fields.field.name");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // This was overriden
        // removed other assertion
        assertEquals("topsecret", compositeConfiguration.getString("mail.account.psswd"));
    }

    @Test
    public void testLoadAdditional_13_oe() throws ConfigurationException {
        final File additonalFile = ConfigurationAssert.getTestFile("testDigesterConfiguration2.xml");
        builder.configure(createParameters().setFile(additonalFile));
        final CombinedConfiguration compositeConfiguration = builder.getConfiguration();
        // removed other assertion

        // Test if union was constructed correctly
        Object prop = compositeConfiguration.getProperty("tables.table.name");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        prop = compositeConfiguration.getProperty("tables.table.fields.field.name");
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion

        // This was overriden
        // removed other assertion
        // removed other assertion

        // This was overridden, too, but not in additional section
        assertEquals("enhanced factory", compositeConfiguration.getString("test.configuration"));
    }

    @Test
    public void testLoadOptional_1_oe() throws Exception {
        final File optionalFile = ConfigurationAssert.getTestFile("testDigesterOptionalConfiguration.xml");
        builder.configure(createParameters().setFile(optionalFile));
        final Configuration config = builder.getConfiguration();
        assertTrue(config.getBoolean("test.boolean"));
    }

    @Test
    public void testLoadOptional_2_oe() throws Exception {
        final File optionalFile = ConfigurationAssert.getTestFile("testDigesterOptionalConfiguration.xml");
        builder.configure(createParameters().setFile(optionalFile));
        final Configuration config = builder.getConfiguration();
        // removed other assertion
        assertEquals("value", config.getProperty("element"));
    }

    @Test
    public void testLoadOptionalForceCreate_1_oe() throws ConfigurationException {
        final String name = "optionalConfig";
        final Map<String, Object> attrs = new HashMap<>();
        attrs.put("fileName", "nonExisting.xml");
        attrs.put("config-name", name);
        attrs.put("config-optional", Boolean.TRUE);
        attrs.put("config-forceCreate", Boolean.TRUE);
        final BaseHierarchicalConfiguration defConfig = createDefinitionConfig("xml", attrs);
        final BasicConfigurationBuilder<? extends BaseHierarchicalConfiguration> defBuilder = createDefinitionBuilder(defConfig);
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilder(defBuilder));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertEquals("Wrong number of configurations", 1, cc.getNumberOfConfigurations());
    }

    @Test
    public void testLoadOptionalForceCreate_2_oe() throws ConfigurationException {
        final String name = "optionalConfig";
        final Map<String, Object> attrs = new HashMap<>();
        attrs.put("fileName", "nonExisting.xml");
        attrs.put("config-name", name);
        attrs.put("config-optional", Boolean.TRUE);
        attrs.put("config-forceCreate", Boolean.TRUE);
        final BaseHierarchicalConfiguration defConfig = createDefinitionConfig("xml", attrs);
        final BasicConfigurationBuilder<? extends BaseHierarchicalConfiguration> defBuilder = createDefinitionBuilder(defConfig);
        builder.configure(new CombinedBuilderParametersImpl().setDefinitionBuilder(defBuilder));
        final CombinedConfiguration cc = builder.getConfiguration();
        // removed other assertion
        assertTrue("Wrong configuration type", cc.getConfiguration(name) instanceof XMLConfiguration);
    }

    @Test
    public void testMultiTenentConfigurationProperties_1_oe() throws ConfigurationException {
        final CombinedConfiguration config = createMultiFileConfig("testCCMultiTenent.xml");
        switchToMultiFile("1001");
        final HierarchicalConfiguration<?> multiConf = (HierarchicalConfiguration<?>) config.getConfiguration("clientConfig");
        assertTrue("Expression engine not configured", multiConf.getExpressionEngine() instanceof XPathExpressionEngine);
    }

    @Test
    public void testMultiTenentConfigurationProperties_2_oe() throws ConfigurationException {
        final CombinedConfiguration config = createMultiFileConfig("testCCMultiTenent.xml");
        switchToMultiFile("1001");
        final HierarchicalConfiguration<?> multiConf = (HierarchicalConfiguration<?>) config.getConfiguration("clientConfig");
        // removed other assertion
        assertEquals("Wrong bg color", "#808080", config.getString("colors.background"));
    }

    @Test
    public void testMultiTenentConfigurationProperties_3_oe() throws ConfigurationException {
        final CombinedConfiguration config = createMultiFileConfig("testCCMultiTenent.xml");
        switchToMultiFile("1001");
        final HierarchicalConfiguration<?> multiConf = (HierarchicalConfiguration<?>) config.getConfiguration("clientConfig");
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong text color", "#000000", multiConf.getString("colors/text"));
    }

    @Test
    public void testMultiTenentConfigurationReloading_7_oe() throws ConfigurationException, InterruptedException {
        final CombinedConfiguration config = createMultiFileConfig("testCCMultiTenentReloading.xml");
        final File outFile = ConfigurationAssert.getOutFile("MultiFileReloadingTest.xml");
        switchToMultiFile(outFile.getAbsolutePath());
        final XMLConfiguration reloadConfig = new XMLConfiguration();
        final FileHandler handler = new FileHandler(reloadConfig);
        handler.setFile(outFile);
        final String key = "test.reload";
        reloadConfig.setProperty(key, "no");
        handler.save();
        try {
            // removed other assertion
            final ConfigurationBuilder<? extends Configuration> childBuilder = builder.getNamedBuilder("clientConfig");
            // removed other assertion
            final ReloadingController ctrl = ((ReloadingControllerSupport) childBuilder).getReloadingController();
            ctrl.checkForReloading(null); // initialize reloading
            final BuilderEventListenerImpl listener = new BuilderEventListenerImpl();
            childBuilder.addEventListener(ConfigurationBuilderEvent.RESET, listener);
            reloadConfig.setProperty(key, "yes");
            handler.save();

            int attempts = 10;
            boolean changeDetected;
            do {
                changeDetected = ctrl.checkForReloading(null);
                if (!changeDetected) {
                    Thread.sleep(1000);
                    handler.save(outFile);
                }
            } while (!changeDetected && --attempts > 0);
            // removed other assertion
            // removed other assertion
            final ConfigurationBuilderEvent event = listener.nextEvent(ConfigurationBuilderEvent.RESET);
            // removed other assertion
            final BasicConfigurationBuilder<? extends Configuration> multiBuilder = (BasicConfigurationBuilder<? extends Configuration>) event.getSource();
            childBuilder.removeEventListener(ConfigurationBuilderEvent.RESET, listener);
            multiBuilder.resetResult();
            // removed other assertion
        } finally {
            assertTrue("Output file could not be deleted", outFile.delete());
    }
    }

    @Test
    public void testProviderInDefinitionConfig_1_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(ConfigurationAssert.getTestFile("testCCCustomProvider.xml")));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertTrue("Property not found", cc.getBoolean("testKey"));
    }

    @Test
    public void testReactOnSubBuilderChange_1_oe() throws ConfigurationException {
        final Map<String, Object> attrs = new HashMap<>();
        prepareSubBuilderTest(attrs);
        final CombinedConfiguration cc = builder.getConfiguration();
        final BasicConfigurationBuilder<?> subBuilder = (BasicConfigurationBuilder<?>) builder.getNamedBuilder(BUILDER_NAME);
        subBuilder.reset();
        assertNotSame("Configuration not newly created", cc, builder.getConfiguration());
    }

    @Test
    public void testReloadingBuilder_1_oe() throws ConfigurationException {
        final Map<String, Object> attrs = new HashMap<>();
        attrs.put("config-reload", Boolean.TRUE);
        prepareSubBuilderTest(attrs);
        builder.getConfiguration();
        assertTrue("Not a reloading builder", builder.getNamedBuilder(BUILDER_NAME) instanceof ReloadingFileBasedConfigurationBuilder);
    }

    @Test
    public void testRemoveSubBuilderListener_1_oe() throws ConfigurationException {
        final Map<String, Object> attrs = new HashMap<>();
        prepareSubBuilderTest(attrs);
        builder.getConfiguration();
        final BasicConfigurationBuilder<?> subBuilder = (BasicConfigurationBuilder<?>) builder.getNamedBuilder(BUILDER_NAME);
        builder.reset();
        prepareSubBuilderTest(attrs);
        final CombinedConfiguration cc = builder.getConfiguration();
        final BasicConfigurationBuilder<?> subBuilder2 = (BasicConfigurationBuilder<?>) builder.getNamedBuilder(BUILDER_NAME);
        assertNotSame("Got the same sub builder", subBuilder, subBuilder2);
    }

    @Test
    public void testRemoveSubBuilderListener_2_oe() throws ConfigurationException {
        final Map<String, Object> attrs = new HashMap<>();
        prepareSubBuilderTest(attrs);
        builder.getConfiguration();
        final BasicConfigurationBuilder<?> subBuilder = (BasicConfigurationBuilder<?>) builder.getNamedBuilder(BUILDER_NAME);
        builder.reset();
        prepareSubBuilderTest(attrs);
        final CombinedConfiguration cc = builder.getConfiguration();
        final BasicConfigurationBuilder<?> subBuilder2 = (BasicConfigurationBuilder<?>) builder.getNamedBuilder(BUILDER_NAME);
        // removed other assertion
        subBuilder.reset();
        assertSame("Configuration was reset", cc, builder.getConfiguration());
    }

    @Test
    public void testResetBuilder_1_oe() throws ConfigurationException {
        final Map<String, Object> attrs = new HashMap<>();
        final BasicConfigurationBuilder<? extends HierarchicalConfiguration<ImmutableNode>> defBuilder = prepareSubBuilderTest(attrs);
        final CombinedConfiguration cc = builder.getConfiguration();
        final ConfigurationBuilder<? extends Configuration> subBuilder = builder.getNamedBuilder(BUILDER_NAME);
        defBuilder.reset();
        final CombinedConfiguration cc2 = builder.getConfiguration();
        assertNotSame("No new configuration instance", cc, cc2);
    }

    @Test
    public void testResetBuilder_2_oe() throws ConfigurationException {
        final Map<String, Object> attrs = new HashMap<>();
        final BasicConfigurationBuilder<? extends HierarchicalConfiguration<ImmutableNode>> defBuilder = prepareSubBuilderTest(attrs);
        final CombinedConfiguration cc = builder.getConfiguration();
        final ConfigurationBuilder<? extends Configuration> subBuilder = builder.getNamedBuilder(BUILDER_NAME);
        defBuilder.reset();
        final CombinedConfiguration cc2 = builder.getConfiguration();
        // removed other assertion
        final ConfigurationBuilder<? extends Configuration> subBuilder2 = builder.getNamedBuilder(BUILDER_NAME);
        assertNotSame("No new sub builder instance", subBuilder, subBuilder2);
    }

    @Test
    public void testRootNodeInitializedAfterCreation_1_oe() throws ConfigurationException {
        builder.configure(createParameters().setFile(TEST_FILE));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertNotNull("Root node not initialized", cc.getNodeModel().getNodeHandler().getRootNode());
    }

    @Test
    public void testSuppressChildBuilderPropertyInheritance_1_oe() throws ConfigurationException {
        final Parameters params = new Parameters();
        final CombinedBuilderParameters combinedParams = params.combined().setInheritSettings(false);
        builder.configure(combinedParams, prepareParamsForInheritanceTest(params));
        final CombinedConfiguration config = builder.getConfiguration();

        final XMLConfiguration xmlConfig = (XMLConfiguration) config.getConfiguration("xml");
        final List<String> list = xmlConfig.getList(String.class, "split.list1");
        assertEquals("Wrong number of elements in XML list", 1, list.size());
    }

    @Test
    public void testSystemProperties_1_oe() throws ConfigurationException {
        final File systemFile = ConfigurationAssert.getTestFile("testCCSystemProperties.xml");
        builder.configure(createParameters().setFile(systemFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        assertTrue("System property not found", cc.containsKey("user.name"));
    }

    @Test
    public void testSystemProperties_2_oe() throws ConfigurationException {
        final File systemFile = ConfigurationAssert.getTestFile("testCCSystemProperties.xml");
        builder.configure(createParameters().setFile(systemFile));
        final CombinedConfiguration cc = builder.getConfiguration();
        // removed other assertion
        assertEquals("Properties not added", "value1", System.getProperty("key1"));
    }

}
