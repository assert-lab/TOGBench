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
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.event.ConfigurationEvent;
import org.apache.commons.configuration2.event.EventListener;
import org.apache.commons.configuration2.event.EventListenerTestImpl;
import org.apache.commons.configuration2.ex.ConversionException;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.configuration2.interpol.Lookup;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import junitx.framework.ListAssert;

/**
 * Tests some basic functions of the BaseConfiguration class. Missing keys will throw Exceptions
 *
 */
public class TestBaseConfiguration_OE25Dev {
    /** Constant for the number key. */
    static final String KEY_NUMBER = "number";

    protected static Class<?> missingElementException = NoSuchElementException.class;
    protected static Class<?> incompatibleElementException = ConversionException.class;
    protected BaseConfiguration config;

    @Before
    public void setUp() throws Exception {
        config = new BaseConfiguration();
        config.setThrowExceptionOnMissing(true);
        config.setListDelimiterHandler(new DefaultListDelimiterHandler(','));
    }

    /**
     * Tests cloning a BaseConfiguration.
     */

    /**
     * Tests whether interpolation works as expected after cloning.
     */

    /**
     * Tests the clone() method if a list property is involved.
     */

    /**
     * Tests whether a cloned configuration is decoupled from its original.
     */

    @Test(expected = ConversionException.class)
    public void testGetBigDecimalIncompatibleType() {
        config.setProperty("test.empty", "");
        config.getBigDecimal("test.empty");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetBigDecimalUnknown() {
        config.getBigDecimal("numberNotInConfig");
    }

    @Test(expected = ConversionException.class)
    public void testGetBigIntegerIncompatibleType() {
        config.setProperty("test.empty", "");
        config.getBigInteger("test.empty");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetBigIntegerUnknown() {
        config.getBigInteger("numberNotInConfig");
    }

    @Test(expected = ConversionException.class)
    public void testGetBooleanIncompatibleType() {
        config.setProperty("test.empty", "");
        config.getBoolean("test.empty");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetBooleanUnknown() {
        config.getBoolean("numberNotInConfig");
    }

    @Test(expected = ConversionException.class)
    public void testGetByteIncompatibleType() {
        config.setProperty("test.empty", "");
        config.getByte("test.empty");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetByteUnknown() {
        config.getByte("numberNotInConfig");
    }

    @Test(expected = ConversionException.class)
    public void testGetDoubleIncompatibleType() {
        config.setProperty("test.empty", "");
        config.getDouble("test.empty");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetDoubleUnknown() {
        config.getDouble("numberNotInConfig");
    }

    @Test(expected = ConversionException.class)
    public void testGetDurationIncompatibleType() {
        config.setProperty("test.empty", "");
        config.getDuration("test.empty");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetDurationUnknown() {
        config.getDuration("numberNotInConfig");
    }

    @Test(expected = ConversionException.class)
    public void testGetFloatIncompatibleType() {
        config.setProperty("test.empty", "");
        config.getFloat("test.empty");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetFloatUnknown() {
        config.getFloat("numberNotInConfig");
    }

    /**
     * Tests accessing and manipulating the interpolator object.
     */
    @Test
    public void testGetInterpolator() {
        InterpolationTestHelper.testGetInterpolator(config);
    }

    @Test(expected = ConversionException.class)
    public void testGetLongIncompatibleTypes() {
        config.setProperty("test.empty", "");
        config.getLong("test.empty");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetLongUnknown() {
        config.getLong("numberNotInConfig");
    }

    @Test(expected = ConversionException.class)
    public void testGetShortIncompatibleType() {
        config.setProperty("test.empty", "");
        config.getShort("test.empty");
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetShortUnknown() {
        config.getShort("numberNotInConfig");
    }

    /**
     * Tests that the first scalar of a list is returned.
     */

    @Test(expected = NoSuchElementException.class)
    public void testGetStringUnknown() {
        config.getString("stringNotInConfig");
    }

    /**
     * Tests whether a {@code ConfigurationInterpolator} can be created and installed.
     */

    /**
     * Tests obtaining a configuration with all variables replaced by their actual values.
     */
    @Test
    public void testInterpolatedConfiguration() {
        InterpolationTestHelper.testInterpolatedConfiguration(config);
    }

    @Test
    public void testInterpolation() {
        InterpolationTestHelper.testInterpolation(config);
    }

    /**
     * Tests interpolation of constant values.
     */
    @Test
    public void testInterpolationConstants() {
        InterpolationTestHelper.testInterpolationConstants(config);
    }

    /**
     * Tests interpolation of environment properties.
     */
    @Test
    public void testInterpolationEnvironment() {
        InterpolationTestHelper.testInterpolationEnvironment(config);
    }

    /**
     * Tests whether a variable can be escaped, so that it won't be interpolated.
     */
    @Test
    public void testInterpolationEscaped() {
        InterpolationTestHelper.testInterpolationEscaped(config);
    }

    /**
     * Tests interpolation with localhost values.
     */
    @Test
    public void testInterpolationLocalhost() {
        InterpolationTestHelper.testInterpolationLocalhost(config);
    }

    @Test
    public void testInterpolationLoop() {
        InterpolationTestHelper.testInterpolationLoop(config);
    }

    /**
     * Tests interpolation when a subset configuration is involved.
     */
    @Test
    public void testInterpolationSubset() {
        InterpolationTestHelper.testInterpolationSubset(config);
    }

    /**
     * Tests interpolation of system properties.
     */
    @Test
    public void testInterpolationSystemProperties() {
        InterpolationTestHelper.testInterpolationSystemProperties(config);
    }

    /**
     * Tests interpolation when the referred property is not found.
     */
    @Test
    public void testInterpolationUnknownProperty() {
        InterpolationTestHelper.testInterpolationUnknownProperty(config);
    }

    @Test
    public void testMultipleInterpolation() {
        InterpolationTestHelper.testMultipleInterpolation(config);
    }

    /**
     * Tests whether property access is possible without a {@code ConfigurationInterpolator}.
     */

    /**
     * Tests if conversion between number types is possible.
     */

    /**
     * Tests whether a {@code ConfigurationInterpolator} can be set.
     */

    /**
     * Tests the specific size() implementation.
     */

    @Test
    public void testAddProperty_1_oe() throws Exception {
        Collection<Object> props = new ArrayList<>();
        props.add("one");
        props.add("two,three,four");
        props.add(new String[] {"5.1", "5.2", "5.3,5.4", "5.5"});
        props.add("six");
        config.addProperty("complex.property", props);

        Object val = config.getProperty("complex.property");
        assertTrue(val instanceof Collection);
    }

    @Test
    public void testAddProperty_2_oe() throws Exception {
        Collection<Object> props = new ArrayList<>();
        props.add("one");
        props.add("two,three,four");
        props.add(new String[] {"5.1", "5.2", "5.3,5.4", "5.5"});
        props.add("six");
        config.addProperty("complex.property", props);

        Object val = config.getProperty("complex.property");
        // removed other assertion
        Collection<?> col = (Collection<?>) val;
        assertEquals(10, col.size());
    }

    @Test
    public void testAddProperty_3_oe() throws Exception {
        Collection<Object> props = new ArrayList<>();
        props.add("one");
        props.add("two,three,four");
        props.add(new String[] {"5.1", "5.2", "5.3,5.4", "5.5"});
        props.add("six");
        config.addProperty("complex.property", props);

        Object val = config.getProperty("complex.property");
        // removed other assertion
        Collection<?> col = (Collection<?>) val;
        // removed other assertion

        props = new ArrayList<>();
        props.add("quick");
        props.add("brown");
        props.add("fox,jumps");
        final Object[] data = {"The", props, "over,the", "lazy", "dog."};
        config.setProperty("complex.property", data);
        val = config.getProperty("complex.property");
        assertTrue(val instanceof Collection);
    }

    @Test
    public void testAddProperty_4_oe() throws Exception {
        Collection<Object> props = new ArrayList<>();
        props.add("one");
        props.add("two,three,four");
        props.add(new String[] {"5.1", "5.2", "5.3,5.4", "5.5"});
        props.add("six");
        config.addProperty("complex.property", props);

        Object val = config.getProperty("complex.property");
        // removed other assertion
        Collection<?> col = (Collection<?>) val;
        // removed other assertion

        props = new ArrayList<>();
        props.add("quick");
        props.add("brown");
        props.add("fox,jumps");
        final Object[] data = {"The", props, "over,the", "lazy", "dog."};
        config.setProperty("complex.property", data);
        val = config.getProperty("complex.property");
        // removed other assertion
        col = (Collection<?>) val;
        final Iterator<?> it = col.iterator();
        final StringTokenizer tok = new StringTokenizer("The quick brown fox jumps over the lazy dog.", " ");
        while (tok.hasMoreTokens()) {
            assertTrue(it.hasNext());
    }
    }

    @Test
    public void testAddProperty_5_oe() throws Exception {
        Collection<Object> props = new ArrayList<>();
        props.add("one");
        props.add("two,three,four");
        props.add(new String[] {"5.1", "5.2", "5.3,5.4", "5.5"});
        props.add("six");
        config.addProperty("complex.property", props);

        Object val = config.getProperty("complex.property");
        // removed other assertion
        Collection<?> col = (Collection<?>) val;
        // removed other assertion

        props = new ArrayList<>();
        props.add("quick");
        props.add("brown");
        props.add("fox,jumps");
        final Object[] data = {"The", props, "over,the", "lazy", "dog."};
        config.setProperty("complex.property", data);
        val = config.getProperty("complex.property");
        // removed other assertion
        col = (Collection<?>) val;
        final Iterator<?> it = col.iterator();
        final StringTokenizer tok = new StringTokenizer("The quick brown fox jumps over the lazy dog.", " ");
        while (tok.hasMoreTokens()) {
            // removed other assertion
            assertEquals(tok.nextToken(), it.next());
    }
    }

    @Test
    public void testAddProperty_6_oe() throws Exception {
        Collection<Object> props = new ArrayList<>();
        props.add("one");
        props.add("two,three,four");
        props.add(new String[] {"5.1", "5.2", "5.3,5.4", "5.5"});
        props.add("six");
        config.addProperty("complex.property", props);

        Object val = config.getProperty("complex.property");
        // removed other assertion
        Collection<?> col = (Collection<?>) val;
        // removed other assertion

        props = new ArrayList<>();
        props.add("quick");
        props.add("brown");
        props.add("fox,jumps");
        final Object[] data = {"The", props, "over,the", "lazy", "dog."};
        config.setProperty("complex.property", data);
        val = config.getProperty("complex.property");
        // removed other assertion
        col = (Collection<?>) val;
        final Iterator<?> it = col.iterator();
        final StringTokenizer tok = new StringTokenizer("The quick brown fox jumps over the lazy dog.", " ");
        while (tok.hasMoreTokens()) {
            // removed other assertion
            // removed other assertion
        }
        assertFalse(it.hasNext());
    }

    @Test
    public void testAddProperty_7_oe() throws Exception {
        Collection<Object> props = new ArrayList<>();
        props.add("one");
        props.add("two,three,four");
        props.add(new String[] {"5.1", "5.2", "5.3,5.4", "5.5"});
        props.add("six");
        config.addProperty("complex.property", props);

        Object val = config.getProperty("complex.property");
        // removed other assertion
        Collection<?> col = (Collection<?>) val;
        // removed other assertion

        props = new ArrayList<>();
        props.add("quick");
        props.add("brown");
        props.add("fox,jumps");
        final Object[] data = {"The", props, "over,the", "lazy", "dog."};
        config.setProperty("complex.property", data);
        val = config.getProperty("complex.property");
        // removed other assertion
        col = (Collection<?>) val;
        final Iterator<?> it = col.iterator();
        final StringTokenizer tok = new StringTokenizer("The quick brown fox jumps over the lazy dog.", " ");
        while (tok.hasMoreTokens()) {
            // removed other assertion
            // removed other assertion
        }
        // removed other assertion

        config.setProperty("complex.property", null);
        assertFalse(config.containsKey("complex.property"));
    }

    @Test
    public void testClone_1_oe() {
        for (int i = 0; i < 10; i++) {
            config.addProperty("key" + i, Integer.valueOf(i));
        }
        final BaseConfiguration config2 = (BaseConfiguration) config.clone();

        for (final Iterator<String> it = config.getKeys(); it.hasNext();) {
            final String key = it.next();
            assertTrue("Key not found: " + key, config2.containsKey(key));
    }
    }

    @Test
    public void testClone_2_oe() {
        for (int i = 0; i < 10; i++) {
            config.addProperty("key" + i, Integer.valueOf(i));
        }
        final BaseConfiguration config2 = (BaseConfiguration) config.clone();

        for (final Iterator<String> it = config.getKeys(); it.hasNext();) {
            final String key = it.next();
            // removed other assertion
            assertEquals("Wrong value for key " + key, config.getProperty(key), config2.getProperty(key));
    }
    }

    @Test
    public void testCloneInterpolation_1_oe() {
        final String keyAnswer = "answer";
        config.addProperty(keyAnswer, "The answer is ${" + KEY_NUMBER + "}.");
        config.addProperty(KEY_NUMBER, 42);
        final BaseConfiguration clone = (BaseConfiguration) config.clone();
        clone.setProperty(KEY_NUMBER, 43);
        assertEquals("Wrong interpolation in original", "The answer is 42.", config.getString(keyAnswer));
    }

    @Test
    public void testCloneInterpolation_2_oe() {
        final String keyAnswer = "answer";
        config.addProperty(keyAnswer, "The answer is ${" + KEY_NUMBER + "}.");
        config.addProperty(KEY_NUMBER, 42);
        final BaseConfiguration clone = (BaseConfiguration) config.clone();
        clone.setProperty(KEY_NUMBER, 43);
        // removed other assertion
        assertEquals("Wrong interpolation in clone", "The answer is 43.", clone.getString(keyAnswer));
    }

    @Test
    public void testCloneListProperty_1_oe() {
        final String key = "list";
        config.addProperty(key, "value1");
        config.addProperty(key, "value2");
        final BaseConfiguration config2 = (BaseConfiguration) config.clone();
        config2.addProperty(key, "value3");
        assertEquals("Wrong number of original properties", 2, config.getList(key).size());
    }

    @Test
    public void testCloneModify_1_oe() {
        final EventListener<ConfigurationEvent> l = new EventListenerTestImpl(config);
        config.addEventListener(ConfigurationEvent.ANY, l);
        config.addProperty("original", Boolean.TRUE);
        final BaseConfiguration config2 = (BaseConfiguration) config.clone();

        config2.addProperty("clone", Boolean.TRUE);
        assertFalse("New key appears in original", config.containsKey("clone"));
    }

    @Test
    public void testCloneModify_2_oe() {
        final EventListener<ConfigurationEvent> l = new EventListenerTestImpl(config);
        config.addEventListener(ConfigurationEvent.ANY, l);
        config.addProperty("original", Boolean.TRUE);
        final BaseConfiguration config2 = (BaseConfiguration) config.clone();

        config2.addProperty("clone", Boolean.TRUE);
        // removed other assertion
        config2.setProperty("original", Boolean.FALSE);
        assertTrue("Wrong value of original property", config.getBoolean("original"));
    }

    @Test
    public void testCloneModify_3_oe() {
        final EventListener<ConfigurationEvent> l = new EventListenerTestImpl(config);
        config.addEventListener(ConfigurationEvent.ANY, l);
        config.addProperty("original", Boolean.TRUE);
        final BaseConfiguration config2 = (BaseConfiguration) config.clone();

        config2.addProperty("clone", Boolean.TRUE);
        // removed other assertion
        config2.setProperty("original", Boolean.FALSE);
        // removed other assertion

        assertTrue("Event listener was copied", config2.getEventListeners(ConfigurationEvent.ANY).isEmpty());
    }

    @Test
    public void testCommaSeparatedString_1_oe() {
        final String prop = "hey, that's a test";
        config.setProperty("prop.string", prop);
        final List<Object> list = config.getList("prop.string");
        assertEquals("Wrong number of list elements", 2, list.size());
    }

    @Test
    public void testCommaSeparatedString_2_oe() {
        final String prop = "hey, that's a test";
        config.setProperty("prop.string", prop);
        final List<Object> list = config.getList("prop.string");
        // removed other assertion
        assertEquals("Wrong element 1", "hey", list.get(0));
    }

    @Test
    public void testCommaSeparatedStringEscaped_1_oe() {
        final String prop2 = "hey\\, that's a test";
        config.setProperty("prop.string", prop2);
        assertEquals("Wrong value", "hey, that's a test", config.getString("prop.string"));
    }

    @Test
    public void testGetBigDecimal_1_oe() {
        config.setProperty("numberBigD", "123.456");
        final BigDecimal number = new BigDecimal("123.456");
        final BigDecimal defaultValue = new BigDecimal("654.321");

        assertEquals("Existing key", number, config.getBigDecimal("numberBigD"));
    }

    @Test
    public void testGetBigDecimal_2_oe() {
        config.setProperty("numberBigD", "123.456");
        final BigDecimal number = new BigDecimal("123.456");
        final BigDecimal defaultValue = new BigDecimal("654.321");

        // removed other assertion
        assertEquals("Existing key with default value", number, config.getBigDecimal("numberBigD", defaultValue));
    }

    @Test
    public void testGetBigDecimal_3_oe() {
        config.setProperty("numberBigD", "123.456");
        final BigDecimal number = new BigDecimal("123.456");
        final BigDecimal defaultValue = new BigDecimal("654.321");

        // removed other assertion
        // removed other assertion
        assertEquals("Missing key with default value", defaultValue, config.getBigDecimal("numberNotInConfig", defaultValue));
    }

    @Test
    public void testGetBigInteger_1_oe() {
        config.setProperty("numberBigI", "1234567890");
        final BigInteger number = new BigInteger("1234567890");
        final BigInteger defaultValue = new BigInteger("654321");

        assertEquals("Existing key", number, config.getBigInteger("numberBigI"));
    }

    @Test
    public void testGetBigInteger_2_oe() {
        config.setProperty("numberBigI", "1234567890");
        final BigInteger number = new BigInteger("1234567890");
        final BigInteger defaultValue = new BigInteger("654321");

        // removed other assertion
        assertEquals("Existing key with default value", number, config.getBigInteger("numberBigI", defaultValue));
    }

    @Test
    public void testGetBigInteger_3_oe() {
        config.setProperty("numberBigI", "1234567890");
        final BigInteger number = new BigInteger("1234567890");
        final BigInteger defaultValue = new BigInteger("654321");

        // removed other assertion
        // removed other assertion
        assertEquals("Missing key with default value", defaultValue, config.getBigInteger("numberNotInConfig", defaultValue));
    }

    @Test
    public void testGetBinaryValue_1_oe() {
        config.setProperty("number", "0b11111111");
        assertEquals("byte value", (byte) 0xFF, config.getByte("number"));
    }

    @Test
    public void testGetBinaryValue_2_oe() {
        config.setProperty("number", "0b11111111");
        // removed other assertion

        config.setProperty("number", "0b1111111111111111");
        assertEquals("short value", (short) 0xFFFF, config.getShort("number"));
    }

    @Test
    public void testGetBinaryValue_3_oe() {
        config.setProperty("number", "0b11111111");
        // removed other assertion

        config.setProperty("number", "0b1111111111111111");
        // removed other assertion

        config.setProperty("number", "0b11111111111111111111111111111111");
        assertEquals("int value", 0xFFFFFFFF, config.getInt("number"));
    }

    @Test
    public void testGetBinaryValue_4_oe() {
        config.setProperty("number", "0b11111111");
        // removed other assertion

        config.setProperty("number", "0b1111111111111111");
        // removed other assertion

        config.setProperty("number", "0b11111111111111111111111111111111");
        // removed other assertion

        config.setProperty("number", "0b1111111111111111111111111111111111111111111111111111111111111111");
        assertEquals("long value", 0xFFFFFFFFFFFFFFFFL, config.getLong("number"));
    }

    @Test
    public void testGetBinaryValue_5_oe() {
        config.setProperty("number", "0b11111111");
        // removed other assertion

        config.setProperty("number", "0b1111111111111111");
        // removed other assertion

        config.setProperty("number", "0b11111111111111111111111111111111");
        // removed other assertion

        config.setProperty("number", "0b1111111111111111111111111111111111111111111111111111111111111111");
        // removed other assertion

        assertEquals("long value", 0xFFFFFFFFFFFFFFFFL, config.getBigInteger("number").longValue());
    }

    @Test
    public void testGetBoolean_1_oe() {
        config.setProperty("boolA", Boolean.TRUE);
        final boolean boolT = true, boolF = false;
        assertEquals("This returns true", boolT, config.getBoolean("boolA"));
    }

    @Test
    public void testGetBoolean_2_oe() {
        config.setProperty("boolA", Boolean.TRUE);
        final boolean boolT = true, boolF = false;
        // removed other assertion
        assertEquals("This returns true, not the default", boolT, config.getBoolean("boolA", boolF));
    }

    @Test
    public void testGetBoolean_3_oe() {
        config.setProperty("boolA", Boolean.TRUE);
        final boolean boolT = true, boolF = false;
        // removed other assertion
        // removed other assertion
        assertEquals("This returns false(default)", boolF, config.getBoolean("boolNotInConfig", boolF));
    }

    @Test
    public void testGetBoolean_4_oe() {
        config.setProperty("boolA", Boolean.TRUE);
        final boolean boolT = true, boolF = false;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("This returns true(Boolean)", Boolean.valueOf(boolT), config.getBoolean("boolA", Boolean.valueOf(boolF)));
    }

    @Test
    public void testGetByte_1_oe() {
        config.setProperty("number", "1");
        final byte oneB = 1;
        final byte twoB = 2;
        assertEquals("This returns 1(byte)", oneB, config.getByte("number"));
    }

    @Test
    public void testGetByte_2_oe() {
        config.setProperty("number", "1");
        final byte oneB = 1;
        final byte twoB = 2;
        // removed other assertion
        assertEquals("This returns 1(byte)", oneB, config.getByte("number", twoB));
    }

    @Test
    public void testGetByte_3_oe() {
        config.setProperty("number", "1");
        final byte oneB = 1;
        final byte twoB = 2;
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 2(default byte)", twoB, config.getByte("numberNotInConfig", twoB));
    }

    @Test
    public void testGetByte_4_oe() {
        config.setProperty("number", "1");
        final byte oneB = 1;
        final byte twoB = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 1(Byte)", Byte.valueOf(oneB), config.getByte("number", Byte.valueOf("2")));
    }

    @Test
    public void testGetDouble_1_oe() {
        config.setProperty("numberD", "1.0");
        final double oneD = 1;
        final double twoD = 2;
        assertEquals("This returns 1(double)", oneD, config.getDouble("numberD"), 0);
    }

    @Test
    public void testGetDouble_2_oe() {
        config.setProperty("numberD", "1.0");
        final double oneD = 1;
        final double twoD = 2;
        // removed other assertion
        assertEquals("This returns 1(double)", oneD, config.getDouble("numberD", twoD), 0);
    }

    @Test
    public void testGetDouble_3_oe() {
        config.setProperty("numberD", "1.0");
        final double oneD = 1;
        final double twoD = 2;
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 2(default double)", twoD, config.getDouble("numberNotInConfig", twoD), 0);
    }

    @Test
    public void testGetDouble_4_oe() {
        config.setProperty("numberD", "1.0");
        final double oneD = 1;
        final double twoD = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 1(Double)", Double.valueOf(oneD), config.getDouble("numberD", Double.valueOf("2")));
    }

    @Test
    public void testGetDuration_1_oe() {
        final Duration d = Duration.ofSeconds(1);
        config.setProperty("durationD", d.toString());
        final Duration oneD = Duration.ofSeconds(1);
        final Duration twoD = Duration.ofSeconds(2);
        assertEquals("This returns 1(Duration)", oneD, config.getDuration("durationD"));
    }

    @Test
    public void testGetDuration_2_oe() {
        final Duration d = Duration.ofSeconds(1);
        config.setProperty("durationD", d.toString());
        final Duration oneD = Duration.ofSeconds(1);
        final Duration twoD = Duration.ofSeconds(2);
        // removed other assertion
        assertEquals("This returns 1(Duration)", oneD, config.getDuration("durationD", twoD));
    }

    @Test
    public void testGetDuration_3_oe() {
        final Duration d = Duration.ofSeconds(1);
        config.setProperty("durationD", d.toString());
        final Duration oneD = Duration.ofSeconds(1);
        final Duration twoD = Duration.ofSeconds(2);
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 2(default Duration)", twoD, config.getDuration("numberNotInConfig", twoD));
    }

    @Test
    public void testGetDuration_4_oe() {
        final Duration d = Duration.ofSeconds(1);
        config.setProperty("durationD", d.toString());
        final Duration oneD = Duration.ofSeconds(1);
        final Duration twoD = Duration.ofSeconds(2);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 1(Duration)", oneD, config.getDuration("durationD", twoD));
    }

    @Test
    public void testGetEnum_1_oe() {
        config.setProperty("testEnum", EnumFixture.SMALLTALK.name());
        config.setProperty("testBadEnum", "This is not an enum value.");
        final EnumFixture enum1 = EnumFixture.SMALLTALK;
        final EnumFixture defaultValue = EnumFixture.JAVA;
        //
        assertEquals("Existing key", enum1, config.getEnum("testEnum", EnumFixture.class));
    }

    @Test
    public void testGetEnum_2_oe() {
        config.setProperty("testEnum", EnumFixture.SMALLTALK.name());
        config.setProperty("testBadEnum", "This is not an enum value.");
        final EnumFixture enum1 = EnumFixture.SMALLTALK;
        final EnumFixture defaultValue = EnumFixture.JAVA;
        //
        // removed other assertion
        assertEquals("Existing key with default value", enum1, config.getEnum("testEnum", EnumFixture.class, defaultValue));
    }

    @Test
    public void testGetEnum_3_oe() {
        config.setProperty("testEnum", EnumFixture.SMALLTALK.name());
        config.setProperty("testBadEnum", "This is not an enum value.");
        final EnumFixture enum1 = EnumFixture.SMALLTALK;
        final EnumFixture defaultValue = EnumFixture.JAVA;
        //
        // removed other assertion
        // removed other assertion
        assertEquals("Missing key with default value", defaultValue, config.getEnum("stringNotInConfig", EnumFixture.class, defaultValue));
    }

    @Test
    public void testGetFloat_1_oe() {
        config.setProperty("numberF", "1.0");
        final float oneF = 1;
        final float twoF = 2;
        assertEquals("This returns 1(float)", oneF, config.getFloat("numberF"), 0);
    }

    @Test
    public void testGetFloat_2_oe() {
        config.setProperty("numberF", "1.0");
        final float oneF = 1;
        final float twoF = 2;
        // removed other assertion
        assertEquals("This returns 1(float)", oneF, config.getFloat("numberF", twoF), 0);
    }

    @Test
    public void testGetFloat_3_oe() {
        config.setProperty("numberF", "1.0");
        final float oneF = 1;
        final float twoF = 2;
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 2(default float)", twoF, config.getFloat("numberNotInConfig", twoF), 0);
    }

    @Test
    public void testGetFloat_4_oe() {
        config.setProperty("numberF", "1.0");
        final float oneF = 1;
        final float twoF = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 1(Float)", Float.valueOf(oneF), config.getFloat("numberF", Float.valueOf("2")));
    }

    @Test
    public void testGetHexadecimalValue_1_oe() {
        config.setProperty("number", "0xFF");
        assertEquals("byte value", (byte) 0xFF, config.getByte("number"));
    }

    @Test
    public void testGetHexadecimalValue_2_oe() {
        config.setProperty("number", "0xFF");
        // removed other assertion

        config.setProperty("number", "0xFFFF");
        assertEquals("short value", (short) 0xFFFF, config.getShort("number"));
    }

    @Test
    public void testGetHexadecimalValue_3_oe() {
        config.setProperty("number", "0xFF");
        // removed other assertion

        config.setProperty("number", "0xFFFF");
        // removed other assertion

        config.setProperty("number", "0xFFFFFFFF");
        assertEquals("int value", 0xFFFFFFFF, config.getInt("number"));
    }

    @Test
    public void testGetHexadecimalValue_4_oe() {
        config.setProperty("number", "0xFF");
        // removed other assertion

        config.setProperty("number", "0xFFFF");
        // removed other assertion

        config.setProperty("number", "0xFFFFFFFF");
        // removed other assertion

        config.setProperty("number", "0xFFFFFFFFFFFFFFFF");
        assertEquals("long value", 0xFFFFFFFFFFFFFFFFL, config.getLong("number"));
    }

    @Test
    public void testGetHexadecimalValue_5_oe() {
        config.setProperty("number", "0xFF");
        // removed other assertion

        config.setProperty("number", "0xFFFF");
        // removed other assertion

        config.setProperty("number", "0xFFFFFFFF");
        // removed other assertion

        config.setProperty("number", "0xFFFFFFFFFFFFFFFF");
        // removed other assertion

        assertEquals("long value", 0xFFFFFFFFFFFFFFFFL, config.getBigInteger("number").longValue());
    }

    @Test
    public void testGetInterpolatedList_1_oe() {
        config.addProperty("number", "1");
        config.addProperty("array", "${number}");
        config.addProperty("array", "${number}");

        final List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");

        ListAssert.assertEquals("'array' property", list, config.getList("array"));
    }

    @Test
    public void testGetInterpolatedPrimitives_1_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        assertTrue("boolean interpolation", config.getBoolean("booleanValue"));
    }

    @Test
    public void testGetInterpolatedPrimitives_2_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        assertEquals("byte interpolation", 1, config.getByte("value"));
    }

    @Test
    public void testGetInterpolatedPrimitives_3_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        assertEquals("short interpolation", 1, config.getShort("value"));
    }

    @Test
    public void testGetInterpolatedPrimitives_4_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("int interpolation", 1, config.getInt("value"));
    }

    @Test
    public void testGetInterpolatedPrimitives_5_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("long interpolation", 1, config.getLong("value"));
    }

    @Test
    public void testGetInterpolatedPrimitives_6_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("float interpolation", 1, config.getFloat("value"), 0);
    }

    @Test
    public void testGetInterpolatedPrimitives_7_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("double interpolation", 1, config.getDouble("value"), 0);
    }

    @Test
    public void testGetInterpolatedPrimitives_8_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // primitive wrappers
        assertEquals("Boolean interpolation", Boolean.TRUE, config.getBoolean("booleanValue", null));
    }

    @Test
    public void testGetInterpolatedPrimitives_9_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // primitive wrappers
        // removed other assertion
        assertEquals("Byte interpolation", Byte.valueOf("1"), config.getByte("value", null));
    }

    @Test
    public void testGetInterpolatedPrimitives_10_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // primitive wrappers
        // removed other assertion
        // removed other assertion
        assertEquals("Short interpolation", Short.valueOf("1"), config.getShort("value", null));
    }

    @Test
    public void testGetInterpolatedPrimitives_11_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // primitive wrappers
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Integer interpolation", Integer.valueOf("1"), config.getInteger("value", null));
    }

    @Test
    public void testGetInterpolatedPrimitives_12_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // primitive wrappers
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Long interpolation", Long.valueOf("1"), config.getLong("value", null));
    }

    @Test
    public void testGetInterpolatedPrimitives_13_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // primitive wrappers
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Float interpolation", Float.valueOf("1"), config.getFloat("value", null));
    }

    @Test
    public void testGetInterpolatedPrimitives_14_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // primitive wrappers
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Double interpolation", Double.valueOf("1"), config.getDouble("value", null));
    }

    @Test
    public void testGetInterpolatedPrimitives_15_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // primitive wrappers
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("BigInteger interpolation", new BigInteger("1"), config.getBigInteger("value", null));
    }

    @Test
    public void testGetInterpolatedPrimitives_16_oe() {
        config.addProperty("number", "1");
        config.addProperty("value", "${number}");

        config.addProperty("boolean", "true");
        config.addProperty("booleanValue", "${boolean}");

        // primitive types
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // primitive wrappers
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("BigDecimal interpolation", new BigDecimal("1"), config.getBigDecimal("value", null));
    }

    @Test
    public void testGetList_1_oe() {
        config.addProperty("number", "1");
        config.addProperty("number", "2");
        final List<Object> list = config.getList("number");
        assertNotNull("The list is null", list);
    }

    @Test
    public void testGetList_2_oe() {
        config.addProperty("number", "1");
        config.addProperty("number", "2");
        final List<Object> list = config.getList("number");
        // removed other assertion
        assertEquals("List size", 2, list.size());
    }

    @Test
    public void testGetList_3_oe() {
        config.addProperty("number", "1");
        config.addProperty("number", "2");
        final List<Object> list = config.getList("number");
        // removed other assertion
        // removed other assertion
        assertTrue("The number 1 is missing from the list", list.contains("1"));
    }

    @Test
    public void testGetList_4_oe() {
        config.addProperty("number", "1");
        config.addProperty("number", "2");
        final List<Object> list = config.getList("number");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("The number 2 is missing from the list", list.contains("2"));
    }

    @Test
    public void testGetLong_1_oe() {
        config.setProperty("numberL", "1");
        final long oneL = 1;
        final long twoL = 2;
        assertEquals("This returns 1(long)", oneL, config.getLong("numberL"));
    }

    @Test
    public void testGetLong_2_oe() {
        config.setProperty("numberL", "1");
        final long oneL = 1;
        final long twoL = 2;
        // removed other assertion
        assertEquals("This returns 1(long)", oneL, config.getLong("numberL", twoL));
    }

    @Test
    public void testGetLong_3_oe() {
        config.setProperty("numberL", "1");
        final long oneL = 1;
        final long twoL = 2;
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 2(default long)", twoL, config.getLong("numberNotInConfig", twoL));
    }

    @Test
    public void testGetLong_4_oe() {
        config.setProperty("numberL", "1");
        final long oneL = 1;
        final long twoL = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 1(Long)", Long.valueOf(oneL), config.getLong("numberL", Long.valueOf("2")));
    }

    @Test
    public void testGetProperty_1_oe() {
        /* should be empty and return null */
        assertNull("This returns null", config.getProperty("foo"));
    }

    @Test
    public void testGetProperty_2_oe() {
        /* should be empty and return null */
        // removed other assertion

        /* add a real value, and get it two different ways */
        config.setProperty("number", "1");
        assertEquals("This returns '1'", config.getProperty("number"), "1");
    }

    @Test
    public void testGetProperty_3_oe() {
        /* should be empty and return null */
        // removed other assertion

        /* add a real value, and get it two different ways */
        config.setProperty("number", "1");
        // removed other assertion
        assertEquals("This returns '1'", config.getString("number"), "1");
    }

    @Test
    public void testGetShort_1_oe() {
        config.setProperty("numberS", "1");
        final short oneS = 1;
        final short twoS = 2;
        assertEquals("This returns 1(short)", oneS, config.getShort("numberS"));
    }

    @Test
    public void testGetShort_2_oe() {
        config.setProperty("numberS", "1");
        final short oneS = 1;
        final short twoS = 2;
        // removed other assertion
        assertEquals("This returns 1(short)", oneS, config.getShort("numberS", twoS));
    }

    @Test
    public void testGetShort_3_oe() {
        config.setProperty("numberS", "1");
        final short oneS = 1;
        final short twoS = 2;
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 2(default short)", twoS, config.getShort("numberNotInConfig", twoS));
    }

    @Test
    public void testGetShort_4_oe() {
        config.setProperty("numberS", "1");
        final short oneS = 1;
        final short twoS = 2;
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("This returns 1(Short)", Short.valueOf(oneS), config.getShort("numberS", Short.valueOf("2")));
    }

    @Test
    public void testGetString_1_oe() {
        config.setProperty("testString", "The quick brown fox");
        final String string = "The quick brown fox";
        final String defaultValue = "jumps over the lazy dog";

        assertEquals("Existing key", string, config.getString("testString"));
    }

    @Test
    public void testGetString_2_oe() {
        config.setProperty("testString", "The quick brown fox");
        final String string = "The quick brown fox";
        final String defaultValue = "jumps over the lazy dog";

        // removed other assertion
        assertEquals("Existing key with default value", string, config.getString("testString", defaultValue));
    }

    @Test
    public void testGetString_3_oe() {
        config.setProperty("testString", "The quick brown fox");
        final String string = "The quick brown fox";
        final String defaultValue = "jumps over the lazy dog";

        // removed other assertion
        // removed other assertion
        assertEquals("Missing key with default value", defaultValue, config.getString("stringNotInConfig", defaultValue));
    }

    @Test
    public void testGetStringForListValue_1_oe() {
        config.addProperty("number", "1");
        config.addProperty("number", "2");
        assertEquals("Wrong result", "1", config.getString("number"));
    }

    @Test
    public void testInstallInterpolator_1_oe() {
        final Lookup prefixLookup = EasyMock.createMock(Lookup.class);
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(prefixLookup, defLookup);
        final Map<String, Lookup> prefixLookups = new HashMap<>();
        prefixLookups.put("test", prefixLookup);
        final List<Lookup> defLookups = new ArrayList<>();
        defLookups.add(defLookup);
        config.installInterpolator(prefixLookups, defLookups);
        final ConfigurationInterpolator interpolator = config.getInterpolator();
        assertEquals("Wrong prefix lookups", prefixLookups, interpolator.getLookups());
    }

    @Test
    public void testInstallInterpolator_2_oe() {
        final Lookup prefixLookup = EasyMock.createMock(Lookup.class);
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(prefixLookup, defLookup);
        final Map<String, Lookup> prefixLookups = new HashMap<>();
        prefixLookups.put("test", prefixLookup);
        final List<Lookup> defLookups = new ArrayList<>();
        defLookups.add(defLookup);
        config.installInterpolator(prefixLookups, defLookups);
        final ConfigurationInterpolator interpolator = config.getInterpolator();
        // removed other assertion
        final List<Lookup> defLookups2 = interpolator.getDefaultLookups();
        assertEquals("Wrong number of default lookups", 2, defLookups2.size());
    }

    @Test
    public void testInstallInterpolator_3_oe() {
        final Lookup prefixLookup = EasyMock.createMock(Lookup.class);
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(prefixLookup, defLookup);
        final Map<String, Lookup> prefixLookups = new HashMap<>();
        prefixLookups.put("test", prefixLookup);
        final List<Lookup> defLookups = new ArrayList<>();
        defLookups.add(defLookup);
        config.installInterpolator(prefixLookups, defLookups);
        final ConfigurationInterpolator interpolator = config.getInterpolator();
        // removed other assertion
        final List<Lookup> defLookups2 = interpolator.getDefaultLookups();
        // removed other assertion
        assertSame("Wrong default lookup 1", defLookup, defLookups2.get(0));
    }

    @Test
    public void testInstallInterpolator_4_oe() {
        final Lookup prefixLookup = EasyMock.createMock(Lookup.class);
        final Lookup defLookup = EasyMock.createMock(Lookup.class);
        EasyMock.replay(prefixLookup, defLookup);
        final Map<String, Lookup> prefixLookups = new HashMap<>();
        prefixLookups.put("test", prefixLookup);
        final List<Lookup> defLookups = new ArrayList<>();
        defLookups.add(defLookup);
        config.installInterpolator(prefixLookups, defLookups);
        final ConfigurationInterpolator interpolator = config.getInterpolator();
        // removed other assertion
        final List<Lookup> defLookups2 = interpolator.getDefaultLookups();
        // removed other assertion
        // removed other assertion
        final String var = "testVariable";
        final Object value = 42;
        config.addProperty(var, value);
        assertEquals("Wrong lookup result", value, defLookups2.get(1).lookup(var));
    }

    @Test
    public void testNoInterpolator_1_oe() {
        config.setProperty("test", "${value}");
        config.setInterpolator(null);
        assertEquals("Wrong result", "${value}", config.getString("test"));
    }

    @Test
    public void testNumberConversions_1_oe() {
        config.setProperty(KEY_NUMBER, Integer.valueOf(42));
        assertEquals("Wrong int returned", 42, config.getInt(KEY_NUMBER));
    }

    @Test
    public void testNumberConversions_2_oe() {
        config.setProperty(KEY_NUMBER, Integer.valueOf(42));
        // removed other assertion
        assertEquals("Wrong long returned", 42L, config.getLong(KEY_NUMBER));
    }

    @Test
    public void testNumberConversions_3_oe() {
        config.setProperty(KEY_NUMBER, Integer.valueOf(42));
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong byte returned", (byte) 42, config.getByte(KEY_NUMBER));
    }

    @Test
    public void testNumberConversions_4_oe() {
        config.setProperty(KEY_NUMBER, Integer.valueOf(42));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong float returned", 42.0f, config.getFloat(KEY_NUMBER), 0.01f);
    }

    @Test
    public void testNumberConversions_5_oe() {
        config.setProperty(KEY_NUMBER, Integer.valueOf(42));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong double returned", 42.0, config.getDouble(KEY_NUMBER), 0.001);
    }

    @Test
    public void testNumberConversions_6_oe() {
        config.setProperty(KEY_NUMBER, Integer.valueOf(42));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        assertEquals("Wrong Long returned", Long.valueOf(42L), config.getLong(KEY_NUMBER, null));
    }

    @Test
    public void testNumberConversions_7_oe() {
        config.setProperty(KEY_NUMBER, Integer.valueOf(42));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        assertEquals("Wrong BigInt returned", new BigInteger("42"), config.getBigInteger(KEY_NUMBER));
    }

    @Test
    public void testNumberConversions_8_oe() {
        config.setProperty(KEY_NUMBER, Integer.valueOf(42));
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion

        // removed other assertion
        // removed other assertion
        assertEquals("Wrong DigDecimal returned", new BigDecimal("42"), config.getBigDecimal(KEY_NUMBER));
    }

    @Test
    public void testPropertyAccess_1_oe() {
        config.clearProperty("prop.properties");
        config.setProperty("prop.properties", "");
        assertEquals("This returns an empty Properties object", config.getProperties("prop.properties"), new Properties());
    }

    @Test
    public void testPropertyAccess_2_oe() {
        config.clearProperty("prop.properties");
        config.setProperty("prop.properties", "");
        // removed other assertion
        config.clearProperty("prop.properties");
        config.setProperty("prop.properties", "foo=bar, baz=moo, seal=clubber");

        final Properties p = new Properties();
        p.setProperty("foo", "bar");
        p.setProperty("baz", "moo");
        p.setProperty("seal", "clubber");
        assertEquals("This returns a filled in Properties object", config.getProperties("prop.properties"), p);
    }

    @Test
    public void testSetInterpolator_1_oe() {
        final ConfigurationInterpolator interpolator = EasyMock.createMock(ConfigurationInterpolator.class);
        EasyMock.replay(interpolator);
        config.setInterpolator(interpolator);
        assertSame("Interpolator not set", interpolator, config.getInterpolator());
    }

    @Test
    public void testSize_1_oe() {
        final int count = 16;
        for (int i = 0; i < count; i++) {
            config.addProperty("key" + i, "value" + i);
        }
        assertEquals("Wrong size", count, config.size());
    }

    @Test
    public void testSubset_1_oe() {
        /*
         * test subset : assure we don't reprocess the data elements when generating the subset
         */

        final String prop = "hey, that's a test";
        final String prop2 = "hey\\, that's a test";
        config.setProperty("prop.string", prop2);
        config.setProperty("property.string", "hello");

        Configuration subEprop = config.subset("prop");

        assertEquals("Returns the full string", prop, subEprop.getString("string"));
    }

    @Test
    public void testSubset_2_oe() {
        /*
         * test subset : assure we don't reprocess the data elements when generating the subset
         */

        final String prop = "hey, that's a test";
        final String prop2 = "hey\\, that's a test";
        config.setProperty("prop.string", prop2);
        config.setProperty("property.string", "hello");

        Configuration subEprop = config.subset("prop");

        // removed other assertion
        assertEquals("Wrong list size", 1, subEprop.getList("string").size());
    }

    @Test
    public void testSubset_3_oe() {
        /*
         * test subset : assure we don't reprocess the data elements when generating the subset
         */

        final String prop = "hey, that's a test";
        final String prop2 = "hey\\, that's a test";
        config.setProperty("prop.string", prop2);
        config.setProperty("property.string", "hello");

        Configuration subEprop = config.subset("prop");

        // removed other assertion
        // removed other assertion

        Iterator<String> it = subEprop.getKeys();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void testSubset_4_oe() {
        /*
         * test subset : assure we don't reprocess the data elements when generating the subset
         */

        final String prop = "hey, that's a test";
        final String prop2 = "hey\\, that's a test";
        config.setProperty("prop.string", prop2);
        config.setProperty("property.string", "hello");

        Configuration subEprop = config.subset("prop");

        // removed other assertion
        // removed other assertion

        Iterator<String> it = subEprop.getKeys();
        it.next();
        // removed other assertion

        subEprop = config.subset("prop.");
        it = subEprop.getKeys();
        assertFalse(it.hasNext());
    }

    @Test
    public void testThrowExceptionOnMissing_1_oe() {
        assertTrue("Throw Exception Property is not set!", config.isThrowExceptionOnMissing());
    }

}
