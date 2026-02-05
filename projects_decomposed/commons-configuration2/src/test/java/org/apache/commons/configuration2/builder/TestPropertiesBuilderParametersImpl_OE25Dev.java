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
package org.apache.commons.configuration2.builder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import java.util.Map;

import org.apache.commons.configuration2.ConfigurationConsumer;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;
import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@code PropertiesBuilderParametersImpl}.
 *
 */
public class TestPropertiesBuilderParametersImpl_OE25Dev {
    /** The parameters object to be tested. */
    private PropertiesBuilderParametersImpl params;

    @Before
    public void setUp() throws Exception {
        params = new PropertiesBuilderParametersImpl();
    }

    /**
     * Tests whether properties can be set using BeanUtils.
     */

    /**
     * Tests whether properties can be inherited.
     */

    /**
     * Tests whether the include listener can be set.
     */

    /**
     * Tests whether the IncludeListener property can be correctly set.
     */

    /**
     * Tests whether the includesAllowed property can be set.
     */

    /**
     * Tests whether the IO factory can be set.
     */

    /**
     * Tests whether the IOFactory property can be correctly set. This test is related to CONFIGURATION-648.
     */

    /**
     * Tests whether the layout object can be set.
     */

@Test
    public void testBeanPropertiesAccess_1_oe() throws Exception {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        EasyMock.replay(factory);
        BeanHelper.setProperty(params, "IOFactory", factory);
        BeanHelper.setProperty(params, "throwExceptionOnMissing", Boolean.TRUE);
        BeanHelper.setProperty(params, "fileName", "test.properties");
        assertEquals("Wrong file name", "test.properties", params.getFileHandler().getFileName());
    }

@Test
    public void testBeanPropertiesAccess_2_oe() throws Exception {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        EasyMock.replay(factory);
        BeanHelper.setProperty(params, "IOFactory", factory);
        BeanHelper.setProperty(params, "throwExceptionOnMissing", Boolean.TRUE);
        BeanHelper.setProperty(params, "fileName", "test.properties");
        // removed other assertion
        final Map<String, Object> paramsMap = params.getParameters();
        assertEquals("Wrong exception flag", Boolean.TRUE, paramsMap.get("throwExceptionOnMissing"));
    }

@Test
    public void testBeanPropertiesAccess_3_oe() throws Exception {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        EasyMock.replay(factory);
        BeanHelper.setProperty(params, "IOFactory", factory);
        BeanHelper.setProperty(params, "throwExceptionOnMissing", Boolean.TRUE);
        BeanHelper.setProperty(params, "fileName", "test.properties");
        // removed other assertion
        final Map<String, Object> paramsMap = params.getParameters();
        // removed other assertion
        assertSame("Factory not set", factory, params.getParameters().get("IOFactory"));
    }

@Test
    public void testInheritFrom_1_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        params.setIOFactory(factory).setIncludeListener(includeListener).setIncludesAllowed(false).setLayout(new PropertiesConfigurationLayout())
            .setThrowExceptionOnMissing(true);
        final PropertiesBuilderParametersImpl params2 = new PropertiesBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        assertEquals("Exception flag not set", Boolean.TRUE, parameters.get("throwExceptionOnMissing"));
    }

@Test
    public void testInheritFrom_2_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        params.setIOFactory(factory).setIncludeListener(includeListener).setIncludesAllowed(false).setLayout(new PropertiesConfigurationLayout())
            .setThrowExceptionOnMissing(true);
        final PropertiesBuilderParametersImpl params2 = new PropertiesBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        assertEquals("IncludeListener not set", includeListener, parameters.get("includeListener"));
    }

@Test
    public void testInheritFrom_3_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        params.setIOFactory(factory).setIncludeListener(includeListener).setIncludesAllowed(false).setLayout(new PropertiesConfigurationLayout())
            .setThrowExceptionOnMissing(true);
        final PropertiesBuilderParametersImpl params2 = new PropertiesBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        // removed other assertion
        assertEquals("IOFactory not set", factory, parameters.get("IOFactory"));
    }

@Test
    public void testInheritFrom_4_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        params.setIOFactory(factory).setIncludeListener(includeListener).setIncludesAllowed(false).setLayout(new PropertiesConfigurationLayout())
            .setThrowExceptionOnMissing(true);
        final PropertiesBuilderParametersImpl params2 = new PropertiesBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Include flag not set", Boolean.FALSE, parameters.get("includesAllowed"));
    }

@Test
    public void testInheritFrom_5_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        params.setIOFactory(factory).setIncludeListener(includeListener).setIncludesAllowed(false).setLayout(new PropertiesConfigurationLayout())
            .setThrowExceptionOnMissing(true);
        final PropertiesBuilderParametersImpl params2 = new PropertiesBuilderParametersImpl();

        params2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = params2.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNull("Layout was copied", parameters.get("layout"));
    }

@Test
    public void testSetIncludeListener_1_oe() {
        final ConfigurationConsumer<ConfigurationException> includeListener = EasyMock.createMock(ConfigurationConsumer.class);
        EasyMock.replay(includeListener);
        assertSame("Wrong result", params, params.setIncludeListener(includeListener));
    }

@Test
    public void testSetIncludeListenerProperty_1_oe() throws ConfigurationException {
        final ConfigurationConsumer<ConfigurationException> includeListener = PropertiesConfiguration.DEFAULT_INCLUDE_LISTENER;
        final ConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(params.setIncludeListener(includeListener));

        final PropertiesConfiguration config = builder.getConfiguration();
        assertEquals("Wrong IncludeListener", includeListener, config.getIncludeListener());
    }

@Test
    public void testSetIncludesAllowed_1_oe() {
        assertSame("Wrong result", params, params.setIncludesAllowed(true));
    }

@Test
    public void testSetIOFactory_1_oe() {
        final PropertiesConfiguration.IOFactory factory = EasyMock.createMock(PropertiesConfiguration.IOFactory.class);
        EasyMock.replay(factory);
        assertSame("Wrong result", params, params.setIOFactory(factory));
    }

@Test
    public void testSetIOFactoryProperty_1_oe() throws ConfigurationException {
        final PropertiesConfiguration.IOFactory factory = new PropertiesConfiguration.DefaultIOFactory();
        final ConfigurationBuilder<PropertiesConfiguration> builder = new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(params.setIOFactory(factory));

        final PropertiesConfiguration config = builder.getConfiguration();
        assertEquals("Wrong IO factory", factory, config.getIOFactory());
    }

@Test
    public void testSetLayout_1_oe() {
        final PropertiesConfigurationLayout layout = new PropertiesConfigurationLayout();
        assertSame("Wrong result", params, params.setLayout(layout));
    }

}
