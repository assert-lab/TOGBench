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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junitx.framework.ListAssert;

import org.apache.commons.configuration2.builder.XMLBuilderParametersImpl;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.event.ConfigurationErrorEvent;
import org.apache.commons.configuration2.event.ConfigurationEvent;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.event.EventSource;
import org.apache.commons.configuration2.ex.ConfigurationRuntimeException;
import org.apache.commons.configuration2.sync.NoOpSynchronizer;
import org.apache.commons.configuration2.tree.DefaultExpressionEngine;
import org.apache.commons.configuration2.tree.DefaultExpressionEngineSymbols;
import org.apache.commons.configuration2.tree.ExpressionEngine;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the ConfigurationUtils class
 *
 */
public class TestConfigurationUtils_OE25Dev {
    /**
     * A test Synchronizer implementation which can be cloned.
     */
    private static class CloneableSynchronizer extends NonCloneableSynchronizer implements Cloneable {
        /** A flag whether clone() was called. */
        private final boolean cloned;

        /**
         * Creates a new instance of {@code CloneableSynchronizer} and sets the clone flag.
         *
         * @param clone the clone flag
         */
        public CloneableSynchronizer(final boolean clone) {
            cloned = clone;
        }

        @Override
        public Object clone() {
            return new CloneableSynchronizer(true);
        }

        /**
         * Returns a flag whether this object was cloned.
         *
         * @return the clone flag
         */
        public boolean isCloned() {
            return cloned;
        }
    }

    /**
     * A test Synchronizer implementation which cannot be cloned.
     */
    private static class NonCloneableSynchronizer extends SynchronizerTestImpl {
    }

    /** Constant for the name of a class to be loaded. */
    private static final String CLS_NAME = "org.apache.commons.configuration2.PropertiesConfiguration";

    /** Stores the CCL. */
    private ClassLoader ccl;

    @Before
    public void setUp() throws Exception {
        ccl = Thread.currentThread().getContextClassLoader();
    }

    @After
    public void tearDown() throws Exception {
        Thread.currentThread().setContextClassLoader(ccl);
    }

    /**
     * Tests asEventSource() if an exception is expected.
     */
    @Test(expected = ConfigurationRuntimeException.class)
    public void testAsEventSourceNonSupportedEx() {
        ConfigurationUtils.asEventSource(this, false);
    }

    /**
     * Tests asEventSource() if the passed in object implements this interface.
     */

    /**
     * Tests asEventSource() if a mock object has to be returned.
     */

    /**
     * Tests cloning a configuration that supports this operation.
     */

    /**
     * Tests cloning a configuration that does not support this operation. This should cause an exception.
     */
    @Test(expected = ConfigurationRuntimeException.class)
    public void testCloneConfigurationNotSupported() {
        final Configuration myNonCloneableConfig = new NonCloneableConfiguration();
        ConfigurationUtils.cloneConfiguration(myNonCloneableConfig);
    }

    /**
     * Tests cloning a <b>null</b> configuration.
     */

    /**
     * Tests whether errors are handled correctly by cloneIfPossible().
     */

    /**
     * Tests cloneIfPossible() if the passed in object does not support cloning.
     */

    /**
     * Tests whether cloneIfPossible() can handle null parameters.
     */

    /**
     * Tests whether an object can be cloned which supports cloning.
     */

    /**
     * Tests whether a Synchronizer can be cloned using its clone() method.
     */

    /**
     * Tests cloneSynchronizer() if the argument cannot be cloned.
     */
    @Test(expected = ConfigurationRuntimeException.class)
    public void testCloneSynchronizerFailed() {
        ConfigurationUtils.cloneSynchronizer(new NonCloneableSynchronizer());
    }

    /**
     * Tests whether a new Synchronizer can be created using reflection.
     */

    /**
     * Tests whether the NoOpSyhnchronizer can be cloned.
     */

    /**
     * Tries to clone a null Synchronizer.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCloneSynchronizerNull() {
        ConfigurationUtils.cloneSynchronizer(null);
    }

    /**
     * Tests converting a configuration into a hierarchical one that is already hierarchical.
     */

    /**
     * Tests converting an already hierarchical configuration using an expression engine. The new engine should be set.
     */

    /**
     * Tests converting an already hierarchical configuration using a null expression engine. In this case the expression
     * engine of the configuration should not be touched.
     */

    /**
     * Tests converting a null configuration to a hierarchical one. The result should be null, too.
     */

    /**
     * Tests converting a configuration into a hierarchical one.
     */

    /**
     * Tests converting a configuration into a hierarchical one if some of its properties contain escaped list delimiter
     * characters.
     */

    /**
     * Tests converting a configuration to a hierarchical one using a specific expression engine.
     */

    /**
     * Tests converting a configuration to a hierarchical one that contains a property with multiple values. This test is
     * related to CONFIGURATION-346.
     */

    /**
     * Tests that the structure of the resulting hierarchical configuration does not depend on the order of properties in
     * the source configuration. This test is related to CONFIGURATION-604.
     */

    /**
     * Tests whether runtime exceptions can be enabled.
     */
    @Test(expected = ConfigurationRuntimeException.class)
    public void testEnableRuntimeExceptions() {
        final PropertiesConfiguration config = new PropertiesConfiguration() {
            @Override
            protected void addPropertyDirect(final String key, final Object value) {
                // always simulate an exception
                fireError(ConfigurationErrorEvent.WRITE, ConfigurationEvent.ADD_PROPERTY, key, value, new RuntimeException("A faked exception!"));
            }
        };
        config.clearErrorListeners();
        ConfigurationUtils.enableRuntimeExceptions(config);
        config.addProperty("test", "testValue");
    }

    /**
     * Tries to enable runtime exceptions for a configuration that does not inherit from EventSource. This should cause an
     * exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEnableRuntimeExceptionsInvalid() {
        final Configuration c = EasyMock.createMock(Configuration.class);
        EasyMock.replay(c);
        ConfigurationUtils.enableRuntimeExceptions(c);
    }

    /**
     * Tries to enable runtime exceptions for a null configuration. This should cause an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEnableRuntimeExceptionsNull() {
        ConfigurationUtils.enableRuntimeExceptions(null);
    }

    /**
     * Tests whether a class can be loaded if it is not found by the CCL.
     */

    /**
     * Tests whether a class can be loaded if there is no CCL.
     */

    /**
     * Tests whether a class can be loaded from CCL.
     */

    /**
     * Tests loadClassNoEx() if the class can be resolved.
     */

    /**
     * Tests loadClassNoEx() if the class cannot be resolved.
     */
    @Test(expected = ConfigurationRuntimeException.class)
    public void testLoadClassNoExNotFound() {
        ConfigurationUtils.loadClassNoEx("a non existing class!");
    }

    /**
     * Tests the behavior of loadClass() for a non-existing class.
     */
    @Test(expected = ClassNotFoundException.class)
    public void testLoadClassNotFound() throws ClassNotFoundException {
        ConfigurationUtils.loadClass("a non existing class!");
    }

    @Test
    public void testAppend_1_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("key1", "value1");
        conf1.addProperty("key2", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("key1", "value3");
        conf2.addProperty("key2", "value4");

        ConfigurationUtils.append(conf1, conf2);

        List<Object> expected = new ArrayList<>();
        expected.add("value3");
        expected.add("value1");
        ListAssert.assertEquals("'key1' property", expected, conf2.getList("key1"));
    }

    @Test
    public void testAppend_2_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("key1", "value1");
        conf1.addProperty("key2", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("key1", "value3");
        conf2.addProperty("key2", "value4");

        ConfigurationUtils.append(conf1, conf2);

        List<Object> expected = new ArrayList<>();
        expected.add("value3");
        expected.add("value1");

        expected = new ArrayList<>();
        expected.add("value4");
        expected.add("value2");
        ListAssert.assertEquals("'key2' property", expected, conf2.getList("key2"));
    }

    @Test
    public void testAsEventSourceSupported_1_oe() {
        final XMLConfiguration src = new XMLConfiguration();
        assertSame("Wrong result", src, ConfigurationUtils.asEventSource(src, true));
    }

    @Test
    public void testAsEventSourceUnsupportedMock_1_oe() {
        final EventListener<ConfigurationEvent> cl = EasyMock.createMock(EventListener.class);
        EasyMock.replay(cl);
        final EventSource source = ConfigurationUtils.asEventSource(this, true);
        source.addEventListener(ConfigurationEvent.ANY, cl);
        assertFalse("Wrong result (1)", source.removeEventListener(ConfigurationEvent.ANY, cl));
    }

    @Test
    public void testCloneConfiguration_1_oe() {
        final BaseHierarchicalConfiguration conf = new BaseHierarchicalConfiguration();
        conf.addProperty("test", "yes");
        final BaseHierarchicalConfiguration copy = (BaseHierarchicalConfiguration) ConfigurationUtils.cloneConfiguration(conf);
        assertNotSame("Same object was returned", conf, copy);
    }

    @Test
    public void testCloneConfiguration_2_oe() {
        final BaseHierarchicalConfiguration conf = new BaseHierarchicalConfiguration();
        conf.addProperty("test", "yes");
        final BaseHierarchicalConfiguration copy = (BaseHierarchicalConfiguration) ConfigurationUtils.cloneConfiguration(conf);
        assertEquals("Property was not cloned", "yes", copy.getString("test"));
    }

    @Test
    public void testCloneConfigurationNull_1_oe() {
        assertNull("Wrong return value", ConfigurationUtils.cloneConfiguration(null));
    }

    @Test
    public void testCloneIfPossibleError_1_oe() {
        final XMLBuilderParametersImpl params = new XMLBuilderParametersImpl() {
            @Override
            public XMLBuilderParametersImpl clone() {
                throw new ConfigurationRuntimeException();
            }
        };
        assertSame("Wrong result", params, ConfigurationUtils.cloneIfPossible(params));
    }

    @Test
    public void testCloneIfPossibleNotSupported_1_oe() {
        final Long value = 20130116221714L;
        assertSame("Wrong result", value, ConfigurationUtils.cloneIfPossible(value));
    }

    @Test
    public void testCloneIfPossibleNull_1_oe() {
        assertNull("Wrong result", ConfigurationUtils.cloneIfPossible(null));
    }

    @Test
    public void testCloneIfPossibleSupported_1_oe() {
        final XMLBuilderParametersImpl params = new XMLBuilderParametersImpl();
        params.setPublicID("testID");
        params.setSchemaValidation(true);
        final XMLBuilderParametersImpl clone = (XMLBuilderParametersImpl) ConfigurationUtils.cloneIfPossible(params);
        assertNotSame("No clone was created", params, clone);
    }

    @Test
    public void testCloneIfPossibleSupported_2_oe() {
        final XMLBuilderParametersImpl params = new XMLBuilderParametersImpl();
        params.setPublicID("testID");
        params.setSchemaValidation(true);
        final XMLBuilderParametersImpl clone = (XMLBuilderParametersImpl) ConfigurationUtils.cloneIfPossible(params);
        final Map<String, Object> map = clone.getParameters();
        for (final Map.Entry<String, Object> e : params.getParameters().entrySet()) {
            if (!e.getKey().startsWith("config-")) {
                assertEquals("Wrong value for field " + e.getKey(), e.getValue(), map.get(e.getKey()));
    }
    }
    }

    @Test
    public void testCloneSynchronizerClone_1_oe() {
        final CloneableSynchronizer sync = new CloneableSynchronizer(false);
        final CloneableSynchronizer sync2 = (CloneableSynchronizer) ConfigurationUtils.cloneSynchronizer(sync);
        assertTrue("Not cloned", sync2.isCloned());
    }

    @Test
    public void testCloneSynchronizerNewInstance_1_oe() {
        final SynchronizerTestImpl sync = new SynchronizerTestImpl();
        final SynchronizerTestImpl sync2 = (SynchronizerTestImpl) ConfigurationUtils.cloneSynchronizer(sync);
        assertNotNull("Clone is null", sync2);
    }

    @Test
    public void testCloneSynchronizerNewInstance_2_oe() {
        final SynchronizerTestImpl sync = new SynchronizerTestImpl();
        final SynchronizerTestImpl sync2 = (SynchronizerTestImpl) ConfigurationUtils.cloneSynchronizer(sync);
        assertNotSame("Same instance", sync, sync2);
    }

    @Test
    public void testCloneSynchronizerNoOp_1_oe() {
        assertSame("Wrong result", NoOpSynchronizer.INSTANCE, ConfigurationUtils.cloneSynchronizer(NoOpSynchronizer.INSTANCE));
    }

    @Test
    public void testConvertHierarchicalToHierarchical_1_oe() {
        final Configuration conf = new BaseHierarchicalConfiguration();
        conf.addProperty("test", "yes");
        assertSame("Wrong configuration returned", conf, ConfigurationUtils.convertToHierarchical(conf));
    }

    @Test
    public void testConvertHierarchicalToHierarchicalEngine_1_oe() {
        final BaseHierarchicalConfiguration hc = new BaseHierarchicalConfiguration();
        final ExpressionEngine engine = new DefaultExpressionEngine(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
        assertSame("Created new configuration", hc, ConfigurationUtils.convertToHierarchical(hc, engine));
    }

    @Test
    public void testConvertHierarchicalToHierarchicalNullEngine_1_oe() {
        final BaseHierarchicalConfiguration hc = new BaseHierarchicalConfiguration();
        final ExpressionEngine engine = new DefaultExpressionEngine(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
        hc.setExpressionEngine(engine);
        assertSame("Created new configuration", hc, ConfigurationUtils.convertToHierarchical(hc, null));
    }

    @Test
    public void testConvertHierarchicalToHierarchicalNullEngine_2_oe() {
        final BaseHierarchicalConfiguration hc = new BaseHierarchicalConfiguration();
        final ExpressionEngine engine = new DefaultExpressionEngine(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS);
        hc.setExpressionEngine(engine);
        assertSame("Expression engine was changed", engine, hc.getExpressionEngine());
    }

    @Test
    public void testConvertNullToHierarchical_1_oe() {
        assertNull("Wrong conversion result for null config", ConfigurationUtils.convertToHierarchical(null));
    }

    @Test
    public void testConvertToHierarchical_1_oe() {
        final Configuration conf = new BaseConfiguration();
        for (int i = 0; i < 10; i++) {
            conf.addProperty("test" + i, "value" + i);
            conf.addProperty("test.list", "item" + i);
        }

        final BaseHierarchicalConfiguration hc = (BaseHierarchicalConfiguration) ConfigurationUtils.convertToHierarchical(conf);
        for (final Iterator<String> it = conf.getKeys(); it.hasNext();) {
            final String key = it.next();
            assertEquals("Wrong value for key " + key, conf.getProperty(key), hc.getProperty(key));
    }
    }

    @Test
    public void testConvertToHierarchicalDelimiters_1_oe() {
        final BaseConfiguration conf = new BaseConfiguration();
        conf.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        conf.addProperty("test.key", "1\\,2\\,3");
        assertEquals("Wrong property value", "1,2,3", conf.getString("test.key"));
    }

    @Test
    public void testConvertToHierarchicalDelimiters_2_oe() {
        final BaseConfiguration conf = new BaseConfiguration();
        conf.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        conf.addProperty("test.key", "1\\,2\\,3");
        final HierarchicalConfiguration<?> hc = ConfigurationUtils.convertToHierarchical(conf);
        assertEquals("Escaped list delimiters not correctly handled", "1,2,3", hc.getString("test.key"));
    }

    @Test
    public void testConvertToHierarchicalEngine_1_oe() {
        final Configuration conf = new BaseConfiguration();
        conf.addProperty("test(a)", Boolean.TRUE);
        conf.addProperty("test(b)", Boolean.FALSE);
        final DefaultExpressionEngine engine = new DefaultExpressionEngine(
            new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS).setIndexStart("[").setIndexEnd("]").create());
        final HierarchicalConfiguration<?> hc = ConfigurationUtils.convertToHierarchical(conf, engine);
        assertTrue("Wrong value for test(a)", hc.getBoolean("test(a)"));
    }

    @Test
    public void testConvertToHierarchicalEngine_2_oe() {
        final Configuration conf = new BaseConfiguration();
        conf.addProperty("test(a)", Boolean.TRUE);
        conf.addProperty("test(b)", Boolean.FALSE);
        final DefaultExpressionEngine engine = new DefaultExpressionEngine(
            new DefaultExpressionEngineSymbols.Builder(DefaultExpressionEngineSymbols.DEFAULT_SYMBOLS).setIndexStart("[").setIndexEnd("]").create());
        final HierarchicalConfiguration<?> hc = ConfigurationUtils.convertToHierarchical(conf, engine);
        assertFalse("Wrong value for test(b)", hc.getBoolean("test(b)"));
    }

    @Test
    public void testConvertToHierarchicalMultiValues_1_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        config.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        config.addProperty("test", "1,2,3");
        final HierarchicalConfiguration<?> hc = ConfigurationUtils.convertToHierarchical(config);
        assertEquals("Wrong value 1", 1, hc.getInt("test(0)"));
    }

    @Test
    public void testConvertToHierarchicalMultiValues_2_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        config.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        config.addProperty("test", "1,2,3");
        final HierarchicalConfiguration<?> hc = ConfigurationUtils.convertToHierarchical(config);
        assertEquals("Wrong value 2", 2, hc.getInt("test(1)"));
    }

    @Test
    public void testConvertToHierarchicalMultiValues_3_oe() {
        final BaseConfiguration config = new BaseConfiguration();
        config.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
        config.addProperty("test", "1,2,3");
        final HierarchicalConfiguration<?> hc = ConfigurationUtils.convertToHierarchical(config);
        assertEquals("Wrong value 3", 3, hc.getInt("test(2)"));
    }

    @Test
    public void testConvertToHierarchicalOrderOfProperties_1_oe() {
        final PropertiesConfiguration config = new PropertiesConfiguration();
        config.addProperty("x.y.z", true);
        config.addProperty("x.y", true);
        @SuppressWarnings("unchecked")
        final HierarchicalConfiguration<ImmutableNode> hc = (HierarchicalConfiguration<ImmutableNode>) ConfigurationUtils.convertToHierarchical(config);
        final ImmutableNode rootNode = hc.getNodeModel().getNodeHandler().getRootNode();
        final ImmutableNode nodeX = rootNode.getChildren().get(0);
        assertEquals("Wrong number of children of x", 1, nodeX.getChildren().size());
    }

    @Test
    public void testCopy_1_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("key1", "value1");
        conf1.addProperty("key2", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("key1", "value3");
        conf2.addProperty("key2", "value4");

        ConfigurationUtils.copy(conf1, conf2);

        assertEquals("'key1' property", "value1", conf2.getProperty("key1"));
    }

    @Test
    public void testCopy_2_oe() {
        final Configuration conf1 = new BaseConfiguration();
        conf1.addProperty("key1", "value1");
        conf1.addProperty("key2", "value2");

        final Configuration conf2 = new BaseConfiguration();
        conf2.addProperty("key1", "value3");
        conf2.addProperty("key2", "value4");

        ConfigurationUtils.copy(conf1, conf2);

        assertEquals("'key2' property", "value2", conf2.getProperty("key2"));
    }

    @Test
    public void testLoadClassCCLNotFound_1_oe() throws ClassNotFoundException {
        Thread.currentThread().setContextClassLoader(new ClassLoader() {
            @Override
            public Class<?> loadClass(final String name) throws ClassNotFoundException {
                throw new ClassNotFoundException(name);
            }
        });
        assertEquals("Wrong class", CLS_NAME, ConfigurationUtils.loadClass(CLS_NAME).getName());
    }

    @Test
    public void testLoadClassCCLNull_1_oe() throws ClassNotFoundException {
        Thread.currentThread().setContextClassLoader(null);
        assertEquals("Wrong class", CLS_NAME, ConfigurationUtils.loadClass(CLS_NAME).getName());
    }

    @Test
    public void testLoadClassFromCCL_1_oe() throws ClassNotFoundException {
        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
        assertEquals("Wrong class", CLS_NAME, ConfigurationUtils.loadClass(CLS_NAME).getName());
    }

    @Test
    public void testLoadClassNoExFound_1_oe() {
        assertEquals("Wrong class", CLS_NAME, ConfigurationUtils.loadClassNoEx(CLS_NAME).getName());
    }

    @Test
    public void testToString_1_oe() {
        final Configuration config = new BaseConfiguration();
        final String lineSeparator = System.lineSeparator();

        assertEquals("String representation of an empty configuration", "", ConfigurationUtils.toString(config));
    }

    @Test
    public void testToString_2_oe() {
        final Configuration config = new BaseConfiguration();
        final String lineSeparator = System.lineSeparator();


        config.setProperty("one", "1");
        assertEquals("String representation of a configuration", "one=1", ConfigurationUtils.toString(config));
    }

    @Test
    public void testToString_3_oe() {
        final Configuration config = new BaseConfiguration();
        final String lineSeparator = System.lineSeparator();


        config.setProperty("one", "1");

        config.setProperty("two", "2");
        assertEquals("String representation of a configuration", "one=1" + lineSeparator + "two=2", ConfigurationUtils.toString(config));
    }

    @Test
    public void testToString_4_oe() {
        final Configuration config = new BaseConfiguration();
        final String lineSeparator = System.lineSeparator();


        config.setProperty("one", "1");

        config.setProperty("two", "2");

        config.clearProperty("one");
        assertEquals("String representation of a configuration", "two=2", ConfigurationUtils.toString(config));
    }

    @Test
    public void testToString_5_oe() {
        final Configuration config = new BaseConfiguration();
        final String lineSeparator = System.lineSeparator();


        config.setProperty("one", "1");

        config.setProperty("two", "2");

        config.clearProperty("one");

        config.setProperty("one", "1");
        assertEquals("String representation of a configuration", "two=2" + lineSeparator + "one=1", ConfigurationUtils.toString(config));
    }

}
