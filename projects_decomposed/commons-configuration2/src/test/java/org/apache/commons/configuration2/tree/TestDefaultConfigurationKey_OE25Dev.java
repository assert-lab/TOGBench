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
package org.apache.commons.configuration2.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for DefaultConfigurationKey.
 *
 */
public class TestDefaultConfigurationKey_OE25Dev {
    /** Constant for a test key. */
    private static final String TESTPROPS = "tables.table(0).fields.field(1)";

    /** Constant for a test attribute key. */
    private static final String TESTATTR = "[@dataType]";

    /** Constant for a complex attribute key. */
    private static final String TESTKEY = TESTPROPS + TESTATTR;

    /** Stores the expression engine of the key to test. */
    private DefaultExpressionEngine expressionEngine;

    /** Stores the object to be tested. */
    private DefaultConfigurationKey key;

    /**
     * Helper method to create a key instance with the given content.
     *
     * @param k the key for initialization
     * @return the newly created {@code DefaultConfigurationKey} instance
     */
    private DefaultConfigurationKey key(final String k) {
        return new DefaultConfigurationKey(expressionEngine, k);
    }

    @Before
    public void setUp() throws Exception {
        expressionEngine = DefaultExpressionEngine.INSTANCE;
        key = new DefaultConfigurationKey(expressionEngine);
    }

    /**
     * Returns a builder for symbols with default property settings.
     *
     * @return the initialized builder object
     */
    private DefaultExpressionEngineSymbols.Builder symbols() {
        return new DefaultExpressionEngineSymbols.Builder(expressionEngine.getSymbols());
    }

    /**
     * Tests appending keys.
     */

    /**
     * Tests appending attribute keys.
     */

    /**
     * Tests constructing a complex key by chaining multiple append operations.
     */

    /**
     * Tests appending an attribute key that is already decorated-
     */

    /**
     * Tests appending keys that contain delimiters.
     */

    /**
     * Tests appending keys that contain delimiters when no escaped delimiter is defined.
     */

    /**
     * Tests appending an index to a key.
     */

    /**
     * Tests appending a null attribute key.
     */

    /**
     * Tests calling append with the escape flag.
     */

    /**
     * Tests iterating over an attribute key that has an index.
     */

    /**
     * Tests determining an attribute key's name.
     */

    /**
     * Tests whether common key parts can be extracted.
     */

    /**
     * Tries to call commonKey() with null input.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCommonKeyNull() {
        key.commonKey(null);
    }

    /**
     * Tests constructing keys for attributes.
     */

    /**
     * Tests constructing attribute keys when no end markers are defined. In this test case we use the property delimiter as
     * attribute prefix.
     */

    /**
     * Tests the differenceKey() method.
     */

    /**
     * Tests differenceKey() on the same object.
     */

    /**
     * Tests comparing configuration keys.
     */

    /**
     * Tests the isAttributeKey() method with several keys.
     */

    /**
     * Tests if attribute keys are correctly detected if no end markers are set. (In this test case we use the same
     * delimiter for attributes as for simple properties.)
     */

    /**
     * Tests to iterate over a simple key.
     */

    /**
     * Tests iterating over keys when a different escaped delimiter is used.
     */

    /**
     * Tests iteration when the attribute markers equals the property delimiter.
     */

    /**
     * Tests iterating over keys with escaped delimiters.
     */

    /**
     * Tests iterating over some funny keys.
     */

    /**
     * Tests whether a key with brackets in it can be iterated over.
     */

    /**
     * Tests iterating when no escape delimiter is defined.
     */

    /**
     * Tests an iteration where the remove() method is called. This is not supported.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testIterateWithRemove() {
        assertFalse(key.iterator().hasNext());
        key.append("simple");
        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        assertTrue(it.hasNext());
        assertEquals("simple", it.next());
        it.remove();
    }

    /**
     * Tests getting and setting the key's length.
     */

    /**
     * Tests setting the expression engine to null. This should not be allowed.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNullExpressionEngine() {
        new DefaultConfigurationKey(null);
    }

    /**
     * Tests removing delimiters.
     */

    /**
     * Tests removing leading delimiters.
     */

    /**
     * Tests removing trailing delimiters.
     */

    @Test
    public void testAppend_1_oe() {
        key.append("tables").append("table(0).");
        key.append("fields.").append("field(1)");
        key.append(null).append(TESTATTR);
        assertEquals("Wrong key", TESTKEY, key.toString());
    }

    @Test
    public void testAppendAttribute_1_oe() {
        key.appendAttribute("dataType");
        assertEquals("Attribute key not correctly appended", TESTATTR, key.toString());
    }

    @Test
    public void testAppendComplexKey_1_oe() {
        key.append("tables").append("table.").appendIndex(0);
        key.append("fields.").append("field").appendIndex(1);
        key.appendAttribute("dataType");
        assertEquals("Wrong complex key", TESTKEY, key.toString());
    }

    @Test
    public void testAppendDecoratedAttributeKey_1_oe() {
        key.appendAttribute(TESTATTR);
        assertEquals("Decorated attribute key not correctly appended", TESTATTR, key.toString());
    }

    @Test
    public void testAppendDelimiters_1_oe() {
        key.append("key..").append("test").append(".");
        key.append(".more").append("..tests");
        assertEquals("Wrong key", "key...test.more...tests", key.toString());
    }

    @Test
    public void testAppendDelimitersWithoutEscaping_1_oe() {
        expressionEngine = new DefaultExpressionEngine(symbols().setEscapedDelimiter(null).create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("key.......").append("test").append(".");
        key.append(".more").append("..tests");
        assertEquals("Wrong constructed key", "key.test.more.tests", key.toString());
    }

    @Test
    public void testAppendIndex_1_oe() {
        key.append("test").appendIndex(42);
        assertEquals("Index was not correctly appended", "test(42)", key.toString());
    }

    @Test
    public void testAppendNullAttributeKey_1_oe() {
        key.appendAttribute(null);
        assertEquals("Null attribute key not correctly appended", "", key.toString());
    }

    @Test
    public void testAppendWithEscapeFlag_1_oe() {
        key.append(".key.test.", true);
        key.append(".more").append(".tests", true);
        assertEquals("Wrong constructed key", "..key..test...more...tests", key.toString());
    }

    @Test
    public void testAttributeKeyWithIndex_1_oe() {
        key.append(TESTATTR);
        key.appendIndex(0);
        assertEquals("Wrong attribute key with index", TESTATTR + "(0)", key.toString());
    }

    @Test
    public void testAttributeKeyWithIndex_2_oe() {
        key.append(TESTATTR);
        key.appendIndex(0);
        // removed other assertion

        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        assertTrue("No first element", it.hasNext());
    }

    @Test
    public void testAttributeKeyWithIndex_3_oe() {
        key.append(TESTATTR);
        key.appendIndex(0);
        // removed other assertion

        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        it.next();
        assertTrue("Index not found", it.hasIndex());
    }

    @Test
    public void testAttributeKeyWithIndex_4_oe() {
        key.append(TESTATTR);
        key.appendIndex(0);
        // removed other assertion

        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        it.next();
        // removed other assertion
        assertEquals("Incorrect index", 0, it.getIndex());
    }

    @Test
    public void testAttributeKeyWithIndex_5_oe() {
        key.append(TESTATTR);
        key.appendIndex(0);
        // removed other assertion

        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        it.next();
        // removed other assertion
        // removed other assertion
        assertTrue("Attribute not found", it.isAttribute());
    }

    @Test
    public void testAttributeKeyWithIndex_6_oe() {
        key.append(TESTATTR);
        key.appendIndex(0);
        // removed other assertion

        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        it.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong plain key", "dataType", it.currentKey(false));
    }

    @Test
    public void testAttributeKeyWithIndex_7_oe() {
        key.append(TESTATTR);
        key.appendIndex(0);
        // removed other assertion

        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        it.next();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong decorated key", TESTATTR, it.currentKey(true));
    }

    @Test
    public void testAttributeName_1_oe() {
        assertEquals("Plain key not detected", "test", key.attributeName("test"));
    }

    @Test
    public void testAttributeName_2_oe() {
        // removed other assertion
        assertEquals("Attribute markers not stripped", "dataType", key.attributeName(TESTATTR));
    }

    @Test
    public void testAttributeName_3_oe() {
        // removed other assertion
        // removed other assertion
        assertNull("Null key not processed", key.attributeName(null));
    }

    @Test
    public void testCommonKey_1_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        DefaultConfigurationKey k2 = key("tables.table(0).name");
        DefaultConfigurationKey kc = k1.commonKey(k2);
        assertEquals("Wrong common key (1)", key("tables.table(0)"), kc);
    }

    @Test
    public void testCommonKey_2_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        DefaultConfigurationKey k2 = key("tables.table(0).name");
        DefaultConfigurationKey kc = k1.commonKey(k2);
        // removed other assertion
        assertEquals("Not symmetric", kc, k2.commonKey(k1));
    }

    @Test
    public void testCommonKey_3_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        DefaultConfigurationKey k2 = key("tables.table(0).name");
        DefaultConfigurationKey kc = k1.commonKey(k2);
        // removed other assertion
        // removed other assertion

        k2 = key("tables.table(1).fields.field(1)");
        kc = k1.commonKey(k2);
        assertEquals("Wrong common key (2)", key("tables"), kc);
    }

    @Test
    public void testCommonKey_4_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        DefaultConfigurationKey k2 = key("tables.table(0).name");
        DefaultConfigurationKey kc = k1.commonKey(k2);
        // removed other assertion
        // removed other assertion

        k2 = key("tables.table(1).fields.field(1)");
        kc = k1.commonKey(k2);
        // removed other assertion

        k2 = key("completely.different.key");
        kc = k1.commonKey(k2);
        assertEquals("Got a common key for different keys", 0, kc.length());
    }

    @Test
    public void testCommonKey_5_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        DefaultConfigurationKey k2 = key("tables.table(0).name");
        DefaultConfigurationKey kc = k1.commonKey(k2);
        // removed other assertion
        // removed other assertion

        k2 = key("tables.table(1).fields.field(1)");
        kc = k1.commonKey(k2);
        // removed other assertion

        k2 = key("completely.different.key");
        kc = k1.commonKey(k2);
        // removed other assertion

        kc = k1.commonKey(key);
        assertEquals("Got a common key for empty key", 0, kc.length());
    }

    @Test
    public void testCommonKey_6_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        DefaultConfigurationKey k2 = key("tables.table(0).name");
        DefaultConfigurationKey kc = k1.commonKey(k2);
        // removed other assertion
        // removed other assertion

        k2 = key("tables.table(1).fields.field(1)");
        kc = k1.commonKey(k2);
        // removed other assertion

        k2 = key("completely.different.key");
        kc = k1.commonKey(k2);
        // removed other assertion

        kc = k1.commonKey(key);
        // removed other assertion

        kc = k1.commonKey(k1);
        assertEquals("Wrong result for reflexiv invocation", kc, k1);
    }

    @Test
    public void testConstructAttributeKey_1_oe() {
        assertEquals("Wrong attribute key", TESTATTR, key.constructAttributeKey("dataType"));
    }

    @Test
    public void testConstructAttributeKey_2_oe() {
        // removed other assertion
        assertEquals("Attribute key was incorrectly converted", TESTATTR, key.constructAttributeKey(TESTATTR));
    }

    @Test
    public void testConstructAttributeKey_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("Null key could not be processed", "", key.constructAttributeKey(null));
    }

    @Test
    public void testConstructAttributeKeyWithoutEndMarkers_1_oe() {
        final DefaultExpressionEngineSymbols symbols = symbols().setAttributeEnd(null).setAttributeStart(expressionEngine.getSymbols().getPropertyDelimiter())
            .create();
        expressionEngine = new DefaultExpressionEngine(symbols);
        key = new DefaultConfigurationKey(expressionEngine);
        assertEquals("Wrong attribute key", ".test", key.constructAttributeKey("test"));
    }

    @Test
    public void testConstructAttributeKeyWithoutEndMarkers_2_oe() {
        final DefaultExpressionEngineSymbols symbols = symbols().setAttributeEnd(null).setAttributeStart(expressionEngine.getSymbols().getPropertyDelimiter())
            .create();
        expressionEngine = new DefaultExpressionEngine(symbols);
        key = new DefaultConfigurationKey(expressionEngine);
        // removed other assertion
        assertEquals("Attribute key was incorrectly converted", ".test", key.constructAttributeKey(".test"));
    }

    @Test
    public void testDifferenceKey_1_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        DefaultConfigurationKey k2 = key("tables.table(0).name");
        DefaultConfigurationKey kd = k1.differenceKey(k2);
        assertEquals("Wrong difference (1)", "name", kd.toString());
    }

    @Test
    public void testDifferenceKey_2_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        DefaultConfigurationKey k2 = key("tables.table(0).name");
        DefaultConfigurationKey kd = k1.differenceKey(k2);
        // removed other assertion

        k2 = key("tables.table(1).fields.field(1)");
        kd = k1.differenceKey(k2);
        assertEquals("Wrong difference (2)", "table(1).fields.field(1)", kd.toString());
    }

    @Test
    public void testDifferenceKey_3_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        DefaultConfigurationKey k2 = key("tables.table(0).name");
        DefaultConfigurationKey kd = k1.differenceKey(k2);
        // removed other assertion

        k2 = key("tables.table(1).fields.field(1)");
        kd = k1.differenceKey(k2);
        // removed other assertion

        k2 = key("completely.different.key");
        kd = k1.differenceKey(k2);
        assertEquals("Wrong difference (3)", k2, kd);
    }

    @Test
    public void testDifferenceKeySame_1_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        final DefaultConfigurationKey kd = k1.differenceKey(k1);
        assertEquals("Got difference for same keys", 0, kd.length());
    }

    @Test
    public void testEquals_1_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        assertEquals("Key not equal to itself", k1, k1);
    }

    @Test
    public void testEquals_2_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        // removed other assertion
        final DefaultConfigurationKey k2 = key(TESTKEY);
        assertEquals("Keys are not equal", k1, k2);
    }

    @Test
    public void testEquals_3_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        // removed other assertion
        final DefaultConfigurationKey k2 = key(TESTKEY);
        // removed other assertion
        assertEquals("Not reflexiv", k2, k1);
    }

    @Test
    public void testEquals_4_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        // removed other assertion
        final DefaultConfigurationKey k2 = key(TESTKEY);
        // removed other assertion
        // removed other assertion
        assertEquals("Hash codes not equal", k1.hashCode(), k2.hashCode());
    }

    @Test
    public void testEquals_5_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        // removed other assertion
        final DefaultConfigurationKey k2 = key(TESTKEY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        k2.append("anotherPart");
        assertNotEquals("Keys considered equal", k1, k2);
    }

    @Test
    public void testEquals_6_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        // removed other assertion
        final DefaultConfigurationKey k2 = key(TESTKEY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        k2.append("anotherPart");
        // removed other assertion
        assertNotEquals("Keys considered equal (2)", k2, k1);
    }

    @Test
    public void testEquals_7_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        // removed other assertion
        final DefaultConfigurationKey k2 = key(TESTKEY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        k2.append("anotherPart");
        // removed other assertion
        // removed other assertion
        assertNotEquals("Key equals null key", null, k1);
    }

    @Test
    public void testEquals_8_oe() {
        final DefaultConfigurationKey k1 = key(TESTKEY);
        // removed other assertion
        final DefaultConfigurationKey k2 = key(TESTKEY);
        // removed other assertion
        // removed other assertion
        // removed other assertion
        k2.append("anotherPart");
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertNotEquals("Equal with string", TESTKEY, k1);
    }

    @Test
    public void testIsAttributeKey_1_oe() {
        assertTrue("Attribute key not detected", key.isAttributeKey(TESTATTR));
    }

    @Test
    public void testIsAttributeKey_2_oe() {
        // removed other assertion
        assertFalse("Property key considered as attribute", key.isAttributeKey(TESTPROPS));
    }

    @Test
    public void testIsAttributeKey_3_oe() {
        // removed other assertion
        // removed other assertion
        assertFalse("Null key considered as attribute", key.isAttributeKey(null));
    }

    @Test
    public void testIsAttributeKeyWithoutEndMarkers_1_oe() {
        final DefaultExpressionEngineSymbols symbols = symbols().setAttributeEnd(null)
            .setAttributeStart(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER).create();
        expressionEngine = new DefaultExpressionEngine(symbols);
        key = new DefaultConfigurationKey(expressionEngine);
        assertTrue("Attribute key not detected", key.isAttributeKey(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER + "test"));
    }

    @Test
    public void testIsAttributeKeyWithoutEndMarkers_2_oe() {
        final DefaultExpressionEngineSymbols symbols = symbols().setAttributeEnd(null)
            .setAttributeStart(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER).create();
        expressionEngine = new DefaultExpressionEngine(symbols);
        key = new DefaultConfigurationKey(expressionEngine);
        // removed other assertion
        assertFalse("Property key considered as attribute key", key.isAttributeKey(TESTATTR));
    }

    @Test
    public void testIterate_1_oe() {
        key.append(TESTKEY);
        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        assertTrue("No key parts", it.hasNext());
    }

    @Test
    public void testIterate_2_oe() {
        key.append(TESTKEY);
        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        assertEquals("Wrong key part", "tables", it.nextKey());
    }

    @Test
    public void testIterate_5_oe() {
        key.append(TESTKEY);
        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong index", 0, it.getIndex());
    }

    @Test
    public void testIterate_7_oe() {
        key.append(TESTKEY);
        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Found an index", it.hasIndex());
    }

    @Test
    public void testIterate_10_oe() {
        key.append(TESTKEY);
        final DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Found an attribute", it.isAttribute());
    }

    @Test
    public void testIterateAlternativeEscapeDelimiter_1_oe() {
        expressionEngine = new DefaultExpressionEngine(symbols().setEscapedDelimiter("\\.").create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("\\.my\\.elem");
        key.append("trailing\\.dot\\.");
        key.append(".strange");
        assertEquals("\\.my\\.elem.trailing\\.dot\\..strange", key.toString());
    }

    @Test
    public void testIterateAlternativeEscapeDelimiter_2_oe() {
        expressionEngine = new DefaultExpressionEngine(symbols().setEscapedDelimiter("\\.").create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("\\.my\\.elem");
        key.append("trailing\\.dot\\.");
        key.append(".strange");
        // removed other assertion
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        assertEquals("Wrong first part", ".my.elem", kit.nextKey());
    }

    @Test
    public void testIterateAttributeEqualsPropertyDelimiter_1_oe() {
        expressionEngine = new DefaultExpressionEngine(
            symbols().setAttributeEnd(null).setAttributeStart(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER).create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("this.isa.key");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        assertEquals("Wrong first key part", "this", kit.next());
    }

    @Test
    public void testIterateAttributeEqualsPropertyDelimiter_2_oe() {
        expressionEngine = new DefaultExpressionEngine(
            symbols().setAttributeEnd(null).setAttributeStart(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER).create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("this.isa.key");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        // removed other assertion
        assertFalse("First part is an attribute", kit.isAttribute());
    }

    @Test
    public void testIterateAttributeEqualsPropertyDelimiter_3_oe() {
        expressionEngine = new DefaultExpressionEngine(
            symbols().setAttributeEnd(null).setAttributeStart(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER).create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("this.isa.key");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        // removed other assertion
        // removed other assertion
        assertTrue("First part is not a property key", kit.isPropertyKey());
    }

    @Test
    public void testIterateAttributeEqualsPropertyDelimiter_5_oe() {
        expressionEngine = new DefaultExpressionEngine(
            symbols().setAttributeEnd(null).setAttributeStart(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER).create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("this.isa.key");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertFalse("Second part is an attribute", kit.isAttribute());
    }

    @Test
    public void testIterateAttributeEqualsPropertyDelimiter_6_oe() {
        expressionEngine = new DefaultExpressionEngine(
            symbols().setAttributeEnd(null).setAttributeStart(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER).create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("this.isa.key");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Second part is not a property key", kit.isPropertyKey());
    }

    @Test
    public void testIterateAttributeEqualsPropertyDelimiter_9_oe() {
        expressionEngine = new DefaultExpressionEngine(
            symbols().setAttributeEnd(null).setAttributeStart(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER).create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("this.isa.key");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertTrue("Third part is not a property key", kit.isPropertyKey());
    }

    @Test
    public void testIterateEscapedDelimiters_1_oe() {
        key.append("my..elem");
        key.append("trailing..dot..");
        key.append(".strange");
        assertEquals("my..elem.trailing..dot...strange", key.toString());
    }

    @Test
    public void testIterateEscapedDelimiters_2_oe() {
        key.append("my..elem");
        key.append("trailing..dot..");
        key.append(".strange");
        // removed other assertion
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        assertEquals("Wrong first part", "my.elem", kit.nextKey());
    }

    @Test
    public void testIterateStrangeKeys_1_oe() {
        key = new DefaultConfigurationKey(expressionEngine, "key.");
        DefaultConfigurationKey.KeyIterator it = key.iterator();
        assertTrue("Too few key parts", it.hasNext());
    }

    @Test
    public void testIterateStrangeKeys_2_oe() {
        key = new DefaultConfigurationKey(expressionEngine, "key.");
        DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        assertEquals("Wrong key part", "key", it.next());
    }

    @Test
    public void testIterateStrangeKeys_4_oe() {
        key = new DefaultConfigurationKey(expressionEngine, "key.");
        DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        key = new DefaultConfigurationKey(expressionEngine, ".");
        it = key.iterator();
        assertFalse("Simple delimiter key has more parts", it.hasNext());
    }

    @Test
    public void testIterateStrangeKeys_5_oe() {
        key = new DefaultConfigurationKey(expressionEngine, "key.");
        DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        key = new DefaultConfigurationKey(expressionEngine, ".");
        it = key.iterator();
        // removed other assertion

        key = new DefaultConfigurationKey(expressionEngine, "key().index()undefined(0).test");
        it = key.iterator();
        assertEquals("Wrong first part", "key()", it.next());
    }

    @Test
    public void testIterateStrangeKeys_6_oe() {
        key = new DefaultConfigurationKey(expressionEngine, "key.");
        DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        key = new DefaultConfigurationKey(expressionEngine, ".");
        it = key.iterator();
        // removed other assertion

        key = new DefaultConfigurationKey(expressionEngine, "key().index()undefined(0).test");
        it = key.iterator();
        // removed other assertion
        assertFalse("Index detected in first part", it.hasIndex());
    }

    @Test
    public void testIterateStrangeKeys_9_oe() {
        key = new DefaultConfigurationKey(expressionEngine, "key.");
        DefaultConfigurationKey.KeyIterator it = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion

        key = new DefaultConfigurationKey(expressionEngine, ".");
        it = key.iterator();
        // removed other assertion

        key = new DefaultConfigurationKey(expressionEngine, "key().index()undefined(0).test");
        it = key.iterator();
        // removed other assertion
        // removed other assertion
        // removed other assertion
        // removed other assertion
        assertEquals("Wrong index value", 0, it.getIndex());
    }

    @Test
    public void testIterateWithBrackets_1_oe() {
        key.append("directory.platform(x86).path");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        String part = kit.nextKey();
        assertEquals("Wrong part 1", "directory", part);
    }

    @Test
    public void testIterateWithBrackets_2_oe() {
        key.append("directory.platform(x86).path");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        String part = kit.nextKey();
        // removed other assertion
        assertFalse("Has index 1", kit.hasIndex());
    }

    @Test
    public void testIterateWithBrackets_3_oe() {
        key.append("directory.platform(x86).path");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        String part = kit.nextKey();
        // removed other assertion
        // removed other assertion
        part = kit.nextKey();
        assertEquals("Wrong part 2", "platform(x86)", part);
    }

    @Test
    public void testIterateWithBrackets_4_oe() {
        key.append("directory.platform(x86).path");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        String part = kit.nextKey();
        // removed other assertion
        // removed other assertion
        part = kit.nextKey();
        // removed other assertion
        assertFalse("Has index 2", kit.hasIndex());
    }

    @Test
    public void testIterateWithBrackets_5_oe() {
        key.append("directory.platform(x86).path");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        String part = kit.nextKey();
        // removed other assertion
        // removed other assertion
        part = kit.nextKey();
        // removed other assertion
        // removed other assertion
        part = kit.nextKey();
        assertEquals("Wrong part 3", "path", part);
    }

    @Test
    public void testIterateWithBrackets_6_oe() {
        key.append("directory.platform(x86).path");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        String part = kit.nextKey();
        // removed other assertion
        // removed other assertion
        part = kit.nextKey();
        // removed other assertion
        // removed other assertion
        part = kit.nextKey();
        // removed other assertion
        assertFalse("Has index 3", kit.hasIndex());
    }

    @Test
    public void testIterateWithBrackets_7_oe() {
        key.append("directory.platform(x86).path");
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        String part = kit.nextKey();
        // removed other assertion
        // removed other assertion
        part = kit.nextKey();
        // removed other assertion
        // removed other assertion
        part = kit.nextKey();
        // removed other assertion
        // removed other assertion
        assertFalse("Too many elements", kit.hasNext());
    }

    @Test
    public void testIterateWithoutEscapeDelimiter_1_oe() {
        expressionEngine = new DefaultExpressionEngine(symbols().setEscapedDelimiter(null).create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("..my..elem.trailing..dot...strange");
        assertEquals("Wrong key", "my..elem.trailing..dot...strange", key.toString());
    }

    @Test
    public void testIterateWithoutEscapeDelimiter_2_oe() {
        expressionEngine = new DefaultExpressionEngine(symbols().setEscapedDelimiter(null).create());
        key = new DefaultConfigurationKey(expressionEngine);
        key.append("..my..elem.trailing..dot...strange");
        // removed other assertion
        final DefaultConfigurationKey.KeyIterator kit = key.iterator();
        final String[] parts = {"my", "elem", "trailing", "dot", "strange"};
        for (int i = 0; i < parts.length; i++) {
            assertEquals("Wrong key part " + i, parts[i], kit.next());
    }
    }

    @Test
    public void testLength_1_oe() {
        key.append(TESTPROPS);
        assertEquals("Wrong length", TESTPROPS.length(), key.length());
    }

    @Test
    public void testLength_2_oe() {
        key.append(TESTPROPS);
        // removed other assertion
        key.appendAttribute("dataType");
        assertEquals("Wrong length", TESTKEY.length(), key.length());
    }

    @Test
    public void testLength_3_oe() {
        key.append(TESTPROPS);
        // removed other assertion
        key.appendAttribute("dataType");
        // removed other assertion
        key.setLength(TESTPROPS.length());
        assertEquals("Wrong length after shortening", TESTPROPS.length(), key.length());
    }

    @Test
    public void testLength_4_oe() {
        key.append(TESTPROPS);
        // removed other assertion
        key.appendAttribute("dataType");
        // removed other assertion
        key.setLength(TESTPROPS.length());
        // removed other assertion
        assertEquals("Wrong resulting key", TESTPROPS, key.toString());
    }

    @Test
    public void testTrim_1_oe() {
        assertEquals("Key was not trimmed", "test", key.trim(".test."));
    }

    @Test
    public void testTrim_2_oe() {
        // removed other assertion
        assertEquals("Null key could not be processed", "", key.trim(null));
    }

    @Test
    public void testTrim_3_oe() {
        // removed other assertion
        // removed other assertion
        assertEquals("Delimiter could not be processed", "", key.trim(DefaultExpressionEngineSymbols.DEFAULT_PROPERTY_DELIMITER));
    }

    @Test
    public void testTrimLeft_1_oe() {
        assertEquals("Key was not left trimmed", "test.", key.trimLeft(".test."));
    }

    @Test
    public void testTrimLeft_2_oe() {
        // removed other assertion
        assertEquals("Too much left trimming", "..test.", key.trimLeft("..test."));
    }

    @Test
    public void testTrimRight_1_oe() {
        assertEquals("Key was not right trimmed", ".test", key.trimRight(".test."));
    }

    @Test
    public void testTrimRight_2_oe() {
        // removed other assertion
        assertEquals("Too much right trimming", ".test..", key.trimRight(".test.."));
    }

}
