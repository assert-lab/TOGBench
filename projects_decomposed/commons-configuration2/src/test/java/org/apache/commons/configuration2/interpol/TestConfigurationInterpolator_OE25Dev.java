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
package org.apache.commons.configuration2.interpol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.text.lookup.StringLookupFactory;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ConfigurationInterpolator.
 *
 */
public class TestConfigurationInterpolator_OE25Dev {
    /** Constant for a test variable name. */
    private static final String TEST_NAME = "varname";

    /** Constant for a test variable prefix. */
    private static final String TEST_PREFIX = "prefix";

    /** Constant for the value of the test variable. */
    private static final String TEST_VALUE = "TestVariableValue";

    /**
     * Creates a lookup object that can resolve the test variable (and nothing else).
     *
     * @return the test lookup object
     */
    private static Lookup setUpTestLookup() {
        return setUpTestLookup(TEST_NAME, TEST_VALUE);
    }

    /**
     * Creates a lookup object that can resolve the specified variable (and nothing else).
     *
     * @param var the variable name
     * @param value the value of this variable
     * @return the test lookup object
     */
    private static Lookup setUpTestLookup(final String var, final Object value) {
        final Lookup lookup = EasyMock.createMock(Lookup.class);
        EasyMock.expect(lookup.lookup(EasyMock.anyObject(String.class))).andAnswer(() -> {
            if (var.equals(EasyMock.getCurrentArguments()[0])) {
                return value;
            }
            return null;
        }).anyTimes();
        EasyMock.replay(lookup);
        return lookup;
    }

    /** Stores the object to be tested. */
    private ConfigurationInterpolator interpolator;

    @Before
    public void setUp() throws Exception {
        interpolator = new ConfigurationInterpolator();
    }

    /**
     * Tests whether multiple default lookups can be added.
     */

    /**
     * Tests whether a null collection of default lookups is handled correctly.
     */

    /**
     * Tests deregistering a lookup object.
     */

    /**
     * Tests deregistering an unknown lookup object.
     */

    /**
     * Tests whether the flag for substitution in variable names can be modified.
     */

    /**
     * Tests fromSpecification() if the specification contains an instance.
     */

    /**
     * Tests fromSpecification() if a new instance has to be created.
     */

    /**
     * Tries to obtain an instance from a null specification.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFromSpecificationNull() {
        ConfigurationInterpolator.fromSpecification(null);
    }

    /**
     * Tests whether modification of the list of default lookups does not affect the object.
     */

    /**
     * Tests whether default prefix lookups can be queried as a map.
     */

    /**
     * Tests that the map with default lookups cannot be modified.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetDefaultPrefixLookupsModify() {
        ConfigurationInterpolator.getDefaultPrefixLookups().put("test", EasyMock.createMock(Lookup.class));
    }

    /**
     * Tests that modification of the map with lookups does not affect the object.
     */

    /**
     * Tests that a custom string converter can be used.
     */

    /**
     * Tests that the default string converter can be reapplied by passing {@code null}.
     */

    /**
     * Tests creating an instance. Does it contain some predefined lookups and a default string converter?
     */

    /**
     * Tests that an empty variable definition does not cause problems.
     */

    /**
     * Tests that a blank variable definition does not cause problems.
     */

    /**
     * Tests interpolation of a non string argument.
     */

    /**
     * Tests interpolation of a collection argument.
     */

    /**
     * Tests interpolation of an array argument.
     */

    /**
     * Tests a successful interpolation of a string value.
     */

    /**
     * Tests interpolation with a variable which cannot be resolved.
     */

    /**
     * Tests a property value consisting of multiple variables.
     */

    /**
     * Tests interpolation with variables containing multiple simple non-string variables.
     */

    /**
     * Tests interpolation with multiple variables containing collections and iterators.
     */

    /**
     * Tests interpolation with multiple variables containing arrays.
     */

    /**
     * Tests an interpolation that consists of a single variable only. The variable's value should be returned verbatim.
     */

    /**
     * Tests an interpolation that consists of a single collection variable only. The variable's value
     * should be returned verbatim.
     */

    /**
     * Tests an interpolation that consists of a single array variable only. The variable's value
     * should be returned verbatim.
     */

    /**
     * Tests an interpolation that consists of a single undefined variable only with and without a default value.
     */

    /**
     * Tests a variable declaration which lacks the trailing closing bracket.
     */
    @Test
    public void testInterpolationVariableIncomplete() {
        final String value = "${" + TEST_NAME;
        interpolator.addDefaultLookup(setUpTestLookup(TEST_NAME, "someValue"));
        assertEquals("Wrong result", value, interpolator.interpolate(value));
    }

    /**
     * Tests an interpolated string that begins and ends with variable lookups that have
     * the potential to fail. Part of CONFIGURATION-764.
     */

    /**
     * Tests nullSafeLookup() if a lookup object was provided.
     */

    /**
     * Tests whether nullSafeLookup() can handle null input.
     */

    /**
     * Tests that the prefix set cannot be modified.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testPrefixSetModify() {
        interpolator.registerLookup(TEST_PREFIX, setUpTestLookup());
        final Iterator<String> it = interpolator.prefixSet().iterator();
        it.next();
        it.remove();
    }

    /**
     * Tests registering a lookup object at an instance.
     */

    /**
     * Tests registering a null lookup object. This should cause an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterLookupNull() {
        interpolator.registerLookup(TEST_PREFIX, null);
    }

    /**
     * Tests registering a lookup object for an undefined prefix. This should cause an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRegisterLookupNullPrefix() {
        interpolator.registerLookup(null, EasyMock.createMock(Lookup.class));
    }

    /**
     * Tests whether a map with lookup objects can be registered.
     */

    /**
     * Tests whether a null map with lookup objects is handled correctly.
     */

    /**
     * Tests whether a default lookup object can be removed.
     */

    /**
     * Tests whether a non existing default lookup object can be removed.
     */

    /**
     * Tests looking up a variable without a prefix. This should trigger the default lookup object.
     */

    /**
     * Tests whether the default lookup is called for variables with a prefix when the lookup that was registered for this
     * prefix is not able to resolve the variable.
     */

    /**
     * Tests an empty variable name without a prefix.
     */

    /**
     * Tests the empty variable prefix. This is a special case, but legal.
     */

    /**
     * Tests an empty variable name.
     */

    /**
     * Tests looking up a variable without a prefix when no default lookup is specified. Result should be null in this case.
     */

    /**
     * Tests looking up a null variable. Result should be null, too.
     */

    /**
     * Tests handling of a parent {@code ConfigurationInterpolator} if the variable can already be resolved by the current
     * instance.
     */

    /**
     * Tests whether the parent {@code ConfigurationInterpolator} is invoked if the test instance cannot resolve a variable.
     */

    /**
     * Tests whether a variable can be resolved using the associated lookup object. The lookup is identified by the
     * variable's prefix.
     */

    /**
     * Tests the behavior of the lookup method for variables with an unknown prefix. These variables should not be resolved.
     */

    @Test
    public void testDefaultStringLookupsHolder_lookupsPropertyNotPresent() {
        checkDefaultPrefixLookupsHolder(new Properties(),
                "base64",
                StringLookupFactory.KEY_BASE64_DECODER,
                StringLookupFactory.KEY_BASE64_ENCODER,
                StringLookupFactory.KEY_CONST,
                StringLookupFactory.KEY_DATE,
                StringLookupFactory.KEY_ENV,
                StringLookupFactory.KEY_FILE,
                StringLookupFactory.KEY_JAVA,
                StringLookupFactory.KEY_LOCALHOST,
                StringLookupFactory.KEY_PROPERTIES,
                StringLookupFactory.KEY_RESOURCE_BUNDLE,
                StringLookupFactory.KEY_SYS,
                StringLookupFactory.KEY_URL_DECODER,
                StringLookupFactory.KEY_URL_ENCODER,
                StringLookupFactory.KEY_XML);
    }

    @Test
    public void testDefaultStringLookupsHolder_lookupsPropertyEmptyAndBlank() {
        final Properties propsWithNull = new Properties();
        propsWithNull.setProperty(ConfigurationInterpolator.DEFAULT_PREFIX_LOOKUPS_PROPERTY, "");

        checkDefaultPrefixLookupsHolder(propsWithNull);

        final Properties propsWithBlank = new Properties();
        propsWithBlank.setProperty(ConfigurationInterpolator.DEFAULT_PREFIX_LOOKUPS_PROPERTY, " ");

        checkDefaultPrefixLookupsHolder(propsWithBlank);
    }

    @Test
    public void testDefaultStringLookupsHolder_givenSingleLookup() {
        final Properties props = new Properties();
        props.setProperty(ConfigurationInterpolator.DEFAULT_PREFIX_LOOKUPS_PROPERTY, "base64_encoder");

        checkDefaultPrefixLookupsHolder(props,
                "base64",
                StringLookupFactory.KEY_BASE64_ENCODER);
    }

    @Test
    public void testDefaultStringLookupsHolder_givenSingleLookup_weirdString() {
        final Properties props = new Properties();
        props.setProperty(ConfigurationInterpolator.DEFAULT_PREFIX_LOOKUPS_PROPERTY, " \n \t  ,, DnS , , ");

        checkDefaultPrefixLookupsHolder(props, StringLookupFactory.KEY_DNS);
    }

    @Test
    public void testDefaultStringLookupsHolder_multipleLookups() {
        final Properties props = new Properties();
        props.setProperty(ConfigurationInterpolator.DEFAULT_PREFIX_LOOKUPS_PROPERTY, "dns, url script ");

        checkDefaultPrefixLookupsHolder(props,
                StringLookupFactory.KEY_DNS,
                StringLookupFactory.KEY_URL,
                StringLookupFactory.KEY_SCRIPT);
    }

    @Test
    public void testDefaultStringLookupsHolder_allLookups() {
        final Properties props = new Properties();
        props.setProperty(ConfigurationInterpolator.DEFAULT_PREFIX_LOOKUPS_PROPERTY,
                "BASE64_DECODER BASE64_ENCODER const, date, dns, environment "
                + "file ,java, local_host properties, resource_bundle,script,system_properties "
                + "url url_decoder  , url_encoder, xml");

        checkDefaultPrefixLookupsHolder(props,
                "base64",
                StringLookupFactory.KEY_BASE64_DECODER,
                StringLookupFactory.KEY_BASE64_ENCODER,
                StringLookupFactory.KEY_CONST,
                StringLookupFactory.KEY_DATE,
                StringLookupFactory.KEY_ENV,
                StringLookupFactory.KEY_FILE,
                StringLookupFactory.KEY_JAVA,
                StringLookupFactory.KEY_LOCALHOST,
                StringLookupFactory.KEY_PROPERTIES,
                StringLookupFactory.KEY_RESOURCE_BUNDLE,
                StringLookupFactory.KEY_SYS,
                StringLookupFactory.KEY_URL_DECODER,
                StringLookupFactory.KEY_URL_ENCODER,
                StringLookupFactory.KEY_XML,

                StringLookupFactory.KEY_DNS,
                StringLookupFactory.KEY_URL,
                StringLookupFactory.KEY_SCRIPT);
    }

    private static void checkDefaultPrefixLookupsHolder(final Properties props, final String... keys) {
        final ConfigurationInterpolator.DefaultPrefixLookupsHolder holder =
                new ConfigurationInterpolator.DefaultPrefixLookupsHolder(props);

        final Map<String, Lookup> lookupMap = holder.getDefaultPrefixLookups();

        assertMappedLookups(lookupMap, keys);
    }

    private static void assertMappedLookups(final Map<String, Lookup> lookupMap, final String... keys) {
        final Set<String> remainingKeys = new HashSet<>(lookupMap.keySet());

        for (final String key : keys) {
            assertNotNull("Expected map to contain string lookup for key " + key, key);

            remainingKeys.remove(key);
        }

        assertTrue("Unexpected keys in lookup map: " + remainingKeys, remainingKeys.isEmpty());
    }

    /**
     * Main method used to verify the default lookups resolved during JVM execution.
     * @param args
     */
    public static void main(final String[] args) {
        System.out.println("Default lookups");
        for (final String key : ConfigurationInterpolator.getDefaultPrefixLookups().keySet()) {
            System.out.println("- " + key);
        }
    }

    @Test
    public void testAddDefaultLookups_1_oe() {
        final List<Lookup> lookups = new ArrayList<>();
        lookups.add(setUpTestLookup());
        lookups.add(setUpTestLookup("test", "value"));
        interpolator.addDefaultLookups(lookups);
        final List<Lookup> lookups2 = interpolator.getDefaultLookups();
        assertEquals("Wrong number of default lookups", 2, lookups2.size());
    }

    @Test
    public void testAddDefaultLookups_2_oe() {
        final List<Lookup> lookups = new ArrayList<>();
        lookups.add(setUpTestLookup());
        lookups.add(setUpTestLookup("test", "value"));
        interpolator.addDefaultLookups(lookups);
        final List<Lookup> lookups2 = interpolator.getDefaultLookups();
        assertTrue("Wrong content", lookups2.containsAll(lookups));
    }

    @Test
    public void testAddDefaultLookupsNull_1_oe() {
        interpolator.addDefaultLookups(null);
        assertTrue("Got default lookups", interpolator.getDefaultLookups().isEmpty());
    }

    @Test
    public void testDeregisterLookup_1_oe() {
        final Lookup lookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(lookup);
        interpolator.registerLookup(TEST_PREFIX, lookup);
        assertTrue("Derigstration not successfull", interpolator.deregisterLookup(TEST_PREFIX));
    }

    @Test
    public void testDeregisterLookupNonExisting_1_oe() {
        assertFalse("Could deregister unknown lookup", interpolator.deregisterLookup(TEST_PREFIX));
    }

    @Test
    public void testEnableSubstitutionInVariables_1_oe() {
        assertFalse("Variable substitution enabled", interpolator.isEnableSubstitutionInVariables());
    }

    @Test
    public void testEnableSubstitutionInVariables_2_oe() {
        interpolator.addDefaultLookup(setUpTestLookup("java.version", "1.4"));
        interpolator.addDefaultLookup(setUpTestLookup("jre-1.4", "C:\\java\\1.4"));
        final String var = "${jre-${java.version}}";
        assertEquals("Wrong result (1)", var, interpolator.interpolate(var));
    }

    @Test
    public void testEnableSubstitutionInVariables_3_oe() {
        interpolator.addDefaultLookup(setUpTestLookup("java.version", "1.4"));
        interpolator.addDefaultLookup(setUpTestLookup("jre-1.4", "C:\\java\\1.4"));
        final String var = "${jre-${java.version}}";
        interpolator.setEnableSubstitutionInVariables(true);
        assertTrue("Variable substitution not enabled", interpolator.isEnableSubstitutionInVariables());
    }

    @Test
    public void testEnableSubstitutionInVariables_4_oe() {
        interpolator.addDefaultLookup(setUpTestLookup("java.version", "1.4"));
        interpolator.addDefaultLookup(setUpTestLookup("jre-1.4", "C:\\java\\1.4"));
        final String var = "${jre-${java.version}}";
        interpolator.setEnableSubstitutionInVariables(true);
        assertEquals("Wrong result (2)", "C:\\java\\1.4", interpolator.interpolate(var));
    }

    @Test
    public void testFromSpecificationInterpolator_1_oe() {
        final ConfigurationInterpolator ci = EasyMock.createMock(ConfigurationInterpolator.class);
        EasyMock.replay(ci);
        final InterpolatorSpecification spec = new InterpolatorSpecification.Builder().withDefaultLookup(EasyMock.createMock(Lookup.class))
            .withParentInterpolator(interpolator).withInterpolator(ci).create();
        assertSame("Wrong result", ci, ConfigurationInterpolator.fromSpecification(spec));
    }

    @Test
    public void testFromSpecificationNewInstance_1_oe() {
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        final Lookup preLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(defLookup, preLookup);
        final Function<Object, String> stringConverter = obj -> Objects.toString(obj, null);
        final InterpolatorSpecification spec = new InterpolatorSpecification.Builder()
            .withDefaultLookup(defLookup)
            .withPrefixLookup("p", preLookup)
            .withParentInterpolator(interpolator)
            .withStringConverter(stringConverter)
            .create();
        final ConfigurationInterpolator ci = ConfigurationInterpolator.fromSpecification(spec);
        assertEquals("Wrong number of default lookups", 1, ci.getDefaultLookups().size());
    }

    @Test
    public void testFromSpecificationNewInstance_2_oe() {
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        final Lookup preLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(defLookup, preLookup);
        final Function<Object, String> stringConverter = obj -> Objects.toString(obj, null);
        final InterpolatorSpecification spec = new InterpolatorSpecification.Builder()
            .withDefaultLookup(defLookup)
            .withPrefixLookup("p", preLookup)
            .withParentInterpolator(interpolator)
            .withStringConverter(stringConverter)
            .create();
        final ConfigurationInterpolator ci = ConfigurationInterpolator.fromSpecification(spec);
        assertTrue("Wrong default lookup", ci.getDefaultLookups().contains(defLookup));
    }

    @Test
    public void testFromSpecificationNewInstance_3_oe() {
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        final Lookup preLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(defLookup, preLookup);
        final Function<Object, String> stringConverter = obj -> Objects.toString(obj, null);
        final InterpolatorSpecification spec = new InterpolatorSpecification.Builder()
            .withDefaultLookup(defLookup)
            .withPrefixLookup("p", preLookup)
            .withParentInterpolator(interpolator)
            .withStringConverter(stringConverter)
            .create();
        final ConfigurationInterpolator ci = ConfigurationInterpolator.fromSpecification(spec);
        assertEquals("Wrong number of prefix lookups", 1, ci.getLookups().size());
    }

    @Test
    public void testFromSpecificationNewInstance_4_oe() {
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        final Lookup preLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(defLookup, preLookup);
        final Function<Object, String> stringConverter = obj -> Objects.toString(obj, null);
        final InterpolatorSpecification spec = new InterpolatorSpecification.Builder()
            .withDefaultLookup(defLookup)
            .withPrefixLookup("p", preLookup)
            .withParentInterpolator(interpolator)
            .withStringConverter(stringConverter)
            .create();
        final ConfigurationInterpolator ci = ConfigurationInterpolator.fromSpecification(spec);
        assertSame("Wrong prefix lookup", preLookup, ci.getLookups().get("p"));
    }

    @Test
    public void testFromSpecificationNewInstance_5_oe() {
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        final Lookup preLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(defLookup, preLookup);
        final Function<Object, String> stringConverter = obj -> Objects.toString(obj, null);
        final InterpolatorSpecification spec = new InterpolatorSpecification.Builder()
            .withDefaultLookup(defLookup)
            .withPrefixLookup("p", preLookup)
            .withParentInterpolator(interpolator)
            .withStringConverter(stringConverter)
            .create();
        final ConfigurationInterpolator ci = ConfigurationInterpolator.fromSpecification(spec);
        assertSame("Wrong parent", interpolator, ci.getParentInterpolator());
    }

    @Test
    public void testFromSpecificationNewInstance_6_oe() {
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        final Lookup preLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(defLookup, preLookup);
        final Function<Object, String> stringConverter = obj -> Objects.toString(obj, null);
        final InterpolatorSpecification spec = new InterpolatorSpecification.Builder()
            .withDefaultLookup(defLookup)
            .withPrefixLookup("p", preLookup)
            .withParentInterpolator(interpolator)
            .withStringConverter(stringConverter)
            .create();
        final ConfigurationInterpolator ci = ConfigurationInterpolator.fromSpecification(spec);
        assertSame("Wrong string converter", stringConverter, ci.getStringConverter());
    }

    @Test
    public void testGetDefaultLookupsModify_1_oe() {
        final List<Lookup> lookups = interpolator.getDefaultLookups();
        lookups.add(setUpTestLookup());
        assertTrue("List was modified", interpolator.getDefaultLookups().isEmpty());
    }

    @Test
    public void testGetDefaultPrefixLookups_1_oe() {
        final EnumSet<DefaultLookups> excluded = EnumSet.of(
                DefaultLookups.DNS,
                DefaultLookups.URL,
                DefaultLookups.SCRIPT);

        final EnumSet<DefaultLookups> included = EnumSet.complementOf(excluded);

        final Map<String, Lookup> lookups = ConfigurationInterpolator.getDefaultPrefixLookups();

        assertEquals("Wrong number of lookups", included.size(), lookups.size());
    }

    @Test
    public void testGetDefaultPrefixLookups_2_oe() {
        final EnumSet<DefaultLookups> excluded = EnumSet.of(
                DefaultLookups.DNS,
                DefaultLookups.URL,
                DefaultLookups.SCRIPT);

        final EnumSet<DefaultLookups> included = EnumSet.complementOf(excluded);

        final Map<String, Lookup> lookups = ConfigurationInterpolator.getDefaultPrefixLookups();

        for (final DefaultLookups l : included) {
            assertSame("Wrong entry for " + l, l.getLookup(), lookups.get(l.getPrefix()));
    }
    }

    @Test
    public void testGetDefaultPrefixLookups_3_oe() {
        final EnumSet<DefaultLookups> excluded = EnumSet.of(
                DefaultLookups.DNS,
                DefaultLookups.URL,
                DefaultLookups.SCRIPT);

        final EnumSet<DefaultLookups> included = EnumSet.complementOf(excluded);

        final Map<String, Lookup> lookups = ConfigurationInterpolator.getDefaultPrefixLookups();

        for (final DefaultLookups l : included) {
        }

        for (final DefaultLookups l : excluded) {
            assertNull("Unexpected entry for " + l, lookups.get(l.getPrefix()));
    }
    }

    @Test
    public void testGetLookupsModify_1_oe() {
        final Map<String, Lookup> lookups = interpolator.getLookups();
        lookups.put(TEST_PREFIX, setUpTestLookup());
        assertTrue("Map was modified", interpolator.getLookups().isEmpty());
    }

    @Test
    public void testSetStringConverter_1_oe() {
        final Function<Object, String> stringConverter = obj -> "'" + obj + "'";
        interpolator.addDefaultLookup(setUpTestLookup("x", Arrays.asList(1, 2)));
        interpolator.addDefaultLookup(setUpTestLookup("y", "abc"));
        interpolator.setStringConverter(stringConverter);
        assertSame("Wrong string converter", stringConverter, interpolator.getStringConverter());
    }

    @Test
    public void testSetStringConverter_2_oe() {
        final Function<Object, String> stringConverter = obj -> "'" + obj + "'";
        interpolator.addDefaultLookup(setUpTestLookup("x", Arrays.asList(1, 2)));
        interpolator.addDefaultLookup(setUpTestLookup("y", "abc"));
        interpolator.setStringConverter(stringConverter);
        assertEquals("Wrong value", "'abc': '[1, 2]'", interpolator.interpolate("${y}: ${x}"));
    }

    @Test
    public void testSetStringConverterNullArgumentUsesDefault_1_oe() {
        final Function<Object, String> stringConverter = obj -> "'" + obj + "'";
        interpolator.addDefaultLookup(setUpTestLookup("x", Arrays.asList(1, 2)));
        interpolator.addDefaultLookup(setUpTestLookup("y", "abc"));
        interpolator.setStringConverter(stringConverter);
        interpolator.setStringConverter(null);
        assertNotSame("Wrong string converter", stringConverter, interpolator.getStringConverter());
    }

    @Test
    public void testSetStringConverterNullArgumentUsesDefault_2_oe() {
        final Function<Object, String> stringConverter = obj -> "'" + obj + "'";
        interpolator.addDefaultLookup(setUpTestLookup("x", Arrays.asList(1, 2)));
        interpolator.addDefaultLookup(setUpTestLookup("y", "abc"));
        interpolator.setStringConverter(stringConverter);
        interpolator.setStringConverter(null);
        assertEquals("Wrong value", "abc: 1", interpolator.interpolate("${y}: ${x}"));
    }

    @Test
    public void testInit_1_oe() {
        assertTrue("A default lookup is set", interpolator.getDefaultLookups().isEmpty());
    }

    @Test
    public void testInit_2_oe() {
        assertTrue("Got predefined lookups", interpolator.getLookups().isEmpty());
    }

    @Test
    public void testInit_3_oe() {
        assertNull("Got a parent interpolator", interpolator.getParentInterpolator());
    }

    @Test
    public void testInit_4_oe() {
        assertNotNull("Missing string converter", interpolator.getStringConverter());
    }

    @Test
    public void testInit_5_oe() {
        assertEquals("Incorrect string converter value","1",interpolator.getStringConverter().apply(Arrays.asList(1,2)));
    }

    @Test
    public void testInterpolateEmptyVariable_1_oe() {
        final String value = "${}";
        assertEquals("Wrong result", value, interpolator.interpolate(value));
    }

    @Test
    public void testInterpolateBlankVariable_1_oe() {
        final String value = "${ }";
        assertEquals("Wrong result", value, interpolator.interpolate(value));
    }

    @Test
    public void testInterpolateObject_1_oe() {
        final Object value = 42;
        assertSame("Value was changed", value, interpolator.interpolate(value));
    }

    @Test
    public void testInterpolateCollection_1_oe() {
        final List<Integer> value = Arrays.asList(1, 2);
        assertSame("Value was changed", value, interpolator.interpolate(value));
    }

    @Test
    public void testInterpolateArray_1_oe() {
        final int[] value = {1, 2};
        assertSame("Value was changed", value, interpolator.interpolate(value));
    }

    @Test
    public void testInterpolateString_1_oe() {
        final String value = "${" + TEST_PREFIX + ':' + TEST_NAME + "}";
        interpolator.registerLookup(TEST_PREFIX, setUpTestLookup());
        assertEquals("Wrong result", TEST_VALUE, interpolator.interpolate(value));
    }

    @Test
    public void testInterpolateStringUnknownVariable_1_oe() {
        final String value = "${unknownVariable}";
        assertEquals("Wrong result", value, interpolator.interpolate(value));
    }

    @Test
    public void testInterpolationMultipleVariables_1_oe() {
        final String value = "The ${subject} jumps over ${object}.";
        interpolator.addDefaultLookup(setUpTestLookup("subject", "quick brown fox"));
        interpolator.addDefaultLookup(setUpTestLookup("object", "the lazy dog"));
        assertEquals("Wrong result", "The quick brown fox jumps over the lazy dog.", interpolator.interpolate(value));
    }

    @Test
    public void testInterpolationMultipleSimpleNonStringVariables_1_oe() {
        final String value = "${x} = ${y} is ${result}";
        interpolator.addDefaultLookup(setUpTestLookup("x", 1));
        interpolator.addDefaultLookup(setUpTestLookup("y", 2));
        interpolator.addDefaultLookup(setUpTestLookup("result", false));
        assertEquals("Wrong result", "1 = 2 is false", interpolator.interpolate(value));
    }

    @Test
    public void testInterpolationMultipleCollectionVariables_1_oe() {
        final String value = "${single}bc${multi}23${empty}${null}${multiIt}${emptyIt}${nullIt}";
        final List<Integer> multi = Arrays.asList(1, 0, 0);
        final List<String> single = Arrays.asList("a");
        final List<Object> empty = Collections.emptyList();
        final List<Object> containsNull = Arrays.asList((Object) null);
        interpolator.addDefaultLookup(setUpTestLookup("multi", multi));
        interpolator.addDefaultLookup(setUpTestLookup("multiIt", multi.iterator()));
        interpolator.addDefaultLookup(setUpTestLookup("single", single));
        interpolator.addDefaultLookup(setUpTestLookup("empty", empty));
        interpolator.addDefaultLookup(setUpTestLookup("emptyIt", empty.iterator()));
        interpolator.addDefaultLookup(setUpTestLookup("null", containsNull));
        interpolator.addDefaultLookup(setUpTestLookup("nullIt", containsNull.iterator()));
        assertEquals("Wrong result", "abc123${empty}${null}1${emptyIt}${nullIt}", interpolator.interpolate(value));
    }

    @Test
    public void testInterpolationMultipleArrayVariables_1_oe() {
        final String value = "${single}bc${multi}23${empty}${null}";
        final int[] multi = {1, 0, 0};
        final String[] single = {"a"};
        final int[] empty = {};
        final Object[] containsNull = {null};
        interpolator.addDefaultLookup(setUpTestLookup("multi", multi));
        interpolator.addDefaultLookup(setUpTestLookup("single", single));
        interpolator.addDefaultLookup(setUpTestLookup("empty", empty));
        interpolator.addDefaultLookup(setUpTestLookup("null", containsNull));
        assertEquals("Wrong result", "abc123${empty}${null}", interpolator.interpolate(value));
    }

    @Test
    public void testInterpolationSingleVariable_1_oe() {
        final Object value = 42;
        interpolator.addDefaultLookup(setUpTestLookup(TEST_NAME, value));
        assertEquals("Wrong result", value, interpolator.interpolate("${" + TEST_NAME + "}"));
    }

    @Test
    public void testInterpolationSingleCollectionVariable_1_oe() {
        final List<Integer> value = Arrays.asList(42);
        interpolator.addDefaultLookup(setUpTestLookup(TEST_NAME, value));
        assertEquals("Wrong result", value, interpolator.interpolate("${" + TEST_NAME + "}"));
    }

    @Test
    public void testInterpolationSingleArrayVariable_1_oe() {
        final int[] value = {42, -1};
        interpolator.addDefaultLookup(setUpTestLookup(TEST_NAME, value));
        assertEquals("Wrong result", value, interpolator.interpolate("${" + TEST_NAME + "}"));
    }

    @Test
    public void testInterpolationSingleVariableDefaultValue_1_oe() {
        final Object value = 42;
        interpolator.addDefaultLookup(setUpTestLookup(TEST_NAME, value));
        assertEquals("Wrong result", "${I_am_not_defined}", interpolator.interpolate("${I_am_not_defined}"));
    }

    @Test
    public void testInterpolationSingleVariableDefaultValue_2_oe() {
        final Object value = 42;
        interpolator.addDefaultLookup(setUpTestLookup(TEST_NAME, value));
        assertEquals("Wrong result", "42", interpolator.interpolate("${I_am_not_defined:-42}"));
    }

    @Test
    public void testInterpolationSingleVariableDefaultValue_3_oe() {
        final Object value = 42;
        interpolator.addDefaultLookup(setUpTestLookup(TEST_NAME, value));
        assertEquals("Wrong result", "", interpolator.interpolate("${I_am_not_defined:-}"));
    }

    @Test
    public void testInterpolationBeginningAndEndingRiskyVariableLookups_1_oe() {
        interpolator.registerLookups(ConfigurationInterpolator.getDefaultPrefixLookups());
        final String result = (String) interpolator.interpolate("${date:yyyy-MM}-${date:dd}");
        assertTrue("Wrong result: " + result, result.matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    public void testNullSafeLookupExisting_1_oe() {
        final Lookup look = EasyMock.createMock(Lookup.class);
        EasyMock.replay(look);
        assertSame("Wrong result", look, ConfigurationInterpolator.nullSafeLookup(look));
    }

    @Test
    public void testNullSafeLookupNull_1_oe() {
        final Lookup lookup = ConfigurationInterpolator.nullSafeLookup(null);
        assertNull("Got a lookup result", lookup.lookup("someVar"));
    }

    @Test
    public void testRegisterLookup_1_oe() {
        final Lookup lookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(lookup);
        interpolator.registerLookup(TEST_PREFIX, lookup);
        assertSame("New lookup not registered", lookup, interpolator.getLookups().get(TEST_PREFIX));
    }

    @Test
    public void testRegisterLookup_2_oe() {
        final Lookup lookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(lookup);
        interpolator.registerLookup(TEST_PREFIX, lookup);
        assertTrue("Not in prefix set", interpolator.prefixSet().contains(TEST_PREFIX));
    }

    @Test
    public void testRegisterLookup_3_oe() {
        final Lookup lookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(lookup);
        interpolator.registerLookup(TEST_PREFIX, lookup);
        assertTrue("Default lookups were changed", interpolator.getDefaultLookups().isEmpty());
    }

    @Test
    public void testRegisterLookups_1_oe() {
        final Lookup l1 = setUpTestLookup();
        final Lookup l2 = setUpTestLookup("someVar", "someValue");
        final Map<String, Lookup> lookups = new HashMap<>();
        lookups.put(TEST_PREFIX, l1);
        final String prefix2 = TEST_PREFIX + "_other";
        lookups.put(prefix2, l2);
        interpolator.registerLookups(lookups);
        final Map<String, Lookup> lookups2 = interpolator.getLookups();
        assertEquals("Wrong number of lookups", 2, lookups2.size());
    }

    @Test
    public void testRegisterLookups_2_oe() {
        final Lookup l1 = setUpTestLookup();
        final Lookup l2 = setUpTestLookup("someVar", "someValue");
        final Map<String, Lookup> lookups = new HashMap<>();
        lookups.put(TEST_PREFIX, l1);
        final String prefix2 = TEST_PREFIX + "_other";
        lookups.put(prefix2, l2);
        interpolator.registerLookups(lookups);
        final Map<String, Lookup> lookups2 = interpolator.getLookups();
        assertEquals("Wrong l1", l1, lookups2.get(TEST_PREFIX));
    }

    @Test
    public void testRegisterLookups_3_oe() {
        final Lookup l1 = setUpTestLookup();
        final Lookup l2 = setUpTestLookup("someVar", "someValue");
        final Map<String, Lookup> lookups = new HashMap<>();
        lookups.put(TEST_PREFIX, l1);
        final String prefix2 = TEST_PREFIX + "_other";
        lookups.put(prefix2, l2);
        interpolator.registerLookups(lookups);
        final Map<String, Lookup> lookups2 = interpolator.getLookups();
        assertEquals("Wrong l2", l2, lookups2.get(prefix2));
    }

    @Test
    public void testRegisterLookupsNull_1_oe() {
        interpolator.registerLookups(null);
        assertTrue("Got lookups", interpolator.getLookups().isEmpty());
    }

    @Test
    public void testRemoveDefaultLookup_1_oe() {
        final List<Lookup> lookups = new ArrayList<>();
        lookups.add(setUpTestLookup());
        lookups.add(setUpTestLookup("test", "value"));
        interpolator.addDefaultLookups(lookups);
        assertTrue("Wrong result", interpolator.removeDefaultLookup(lookups.get(0)));
    }

    @Test
    public void testRemoveDefaultLookupNonExisting_1_oe() {
        assertFalse("Wrong result", interpolator.removeDefaultLookup(setUpTestLookup()));
    }

    @Test
    public void testResolveDefault_1_oe() {
        final Lookup l1 = EasyMock.createMock(Lookup.class);
        final Lookup l2 = EasyMock.createMock(Lookup.class);
        final Lookup l3 = EasyMock.createMock(Lookup.class);
        EasyMock.expect(l1.lookup(TEST_NAME)).andReturn(null);
        EasyMock.expect(l2.lookup(TEST_NAME)).andReturn(TEST_VALUE);
        EasyMock.replay(l1, l2, l3);
        interpolator.addDefaultLookups(Arrays.asList(l1, l2, l3));
        assertEquals("Wrong variable value", TEST_VALUE, interpolator.resolve(TEST_NAME));
    }

    @Test
    public void testResolveDefaultAfterPrefixFails_1_oe() {
        final String varName = TEST_PREFIX + ':' + TEST_NAME + "2";
        interpolator.registerLookup(TEST_PREFIX, setUpTestLookup());
        interpolator.addDefaultLookup(setUpTestLookup(varName, TEST_VALUE));
        assertEquals("Variable is not resolved by default lookup", TEST_VALUE, interpolator.resolve(varName));
    }

    @Test
    public void testResolveDefaultEmptyVarName_1_oe() {
        interpolator.addDefaultLookup(setUpTestLookup("", TEST_VALUE));
        assertEquals("Wrong variable value", TEST_VALUE, interpolator.resolve(""));
    }

    @Test
    public void testResolveEmptyPrefix_1_oe() {
        interpolator.registerLookup("", setUpTestLookup());
        assertEquals("Wrong variable value", TEST_VALUE, interpolator.resolve(":" + TEST_NAME));
    }

    @Test
    public void testResolveEmptyVarName_1_oe() {
        interpolator.registerLookup(TEST_PREFIX, setUpTestLookup("", TEST_VALUE));
        assertEquals("Wrong variable value", TEST_VALUE, interpolator.resolve(TEST_PREFIX + ":"));
    }

    @Test
    public void testResolveNoDefault_1_oe() {
        assertNull("Variable could be resolved", interpolator.resolve(TEST_NAME));
    }

    @Test
    public void testResolveNull_1_oe() {
        assertNull("Could resolve null variable", interpolator.resolve(null));
    }

    @Test
    public void testResolveParentVariableFound_1_oe() {
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        EasyMock.replay(parent);
        interpolator.setParentInterpolator(parent);
        interpolator.registerLookup(TEST_PREFIX, setUpTestLookup());
        assertEquals("Wrong value", TEST_VALUE, interpolator.resolve(TEST_PREFIX + ':' + TEST_NAME));
    }

    @Test
    public void testResolveParentVariableNotFound_1_oe() {
        final ConfigurationInterpolator parent = EasyMock.createMock(ConfigurationInterpolator.class);
        EasyMock.expect(parent.resolve(TEST_NAME)).andReturn(TEST_VALUE);
        EasyMock.replay(parent);
        interpolator.setParentInterpolator(parent);
        assertEquals("Wrong value", TEST_VALUE, interpolator.resolve(TEST_NAME));
    }

    @Test
    public void testResolveWithPrefix_1_oe() {
        interpolator.registerLookup(TEST_PREFIX, setUpTestLookup());
        assertEquals("Wrong variable value", TEST_VALUE, interpolator.resolve(TEST_PREFIX + ':' + TEST_NAME));
    }

    @Test
    public void testResolveWithUnknownPrefix_1_oe() {
        interpolator.registerLookup(TEST_PREFIX, setUpTestLookup());
        assertNull("Variable could be resolved", interpolator.resolve("UnknownPrefix:" + TEST_NAME));
    }

    @Test
    public void testResolveWithUnknownPrefix_2_oe() {
        interpolator.registerLookup(TEST_PREFIX, setUpTestLookup());
        assertNull("Variable with empty prefix could be resolved", interpolator.resolve(":" + TEST_NAME));
    }

    @Test
    public void testDefaultStringLookupsHolder_invalidLookupsDefinition_2_oe() {
        final Properties props = new Properties();
        props.setProperty(ConfigurationInterpolator.DEFAULT_PREFIX_LOOKUPS_PROPERTY, "base64_encoder nope");

        try {
            new ConfigurationInterpolator.DefaultPrefixLookupsHolder(props);

        } catch (Exception exc) {
            assertEquals("Invalid default lookups definition: base64_encoder nope", exc.getMessage());
    }
    }

}
