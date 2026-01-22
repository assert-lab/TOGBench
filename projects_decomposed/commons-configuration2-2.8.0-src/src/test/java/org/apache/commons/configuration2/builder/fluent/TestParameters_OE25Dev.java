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
package org.apache.commons.configuration2.builder.fluent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration2.ConfigurationConsumer;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.BasicBuilderParameters;
import org.apache.commons.configuration2.builder.BasicBuilderProperties;
import org.apache.commons.configuration2.builder.BuilderParameters;
import org.apache.commons.configuration2.builder.DefaultParametersHandler;
import org.apache.commons.configuration2.builder.DefaultParametersManager;
import org.apache.commons.configuration2.builder.FileBasedBuilderParametersImpl;
import org.apache.commons.configuration2.builder.combined.CombinedBuilderParametersImpl;
import org.apache.commons.configuration2.builder.combined.MultiFileBuilderParametersImpl;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.ExpressionEngine;
import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for {@code Parameters}.
 *
 */
public class TestParameters_OE25Dev {
    /** A default encoding. */
    private static final String DEF_ENCODING = "UTF-8";

    /** A test list delimiter handler. */
    private static ListDelimiterHandler listHandler;

    /**
     * Checks whether the given parameters map contains the standard values for basic properties.
     *
     * @param map the map to be tested
     */
    private static void checkBasicProperties(final Map<String, Object> map) {
        assertEquals("Wrong delimiter handler", listHandler, map.get("listDelimiterHandler"));
        assertEquals("Wrong exception flag value", Boolean.TRUE, map.get("throwExceptionOnMissing"));
    }

    /**
     * Checks whether a given parameters object implements all the specified interfaces.
     *
     * @param params the parameters object to check
     * @param ifcClasses the interface classes to be implemented
     */
    private static void checkInheritance(final Object params, final Class<?>... ifcClasses) {
        checkInstanceOf(params, BasicBuilderProperties.class);
        for (final Class<?> c : ifcClasses) {
            checkInstanceOf(params, c);
        }
    }

    /**
     * Helper method for testing whether the given object is an instance of the provided class.
     *
     * @param obj the object to be checked
     * @param cls the class
     */
    private static void checkInstanceOf(final Object obj, final Class<?> cls) {
        assertTrue(obj + " is not an instance of " + cls, cls.isInstance(obj));
    }

    /**
     * Creates a mock for a defaults parameter handler.
     *
     * @return the mock object
     */
    private static DefaultParametersHandler<XMLBuilderParameters> createHandlerMock() {
        return EasyMock.createMock(DefaultParametersHandler.class);
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        listHandler = EasyMock.createMock(ListDelimiterHandler.class);
    }

    /**
     * Tests whether default values are set for newly created parameters objects.
     */

    /**
     * Tests whether a basic parameters object can be created.
     */

    /**
     * Tests whether a combined parameters object can be created.
     */

    /**
     * Tests whether a parameters object for a database configuration can be created.
     */

    /**
     * Tests whether an uninitialized default parameters manager is created at construction time.
     */

    /**
     * Tests whether a file-based parameters object can be created.
     */

    /**
     * Tests the inheritance structure of a fileBased parameters object.
     */
    @Test
    public void testFileBasedInheritance() {
        checkInheritance(new Parameters().fileBased());
    }

    /**
     * Tests whether a parameters object for a hierarchical configuration can be created.
     */

    /**
     * Tests the inheritance structure of a hierarchical parameters object.
     */
    @Test
    public void testHierarchicalInheritance() {
        checkInheritance(new Parameters().hierarchical(), FileBasedBuilderParameters.class);
    }

    /**
     * Tests whether the parameters objects created by the Parameters instance have a logic inheritance hierarchy. This
     * means that they also implement all base interfaces that make sense.
     */

    /**
     * Tests whether a JNDI parameters object can be created.
     */

    /**
     * Tests whether a {@code MultiFileBuilderParameters} object can be created.
     */

    /**
     * Tests whether a parameters object for a properties configuration can be created.
     */

    /**
     * Tests the inheritance structure of a properties parameters object.
     */
    @Test
    public void testPropertiesInheritance() {
        checkInheritance(new Parameters().properties(), FileBasedBuilderParameters.class);
    }

    /**
     * Tests whether the proxy parameters object can deal with methods inherited from Object.
     */

    /**
     * Tests the registration of a defaults handler if no start class is provided.
     */
    @Test
    public void testRegisterDefaultsHandlerNoStartClass() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        final DefaultParametersHandler<XMLBuilderParameters> handler = createHandlerMock();
        manager.registerDefaultsHandler(XMLBuilderParameters.class, handler);
        EasyMock.replay(manager, handler);

        final Parameters params = new Parameters(manager);
        params.registerDefaultsHandler(XMLBuilderParameters.class, handler);
        EasyMock.verify(manager);
    }

    /**
     * Tests whether a default handler with a start class can be registered.
     */
    @Test
    public void testRegisterDefaultsHandlerWithStartClass() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        final DefaultParametersHandler<XMLBuilderParameters> handler = createHandlerMock();
        manager.registerDefaultsHandler(XMLBuilderParameters.class, handler, FileBasedBuilderParameters.class);
        EasyMock.replay(manager, handler);

        final Parameters params = new Parameters(manager);
        params.registerDefaultsHandler(XMLBuilderParameters.class, handler, FileBasedBuilderParameters.class);
        EasyMock.verify(manager);
    }

    /**
     * Tests whether a parameters object for an XML configuration can be created.
     */

    /**
     * Tests the inheritance structure of an XML parameters object.
     */
    @Test
    public void testXmlInheritance() {
        checkInheritance(new Parameters().xml(), HierarchicalBuilderParameters.class, FileBasedBuilderParameters.class);
    }

    @Test
    public void testApplyDefaults_1_oe() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        final List<Object> initializedParams = new ArrayList<>(1);
        manager.initializeParameters(EasyMock.anyObject(BuilderParameters.class));
        EasyMock.expectLastCall().andAnswer(() -> {
            initializedParams.add(EasyMock.getCurrentArguments()[0]);
            return null;
        });
        EasyMock.replay(manager);

        final Parameters params = new Parameters(manager);
        final XMLBuilderParameters xmlParams = params.xml();
        assertEquals("Wrong number of initializations", 1, initializedParams.size());
    }

    @Test
    public void testApplyDefaults_2_oe() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        final List<Object> initializedParams = new ArrayList<>(1);
        manager.initializeParameters(EasyMock.anyObject(BuilderParameters.class));
        EasyMock.expectLastCall().andAnswer(() -> {
            initializedParams.add(EasyMock.getCurrentArguments()[0]);
            return null;
        });
        EasyMock.replay(manager);

        final Parameters params = new Parameters(manager);
        final XMLBuilderParameters xmlParams = params.xml();
        // removed other assertion
        assertSame("Wrong initialized object", xmlParams, initializedParams.get(0));
    }

    @Test
    public void testBasic_1_oe() {
        final BasicBuilderParameters basic = new Parameters().basic();
        assertNotNull("No result object", basic);
    }

    @Test
    public void testCombined_1_oe() {
        final Map<String, Object> map = new Parameters().combined().setThrowExceptionOnMissing(true).setBasePath("test").setListDelimiterHandler(listHandler)
            .getParameters();
        final CombinedBuilderParametersImpl cparams = CombinedBuilderParametersImpl.fromParameters(map);
        assertEquals("Wrong base path", "test", cparams.getBasePath());
    }

    @Test
    public void testDatabase_1_oe() {
        final Map<String, Object> map = new Parameters().database().setThrowExceptionOnMissing(true).setAutoCommit(true).setTable("table")
            .setListDelimiterHandler(listHandler).setKeyColumn("keyColumn").getParameters();
        checkBasicProperties(map);
        assertEquals("Wrong table name", "table", map.get("table"));
    }

    @Test
    public void testDatabase_2_oe() {
        final Map<String, Object> map = new Parameters().database().setThrowExceptionOnMissing(true).setAutoCommit(true).setTable("table")
            .setListDelimiterHandler(listHandler).setKeyColumn("keyColumn").getParameters();
        checkBasicProperties(map);
        // removed other assertion
        assertEquals("Wrong key column name", "keyColumn", map.get("keyColumn"));
    }

    @Test
    public void testDatabase_3_oe() {
        final Map<String, Object> map = new Parameters().database().setThrowExceptionOnMissing(true).setAutoCommit(true).setTable("table")
            .setListDelimiterHandler(listHandler).setKeyColumn("keyColumn").getParameters();
        checkBasicProperties(map);
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong auto commit flag", Boolean.TRUE, map.get("autoCommit"));
    }

    @Test
    public void testDefaultParametersManager_1_oe() {
        final Parameters parameters = new Parameters();
        assertNotNull("No default manager", parameters.getDefaultParametersManager());
    }

    @Test
    public void testFileBased_1_oe() {
        final Map<String, Object> map = new Parameters().fileBased().setThrowExceptionOnMissing(true).setEncoding(DEF_ENCODING)
            .setListDelimiterHandler(listHandler).setFileName("test.xml").getParameters();
        final FileBasedBuilderParametersImpl fbparams = FileBasedBuilderParametersImpl.fromParameters(map);
        assertEquals("Wrong file name", "test.xml", fbparams.getFileHandler().getFileName());
    }

    @Test
    public void testFileBased_2_oe() {
        final Map<String, Object> map = new Parameters().fileBased().setThrowExceptionOnMissing(true).setEncoding(DEF_ENCODING)
            .setListDelimiterHandler(listHandler).setFileName("test.xml").getParameters();
        final FileBasedBuilderParametersImpl fbparams = FileBasedBuilderParametersImpl.fromParameters(map);
        // removed other assertion
        assertEquals("Wrong encoding", DEF_ENCODING, fbparams.getFileHandler().getEncoding());
    }

    @Test
    public void testHierarchical_1_oe() {
        final ExpressionEngine engine = EasyMock.createMock(ExpressionEngine.class);
        final Map<String, Object> map = new Parameters().hierarchical().setThrowExceptionOnMissing(true).setExpressionEngine(engine).setFileName("test.xml")
            .setListDelimiterHandler(listHandler).getParameters();
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        assertEquals("Wrong file name", "test.xml", fbp.getFileHandler().getFileName());
    }

    @Test
    public void testHierarchical_2_oe() {
        final ExpressionEngine engine = EasyMock.createMock(ExpressionEngine.class);
        final Map<String, Object> map = new Parameters().hierarchical().setThrowExceptionOnMissing(true).setExpressionEngine(engine).setFileName("test.xml")
            .setListDelimiterHandler(listHandler).getParameters();
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        // removed other assertion
        assertEquals("Wrong expression engine", engine, map.get("expressionEngine"));
    }

    @Test
    public void testInheritance_1_oe() {
        final Object params = new Parameters().xml();
        assertTrue("No instance of base interface", params instanceof FileBasedBuilderParameters);
    }

    @Test
    public void testInheritance_2_oe() {
        final Object params = new Parameters().xml();
        // removed other assertion
        assertTrue("No instance of base interface (dynamic)", FileBasedBuilderParameters.class.isInstance(params));
    }

    @Test
    public void testInheritance_3_oe() {
        final Object params = new Parameters().xml();
        // removed other assertion
        // removed other assertion
        final FileBasedBuilderParameters fbParams = (FileBasedBuilderParameters) params;
        fbParams.setListDelimiterHandler(listHandler).setFileName("test.xml").setThrowExceptionOnMissing(true);
        final ExpressionEngine engine = EasyMock.createMock(ExpressionEngine.class);
        ((HierarchicalBuilderParameters) params).setExpressionEngine(engine);
        final Map<String, Object> map = fbParams.getParameters();
        checkBasicProperties(map);
        assertSame("Wrong expression engine", engine, map.get("expressionEngine"));
    }

    @Test
    public void testJndi_1_oe() {
        final Map<String, Object> map = new Parameters().jndi().setThrowExceptionOnMissing(true).setPrefix("test").setListDelimiterHandler(listHandler)
            .getParameters();
        assertEquals("Wrong prefix", "test", map.get("prefix"));
    }

    @Test
    public void testMultiFile_1_oe() {
        final BuilderParameters bp = EasyMock.createMock(BuilderParameters.class);
        final String pattern = "a pattern";
        final Map<String, Object> map = new Parameters().multiFile().setThrowExceptionOnMissing(true).setFilePattern(pattern)
            .setListDelimiterHandler(listHandler).setManagedBuilderParameters(bp).getParameters();
        checkBasicProperties(map);
        final MultiFileBuilderParametersImpl params = MultiFileBuilderParametersImpl.fromParameters(map);
        assertSame("Wrong builder parameters", bp, params.getManagedBuilderParameters());
    }

    @Test
    public void testMultiFile_2_oe() {
        final BuilderParameters bp = EasyMock.createMock(BuilderParameters.class);
        final String pattern = "a pattern";
        final Map<String, Object> map = new Parameters().multiFile().setThrowExceptionOnMissing(true).setFilePattern(pattern)
            .setListDelimiterHandler(listHandler).setManagedBuilderParameters(bp).getParameters();
        checkBasicProperties(map);
        final MultiFileBuilderParametersImpl params = MultiFileBuilderParametersImpl.fromParameters(map);
        // removed other assertion
        assertEquals("Wrong pattern", pattern, params.getFilePattern());
    }

    @Test
    public void testProperties_1_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        // @formatter:off
        final Map<String, Object> map =
                new Parameters().properties()
                        .setThrowExceptionOnMissing(true)
                        .setFileName("test.properties")
                        .setIncludeListener(includeListener)
                        .setIOFactory(factory)
                        .setListDelimiterHandler(listHandler)
                        .setIncludesAllowed(false)
                        .getParameters();
        // @formatter:on
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        assertEquals("Wrong file name", "test.properties", fbp.getFileHandler().getFileName());
    }

    @Test
    public void testProperties_2_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        // @formatter:off
        final Map<String, Object> map =
                new Parameters().properties()
                        .setThrowExceptionOnMissing(true)
                        .setFileName("test.properties")
                        .setIncludeListener(includeListener)
                        .setIOFactory(factory)
                        .setListDelimiterHandler(listHandler)
                        .setIncludesAllowed(false)
                        .getParameters();
        // @formatter:on
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        // removed other assertion
        assertEquals("Wrong includes flag", Boolean.FALSE, map.get("includesAllowed"));
    }

    @Test
    public void testProperties_3_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        // @formatter:off
        final Map<String, Object> map =
                new Parameters().properties()
                        .setThrowExceptionOnMissing(true)
                        .setFileName("test.properties")
                        .setIncludeListener(includeListener)
                        .setIOFactory(factory)
                        .setListDelimiterHandler(listHandler)
                        .setIncludesAllowed(false)
                        .getParameters();
        // @formatter:on
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        // removed other assertion
        // removed other assertion
        assertSame("Wrong include listener", includeListener, map.get("includeListener"));
    }

    @Test
    public void testProperties_4_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        // @formatter:off
        final Map<String, Object> map =
                new Parameters().properties()
                        .setThrowExceptionOnMissing(true)
                        .setFileName("test.properties")
                        .setIncludeListener(includeListener)
                        .setIOFactory(factory)
                        .setListDelimiterHandler(listHandler)
                        .setIncludesAllowed(false)
                        .getParameters();
        // @formatter:on
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("Wrong factory", factory, map.get("IOFactory"));
    }

    @Test
    public void testProxyObjectMethods_1_oe() {
        final FileBasedBuilderParameters params = new Parameters().fileBased();
        final String s = params.toString();
        assertTrue("Wrong string: " + s, s.indexOf(FileBasedBuilderParametersImpl.class.getSimpleName()) >= 0);
    }

    @Test
    public void testProxyObjectMethods_2_oe() {
        final FileBasedBuilderParameters params = new Parameters().fileBased();
        final String s = params.toString();
        // removed other assertion
        assertTrue("No hash code", params.hashCode() != 0);
    }

    @Test
    public void testXml_1_oe() {
        final ExpressionEngine engine = EasyMock.createMock(ExpressionEngine.class);
        final Map<String, Object> map = new Parameters().xml().setThrowExceptionOnMissing(true).setFileName("test.xml").setValidating(true)
            .setExpressionEngine(engine).setListDelimiterHandler(listHandler).setSchemaValidation(true).getParameters();
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        assertEquals("Wrong file name", "test.xml", fbp.getFileHandler().getFileName());
    }

    @Test
    public void testXml_2_oe() {
        final ExpressionEngine engine = EasyMock.createMock(ExpressionEngine.class);
        final Map<String, Object> map = new Parameters().xml().setThrowExceptionOnMissing(true).setFileName("test.xml").setValidating(true)
            .setExpressionEngine(engine).setListDelimiterHandler(listHandler).setSchemaValidation(true).getParameters();
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        // removed other assertion
        assertEquals("Wrong validation flag", Boolean.TRUE, map.get("validating"));
    }

    @Test
    public void testXml_3_oe() {
        final ExpressionEngine engine = EasyMock.createMock(ExpressionEngine.class);
        final Map<String, Object> map = new Parameters().xml().setThrowExceptionOnMissing(true).setFileName("test.xml").setValidating(true)
            .setExpressionEngine(engine).setListDelimiterHandler(listHandler).setSchemaValidation(true).getParameters();
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong schema flag", Boolean.TRUE, map.get("schemaValidation"));
    }

    @Test
    public void testXml_4_oe() {
        final ExpressionEngine engine = EasyMock.createMock(ExpressionEngine.class);
        final Map<String, Object> map = new Parameters().xml().setThrowExceptionOnMissing(true).setFileName("test.xml").setValidating(true)
            .setExpressionEngine(engine).setListDelimiterHandler(listHandler).setSchemaValidation(true).getParameters();
        checkBasicProperties(map);
        final FileBasedBuilderParametersImpl fbp = FileBasedBuilderParametersImpl.fromParameters(map);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong expression engine", engine, map.get("expressionEngine"));
    }

}
