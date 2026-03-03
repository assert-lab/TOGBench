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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration2.BaseConfiguration;
import org.apache.commons.configuration2.BaseHierarchicalConfiguration;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.Initializable;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.beanutils.BeanCreationContext;
import org.apache.commons.configuration2.beanutils.BeanDeclaration;
import org.apache.commons.configuration2.beanutils.BeanFactory;
import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.beanutils.DefaultBeanFactory;
import org.apache.commons.configuration2.beanutils.XMLBeanDeclaration;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.event.ConfigurationErrorEvent;
import org.apache.commons.configuration2.event.ConfigurationEvent;
import org.apache.commons.configuration2.event.ErrorListenerTestImpl;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.event.EventListenerRegistrationData;
import org.apache.commons.configuration2.event.EventListenerTestImpl;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.ex.ConfigurationRuntimeException;
import org.apache.commons.configuration2.reloading.ReloadingController;
import org.apache.commons.configuration2.reloading.ReloadingDetector;
import org.easymock.EasyMock;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for {@code BasicConfigurationBuilder}.
 *
 */
public class TestBasicConfigurationBuilder_OE25Dev {
    /**
     * A test thread class for testing whether the builder's result object can be requested concurrently.
     */
    private static class AccessBuilderThread extends Thread {
        /** A latch for controlling the start of the thread. */
        private final CountDownLatch startLatch;

        /** A latch for controlling the end of the thread. */
        private final CountDownLatch endLatch;

        /** The builder to be accessed. */
        private final ConfigurationBuilder<?> builder;

        /** The result object obtained from the builder. */
        private volatile Object result;

        /**
         * Creates a new instance of {@code AccessBuilderThread}.
         *
         * @param lstart the latch for controlling the thread start
         * @param lend the latch for controlling the thread end
         * @param bldr the builder to be tested
         */
        public AccessBuilderThread(final CountDownLatch lstart, final CountDownLatch lend, final ConfigurationBuilder<?> bldr) {
            startLatch = lstart;
            endLatch = lend;
            builder = bldr;
        }

        @Override
        public void run() {
            try {
                startLatch.await();
                result = builder.getConfiguration();
            } catch (final Exception ex) {
                result = ex;
            } finally {
                endLatch.countDown();
            }
        }
    }

    /**
     * A builder test implementation which allows checking exception handling when creating new configuration objects.
     */
    private static class BasicConfigurationBuilderInitFailImpl extends BasicConfigurationBuilder<PropertiesConfiguration> {
        public BasicConfigurationBuilderInitFailImpl(final boolean allowFailOnInit) {
            super(PropertiesConfiguration.class, null, allowFailOnInit);
        }

        /**
         * {@inheritDoc} This implementation only throws an exception.
         */
        @Override
        protected void initResultInstance(final PropertiesConfiguration obj) throws ConfigurationException {
            throw new ConfigurationException("Initialization test exception!");
        }
    }

    /**
     * A test configuration implementation which also implements Initializable.
     */
    public static class InitializableConfiguration extends BaseConfiguration implements Initializable {
        /** A property which is initialized if the builder works as expected. */
        private String initProperty;

        public String getInitProperty() {
            return initProperty;
        }

        /**
         * Sets the value of the initProperty member based on other flag values. This tests whether the method is called after
         * other properties have been set.
         */
        @Override
        public void initialize() {
            initProperty = "Initialized with flag " + isThrowExceptionOnMissing();
        }
    }

    /** A test list delimiter handler. */
    private static ListDelimiterHandler listHandler;

    /**
     * Creates a mock for an event listener.
     *
     * @return the event listener mock
     */
    private static EventListener<ConfigurationEvent> createEventListener() {
        return EasyMock.createMock(EventListener.class);
    }

    /**
     * Creates a map with test initialization parameters.
     *
     * @return the map with parameters
     */
    private static Map<String, Object> createTestParameters() {
        final Map<String, Object> params = new HashMap<>();
        params.put("throwExceptionOnMissing", Boolean.TRUE);
        params.put("listDelimiterHandler", listHandler);
        return params;
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        listHandler = new DefaultListDelimiterHandler(';');
    }

    /**
     * Tests whether configuration listeners can be added.
     */

    /**
     * Tests whether additional parameters can be added.
     */

    /**
     * Tests whether null parameters are handled correctly by addParameters().
     */

    /**
     * Tests whether a configured BeanHelper is used for result creation.
     */

    /**
     * Tests whether parameters can be set using the configure() method.
     */

    /**
     * Tests whether a builder can be connected to a reloading controller.
     */

    /**
     * Tries to connect to a null reloading controller.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConnectToReloadingControllerNull() {
        final BasicConfigurationBuilder<Configuration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.connectToReloadingController(null);
    }

    /**
     * Tests whether event listeners can be copied to another builder.
     */

    /**
     * Tests whether configuration listeners can be defined via the configure() method.
     */

    /**
     * Tests whether the builder can create a correctly initialized configuration object.
     */

    /**
     * Tests whether the builder can be accessed by multiple threads and that only a single result object is produced.
     */

    /**
     * Tests that the map with parameters cannot be modified.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetParametersModify() {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class,
            createTestParameters());
        builder.getParameters().clear();
    }

    /**
     * Tests whether a check for the correct bean class is made.
     */
    @Test(expected = ConfigurationRuntimeException.class)
    public void testGetResultDeclarationInvalidBeanClass() throws ConfigurationException {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class,
            createTestParameters()) {
            @Override
            protected BeanDeclaration createResultDeclaration(final Map<String, Object> params) {
                return new XMLBeanDeclaration(new BaseHierarchicalConfiguration(), "bean", true, Object.class.getName());
            }
        };
        builder.getConfiguration();
    }

    /**
     * Tests whether a configuration implementing {@code Initializable} is correctly handled.
     */

    /**
     * Tests an exception during configuration initialization if the allowFailOnInit flag is true.
     */

    /**
     * Tests an exception during configuration initialization if the allowFailOnInit flag is false.
     */
    @Test(expected = ConfigurationException.class)
    public void testInitializationErrorNotAllowed() throws ConfigurationException {
        final BasicConfigurationBuilderInitFailImpl builder = new BasicConfigurationBuilderInitFailImpl(false);
        builder.getConfiguration();
    }

    /**
     * Tries to create an instance without a result class.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInitNoClass() {
        new BasicConfigurationBuilder<Configuration>(null);
    }

    /**
     * Tests whether initialization parameters can be passed to the constructor.
     */

    /**
     * Tests whether a copy of the passed in parameters is created.
     */

    /**
     * Tests whether null parameters are handled correctly.
     */

    /**
     * Tests whether configuration listeners can be removed.
     */

    /**
     * Tests whether configuration listeners are removed from the managed configuration when the builder's result object is
     * reset.
     */
    @Test
    public void testRemoveConfigurationListenersOnReset() throws ConfigurationException {
        final EventListenerTestImpl listener = new EventListenerTestImpl(null);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(new EventListenerParameters().addEventListener(ConfigurationEvent.ANY, listener));
        final PropertiesConfiguration config = builder.getConfiguration();
        builder.resetResult();
        config.addProperty("foo", "bar");
        listener.done();
    }

    /**
     * Tests whether parameters starting with a reserved prefix are filtered out before result objects are initialized.
     */

    /**
     * Tests a full reset of the builder.
     */

    /**
     * Tests whether all parameters can be reset.
     */

    /**
     * Tests whether a reset of the result object can be performed.
     */

    /**
     * Tests whether new parameters can be set to replace existing ones.
     */

    @Test
    public void testAddConfigurationListener_1_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        EasyMock.replay(l1, l2);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY, l1);
        final PropertiesConfiguration config = builder.getConfiguration();
        builder.addEventListener(ConfigurationEvent.ANY, l2);
        final Collection<EventListener<? super ConfigurationEvent>> listeners = config.getEventListeners(ConfigurationEvent.ANY);
        assertTrue("Listener 1 not registered", listeners.contains(l1));
    }

    @Test
    public void testAddConfigurationListener_2_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        EasyMock.replay(l1, l2);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY, l1);
        final PropertiesConfiguration config = builder.getConfiguration();
        builder.addEventListener(ConfigurationEvent.ANY, l2);
        final Collection<EventListener<? super ConfigurationEvent>> listeners = config.getEventListeners(ConfigurationEvent.ANY);
        assertTrue("Listener 2 not registered", listeners.contains(l2));
    }

    @Test
    public void testAddParameters_1_oe() {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class,
            createTestParameters());
        final Map<String, Object> params = createTestParameters();
        params.put("anotherParameter", "value");
        assertSame("Wrong result", builder, builder.addParameters(params));
    }

    @Test
    public void testAddParameters_2_oe() {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class,
            createTestParameters());
        final Map<String, Object> params = createTestParameters();
        params.put("anotherParameter", "value");
        final Map<String, Object> params2 = builder.getParameters();
        assertTrue("No original parameters", params2.keySet().containsAll(createTestParameters().keySet()));
    }

    @Test
    public void testAddParametersNull_1_oe() {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class,
            createTestParameters());
        final Map<String, Object> params = builder.getParameters();
        builder.addParameters(null);
        assertEquals("Parameters changed", params, builder.getParameters());
    }

    @Test
    public void testBeanHelperInConfiguration_1_oe() throws ConfigurationException {
        final Set<Class<?>> classesPassedToFactory = new HashSet<>();
        final BeanFactory factory = new DefaultBeanFactory() {
            @Override
            public Object createBean(final BeanCreationContext bcc) throws Exception {
                classesPassedToFactory.add(bcc.getBeanClass());
                return super.createBean(bcc);
            }
        };
        final BeanHelper helper = new BeanHelper(factory);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.configure(new BasicBuilderParameters().setBeanHelper(helper));
        final PropertiesConfiguration config = builder.getConfiguration();
        assertTrue("BeanFactory was not used correctly", classesPassedToFactory.contains(config.getClass()));
    }

    @Test
    public void testConfigure_1_oe() {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(new BasicBuilderParameters().setListDelimiterHandler(listHandler).setThrowExceptionOnMissing(true));
        final Map<String, Object> params2 = new HashMap<>(builder.getParameters());
        assertEquals("Wrong parameters", createTestParameters(), params2);
    }

    @Test
    public void testConnectToReloadingController_1_oe() throws ConfigurationException {
        final ReloadingDetector detector = EasyMock.createNiceMock(ReloadingDetector.class);
        EasyMock.expect(detector.isReloadingRequired()).andReturn(Boolean.TRUE);
        EasyMock.replay(detector);
        final ReloadingController controller = new ReloadingController(detector);
        final BasicConfigurationBuilder<Configuration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        final Configuration configuration = builder.getConfiguration();

        builder.connectToReloadingController(controller);
        controller.checkForReloading(null);
        assertTrue("Not in reloading state", controller.isInReloadingState());
    }

    @Test
    public void testConnectToReloadingController_2_oe() throws ConfigurationException {
        final ReloadingDetector detector = EasyMock.createNiceMock(ReloadingDetector.class);
        EasyMock.expect(detector.isReloadingRequired()).andReturn(Boolean.TRUE);
        EasyMock.replay(detector);
        final ReloadingController controller = new ReloadingController(detector);
        final BasicConfigurationBuilder<Configuration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        final Configuration configuration = builder.getConfiguration();

        builder.connectToReloadingController(controller);
        controller.checkForReloading(null);
        assertNotSame("No new configuration created", configuration, builder.getConfiguration());
    }

    @Test
    public void testCopyEventListeners_1_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        final EventListener<ConfigurationErrorEvent> l3 = new ErrorListenerTestImpl(null);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l2);
        builder.addEventListener(ConfigurationErrorEvent.ANY, l3);
        final BasicConfigurationBuilder<XMLConfiguration> builder2 = new BasicConfigurationBuilder<>(XMLConfiguration.class);
        builder.copyEventListeners(builder2);
        final XMLConfiguration config = builder2.getConfiguration();
        Collection<EventListener<? super ConfigurationEvent>> listeners = config.getEventListeners(ConfigurationEvent.ANY);
        assertEquals("Wrong number of listeners", 1, listeners.size());
    }

    @Test
    public void testCopyEventListeners_2_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        final EventListener<ConfigurationErrorEvent> l3 = new ErrorListenerTestImpl(null);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l2);
        builder.addEventListener(ConfigurationErrorEvent.ANY, l3);
        final BasicConfigurationBuilder<XMLConfiguration> builder2 = new BasicConfigurationBuilder<>(XMLConfiguration.class);
        builder.copyEventListeners(builder2);
        final XMLConfiguration config = builder2.getConfiguration();
        Collection<EventListener<? super ConfigurationEvent>> listeners = config.getEventListeners(ConfigurationEvent.ANY);
        assertTrue("Wrong listener", listeners.contains(l1));
    }

    @Test
    public void testCopyEventListeners_3_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        final EventListener<ConfigurationErrorEvent> l3 = new ErrorListenerTestImpl(null);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l2);
        builder.addEventListener(ConfigurationErrorEvent.ANY, l3);
        final BasicConfigurationBuilder<XMLConfiguration> builder2 = new BasicConfigurationBuilder<>(XMLConfiguration.class);
        builder.copyEventListeners(builder2);
        final XMLConfiguration config = builder2.getConfiguration();
        Collection<EventListener<? super ConfigurationEvent>> listeners = config.getEventListeners(ConfigurationEvent.ANY);
        listeners = config.getEventListeners(ConfigurationEvent.ANY_HIERARCHICAL);
        assertEquals("Wrong number of listeners for hierarchical", 2, listeners.size());
    }

    @Test
    public void testCopyEventListeners_4_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        final EventListener<ConfigurationErrorEvent> l3 = new ErrorListenerTestImpl(null);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l2);
        builder.addEventListener(ConfigurationErrorEvent.ANY, l3);
        final BasicConfigurationBuilder<XMLConfiguration> builder2 = new BasicConfigurationBuilder<>(XMLConfiguration.class);
        builder.copyEventListeners(builder2);
        final XMLConfiguration config = builder2.getConfiguration();
        Collection<EventListener<? super ConfigurationEvent>> listeners = config.getEventListeners(ConfigurationEvent.ANY);
        listeners = config.getEventListeners(ConfigurationEvent.ANY_HIERARCHICAL);
        assertTrue("Listener 1 not found", listeners.contains(l1));
    }

    @Test
    public void testCopyEventListeners_5_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        final EventListener<ConfigurationErrorEvent> l3 = new ErrorListenerTestImpl(null);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l2);
        builder.addEventListener(ConfigurationErrorEvent.ANY, l3);
        final BasicConfigurationBuilder<XMLConfiguration> builder2 = new BasicConfigurationBuilder<>(XMLConfiguration.class);
        builder.copyEventListeners(builder2);
        final XMLConfiguration config = builder2.getConfiguration();
        Collection<EventListener<? super ConfigurationEvent>> listeners = config.getEventListeners(ConfigurationEvent.ANY);
        listeners = config.getEventListeners(ConfigurationEvent.ANY_HIERARCHICAL);
        assertTrue("Listener 2 not found", listeners.contains(l2));
    }

    @Test
    public void testCopyEventListeners_6_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        final EventListener<ConfigurationErrorEvent> l3 = new ErrorListenerTestImpl(null);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l2);
        builder.addEventListener(ConfigurationErrorEvent.ANY, l3);
        final BasicConfigurationBuilder<XMLConfiguration> builder2 = new BasicConfigurationBuilder<>(XMLConfiguration.class);
        builder.copyEventListeners(builder2);
        final XMLConfiguration config = builder2.getConfiguration();
        Collection<EventListener<? super ConfigurationEvent>> listeners = config.getEventListeners(ConfigurationEvent.ANY);
        listeners = config.getEventListeners(ConfigurationEvent.ANY_HIERARCHICAL);
        final Collection<EventListener<? super ConfigurationErrorEvent>> errListeners = config.getEventListeners(ConfigurationErrorEvent.ANY);
        assertEquals("Wrong number of error listeners", 1, errListeners.size());
    }

    @Test
    public void testCopyEventListeners_7_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        final EventListener<ConfigurationErrorEvent> l3 = new ErrorListenerTestImpl(null);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY, l1);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l2);
        builder.addEventListener(ConfigurationErrorEvent.ANY, l3);
        final BasicConfigurationBuilder<XMLConfiguration> builder2 = new BasicConfigurationBuilder<>(XMLConfiguration.class);
        builder.copyEventListeners(builder2);
        final XMLConfiguration config = builder2.getConfiguration();
        Collection<EventListener<? super ConfigurationEvent>> listeners = config.getEventListeners(ConfigurationEvent.ANY);
        listeners = config.getEventListeners(ConfigurationEvent.ANY_HIERARCHICAL);
        final Collection<EventListener<? super ConfigurationErrorEvent>> errListeners = config.getEventListeners(ConfigurationErrorEvent.ANY);
        assertTrue("Wrong error listener", errListeners.contains(l3));
    }

    @Test
    public void testEventListenerConfiguration_1_oe() throws ConfigurationException {
        final EventListenerTestImpl listener1 = new EventListenerTestImpl(null);
        final EventListenerRegistrationData<ConfigurationErrorEvent> regData = new EventListenerRegistrationData<>(ConfigurationErrorEvent.WRITE,
            new ErrorListenerTestImpl(null));
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(new EventListenerParameters().addEventListener(ConfigurationEvent.ANY, listener1).addEventListener(regData));
        final PropertiesConfiguration config = builder.getConfiguration();
        assertTrue("Configuration listener not found", config.getEventListeners(ConfigurationEvent.ANY).contains(listener1));
    }

    @Test
    public void testEventListenerConfiguration_2_oe() throws ConfigurationException {
        final EventListenerTestImpl listener1 = new EventListenerTestImpl(null);
        final EventListenerRegistrationData<ConfigurationErrorEvent> regData = new EventListenerRegistrationData<>(ConfigurationErrorEvent.WRITE,
            new ErrorListenerTestImpl(null));
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(new EventListenerParameters().addEventListener(ConfigurationEvent.ANY, listener1).addEventListener(regData));
        final PropertiesConfiguration config = builder.getConfiguration();
        assertTrue("Error listener not found", config.getEventListeners(regData.getEventType()).contains(regData.getListener()));
    }

    @Test
    public void testGetConfiguration_1_oe() throws ConfigurationException {
        final PropertiesConfiguration config = new BasicConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(new BasicBuilderParameters().setListDelimiterHandler(listHandler).setThrowExceptionOnMissing(true)).getConfiguration();
        assertTrue("Wrong exception flag", config.isThrowExceptionOnMissing());
    }

    @Test
    public void testGetConfiguration_2_oe() throws ConfigurationException {
        final PropertiesConfiguration config = new BasicConfigurationBuilder<>(PropertiesConfiguration.class)
            .configure(new BasicBuilderParameters().setListDelimiterHandler(listHandler).setThrowExceptionOnMissing(true)).getConfiguration();
        assertEquals("Wrong list delimiter handler", listHandler, config.getListDelimiterHandler());
    }

    @Test
    public void testGetConfigurationConcurrently_1_oe() throws Exception {
        final int threadCount = 32;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(threadCount);
        final ConfigurationBuilder<?> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        final AccessBuilderThread[] threads = new AccessBuilderThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new AccessBuilderThread(startLatch, endLatch, builder);
            threads[i].start();
        }
        startLatch.countDown();
        assertTrue("Timeout", endLatch.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void testGetConfigurationConcurrently_2_oe() throws Exception {
        final int threadCount = 32;
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch endLatch = new CountDownLatch(threadCount);
        final ConfigurationBuilder<?> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        final AccessBuilderThread[] threads = new AccessBuilderThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new AccessBuilderThread(startLatch, endLatch, builder);
            threads[i].start();
        }
        startLatch.countDown();
        final Set<Object> results = new HashSet<>();
        for (final AccessBuilderThread t : threads) {
            results.add(t.result);
        }
        assertEquals("Wrong number of result objects", 1, results.size());
    }

    @Test
    public void testInitializableCalled_1_oe() throws ConfigurationException {
        final BasicConfigurationBuilder<InitializableConfiguration> builder = new BasicConfigurationBuilder<>(InitializableConfiguration.class);
        builder.configure(new BasicBuilderParameters().setThrowExceptionOnMissing(true));
        final InitializableConfiguration config = builder.getConfiguration();
        assertEquals("Property not correctly initialized", "Initialized with flag true", config.getInitProperty());
    }

    @Test
    public void testInitializationErrorAllowed_1_oe() throws ConfigurationException {
        final BasicConfigurationBuilderInitFailImpl builder = new BasicConfigurationBuilderInitFailImpl(true);
        final PropertiesConfiguration config = builder.getConfiguration();
        assertTrue("Got data", config.isEmpty());
    }

    @Test
    public void testInitWithParameters_1_oe() {
        final Map<String, Object> params = createTestParameters();
        final BasicConfigurationBuilder<Configuration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class, params);
        final Map<String, Object> params2 = new HashMap<>(builder.getParameters());
        assertEquals("Wrong parameters", createTestParameters(), params2);
    }

    @Test
    public void testInitWithParametersDefensiveCopy_1_oe() {
        final Map<String, Object> params = createTestParameters();
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class, params);
        params.put("anotherParameter", "value");
        final Map<String, Object> params2 = new HashMap<>(builder.getParameters());
        assertEquals("Wrong parameters", createTestParameters(), params2);
    }

    @Test
    public void testInitWithParametersNull_1_oe() {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class, null);
        assertTrue("Got parameters", builder.getParameters().isEmpty());
    }

    @Test
    public void testRemoveConfigurationListener_1_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        EasyMock.replay(l1, l2);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l1);
        builder.addEventListener(ConfigurationEvent.ANY, l2);
        assertTrue("Wrong result", builder.removeEventListener(ConfigurationEvent.ANY, l2));
    }

    @Test
    public void testRemoveConfigurationListener_3_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        EasyMock.replay(l1, l2);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l1);
        builder.addEventListener(ConfigurationEvent.ANY, l2);
        final PropertiesConfiguration config = builder.getConfiguration();
        assertTrue("Listener not registered", config.getEventListeners(ConfigurationEvent.ANY_HIERARCHICAL).contains(l1));
    }

    @Test
    public void testRemoveConfigurationListener_4_oe() throws ConfigurationException {
        final EventListener<ConfigurationEvent> l1 = createEventListener();
        final EventListener<ConfigurationEvent> l2 = createEventListener();
        EasyMock.replay(l1, l2);
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class);
        builder.addEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l1);
        builder.addEventListener(ConfigurationEvent.ANY, l2);
        final PropertiesConfiguration config = builder.getConfiguration();
        builder.removeEventListener(ConfigurationEvent.ANY_HIERARCHICAL, l1);
        assertFalse("Listener still registered", config.getEventListeners(ConfigurationEvent.ANY_HIERARCHICAL).contains(l1));
    }

    @Test
    public void testReservedParameter_1_oe() throws ConfigurationException {
        final Map<String, Object> params = new HashMap<>();
        params.put("throwExceptionOnMissing", Boolean.TRUE);
        params.put("config-test", "a test");
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class, params);
        final PropertiesConfiguration config = builder.getConfiguration();
        assertTrue("Flag not set", config.isThrowExceptionOnMissing());
    }

    @Test
    public void testReset_1_oe() throws ConfigurationException {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class,
            createTestParameters());
        final PropertiesConfiguration config = builder.getConfiguration();
        builder.reset();
        final PropertiesConfiguration config2 = builder.getConfiguration();
        assertNotSame("No new result", config, config2);
    }

    @Test
    public void testReset_2_oe() throws ConfigurationException {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class,
            createTestParameters());
        final PropertiesConfiguration config = builder.getConfiguration();
        builder.reset();
        final PropertiesConfiguration config2 = builder.getConfiguration();
        assertFalse("Parameters not reset", config2.isThrowExceptionOnMissing());
    }

    @Test
    public void testResetParameters_1_oe() {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class,
            createTestParameters());
        builder.resetParameters();
        assertTrue("Still got parameters", builder.getParameters().isEmpty());
    }

    @Test
    public void testResetResult_1_oe() throws ConfigurationException {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class,
            createTestParameters());
        final PropertiesConfiguration config = builder.getConfiguration();
        builder.resetResult();
        final PropertiesConfiguration config2 = builder.getConfiguration();
        assertNotSame("No new result", config, config2);
    }

    @Test
    public void testResetResult_2_oe() throws ConfigurationException {
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class,
            createTestParameters());
        final PropertiesConfiguration config = builder.getConfiguration();
        builder.resetResult();
        final PropertiesConfiguration config2 = builder.getConfiguration();
        assertTrue("Wrong property", config2.isThrowExceptionOnMissing());
    }

    @Test
    public void testSetParameters_1_oe() {
        final Map<String, Object> params1 = new HashMap<>();
        params1.put("someParameter", "value");
        final BasicConfigurationBuilder<PropertiesConfiguration> builder = new BasicConfigurationBuilder<>(PropertiesConfiguration.class, params1);
        assertSame("Wrong result", builder, builder.setParameters(createTestParameters()));
    }

}
