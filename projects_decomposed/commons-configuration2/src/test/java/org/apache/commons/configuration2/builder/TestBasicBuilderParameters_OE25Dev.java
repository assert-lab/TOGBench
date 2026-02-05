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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.ConfigurationDecoder;
import org.apache.commons.configuration2.io.ConfigurationLogger;
import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.convert.ConversionHandler;
import org.apache.commons.configuration2.convert.DefaultConversionHandler;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.convert.ListDelimiterHandler;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.configuration2.interpol.InterpolatorSpecification;
import org.apache.commons.configuration2.interpol.Lookup;
import org.apache.commons.configuration2.sync.ReadWriteSynchronizer;
import org.apache.commons.configuration2.sync.Synchronizer;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@code BasicBuilderParameters}.
 *
 */
public class TestBasicBuilderParameters_OE25Dev {
    /** The instance to be tested. */
    private BasicBuilderParameters params;

    @Before
    public void setUp() throws Exception {
        params = new BasicBuilderParameters();
    }

    /**
     * Tests whether the collection with default lookups can be cloned, too.
     */

    /**
     * Tests whether the map with prefix lookups is cloned, too.
     */

    /**
     * Tests whether a cloned instance contains the same data as the original object.
     */

    /**
     * Tests the default parameter values.
     */

    /**
     * Tests fetchBeanHelper() if no helper was set.
     */

    /**
     * Tries to invoke fetchBeanHelper() on a null map.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFetchBeanHelperNullMap() {
        BasicBuilderParameters.fetchBeanHelper(null);
    }

    /**
     * Tests whether a specification object for interpolation can be obtained.
     */

    /**
     * Tests that an empty map does not cause any problems.
     */

    /**
     * Tests fetchInterpolatorSpecification() if the collection with default lookups contains an invalid value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFetchInterpolatorSpecificationInvalidCollectionValue() {
        final Map<String, Object> map = new HashMap<>();
        map.put("defaultLookups", Collections.singleton("not a lookup"));
        BasicBuilderParameters.fetchInterpolatorSpecification(map);
    }

    /**
     * Tests fetchInterpolatorSpecification() if the map contains a property of an invalid data type.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFetchInterpolatorSpecificationInvalidDataType() {
        final Map<String, Object> map = new HashMap<>();
        map.put("interpolator", this);
        BasicBuilderParameters.fetchInterpolatorSpecification(map);
    }

    /**
     * Tests fetchInterpolatorSpecification() if the map with prefix lookups contains an invalid key.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFetchInterpolatorSpecificationInvalidMapKey() {
        final Map<String, Object> map = new HashMap<>();
        final Map<Object, Object> prefix = new HashMap<>();
        prefix.put(42, EasyMock.createMock(Lookup.class));
        map.put("prefixLookups", prefix);
        BasicBuilderParameters.fetchInterpolatorSpecification(map);
    }

    /**
     * Tests fetchInterpolatorSpecification() if the map with prefix lookups contains an invalid value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFetchInterpolatorSpecificationInvalidMapValue() {
        final Map<String, Object> map = new HashMap<>();
        final Map<Object, Object> prefix = new HashMap<>();
        prefix.put("test", this);
        map.put("prefixLookups", prefix);
        BasicBuilderParameters.fetchInterpolatorSpecification(map);
    }

    /**
     * Tries to obtain an {@code InterpolatorSpecification} from a null map.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFetchInterpolatorSpecificationNull() {
        BasicBuilderParameters.fetchInterpolatorSpecification(null);
    }

    /**
     * Tests whether an InterpolatorSpecification can be fetched if a ConfigurationInterpolator is present.
     */

    /**
     * Tests whether a defensive copy is created when the parameter map is returned.
     */

    /**
     * Tests whether properties can be inherited from another parameters map.
     */

    /**
     * Tests whether null input is handled by inheritFrom().
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInheritFromNull() {
        params.inheritFrom(null);
    }

    /**
     * Tests that undefined properties are not copied over by inheritFrom().
     */

    /**
     * Tests whether properties of other parameter objects can be merged.
     */

    /**
     * Tries a merge with a null object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMergeNull() {
        params.merge(null);
    }

    /**
     * Tests whether a BeanHelper can be set.
     */

    /**
     * Tests whether a decoder can be set.
     */

    /**
     * Tests whether a ConversionHandler can be set.
     */

    /**
     * Tests whether default lookups can be set.
     */

    /**
     * Tests whether null values are handled by setDefaultLookups().
     */

    /**
     * Tests whether a {@code ConfigurationInterpolator} can be set.
     */

    /**
     * Tests whether the list delimiter handler property can be set.
     */

    /**
     * Tests whether the logger parameter can be set.
     */

    /**
     * Tests whether a custom {@code ConfigurationInterpolator} overrides settings for custom lookups.
     */

    /**
     * Tests whether a parent {@code ConfigurationInterpolator} can be set.
     */

    /**
     * Tests whether prefix lookups can be set.
     */

    /**
     * Tests whether null values are handled by setPrefixLookups().
     */

    /**
     * Tests whether a Synchronizer can be set.
     */

    /**
     * Tests whether the throw exception on missing property can be set.
     */

@Test
    public void testCloneDefaultLookups_1_oe() {
        final Lookup look = EasyMock.createMock(Lookup.class);
        final Collection<Lookup> looks = Collections.singleton(look);
        params.setDefaultLookups(looks);
        final BasicBuilderParameters clone = params.clone();
        Collection<?> defLooks = (Collection<?>) params.getParameters().get("defaultLookups");
        defLooks.clear();
        defLooks = (Collection<?>) clone.getParameters().get("defaultLookups");
        assertEquals("Wrong number of default lookups", 1, defLooks.size());
    }

@Test
    public void testCloneDefaultLookups_2_oe() {
        final Lookup look = EasyMock.createMock(Lookup.class);
        final Collection<Lookup> looks = Collections.singleton(look);
        params.setDefaultLookups(looks);
        final BasicBuilderParameters clone = params.clone();
        Collection<?> defLooks = (Collection<?>) params.getParameters().get("defaultLookups");
        defLooks.clear();
        defLooks = (Collection<?>) clone.getParameters().get("defaultLookups");
        // removed other assertion
        assertTrue("Wrong default lookup", defLooks.contains(look));
    }

@Test
    public void testClonePrefixLookups_1_oe() {
        final Lookup look = EasyMock.createMock(Lookup.class);
        final Map<String, Lookup> lookups = Collections.singletonMap("test", look);
        params.setPrefixLookups(lookups);
        final BasicBuilderParameters clone = params.clone();
        Map<?, ?> map = (Map<?, ?>) params.getParameters().get("prefixLookups");
        map.clear();
        map = (Map<?, ?>) clone.getParameters().get("prefixLookups");
        assertEquals("Wrong number of lookups", 1, map.size());
    }

@Test
    public void testClonePrefixLookups_2_oe() {
        final Lookup look = EasyMock.createMock(Lookup.class);
        final Map<String, Lookup> lookups = Collections.singletonMap("test", look);
        params.setPrefixLookups(lookups);
        final BasicBuilderParameters clone = params.clone();
        Map<?, ?> map = (Map<?, ?>) params.getParameters().get("prefixLookups");
        map.clear();
        map = (Map<?, ?>) clone.getParameters().get("prefixLookups");
        // removed other assertion
        assertSame("Wrong lookup", look, map.get("test"));
    }

@Test
    public void testCloneValues_1_oe() {
        final ConfigurationLogger log = EasyMock.createMock(ConfigurationLogger.class);
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        final ListDelimiterHandler handler1 = EasyMock.createMock(ListDelimiterHandler.class);
        final ListDelimiterHandler handler2 = EasyMock.createMock(ListDelimiterHandler.class);
        params.setListDelimiterHandler(handler1);
        params.setLogger(log);
        params.setInterpolator(ci);
        params.setThrowExceptionOnMissing(true);
        final BasicBuilderParameters clone = params.clone();
        params.setListDelimiterHandler(handler2);
        params.setThrowExceptionOnMissing(false);
        final Map<String, Object> map = clone.getParameters();
        assertSame("Wrong logger", log, map.get("logger"));
    }

@Test
    public void testCloneValues_2_oe() {
        final ConfigurationLogger log = EasyMock.createMock(ConfigurationLogger.class);
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        final ListDelimiterHandler handler1 = EasyMock.createMock(ListDelimiterHandler.class);
        final ListDelimiterHandler handler2 = EasyMock.createMock(ListDelimiterHandler.class);
        params.setListDelimiterHandler(handler1);
        params.setLogger(log);
        params.setInterpolator(ci);
        params.setThrowExceptionOnMissing(true);
        final BasicBuilderParameters clone = params.clone();
        params.setListDelimiterHandler(handler2);
        params.setThrowExceptionOnMissing(false);
        final Map<String, Object> map = clone.getParameters();
        // removed other assertion
        assertSame("Wrong interpolator", ci, map.get("interpolator"));
    }

@Test
    public void testCloneValues_3_oe() {
        final ConfigurationLogger log = EasyMock.createMock(ConfigurationLogger.class);
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        final ListDelimiterHandler handler1 = EasyMock.createMock(ListDelimiterHandler.class);
        final ListDelimiterHandler handler2 = EasyMock.createMock(ListDelimiterHandler.class);
        params.setListDelimiterHandler(handler1);
        params.setLogger(log);
        params.setInterpolator(ci);
        params.setThrowExceptionOnMissing(true);
        final BasicBuilderParameters clone = params.clone();
        params.setListDelimiterHandler(handler2);
        params.setThrowExceptionOnMissing(false);
        final Map<String, Object> map = clone.getParameters();
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong list delimiter handler", handler1, map.get("listDelimiterHandler"));
    }

@Test
    public void testCloneValues_4_oe() {
        final ConfigurationLogger log = EasyMock.createMock(ConfigurationLogger.class);
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        final ListDelimiterHandler handler1 = EasyMock.createMock(ListDelimiterHandler.class);
        final ListDelimiterHandler handler2 = EasyMock.createMock(ListDelimiterHandler.class);
        params.setListDelimiterHandler(handler1);
        params.setLogger(log);
        params.setInterpolator(ci);
        params.setThrowExceptionOnMissing(true);
        final BasicBuilderParameters clone = params.clone();
        params.setListDelimiterHandler(handler2);
        params.setThrowExceptionOnMissing(false);
        final Map<String, Object> map = clone.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong exception flag", Boolean.TRUE, map.get("throwExceptionOnMissing"));
    }

@Test
    public void testDefaults_1_oe() {
        final Map<String, Object> paramMap = params.getParameters();
        assertTrue("Got parameters", paramMap.isEmpty());
    }

@Test
    public void testFetchBeanHelperNoSet_1_oe() {
        assertNull("Got a BeanHelper", BasicBuilderParameters.fetchBeanHelper(params.getParameters()));
    }

@Test
    public void testFetchInterpolatorSpecification_1_oe() {
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        final Lookup l1 = EasyMock.createMock(Lookup.class);
        final Lookup l2 = EasyMock.createMock(Lookup.class);
        final Lookup l3 = EasyMock.createMock(Lookup.class);
        final Map<String, Lookup> prefixLookups = new HashMap<>();
        prefixLookups.put("p1", l1);
        prefixLookups.put("p2", l2);
        final Collection<Lookup> defLookups = Collections.singleton(l3);
        params.setParentInterpolator(parent);
        params.setPrefixLookups(prefixLookups);
        params.setDefaultLookups(defLookups);
        final Map<String, Object> map = params.getParameters();
        final InterpolatorSpecification spec = BasicBuilderParameters.fetchInterpolatorSpecification(map);
        assertSame("Wrong parent", parent, spec.getParentInterpolator());
    }

@Test
    public void testFetchInterpolatorSpecification_2_oe() {
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        final Lookup l1 = EasyMock.createMock(Lookup.class);
        final Lookup l2 = EasyMock.createMock(Lookup.class);
        final Lookup l3 = EasyMock.createMock(Lookup.class);
        final Map<String, Lookup> prefixLookups = new HashMap<>();
        prefixLookups.put("p1", l1);
        prefixLookups.put("p2", l2);
        final Collection<Lookup> defLookups = Collections.singleton(l3);
        params.setParentInterpolator(parent);
        params.setPrefixLookups(prefixLookups);
        params.setDefaultLookups(defLookups);
        final Map<String, Object> map = params.getParameters();
        final InterpolatorSpecification spec = BasicBuilderParameters.fetchInterpolatorSpecification(map);
        // removed other assertion
        assertEquals("Wrong prefix lookups", prefixLookups, spec.getPrefixLookups());
    }

@Test
    public void testFetchInterpolatorSpecification_3_oe() {
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        final Lookup l1 = EasyMock.createMock(Lookup.class);
        final Lookup l2 = EasyMock.createMock(Lookup.class);
        final Lookup l3 = EasyMock.createMock(Lookup.class);
        final Map<String, Lookup> prefixLookups = new HashMap<>();
        prefixLookups.put("p1", l1);
        prefixLookups.put("p2", l2);
        final Collection<Lookup> defLookups = Collections.singleton(l3);
        params.setParentInterpolator(parent);
        params.setPrefixLookups(prefixLookups);
        params.setDefaultLookups(defLookups);
        final Map<String, Object> map = params.getParameters();
        final InterpolatorSpecification spec = BasicBuilderParameters.fetchInterpolatorSpecification(map);
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong number of default lookups", 1, spec.getDefaultLookups().size());
    }

@Test
    public void testFetchInterpolatorSpecification_4_oe() {
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        final Lookup l1 = EasyMock.createMock(Lookup.class);
        final Lookup l2 = EasyMock.createMock(Lookup.class);
        final Lookup l3 = EasyMock.createMock(Lookup.class);
        final Map<String, Lookup> prefixLookups = new HashMap<>();
        prefixLookups.put("p1", l1);
        prefixLookups.put("p2", l2);
        final Collection<Lookup> defLookups = Collections.singleton(l3);
        params.setParentInterpolator(parent);
        params.setPrefixLookups(prefixLookups);
        params.setDefaultLookups(defLookups);
        final Map<String, Object> map = params.getParameters();
        final InterpolatorSpecification spec = BasicBuilderParameters.fetchInterpolatorSpecification(map);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Wrong default lookup", spec.getDefaultLookups().contains(l3));
    }

@Test
    public void testFetchInterpolatorSpecificationEmpty_1_oe() {
        final InterpolatorSpecification spec = BasicBuilderParameters.fetchInterpolatorSpecification(params.getParameters());
        assertNull("Got an interpolator", spec.getInterpolator());
    }

@Test
    public void testFetchInterpolatorSpecificationEmpty_2_oe() {
        final InterpolatorSpecification spec = BasicBuilderParameters.fetchInterpolatorSpecification(params.getParameters());
        // removed other assertion
        assertTrue("Got lookups", spec.getDefaultLookups().isEmpty());
    }

@Test
    public void testFetchInterpolatorSpecificationWithInterpolator_1_oe() {
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        params.setInterpolator(ci);
        final InterpolatorSpecification spec = BasicBuilderParameters.fetchInterpolatorSpecification(params.getParameters());
        assertSame("Wrong interpolator", ci, spec.getInterpolator());
    }

@Test
    public void testFetchInterpolatorSpecificationWithInterpolator_2_oe() {
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        params.setInterpolator(ci);
        final InterpolatorSpecification spec = BasicBuilderParameters.fetchInterpolatorSpecification(params.getParameters());
        // removed other assertion
        assertNull("Got a parent", spec.getParentInterpolator());
    }

@Test
    public void testGetParametersDefensiveCopy_1_oe() {
        final Map<String, Object> map1 = params.getParameters();
        final Map<String, Object> mapCopy = new HashMap<>(map1);
        map1.put("otherProperty", "value");
        final Map<String, Object> map2 = params.getParameters();
        assertNotSame("Same map returned", map1, map2);
    }

@Test
    public void testGetParametersDefensiveCopy_2_oe() {
        final Map<String, Object> map1 = params.getParameters();
        final Map<String, Object> mapCopy = new HashMap<>(map1);
        map1.put("otherProperty", "value");
        final Map<String, Object> map2 = params.getParameters();
        // removed other assertion
        assertEquals("Different properties", mapCopy, map2);
    }

@Test
    public void testInheritFrom_1_oe() {
        final BeanHelper beanHelper = new BeanHelper();
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        final ConversionHandler conversionHandler = new DefaultConversionHandler();
        final ListDelimiterHandler listDelimiterHandler = new DefaultListDelimiterHandler('#');
        final ConfigurationLogger logger = new ConfigurationLogger("test");
        final Synchronizer synchronizer = new ReadWriteSynchronizer();
        params.setBeanHelper(beanHelper).setConfigurationDecoder(decoder).setConversionHandler(conversionHandler).setListDelimiterHandler(listDelimiterHandler)
            .setLogger(logger).setSynchronizer(synchronizer).setThrowExceptionOnMissing(true);
        final BasicBuilderParameters p2 = new BasicBuilderParameters();

        p2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = p2.getParameters();
        assertEquals("Bean helper not set", beanHelper, parameters.get("config-BeanHelper"));
    }

@Test
    public void testInheritFrom_2_oe() {
        final BeanHelper beanHelper = new BeanHelper();
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        final ConversionHandler conversionHandler = new DefaultConversionHandler();
        final ListDelimiterHandler listDelimiterHandler = new DefaultListDelimiterHandler('#');
        final ConfigurationLogger logger = new ConfigurationLogger("test");
        final Synchronizer synchronizer = new ReadWriteSynchronizer();
        params.setBeanHelper(beanHelper).setConfigurationDecoder(decoder).setConversionHandler(conversionHandler).setListDelimiterHandler(listDelimiterHandler)
            .setLogger(logger).setSynchronizer(synchronizer).setThrowExceptionOnMissing(true);
        final BasicBuilderParameters p2 = new BasicBuilderParameters();

        p2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = p2.getParameters();
        // removed other assertion
        assertEquals("Decoder not set", decoder, parameters.get("configurationDecoder"));
    }

@Test
    public void testInheritFrom_3_oe() {
        final BeanHelper beanHelper = new BeanHelper();
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        final ConversionHandler conversionHandler = new DefaultConversionHandler();
        final ListDelimiterHandler listDelimiterHandler = new DefaultListDelimiterHandler('#');
        final ConfigurationLogger logger = new ConfigurationLogger("test");
        final Synchronizer synchronizer = new ReadWriteSynchronizer();
        params.setBeanHelper(beanHelper).setConfigurationDecoder(decoder).setConversionHandler(conversionHandler).setListDelimiterHandler(listDelimiterHandler)
            .setLogger(logger).setSynchronizer(synchronizer).setThrowExceptionOnMissing(true);
        final BasicBuilderParameters p2 = new BasicBuilderParameters();

        p2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = p2.getParameters();
        // removed other assertion
        // removed other assertion
        assertEquals("Conversion handler not set", conversionHandler, parameters.get("conversionHandler"));
    }

@Test
    public void testInheritFrom_4_oe() {
        final BeanHelper beanHelper = new BeanHelper();
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        final ConversionHandler conversionHandler = new DefaultConversionHandler();
        final ListDelimiterHandler listDelimiterHandler = new DefaultListDelimiterHandler('#');
        final ConfigurationLogger logger = new ConfigurationLogger("test");
        final Synchronizer synchronizer = new ReadWriteSynchronizer();
        params.setBeanHelper(beanHelper).setConfigurationDecoder(decoder).setConversionHandler(conversionHandler).setListDelimiterHandler(listDelimiterHandler)
            .setLogger(logger).setSynchronizer(synchronizer).setThrowExceptionOnMissing(true);
        final BasicBuilderParameters p2 = new BasicBuilderParameters();

        p2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = p2.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Delimiter handler not set", listDelimiterHandler, parameters.get("listDelimiterHandler"));
    }

@Test
    public void testInheritFrom_5_oe() {
        final BeanHelper beanHelper = new BeanHelper();
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        final ConversionHandler conversionHandler = new DefaultConversionHandler();
        final ListDelimiterHandler listDelimiterHandler = new DefaultListDelimiterHandler('#');
        final ConfigurationLogger logger = new ConfigurationLogger("test");
        final Synchronizer synchronizer = new ReadWriteSynchronizer();
        params.setBeanHelper(beanHelper).setConfigurationDecoder(decoder).setConversionHandler(conversionHandler).setListDelimiterHandler(listDelimiterHandler)
            .setLogger(logger).setSynchronizer(synchronizer).setThrowExceptionOnMissing(true);
        final BasicBuilderParameters p2 = new BasicBuilderParameters();

        p2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = p2.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Logger not set", logger, parameters.get("logger"));
    }

@Test
    public void testInheritFrom_6_oe() {
        final BeanHelper beanHelper = new BeanHelper();
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        final ConversionHandler conversionHandler = new DefaultConversionHandler();
        final ListDelimiterHandler listDelimiterHandler = new DefaultListDelimiterHandler('#');
        final ConfigurationLogger logger = new ConfigurationLogger("test");
        final Synchronizer synchronizer = new ReadWriteSynchronizer();
        params.setBeanHelper(beanHelper).setConfigurationDecoder(decoder).setConversionHandler(conversionHandler).setListDelimiterHandler(listDelimiterHandler)
            .setLogger(logger).setSynchronizer(synchronizer).setThrowExceptionOnMissing(true);
        final BasicBuilderParameters p2 = new BasicBuilderParameters();

        p2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = p2.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Synchronizer not set", synchronizer, parameters.get("synchronizer"));
    }

@Test
    public void testInheritFrom_7_oe() {
        final BeanHelper beanHelper = new BeanHelper();
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        final ConversionHandler conversionHandler = new DefaultConversionHandler();
        final ListDelimiterHandler listDelimiterHandler = new DefaultListDelimiterHandler('#');
        final ConfigurationLogger logger = new ConfigurationLogger("test");
        final Synchronizer synchronizer = new ReadWriteSynchronizer();
        params.setBeanHelper(beanHelper).setConfigurationDecoder(decoder).setConversionHandler(conversionHandler).setListDelimiterHandler(listDelimiterHandler)
            .setLogger(logger).setSynchronizer(synchronizer).setThrowExceptionOnMissing(true);
        final BasicBuilderParameters p2 = new BasicBuilderParameters();

        p2.inheritFrom(params.getParameters());
        final Map<String, Object> parameters = p2.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Exception flag not set", Boolean.TRUE, parameters.get("throwExceptionOnMissing"));
    }

@Test
    public void testInheritFromUndefinedProperties_1_oe() {
        final BasicBuilderParameters p2 = new BasicBuilderParameters().setThrowExceptionOnMissing(true);

        p2.inheritFrom(Collections.<String, Object>emptyMap());
        final Map<String, Object> parameters = p2.getParameters();
        assertEquals("Wrong number of properties", 1, parameters.size());
    }

@Test
    public void testInheritFromUndefinedProperties_2_oe() {
        final BasicBuilderParameters p2 = new BasicBuilderParameters().setThrowExceptionOnMissing(true);

        p2.inheritFrom(Collections.<String, Object>emptyMap());
        final Map<String, Object> parameters = p2.getParameters();
        // removed other assertion
        assertEquals("Exception flag not set", Boolean.TRUE, parameters.get("throwExceptionOnMissing"));
    }

@Test
    public void testMerge_1_oe() {
        final ListDelimiterHandler handler1 = EasyMock.createMock(ListDelimiterHandler.class);
        final ListDelimiterHandler handler2 = EasyMock.createMock(ListDelimiterHandler.class);
        final Map<String, Object> props = new HashMap<>();
        props.put("throwExceptionOnMissing", Boolean.TRUE);
        props.put("listDelimiterHandler", handler1);
        props.put("other", "test");
        props.put(BuilderParameters.RESERVED_PARAMETER_PREFIX + "test", "reserved");
        final BuilderParameters p = EasyMock.createMock(BuilderParameters.class);
        EasyMock.expect(p.getParameters()).andReturn(props);
        EasyMock.replay(p);
        params.setListDelimiterHandler(handler2);
        params.merge(p);
        final Map<String, Object> map = params.getParameters();
        assertEquals("Wrong list delimiter handler", handler2, map.get("listDelimiterHandler"));
    }

@Test
    public void testMerge_2_oe() {
        final ListDelimiterHandler handler1 = EasyMock.createMock(ListDelimiterHandler.class);
        final ListDelimiterHandler handler2 = EasyMock.createMock(ListDelimiterHandler.class);
        final Map<String, Object> props = new HashMap<>();
        props.put("throwExceptionOnMissing", Boolean.TRUE);
        props.put("listDelimiterHandler", handler1);
        props.put("other", "test");
        props.put(BuilderParameters.RESERVED_PARAMETER_PREFIX + "test", "reserved");
        final BuilderParameters p = EasyMock.createMock(BuilderParameters.class);
        EasyMock.expect(p.getParameters()).andReturn(props);
        EasyMock.replay(p);
        params.setListDelimiterHandler(handler2);
        params.merge(p);
        final Map<String, Object> map = params.getParameters();
        // removed other assertion
        assertEquals("Wrong exception flag", Boolean.TRUE, map.get("throwExceptionOnMissing"));
    }

@Test
    public void testMerge_3_oe() {
        final ListDelimiterHandler handler1 = EasyMock.createMock(ListDelimiterHandler.class);
        final ListDelimiterHandler handler2 = EasyMock.createMock(ListDelimiterHandler.class);
        final Map<String, Object> props = new HashMap<>();
        props.put("throwExceptionOnMissing", Boolean.TRUE);
        props.put("listDelimiterHandler", handler1);
        props.put("other", "test");
        props.put(BuilderParameters.RESERVED_PARAMETER_PREFIX + "test", "reserved");
        final BuilderParameters p = EasyMock.createMock(BuilderParameters.class);
        EasyMock.expect(p.getParameters()).andReturn(props);
        EasyMock.replay(p);
        params.setListDelimiterHandler(handler2);
        params.merge(p);
        final Map<String, Object> map = params.getParameters();
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong other property", "test", map.get("other"));
    }

@Test
    public void testMerge_4_oe() {
        final ListDelimiterHandler handler1 = EasyMock.createMock(ListDelimiterHandler.class);
        final ListDelimiterHandler handler2 = EasyMock.createMock(ListDelimiterHandler.class);
        final Map<String, Object> props = new HashMap<>();
        props.put("throwExceptionOnMissing", Boolean.TRUE);
        props.put("listDelimiterHandler", handler1);
        props.put("other", "test");
        props.put(BuilderParameters.RESERVED_PARAMETER_PREFIX + "test", "reserved");
        final BuilderParameters p = EasyMock.createMock(BuilderParameters.class);
        EasyMock.expect(p.getParameters()).andReturn(props);
        EasyMock.replay(p);
        params.setListDelimiterHandler(handler2);
        params.merge(p);
        final Map<String, Object> map = params.getParameters();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Reserved property was copied", map.containsKey(BuilderParameters.RESERVED_PARAMETER_PREFIX + "test"));
    }

@Test
    public void testSetBeanHelper_1_oe() {
        final BeanHelper helper = new BeanHelper();
        assertSame("Wrong result", params, params.setBeanHelper(helper));
    }

@Test
    public void testSetConfigurationDecoder_1_oe() {
        final ConfigurationDecoder decoder = EasyMock.createMock(ConfigurationDecoder.class);
        EasyMock.replay(decoder);
        assertSame("Wrong result", params, params.setConfigurationDecoder(decoder));
    }

@Test
    public void testSetConversionHandler_1_oe() {
        final ConversionHandler handler = EasyMock.createMock(ConversionHandler.class);
        EasyMock.replay(handler);
        assertSame("Wrong result", params, params.setConversionHandler(handler));
    }

@Test
    public void testSetDefaultLookups_1_oe() {
        final Lookup look = EasyMock.createMock(Lookup.class);
        final Collection<Lookup> looks = Collections.singleton(look);
        assertSame("Wrong result", params, params.setDefaultLookups(looks));
    }

@Test
    public void testSetDefaultLookups_2_oe() {
        final Lookup look = EasyMock.createMock(Lookup.class);
        final Collection<Lookup> looks = Collections.singleton(look);
        // removed other assertion
        final Collection<?> col = (Collection<?>) params.getParameters().get("defaultLookups");
        assertNotSame("No copy was created", col, looks);
    }

@Test
    public void testSetDefaultLookupsNull_1_oe() {
        params.setDefaultLookups(new ArrayList<>());
        params.setDefaultLookups(null);
        assertFalse("Found key", params.getParameters().containsKey("defaultLookups"));
    }

@Test
    public void testSetInterpolator_1_oe() {
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        EasyMock.replay(ci);
        assertSame("Wrong result", params, params.setInterpolator(ci));
    }

@Test
    public void testSetListDelimiter_1_oe() {
        final ListDelimiterHandler handler = EasyMock.createMock(ListDelimiterHandler.class);
        EasyMock.replay(handler);
        assertSame("Wrong result", params, params.setListDelimiterHandler(handler));
    }

@Test
    public void testSetLogger_1_oe() {
        final ConfigurationLogger log = EasyMock.createMock(ConfigurationLogger.class);
        EasyMock.replay(log);
        assertSame("Wrong result", params, params.setLogger(log));
    }

@Test
    public void testSetLookupsAndInterpolator_1_oe() {
        final Lookup look1 = EasyMock.createMock(Lookup.class);
        final Lookup look2 = EasyMock.createMock(Lookup.class);
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        params.setDefaultLookups(Collections.singleton(look1));
        params.setPrefixLookups(Collections.singletonMap("test", look2));
        params.setInterpolator(ci);
        params.setParentInterpolator(parent);
        final Map<String, Object> map = params.getParameters();
        assertFalse("Got prefix lookups", map.containsKey("prefixLookups"));
    }

@Test
    public void testSetLookupsAndInterpolator_2_oe() {
        final Lookup look1 = EasyMock.createMock(Lookup.class);
        final Lookup look2 = EasyMock.createMock(Lookup.class);
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        params.setDefaultLookups(Collections.singleton(look1));
        params.setPrefixLookups(Collections.singletonMap("test", look2));
        params.setInterpolator(ci);
        params.setParentInterpolator(parent);
        final Map<String, Object> map = params.getParameters();
        // removed other assertion
        assertFalse("Got default lookups", map.containsKey("defaultLookups"));
    }

@Test
    public void testSetLookupsAndInterpolator_3_oe() {
        final Lookup look1 = EasyMock.createMock(Lookup.class);
        final Lookup look2 = EasyMock.createMock(Lookup.class);
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        params.setDefaultLookups(Collections.singleton(look1));
        params.setPrefixLookups(Collections.singletonMap("test", look2));
        params.setInterpolator(ci);
        params.setParentInterpolator(parent);
        final Map<String, Object> map = params.getParameters();
        // removed other assertion
        // removed other assertion
        assertFalse("Got a parent interpolator", map.containsKey("parentInterpolator"));
    }

@Test
    public void testSetParentInterpolator_1_oe() {
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        EasyMock.replay(parent);
        assertSame("Wrong result", params, params.setParentInterpolator(parent));
    }

@Test
    public void testSetPrefixLookups_1_oe() {
        final Lookup look = EasyMock.createMock(Lookup.class);
        final Map<String, Lookup> lookups = Collections.singletonMap("test", look);
        assertSame("Wrong result", params, params.setPrefixLookups(lookups));
    }

@Test
    public void testSetPrefixLookups_2_oe() {
        final Lookup look = EasyMock.createMock(Lookup.class);
        final Map<String, Lookup> lookups = Collections.singletonMap("test", look);
        // removed other assertion
        final Map<?, ?> map = (Map<?, ?>) params.getParameters().get("prefixLookups");
        assertNotSame("No copy was created", lookups, map);
    }

@Test
    public void testSetPrefixLookupsNull_1_oe() {
        params.setPrefixLookups(new HashMap<>());
        params.setPrefixLookups(null);
        assertFalse("Found key", params.getParameters().containsKey("prefixLookups"));
    }

@Test
    public void testSetSynchronizer_1_oe() {
        final Synchronizer sync = EasyMock.createMock(Synchronizer.class);
        EasyMock.replay(sync);
        assertSame("Wrong result", params, params.setSynchronizer(sync));
    }

@Test
    public void testSetThrowExceptionOnMissing_1_oe() {
        assertSame("Wrong result", params, params.setThrowExceptionOnMissing(true));
    }

}
