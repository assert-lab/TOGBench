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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.ConfigurationAssert;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.builder.BasicBuilderParameters;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.BuilderParameters;
import org.apache.commons.configuration2.builder.ConfigurationBuilder;
import org.apache.commons.configuration2.builder.DefaultParametersHandler;
import org.apache.commons.configuration2.builder.DefaultParametersManager;
import org.apache.commons.configuration2.builder.XMLBuilderParametersImpl;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Test class for {@code CombinedBuilderParametersImpl}.
 *
 */
public class TestCombinedBuilderParametersImpl_OE25Dev {
    /**
     * Creates a mock for a defaults handler.
     *
     * @return the handler mock
     */
    private static DefaultParametersHandler<BuilderParameters> createDefaultsHandlerMock() {
        return EasyMock.createMock(DefaultParametersHandler.class);
    }

    /**
     * Tests whether cloning works as expected.
     */

    /**
     * Tests whether a new instance can be created if none is found in the parameters map.
     */

    /**
     * Tests whether an instance can be obtained from a parameters map.
     */

    /**
     * Tests fromParameters() if the map does not contain an instance.
     */

    /**
     * Tests whether a default parameters manager can be set and queried.
     */

    /**
     * Tests whether a default parameters manager is dynamically created if it has not been set.
     */

    /**
     * Tests that inherited properties are also stored in the parameters map.
     */

    /**
     * Tests whether the map with providers is initially empty.
     */

    /**
     * Tests that the map with providers cannot be modified.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetProvidersModify() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.getProviders().put("tag", EasyMock.createMock(ConfigurationBuilderProvider.class));
    }

    /**
     * Tests whether properties can be inherited.
     */

    /**
     * Tests that inheritFrom() can handle a map which does not contain a parameters object.
     */

    /**
     * Tests the result for an unknown provider.
     */

    /**
     * Tests whether a defaults handler for a child source can be registered.
     */

    /**
     * Tests whether a defaults handler for a child source with a class restriction can be registered.
     */

    /**
     * Tests whether missing providers can be registered.
     */

    /**
     * Tries to register a map with missing providers containing a null entry.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterMissingProvidersNullEntry() {
        final Map<String, ConfigurationBuilderProvider> map = new HashMap<>();
        map.put("tag", null);
        new CombinedBuilderParametersImpl().registerMissingProviders(map);
    }

    /**
     * Tries to register a null map with missing providers.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterMissingProvidersNullMap() {
        final Map<String, ConfigurationBuilderProvider> map = null;
        new CombinedBuilderParametersImpl().registerMissingProviders(map);
    }

    /**
     * Tests whether missing providers can be copied from a parameters object.
     */

    /**
     * Tries to copy providers from a null parameters object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterMissingProvidersParamsNull() {
        new CombinedBuilderParametersImpl().registerMissingProviders((CombinedBuilderParametersImpl) null);
    }

    /**
     * Tests whether a new builder provider can be registered.
     */

    /**
     * Tries to register a null provider.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterProviderNoProvider() {
        new CombinedBuilderParametersImpl().registerProvider("aTag", null);
    }

    /**
     * Tries to register a provider without a tag name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterProviderNoTag() {
        new CombinedBuilderParametersImpl().registerProvider(null, EasyMock.createMock(ConfigurationBuilderProvider.class));
    }

    /**
     * Tests whether the base path can be set.
     */

    /**
     * Tests whether properties can be set using BeanUtils.
     */

    /**
     * Tests whether the definition builder can be set.
     */

    /**
     * Tests whether a parameters object for the definition builder can be set.
     */

    /**
     * Tests whether the flag that controls settings inheritance can be set.
     */

    @Test
    public void testClone_1_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.setBasePath("some base path");
        final XMLBuilderParametersImpl defParams = new XMLBuilderParametersImpl();
        defParams.setSystemID("someSysID");
        params.setDefinitionBuilderParameters(defParams);
        final CombinedBuilderParametersImpl clone = params.clone();
        assertEquals("Wrong field value", params.getBasePath(), clone.getBasePath());
    }

    @Test
    public void testClone_2_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.setBasePath("some base path");
        final XMLBuilderParametersImpl defParams = new XMLBuilderParametersImpl();
        defParams.setSystemID("someSysID");
        params.setDefinitionBuilderParameters(defParams);
        final CombinedBuilderParametersImpl clone = params.clone();
        // removed other assertion
        assertNotSame("Parameters object not cloned", params.getDefinitionBuilderParameters(), clone.getDefinitionBuilderParameters());
    }

    @Test
    public void testClone_3_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.setBasePath("some base path");
        final XMLBuilderParametersImpl defParams = new XMLBuilderParametersImpl();
        defParams.setSystemID("someSysID");
        params.setDefinitionBuilderParameters(defParams);
        final CombinedBuilderParametersImpl clone = params.clone();
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong field value in parameters object", params.getDefinitionBuilderParameters().getParameters().get("systemID"), clone.getDefinitionBuilderParameters().getParameters().get("systemID"));
    }

    @Test
    public void testFromParametersCreate_1_oe() {
        final CombinedBuilderParametersImpl params = CombinedBuilderParametersImpl.fromParameters(new HashMap<>(), true);
        assertNotNull("No instance", params);
    }

    @Test
    public void testFromParametersCreate_2_oe() {
        final CombinedBuilderParametersImpl params = CombinedBuilderParametersImpl.fromParameters(new HashMap<>(), true);
        // removed other assertion
        assertNull("Got data", params.getDefinitionBuilder());
    }

    @Test
    public void testFromParametersExisting_1_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        final Map<String, Object> map = params.getParameters();
        assertSame("Wrong result", params, CombinedBuilderParametersImpl.fromParameters(map));
    }

    @Test
    public void testFromParametersNotFound_1_oe() {
        assertNull("Got an instance", CombinedBuilderParametersImpl.fromParameters(new HashMap<>()));
    }

    @Test
    public void testGetChildDefaultParametersManagerSpecific_1_oe() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        EasyMock.replay(manager);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setChildDefaultParametersManager(manager));
    }

    @Test
    public void testGetChildDefaultParametersManagerSpecific_2_oe() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        EasyMock.replay(manager);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        // removed other assertion
        assertSame("Wrong manager", manager, params.getChildDefaultParametersManager());
    }

    @Test
    public void testGetChildDefaultParametersManagerUndefined_1_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        assertNotNull("No default manager", params.getChildDefaultParametersManager());
    }

    @Test
    public void testGetParametersInherited_1_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.setThrowExceptionOnMissing(true);
        final Map<String, Object> map = params.getParameters();
        assertEquals("Exception flag not found", Boolean.TRUE, map.get("throwExceptionOnMissing"));
    }

    @Test
    public void testGetProvidersInitial_1_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        assertTrue("Got providers", params.getProviders().isEmpty());
    }

    @Test
    public void testInheritFrom_1_oe() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl().setInheritSettings(false).setChildDefaultParametersManager(manager);
        params.setThrowExceptionOnMissing(true);
        final CombinedBuilderParametersImpl params2 = new CombinedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        assertEquals("Exception flag not set", Boolean.TRUE, parameters.get("throwExceptionOnMissing"));
    }

    @Test
    public void testInheritFrom_2_oe() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl().setInheritSettings(false).setChildDefaultParametersManager(manager);
        params.setThrowExceptionOnMissing(true);
        final CombinedBuilderParametersImpl params2 = new CombinedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        assertEquals("Default manager not set", manager, params2.getChildDefaultParametersManager());
    }

    @Test
    public void testInheritFrom_3_oe() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl().setInheritSettings(false).setChildDefaultParametersManager(manager);
        params.setThrowExceptionOnMissing(true);
        final CombinedBuilderParametersImpl params2 = new CombinedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        // removed other assertion
        assertFalse("Inherit flag not set", params2.isInheritSettings());
    }

    @Test
    public void testInheritFromNoParametersInMap_1_oe() {
        final BasicBuilderParameters params = new BasicBuilderParameters().setThrowExceptionOnMissing(true);
        final CombinedBuilderParametersImpl params2 = new CombinedBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        assertEquals("Exception flag not set", Boolean.TRUE, parameters.get("throwExceptionOnMissing"));
    }

    @Test
    public void testProviderForUnknown_1_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        assertNull("Got a provider", params.providerForTag("someTag"));
    }

    @Test
    public void testRegisterChildDefaultsHandler_1_oe() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        final DefaultParametersHandler<BuilderParameters> handler = createDefaultsHandlerMock();
        manager.registerDefaultsHandler(BuilderParameters.class, handler);
        EasyMock.replay(manager, handler);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.setChildDefaultParametersManager(manager);
        assertSame("Wrong result", params, params.registerChildDefaultsHandler(BuilderParameters.class, handler));
    }

    @Test
    public void testRegisterChildDefaultsHandlerWithStartClass_1_oe() {
        final DefaultParametersManager manager = EasyMock.createMock(DefaultParametersManager.class);
        final DefaultParametersHandler<BuilderParameters> handler = createDefaultsHandlerMock();
        manager.registerDefaultsHandler(BuilderParameters.class, handler, FileBasedBuilderParameters.class);
        EasyMock.replay(manager, handler);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.setChildDefaultParametersManager(manager);
        assertSame("Wrong result", params, params.registerChildDefaultsHandler(BuilderParameters.class, handler, FileBasedBuilderParameters.class));
    }

    @Test
    public void testRegisterMissingProviders_1_oe() {
        final ConfigurationBuilderProvider provider1 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider2 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider3 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final String tagPrefix = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.registerProvider(tagPrefix, provider1);
        final Map<String, ConfigurationBuilderProvider> map = new HashMap<>();
        map.put(tagPrefix, provider2);
        map.put(tagPrefix + 1, provider3);
        assertSame("Wrong result", params, params.registerMissingProviders(map));
    }

    @Test
    public void testRegisterMissingProviders_2_oe() {
        final ConfigurationBuilderProvider provider1 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider2 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider3 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final String tagPrefix = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.registerProvider(tagPrefix, provider1);
        final Map<String, ConfigurationBuilderProvider> map = new HashMap<>();
        map.put(tagPrefix, provider2);
        map.put(tagPrefix + 1, provider3);
        // removed other assertion
        assertEquals("Wrong number of providers", 2, params.getProviders().size());
    }

    @Test
    public void testRegisterMissingProviders_3_oe() {
        final ConfigurationBuilderProvider provider1 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider2 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider3 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final String tagPrefix = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.registerProvider(tagPrefix, provider1);
        final Map<String, ConfigurationBuilderProvider> map = new HashMap<>();
        map.put(tagPrefix, provider2);
        map.put(tagPrefix + 1, provider3);
        // removed other assertion
        // removed other assertion
        assertSame("Wrong provider (1)", provider1, params.providerForTag(tagPrefix));
    }

    @Test
    public void testRegisterMissingProviders_4_oe() {
        final ConfigurationBuilderProvider provider1 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider2 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider3 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final String tagPrefix = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        params.registerProvider(tagPrefix, provider1);
        final Map<String, ConfigurationBuilderProvider> map = new HashMap<>();
        map.put(tagPrefix, provider2);
        map.put(tagPrefix + 1, provider3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("Wrong provider (2)", provider3, params.providerForTag(tagPrefix + 1));
    }

    @Test
    public void testRegisterMissingProvidersParams_1_oe() {
        final ConfigurationBuilderProvider provider1 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider2 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider3 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final String tagPrefix = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        final CombinedBuilderParametersImpl params2 = new CombinedBuilderParametersImpl();
        params.registerProvider(tagPrefix, provider1);
        params2.registerProvider(tagPrefix, provider2);
        params2.registerProvider(tagPrefix + 1, provider3);
        assertSame("Wrong result", params, params.registerMissingProviders(params2));
    }

    @Test
    public void testRegisterMissingProvidersParams_2_oe() {
        final ConfigurationBuilderProvider provider1 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider2 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider3 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final String tagPrefix = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        final CombinedBuilderParametersImpl params2 = new CombinedBuilderParametersImpl();
        params.registerProvider(tagPrefix, provider1);
        params2.registerProvider(tagPrefix, provider2);
        params2.registerProvider(tagPrefix + 1, provider3);
        // removed other assertion
        assertEquals("Wrong number of providers", 2, params.getProviders().size());
    }

    @Test
    public void testRegisterMissingProvidersParams_3_oe() {
        final ConfigurationBuilderProvider provider1 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider2 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider3 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final String tagPrefix = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        final CombinedBuilderParametersImpl params2 = new CombinedBuilderParametersImpl();
        params.registerProvider(tagPrefix, provider1);
        params2.registerProvider(tagPrefix, provider2);
        params2.registerProvider(tagPrefix + 1, provider3);
        // removed other assertion
        // removed other assertion
        assertSame("Wrong provider (1)", provider1, params.providerForTag(tagPrefix));
    }

    @Test
    public void testRegisterMissingProvidersParams_4_oe() {
        final ConfigurationBuilderProvider provider1 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider2 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final ConfigurationBuilderProvider provider3 = EasyMock.createMock(ConfigurationBuilderProvider.class);
        final String tagPrefix = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        final CombinedBuilderParametersImpl params2 = new CombinedBuilderParametersImpl();
        params.registerProvider(tagPrefix, provider1);
        params2.registerProvider(tagPrefix, provider2);
        params2.registerProvider(tagPrefix + 1, provider3);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertSame("Wrong provider (2)", provider3, params.providerForTag(tagPrefix + 1));
    }

    @Test
    public void testRegisterProvider_1_oe() {
        final ConfigurationBuilderProvider provider = EasyMock.createMock(ConfigurationBuilderProvider.class);
        EasyMock.replay(provider);
        final String tagName = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        assertSame("Wrong result", params, params.registerProvider(tagName, provider));
    }

    @Test
    public void testRegisterProvider_2_oe() {
        final ConfigurationBuilderProvider provider = EasyMock.createMock(ConfigurationBuilderProvider.class);
        EasyMock.replay(provider);
        final String tagName = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        // removed other assertion
        final Map<String, ConfigurationBuilderProvider> providers = params.getProviders();
        assertEquals("Wrong number of providers", 1, providers.size());
    }

    @Test
    public void testRegisterProvider_3_oe() {
        final ConfigurationBuilderProvider provider = EasyMock.createMock(ConfigurationBuilderProvider.class);
        EasyMock.replay(provider);
        final String tagName = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        // removed other assertion
        final Map<String, ConfigurationBuilderProvider> providers = params.getProviders();
        // removed other assertion
        assertSame("Wrong provider (1)", provider, providers.get(tagName));
    }

    @Test
    public void testRegisterProvider_4_oe() {
        final ConfigurationBuilderProvider provider = EasyMock.createMock(ConfigurationBuilderProvider.class);
        EasyMock.replay(provider);
        final String tagName = "testTag";
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        // removed other assertion
        final Map<String, ConfigurationBuilderProvider> providers = params.getProviders();
        // removed other assertion
        // removed other assertion
        assertSame("Wrong provider (2)", provider, params.providerForTag(tagName));
    }

    @Test
    public void testSetBasePath_1_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        final String basePath = ConfigurationAssert.OUT_DIR.getAbsolutePath();
        assertSame("Wrong result", params, params.setBasePath(basePath));
    }

    @Test
    public void testSetBasePath_2_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        final String basePath = ConfigurationAssert.OUT_DIR.getAbsolutePath();
        // removed other assertion
        assertEquals("Wrong base path", basePath, params.getBasePath());
    }

    @Test
    public void testSetBeanProperties_1_oe() throws Exception {
        final BuilderParameters defparams = EasyMock.createMock(BuilderParameters.class);
        EasyMock.replay(defparams);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        BeanHelper.setProperty(params, "basePath", "testPath");
        BeanHelper.setProperty(params, "definitionBuilderParameters", defparams);
        BeanHelper.setProperty(params, "inheritSettings", false);
        assertEquals("Wrong path", "testPath", params.getBasePath());
    }

    @Test
    public void testSetBeanProperties_2_oe() throws Exception {
        final BuilderParameters defparams = EasyMock.createMock(BuilderParameters.class);
        EasyMock.replay(defparams);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        BeanHelper.setProperty(params, "basePath", "testPath");
        BeanHelper.setProperty(params, "definitionBuilderParameters", defparams);
        BeanHelper.setProperty(params, "inheritSettings", false);
        // removed other assertion
        assertSame("Wrong def parameters", defparams, params.getDefinitionBuilderParameters());
    }

    @Test
    public void testSetBeanProperties_3_oe() throws Exception {
        final BuilderParameters defparams = EasyMock.createMock(BuilderParameters.class);
        EasyMock.replay(defparams);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        BeanHelper.setProperty(params, "basePath", "testPath");
        BeanHelper.setProperty(params, "definitionBuilderParameters", defparams);
        BeanHelper.setProperty(params, "inheritSettings", false);
        // removed other assertion
        // removed other assertion
        assertFalse("Wrong inherit flag", params.isInheritSettings());
    }

    @Test
    public void testSetDefinitionBuilder_1_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        assertNull("Got a definition builder", params.getDefinitionBuilder());
    }

    @Test
    public void testSetDefinitionBuilder_2_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        // removed other assertion
        final ConfigurationBuilder<XMLConfiguration> builder = new BasicConfigurationBuilder<>(XMLConfiguration.class);
        assertSame("Wrong result", params, params.setDefinitionBuilder(builder));
    }

    @Test
    public void testSetDefinitionBuilder_3_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        // removed other assertion
        final ConfigurationBuilder<XMLConfiguration> builder = new BasicConfigurationBuilder<>(XMLConfiguration.class);
        // removed other assertion
        assertSame("Builder was not set", builder, params.getDefinitionBuilder());
    }

    @Test
    public void testSetDefinitionBuilderParameters_1_oe() {
        final BuilderParameters defparams = EasyMock.createMock(BuilderParameters.class);
        EasyMock.replay(defparams);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        assertSame("Wrong result", params, params.setDefinitionBuilderParameters(defparams));
    }

    @Test
    public void testSetDefinitionBuilderParameters_2_oe() {
        final BuilderParameters defparams = EasyMock.createMock(BuilderParameters.class);
        EasyMock.replay(defparams);
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        // removed other assertion
        assertSame("Wrong parameters object", defparams, params.getDefinitionBuilderParameters());
    }

    @Test
    public void testSetInheritSettings_1_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        assertTrue("Wrong initial value", params.isInheritSettings());
    }

    @Test
    public void testSetInheritSettings_2_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        // removed other assertion
        assertSame("Wrong result", params, params.setInheritSettings(false));
    }

    @Test
    public void testSetInheritSettings_3_oe() {
        final CombinedBuilderParametersImpl params = new CombinedBuilderParametersImpl();
        // removed other assertion
        // removed other assertion
        assertFalse("Property not set", params.isInheritSettings());
    }

}
